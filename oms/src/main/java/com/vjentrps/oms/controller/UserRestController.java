package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.User;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.ResponseDTO;

@RestController
@RequestMapping(value="/service/user")
public class UserRestController extends BaseRestController{
	
    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getUsers() {
              
        List<User> users = new ArrayList<User>();
   	
        try {
			users = userService.getUsers();
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
        return new ResponseDTO(users);
    }
    
    @RequestMapping(value="/{userId}",method = RequestMethod.GET)
    public ResponseDTO getUser(@PathVariable long userId) {
              
        User user;
		try {
			user = userService.getUserById(userId);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
   	
        return new ResponseDTO(user);
    }
    
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseDTO addUser(@RequestBody User user) {
 
    	try {
    		user.setEncryptedPassword(user.getPassword());
			userService.addUser(user);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
       
        return new ResponseDTO();
    }
    
    
   
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseDTO updateUser(User user) {
 
        try {
			userService.updateUser(user);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
        
         return new ResponseDTO();
    }
    
    @RequestMapping(value="/{userId}",method = RequestMethod.DELETE)
    public ResponseDTO deleteUser(@PathVariable long userId) {
    	
    	 try {
			userService.deleteUser(userId);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
 
           return new ResponseDTO();
    }
    
 
  
  }