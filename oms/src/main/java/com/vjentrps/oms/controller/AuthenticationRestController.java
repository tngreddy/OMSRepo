package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.model.User;

@RestController
@RequestMapping(value="/service/auth")
public class AuthenticationRestController extends BaseRestController{

	@RequestMapping(value="/signIn", method = RequestMethod.POST)
	public ResponseDTO ProcessSignIn( @RequestBody User user) {

		User validUser;
		try {
			validUser = userService.authenticateUser(user);
			if(null == validUser){
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.NO_USER_FOUND));
			}
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
		catch (Exception e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
	    }

 	return new ResponseDTO(validUser);
	}
	
	@RequestMapping(value="/signOut", method = RequestMethod.POST)
	public ResponseDTO processSignOut() {

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


}