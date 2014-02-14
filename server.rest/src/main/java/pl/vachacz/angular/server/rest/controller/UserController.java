package pl.vachacz.angular.server.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.vachacz.angular.server.IUserBCI;
import pl.vachacz.angular.server.UserDO;
import pl.vachacz.angular.server.rest.common.BaseController;

@Controller
public class UserController extends BaseController {

	@Autowired IUserBCI authBCI;
	
	@RequestMapping("/users")
	public @ResponseBody List<UserDO> getUsers() {
		List<UserDO> users = authBCI.getUsers();
		return users;
	}
	
    @RequestMapping("/user/{id}")
    public @ResponseBody UserDO findUser(@PathVariable String id) {
        return authBCI.findUser(id);
    }

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void updateUser(@RequestBody UserDO user) {
        authBCI.updateUser(user);
    }
    
}
