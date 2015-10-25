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
import com.vjentrps.oms.model.Error;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.service.CategoryService;
import com.vjentrps.oms.util.CommonUtil;

@RestController
@RequestMapping(value="/service/category")
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CommonUtil commonUtil;
	
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getCategories() {
              
        List<Category> categories = new ArrayList<Category>();
   	
    	categories = categoryService.listCategories();
    	
    	if(categories.isEmpty()){
    		return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
        }
        return new ResponseDTO(categories);
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseDTO addCategory( @RequestBody Category category) {
 
       int success = categoryService.addCategory(category);
       
       if(success == 0){
    	   return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
       }
       return new ResponseDTO();
    }
    
    
   
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseDTO updateCategory(@RequestBody Category category) {
 
    	 int success = categoryService.updateCategory(category);
         
    	 if(success == 0){
      	   return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
         }
         return new ResponseDTO();
          
    }
    
    @RequestMapping(value="/{categoryId}",method = RequestMethod.DELETE)
    public ResponseDTO deleteCategory(@PathVariable long categoryId) {
    	
    	int success = categoryService.deleteCategory(categoryId);
        
    	if(success == 0){
       	   return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
          }
          return new ResponseDTO();
    }
    
    @RequestMapping(value="/count",method = RequestMethod.GET)
    public int getCategoryCount() {
    	
    	return categoryService.getCategoryCount();
 
           
    }
    
 
}