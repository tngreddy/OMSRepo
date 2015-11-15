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
import com.vjentrps.oms.model.GoodsOutwardChallan;
import com.vjentrps.oms.model.ResponseDTO;

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
 
    	goc.setStatus("pending");
    	try {
			gocService.createGOC(goc);
		}  catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
    	catch (Exception e) {
    		return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
       
        return new ResponseDTO();
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
    
 
}