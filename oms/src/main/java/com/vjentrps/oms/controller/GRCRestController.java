package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.Constants;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.GoodsReturnableChallan;
import com.vjentrps.oms.model.ResponseDTO;

@RestController
@RequestMapping(value="/service/grc")
public class GRCRestController extends BaseRestController{
	

    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getGRCs() {
    	
        List<GoodsReturnableChallan> grcs = new ArrayList<GoodsReturnableChallan>();
   	
        	try {
				grcs = grcService.listGRCs();
			} catch (OmsServiceException e) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
        return new ResponseDTO(grcs);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO createGRC(@RequestBody GoodsReturnableChallan grc) {
 
    	grc.setStatus("pending");
    	try {
			try {
				grcService.createGRC(grc);
			} catch (OmsServiceException e) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
		} catch (Exception e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
       
        return new ResponseDTO();
    }
    
      
   
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseDTO updateGRC(@RequestBody GoodsReturnableChallan grc) {
 
    	try {
			grcService.updateGRC(grc);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
        
         return new ResponseDTO();
    }
    
    @RequestMapping(value="/{grcNo}",method = RequestMethod.DELETE)
    public ResponseDTO deleteGRC(@PathVariable String grcNo) {
    	
    	try {
			grcService.deleteGRC(grcNo);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
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
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
 
           return new ResponseDTO();
    }
    
 
}