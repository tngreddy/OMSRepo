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
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.model.ReturnedInwardNote;
import com.vjentrps.oms.service.RINService;

@RestController
@RequestMapping(value="/service/rin")
public class RINRestController {
	
	@Autowired
	RINService rinService;
	
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getRINs() {
    	
        List<ReturnedInwardNote> rins = new ArrayList<ReturnedInwardNote>();
   	
        	rins = rinService.listRINs();
        return new ResponseDTO(rins);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO createRIN(@RequestBody ReturnedInwardNote rin) {
 
    	rin.setStatus("pending");
    	try {
			rinService.createRIN(rin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return new ResponseDTO();
    }
    
      
   
    @RequestMapping(method = RequestMethod.PUT)
    public String updateRIN(@RequestBody ReturnedInwardNote rin) {
 
    	rinService.updateRIN(rin);
        
         return "Success";
    }
    
    @RequestMapping(value="/{rinNo}",method = RequestMethod.DELETE)
    public String deleteRIN(@PathVariable String rinNo) {
    	
    	rinService.deleteRIN(rinNo);
 
           return "Success";
    }
    
    @RequestMapping(value="/{rinNo}",method = RequestMethod.PUT)
    public String updateRINStatus(@PathVariable String rinNo) {
    	
    	ReturnedInwardNote rin = new ReturnedInwardNote();
    	rin.setRinNo(rinNo);
    	rin.setStatus(Constants.CLOSED);
    	rinService.updateRINStatus(rin);
 
           return "Success";
    }
    
 
}