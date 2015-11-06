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
import com.vjentrps.oms.model.GoodsOutwardChallan;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.service.GOCService;

@RestController
@RequestMapping(value="/service/goc")
public class GOCRestController {
	
	@Autowired
	GOCService gocService;
	
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getGOCs() {
    	
        List<GoodsOutwardChallan> gocs = new ArrayList<GoodsOutwardChallan>();
   	
        	gocs = gocService.listGOCs();
        return new ResponseDTO(gocs);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO createGOC(@RequestBody GoodsOutwardChallan goc) {
 
    	goc.setStatus("pending");
    	try {
			gocService.createGOC(goc);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return new ResponseDTO();
    }
    
      
   
    @RequestMapping(method = RequestMethod.PUT)
    public String updateGOC(@RequestBody GoodsOutwardChallan goc) {
 
    	gocService.updateGOC(goc);
        
         return "Success";
    }
    
    @RequestMapping(value="/{gocNo}",method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable long gocNo) {
    	
    	gocService.deleteGOC(gocNo);
 
           return "Success";
    }
    
    @RequestMapping(value="/{gocNo}",method = RequestMethod.PUT)
    public String updateGOCStatus(@PathVariable String gocNo) {
    	
    	GoodsOutwardChallan goc = new GoodsOutwardChallan();
    	goc.setGocNo(gocNo);
    	goc.setStatus(Constants.CLOSED);
    	gocService.updateGOCStatus(goc);
 
           return "Success";
    }
    
 
}