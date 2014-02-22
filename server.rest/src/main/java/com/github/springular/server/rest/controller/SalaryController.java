package com.github.springular.server.rest.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SalaryController {

    @ResponseBody
    @RequestMapping(value = "/secured/salary", method = RequestMethod.GET)
    public List<Salary> getSalaries() {
      return Arrays.asList(
      	salary("carrots", "10", "3.40$"),
      	salary("tomatos", "20", "1.20$"),
      	salary("potatos", "34", "0.10$"),
      	salary("strawberies", "12", "3.90$")
      ); 
    }
	
    public static Salary salary(String name, String quantity, String price) {
    Salary salary = new Salary();
    salary.setName(name);
    salary.setQuantity(quantity);
    salary.setPrice(price);
		return salary;
	}
    
  public static class Salary {
    	
    private String name;
    private String quantity;
    private String price;
    	
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getQuantity() {
			return quantity;
		}
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
  }
}
