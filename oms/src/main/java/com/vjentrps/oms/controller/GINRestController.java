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
import com.vjentrps.oms.model.GINDetails;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.ResponseDTO;

@RestController
@RequestMapping(value="/service/gin")
public class GINRestController extends BaseRestController{
	
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getGINs() {
    	
        List<GoodsInwardNote> gins = new ArrayList<GoodsInwardNote>();
   	
        	try {
				gins = ginService.listGINs();
			} catch (OmsServiceException e) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
        return new ResponseDTO(gins);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO createGIN(@RequestBody GoodsInwardNote gin) {
 
    	String ginNo="";
    	gin.setStatus("pending");
    	try {
			ginNo = ginService.createGIN(gin);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
       
        return new ResponseDTO(new String(ginNo));
    }
    
      
   
    @RequestMapping(method = RequestMethod.PUT)
    public String updateGIN(@RequestBody GoodsInwardNote gin) {
 
    	try {
			ginService.updateGIN(gin);
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
         return "Success";
    }
    
    @RequestMapping(value="/{ginNo}",method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable long ginNo) {
    	
    	try {
			ginService.deleteGIN(ginNo);
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
           return "Success";
    }
    
    @RequestMapping(value="/{ginNo}",method = RequestMethod.PUT)
    public String updateGINStatus(@PathVariable String ginNo) {
    	
    	GoodsInwardNote gin = new GoodsInwardNote();
    	gin.setGinNo(ginNo);
    	gin.setStatus(Constants.CLOSED);
    	try {
			ginService.updateGINStatus(gin);
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
           return "Success";
    }
    
    
    @RequestMapping(value="/details/{ginNo}/{fromToInfo}", method = RequestMethod.GET)
    public ResponseDTO buildGINDetails(@PathVariable String ginNo, @PathVariable boolean fromToInfo) {
    	
        	try {
        		GINDetails  ginDetails = ginService.buildGINDetails(ginNo, fromToInfo);        		
        		
			if (null == ginDetails.getGin()) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.NO_DATA_FOUND));
			}
        		return new ResponseDTO(ginDetails);
        		
			} catch (OmsServiceException e) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
        
    }
    
    @RequestMapping(value="/{ginNo}", method = RequestMethod.GET)
    public ResponseDTO fetchGINData(@PathVariable String ginNo) {
    	
        	try {
        		GoodsInwardNote  gin = ginService.getGINbyNo(ginNo);        		
        		
        		return new ResponseDTO(gin);
        		
			} catch (OmsServiceException e) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
        
    }
 
}