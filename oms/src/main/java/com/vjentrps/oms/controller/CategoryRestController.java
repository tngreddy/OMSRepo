package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.ResponseDTO;

@RestController
@RequestMapping(value="/service/category")
public class CategoryRestController extends BaseRestController{

	@RequestMapping(method = RequestMethod.GET)
	public ResponseDTO getCategories() {

		List<Category> categories = new ArrayList<Category>();

		try {
			categories = categoryService.listCategories();
			
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
		 catch (Exception e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
	    }

		return new ResponseDTO(categories);
	}


	@RequestMapping(method = RequestMethod.POST)
	public ResponseDTO addCategory( @RequestBody Category category) {

		int success;
		try {
			success = categoryService.addCategory(category);
			if(success == 0){
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
	    }

 	return new ResponseDTO();
	}



	@RequestMapping(method = RequestMethod.PUT)
	public ResponseDTO updateCategory(@RequestBody Category category) {

		int success;
		try {
			success = categoryService.updateCategory(category);
			if(success == 0){
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
	    }

		
		return new ResponseDTO();

	}

	@RequestMapping(value="/{categoryId}",method = RequestMethod.DELETE)
	public ResponseDTO deleteCategory(@PathVariable long categoryId) {

		int success;
		try {
			success = categoryService.deleteCategory(categoryId);
			if(success == 0){
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
			
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}catch (Exception e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
	    }

		return new ResponseDTO();
	}

	@RequestMapping(value="/count",method = RequestMethod.GET)
	public int getCategoryCount() {

		try {
			return categoryService.getCategoryCount();
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;


	}


}