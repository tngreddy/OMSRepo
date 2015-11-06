package com.vjentrps.oms.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.model.Constants;
import com.vjentrps.oms.model.GoodsReturnableChallan;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.service.GRCService;

@RestController
@RequestMapping(value="/service/grc")
public class GRCRestController {
	
	@Autowired
	GRCService grcService;
	
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getGRCs() {
    	
        List<GoodsReturnableChallan> grcs = new ArrayList<GoodsReturnableChallan>();
   	
        	grcs = grcService.listGRCs();
        return new ResponseDTO(grcs);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO createGRC(@RequestBody GoodsReturnableChallan grc) {
 
    	grc.setStatus("pending");
    	try {
			grcService.createGRC(grc);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return new ResponseDTO();
    }
    
      
   
    @RequestMapping(method = RequestMethod.PUT)
    public String updateGRC(@RequestBody GoodsReturnableChallan grc) {
 
    	grcService.updateGRC(grc);
        
         return "Success";
    }
    
    @RequestMapping(value="/{grcNo}",method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable String grcNo) {
    	
    	grcService.deleteGRC(grcNo);
 
           return "Success";
    }
    
    @RequestMapping(value="/{grcNo}",method = RequestMethod.PUT)
    public String updateGRCStatus(@PathVariable String grcNo) {
    	
    	GoodsReturnableChallan grc = new GoodsReturnableChallan();
    	grc.setGrcNo(grcNo);
    	grc.setStatus(Constants.CLOSED);
    	grcService.updateGRCStatus(grc);
 
           return "Success";
    }
    
 
}