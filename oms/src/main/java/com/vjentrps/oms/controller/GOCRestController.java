package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.Constants;
import com.vjentrps.oms.model.Error;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.GOCDetails;
import com.vjentrps.oms.model.GoodsOutwardChallan;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.model.ServiceResponse;

@RestController
@RequestMapping(value="/service/goc")
public class GOCRestController extends BaseRestController{
	

    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getGOCs() {
    	
        List<GoodsOutwardChallan> gocs = new ArrayList<GoodsOutwardChallan>();
   	
        	try {
				gocs = gocService.listGOCs();
			} catch (OmsServiceException e) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
        return new ResponseDTO(gocs);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO createGOC(@RequestBody GoodsOutwardChallan goc) {
    	String gocNo="";
    	goc.setStatus("pending");
    	try {
			if (null != goc	&& CollectionUtils.isNotEmpty(goc.getProdInfoList())) {

				Error err = commonUtil.isCrossedOutwardLimit(goc.getProdInfoList());
				if (null != err) {
					return new ResponseDTO(err);
				}
			  gocNo = gocService.createGOC(goc);

			}
		}  catch (OmsServiceException e) {
			
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
    	catch (Exception e) {
    		return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
       
        return new ResponseDTO(gocNo);
    }
    
      
   
    @RequestMapping(method = RequestMethod.PUT)
    public String updateGOC(@RequestBody GoodsOutwardChallan goc) {
 
    	try {
			gocService.updateGOC(goc);
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
         return "Success";
    }
    
    @RequestMapping(value="/{gocNo}",method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable long gocNo) {
    	
    	try {
			gocService.deleteGOC(gocNo);
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
           return "Success";
    }
    
    @RequestMapping(value="/{gocNo}",method = RequestMethod.PUT)
    public String updateGOCStatus(@PathVariable String gocNo) {
    	
    	GoodsOutwardChallan goc = new GoodsOutwardChallan();
    	goc.setGocNo(gocNo);
    	goc.setStatus(Constants.CLOSED);
    	try {
			gocService.updateGOCStatus(goc);
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
           return "Success";
    }
    
    @RequestMapping(value="/details/{gocNo}/{fromToInfo}", method = RequestMethod.GET)
    public ResponseDTO buildGINDetails(@PathVariable String gocNo, @PathVariable boolean fromToInfo) {
    	
        	try {
        		GOCDetails gocDetails = gocService.buildGOCDetails(gocNo, fromToInfo);        		
        		
        		if (null == gocDetails.getGoc()) {
    				return new ResponseDTO(commonUtil.processError(ErrorsEnum.NO_DATA_FOUND));
    			}
        		return new ResponseDTO(gocDetails);
        		
			} catch (OmsServiceException e) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
        
    }
    
    @RequestMapping(value="/{gocNo}", method = RequestMethod.GET)
    public ResponseDTO fetchGOCData(@PathVariable String gocNo) {
    	
        	try {
        		GoodsOutwardChallan  goc = gocService.getGOCbyNo(gocNo);        		
        		
        		return new ResponseDTO(goc);
        		
			} catch (OmsServiceException e) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
        
    }
 
    
 
}