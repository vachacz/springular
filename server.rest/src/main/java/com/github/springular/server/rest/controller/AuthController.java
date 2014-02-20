package com.github.springular.server.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @ResponseBody
    @RequestMapping(value = "/secured/authenticate", method = RequestMethod.POST)
    public String authenticate() {
        return "authenticated"; 
    }
	
}
