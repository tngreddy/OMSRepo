package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.Constants;
import com.vjentrps.oms.model.Error;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.GRCDetails;
import com.vjentrps.oms.model.GoodsReturnableChallan;
import com.vjentrps.oms.model.PendingGRC;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.model.StatusEnum;

@RestController
@RequestMapping(value="/service/grc")
public class GRCRestController extends BaseRestController{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	

    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getGRCs() {
    	
        List<GoodsReturnableChallan> grcs = new ArrayList<GoodsReturnableChallan>();
   	
        	try {
				grcs = grcService.listGRCs();
			} catch (OmsServiceException e) {
				log.error("Error while getting GRCs",e);
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			} catch (Exception e) {
				log.error("Error while getting GRCs",e);
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
			}
        return new ResponseDTO(grcs);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO createGRC(@RequestBody GoodsReturnableChallan grc) {
    	String grcNo = "";
 
			try {
				if (null != grc	&& CollectionUtils.isNotEmpty(grc.getProdInfoList())) {
					grc.setStatus("PENDING");
					Error err = commonUtil.isCrossedLimit(grc.getProdInfoList(), null, CommonConstants.GRC);
					if (null != err) {
						return new ResponseDTO(err);
					}
					grcNo = grcService.createGRC(grc);
				}

			} catch (OmsServiceException e) {
				log.error("Error while creating a GRC",e);
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			} catch (Exception e) {
				log.error("Error while creating a GRC",e);
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
			}
       
        return new ResponseDTO(grcNo);
    }
    
      
   
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseDTO updateGRC(@RequestBody GoodsReturnableChallan grc) {
 
    	try {
			grcService.updateGRC(grc);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}
         return new ResponseDTO();
    }
    
    @RequestMapping(value="/{grcNo}",method = RequestMethod.DELETE)
    public ResponseDTO deleteGRC(@PathVariable String grcNo) {
    	
    	try {
			grcService.deleteGRC(grcNo);
		} catch (OmsServiceException e) {
			log.error("Error while deleting GRC",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while deleting GRC",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}
           return new ResponseDTO();
    }
    
    @RequestMapping(value="/{grcNo}",method = RequestMethod.PUT)
    public ResponseDTO updateGRCStatus(@PathVariable String grcNo) {
    	
    	GoodsReturnableChallan grc = new GoodsReturnableChallan();
    	grc.setGrcNo(grcNo);
    	grc.setStatus(Constants.CLOSED);
    	try {
			grcService.updateGRCStatus(grc);
		} catch (OmsServiceException e) {
			log.error("Error while updating GRCStatus",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while updating GRCStatus",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}
 
           return new ResponseDTO();
    }
    
    @RequestMapping(value="/grcNos",method = RequestMethod.GET)
    public ResponseDTO getGRCNoList(@RequestParam("toName") String toName, @RequestParam("type") String type) {
    	List<String> grcNoList = new ArrayList<String>();
    	try {
			grcNoList = grcService.getGRCNoList(toName, type);
		} catch (OmsServiceException e) {
			log.error("Error while getting GRCNo list",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while getting GRCNo list",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}
 
           return new ResponseDTO(grcNoList);
    }
    
    @RequestMapping(value="/details/{grcNo}/{fromToInfo}", method = RequestMethod.GET)
    public ResponseDTO buildGRCDetails(@PathVariable String grcNo, @PathVariable boolean fromToInfo) {
    	
        	try {
        		GRCDetails  grcDetails = grcService.buildGRCDetails(grcNo, fromToInfo);        		
        		
        		if (null == grcDetails.getGrc()) {
    				return new ResponseDTO(commonUtil.processError(ErrorsEnum.NO_DATA_FOUND));
    			}
        		return new ResponseDTO(grcDetails);
        		
			} catch (OmsServiceException e) {
				log.error("Error while getting GRC details",e);
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			} catch (Exception e) {
				log.error("Error while getting GRC details",e);
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
			}
        
    }
    
    @RequestMapping(value="/{grcNo}", method = RequestMethod.GET)
    public ResponseDTO fetchGRCData(@PathVariable String grcNo) {
    	
        	try {
        		GoodsReturnableChallan  grc = grcService.getGRCbyNo(grcNo);        		
        		
        		return new ResponseDTO(grc);
        		
			} catch (OmsServiceException e) {
				log.error("Error while getting GRC data",e);
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			} catch (Exception e) {
				log.error("Error while getting GRC data",e);
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
			}
        
    }
    
    @RequestMapping(value="/pending", method = RequestMethod.GET)
    public ResponseDTO fetchGRCPendingProdInfo() {
    	
        	try {
        		Map<String, List<PendingGRC>>  grcPendProdInfos = grcService.fetchAllGRCPendingProdInfo(StatusEnum.PENDING.name());        		
        		
        		return new ResponseDTO(grcPendProdInfos);
        		
			} catch (OmsServiceException e) {
				log.error("Error while getting pending GRC info",e);
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			} catch (Exception e) {
				log.error("Error while getting pending GRC info",e);
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
			}
        
    }
 
    
 
}