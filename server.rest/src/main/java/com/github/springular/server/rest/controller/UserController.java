package com.github.springular.server.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.springular.server.component.user.IUserBCI;
import com.github.springular.server.component.user.UserDO;

@Controller
public class UserController {

	@Autowired IUserBCI authBCI;
	
	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<UserDO> getUsers() {
		return authBCI.getUsers();
	}
	
  @ResponseBody
  @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
  public void updateUser(@RequestBody UserDO user) {
    authBCI.updateUser(user);
  }
    
  @ResponseBody
  @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
  public void deleteUser(@PathVariable(value = "id") String userId) {
    authBCI.deleteUser(userId);
  }
    
}

