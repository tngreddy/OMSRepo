package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.service.CategoryService;

@RestController
@RequestMapping(value="/service/category")
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getCategories() {
              
        List<Category> categories = new ArrayList<Category>();
   	
    	categories = categoryService.listCategories();
    	
    	if(categories.isEmpty()){
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCategory( @RequestBody Category category) {
 
       int success = categoryService.addCategory(category);
       
       if(success == 1){
    	   return new ResponseEntity<String>("Success",HttpStatus.CREATED);
       } else 
    	   return new ResponseEntity<String>("Failure",HttpStatus.NO_CONTENT);
       
        
    }
    
    
   
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCategory(@RequestBody Category category) {
 
    	 int success = categoryService.updateCategory(category);
         
         if(success == 1){
      	   return new ResponseEntity<Void>(HttpStatus.CREATED);
         } else 
      	   return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
         
          
    }
    
    @RequestMapping(value="/{categoryId}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategory(@PathVariable long categoryId) {
    	
    	int success = categoryService.deleteCategory(categoryId);
        
        if(success == 1){
     	   return new ResponseEntity<Void>(HttpStatus.OK);
        } else 
     	   return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/count",method = RequestMethod.GET)
    public int getCategoryCount() {
    	
    	return categoryService.getCategoryCount();
 
           
    }
    
 
}