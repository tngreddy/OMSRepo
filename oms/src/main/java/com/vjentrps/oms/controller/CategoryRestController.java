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
import com.vjentrps.oms.service.CategoryService;

@RestController
@RequestMapping(value="/service/category")
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;
 
    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getCategories() {
              
        List<Category> categories = new ArrayList<Category>();
   	
    	categories = categoryService.listCategories();
        return categories;
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addCategory(Category category) {
 
       categoryService.addCategory(category);
       
        return "Success";
    }
    
    
   
    @RequestMapping(method = RequestMethod.PUT)
    public String updateCategory(Category category) {
 
        categoryService.updateCategory(category);
        
         return "Success";
    }
    
    @RequestMapping(value="/{categoryId}",method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable long categoryId) {
    	
    	 categoryService.deleteCategory(categoryId);
 
           return "Success";
    }
    
    @RequestMapping(value="/count",method = RequestMethod.GET)
    public int getCategoryCount() {
    	
    	return categoryService.getCategoryCount();
 
           
    }
    
 
}