package com.github.springular.server.rest.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaymentController {

    @ResponseBody
    @RequestMapping(value = "/secured/payment", method = RequestMethod.GET)
    public List<Payment> getPayments() {
      return Arrays.asList(
      	payment("carrots", "10", "3.40$"),
      	payment("tomatos", "20", "1.20$"),
      	payment("potatos", "34", "0.10$"),
      	payment("strawberies", "12", "3.90$")
      ); 
    }
	
    public static Payment payment(String name, String quantity, String price) {
    Payment payment = new Payment();
    payment.setName(name);
    payment.setQuantity(quantity);
    payment.setPrice(price);
		return payment;
	}
    
  public static class Payment {
    	
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
