package com.github.springular.server.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.springular.server.component.user.IUserBCI;
import com.github.springular.server.component.user.UserDO;
import com.github.springular.server.rest.common.BaseController;

@Controller
public class UserController extends BaseController {

	@Autowired IUserBCI authBCI;
	
	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<UserDO> getUsers() {
		List<UserDO> users = authBCI.getUsers();
		return users;
	}
	
    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public void updateUser(@RequestBody UserDO user) {
        authBCI.updateUser(user);
    }
    
}
