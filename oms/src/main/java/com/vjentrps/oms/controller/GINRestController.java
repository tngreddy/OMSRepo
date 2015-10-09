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
@RequestMapping(value="/gin")
public class GINRestController {
	
	/*@Autowired
	GINService ginService;*/
 
    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getGINs() {
              
        List<Category> categories = new ArrayList<Category>();
   	
    	//categories = categoryService.listCategories();
        return categories;
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createGIN(GoodsInwardNote gin) {
 
       //categoryService.addCategory(category);
       
        return "Success";
    }
    
      
   
    @RequestMapping(method = RequestMethod.PUT)
    public String updateGIN(GoodsInwardNote gin) {
 
        //categoryService.updateCategory(category);
        
         return "Success";
    }
    
    @RequestMapping(value="/{ginNo}",method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable long ginNo) {
    	
    	 //categoryService.deleteCategory(categoryId);
 
           return "Success";
    }
    
    @RequestMapping(value="/{ginNo}",method = RequestMethod.PUT)
    public String updateGINStatus(@PathVariable long ginNo) {
    	
    	 //categoryService.deleteCategory(categoryId);
 
           return "Success";
    }
    
 
}