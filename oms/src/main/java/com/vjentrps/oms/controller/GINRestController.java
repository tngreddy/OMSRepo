package com.vjentrps.oms.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.Constants;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.service.CategoryService;
import com.vjentrps.oms.service.GINService;

@RestController
@RequestMapping(value="/service/gin")
public class GINRestController {
	
	@Autowired
	GINService ginService;
	
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getGINs() {
    	
        List<GoodsInwardNote> gins = new ArrayList<GoodsInwardNote>();
   	
        	gins = ginService.listGINs();
        return new ResponseDTO(gins);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO createGIN(@RequestBody GoodsInwardNote gin) {
 
    	gin.setStatus("pending");
    	try {
			ginService.createGIN(gin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return new ResponseDTO();
    }
    
      
   
    @RequestMapping(method = RequestMethod.PUT)
    public String updateGIN(@RequestBody GoodsInwardNote gin) {
 
    	ginService.updateGIN(gin);
        
         return "Success";
    }
    
    @RequestMapping(value="/{ginNo}",method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable long ginNo) {
    	
    	ginService.deleteGIN(ginNo);
 
           return "Success";
    }
    
    @RequestMapping(value="/{ginNo}",method = RequestMethod.PUT)
    public String updateGINStatus(@PathVariable String ginNo) {
    	
    	GoodsInwardNote gin = new GoodsInwardNote();
    	gin.setGinNo(ginNo);
    	gin.setStatus(Constants.CLOSED);
    	ginService.updateGINStatus(gin);
 
           return "Success";
    }
    
 
}