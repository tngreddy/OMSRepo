package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.service.CategoryService;
import com.vjentrps.oms.service.GINService;

@RestController
@RequestMapping(value="/service/gin")
public class GINRestController {
	
	@Autowired
	GINService ginService;
 
    @RequestMapping(method = RequestMethod.GET)
    public List<GoodsInwardNote> getGINs() {
              
        List<GoodsInwardNote> gins = new ArrayList<GoodsInwardNote>();
   	
        	gins = ginService.listGINs();
        return gins;
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createGIN(GoodsInwardNote gin) {
 
    	ginService.createGIN(gin);
       
        return "Success";
    }
    
      
   
    @RequestMapping(method = RequestMethod.PUT)
    public String updateGIN(GoodsInwardNote gin) {
 
    	ginService.updateGIN(gin);
        
         return "Success";
    }
    
    @RequestMapping(value="/{ginNo}",method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable long ginNo) {
    	
    	ginService.deleteGIN(ginNo);
 
           return "Success";
    }
    
    @RequestMapping(value="/{ginNo}",method = RequestMethod.PUT)
    public String updateGINStatus(@PathVariable long ginNo) {
    	
    	ginService.updateGINStatus(ginNo);
 
           return "Success";
    }
    
 
}