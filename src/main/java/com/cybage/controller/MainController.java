package com.cybage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cybage.exceptions.UserDefinedException;
import com.cybage.model.OrderDetails;
import com.cybage.model.Product;
import com.cybage.model.User;
import com.cybage.services.MainService;

@Controller
public class MainController 
{ 
 private static final Logger logger = Logger.getLogger(MainController.class);

	
   @Autowired
   MainService service;
   
   @RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpSession session,Model model) 
	{ 
	   session.invalidate();
	   model.addAttribute("msg", "This is index page");
	   return "index";
	}
   
		@RequestMapping(value="/homepage")
	public String listProducts(Model model,HttpSession session)
	{   
		List<Product> productlist= service.listProducts();		
		model.addAttribute("productlist",productlist);
		return "homepage";
	}
		
	
	@RequestMapping(value="/buy")
	public String add(@RequestParam("code") String code,ModelMap model ,HttpSession session)
	{ 		
		System.out.println("in buy..."+code);
	  String user_session= (String) session.getAttribute("user_session");
	  Product productdetails=service.showProductDetails(code);
	  System.out.println("productdetails after buy..."+productdetails);
		if(user_session!= null)
		{
	      model.addAttribute("productdetails",productdetails);
	      return "productdetails";
		}
		else{
			logger.debug("call to login");
/*		     model.addAttribute("code",code);
*/			return "login";
		}
	}
	
	@RequestMapping(value="/login")
	public String showLogin(/*@RequestParam (value="code", required=false) String code,Model model,*/HttpSession session)
	{
	   logger.info("in login...");	
/*	   model.addAttribute("code",code);
*/		return "login";
	}
	
	/*@RequestMapping(value="/submit")
	public String onSubmit(@RequestParam (value="code", required=false) String code, Model model,HttpSession sesion)
	{		logger.info("in onSubmit...");
		model.addAttribute("code", code);
       	 return"afterlogin";
		
	}
*/	
	@RequestMapping(value="/afterlogin")
	public String afterlogin(/*@RequestParam (value="code", required=false) String code,*/
			ModelMap model,HttpSession session,
			@ModelAttribute("SpringWeb")User user,HttpServletRequest request )
	{ 
	  String user_session = (String)session.getAttribute("user_session");
	  logger.debug("in after login....");
/*	  model.addAttribute("code", code);
*/	  
	  if(user_session==null)
		{
	  String user_name= user.getUserName();
	  String user_password= user.getUserPassword();
	  List<User> allUsers= service.getUser();
	  logger.debug("fetching users"+allUsers);
	  for( User u:allUsers)
	  {    
		  System.out.println("U... "+"  "+u.getUserName());
		    if(u.getUserName().equals(user_name)&&(u.getUserPassword().equals(user_password)))
		    {
		    	logger.debug("inside afterlogin if loop"+"u.getUserName()"+" "+u.getUserName());
				 request.getSession().setAttribute("user_session", user.getUserName());
				 logger.debug("matched user!!"+u.getUserName());
				 return "redirect:/homepage";
				
				 /*model.addAttribute("code", code);
				 Product productdetails=service.showProductDetails(code);
			      model.addAttribute("productdetails",productdetails);
				 return "productdetails";
*/				 
		    }
		    else 
		    	{return "falselogin"; }
		  
	  }}
	  return "login";
	}

	/*@RequestMapping("/buy/login")
	public String buyLogin(User input_user,HttpServletRequest request,ModelMap model,HttpSession session)
	{   
		String user_session = (String)session.getAttribute("user_session");
		logger.debug(" in after buy/login..user session"+user_session);
		if(user_session==null)
		{
		String name = input_user.getUserName();
		String pass = input_user.getUserPassword();
		logger.debug("user name:" + name);
		logger.debug("user password:" + pass);

		List<User> allUsers = service.getUser();
		for (User user : allUsers) 
		{
			logger.debug("user......."+user);
			if(user.getUserPassword().equals(pass) && user.getUserName().equals(name))
			{    logger.debug("inside buy/login.. if loop");
				 request.getSession().setAttribute("user_session", user.getUserName());
				 return "redirect:homepage";
			}
			else
				{
     				return "redirect:/login";}
		 }
		
		}
			return "homepage";
	
	}	
*/
	
	@RequestMapping(value="/confirm/{code}")
	public String showOrder(@PathVariable String code,ModelMap model,HttpSession session)
	{  
	    logger.debug("in show order"+code);
		String user_session= (String) session.getAttribute("user_session");
	    if(user_session!=null)
	    {
		String userid = (String) session.getAttribute("user_session");
	     OrderDetails orders= service.confirmOrder(code,userid);
	     model.addAttribute("orders", orders);
	    logger.debug("Order confirmation"+orders+userid);
     	 return "orderplaced";
	    }
	    else
	    	
		return "homepage";
	}

	
	
			
	public String orderPlaced(Model model,HttpSession session)
	{
	  logger.debug("in orderplaced....");
	  String user_session= (String) session.getAttribute("user_session");
	    if(user_session!=null)
	    {
   	   return "orderplaced";
	    }
	    else
	    	
		return "homepage";
	}
	
	@RequestMapping(value="/orderhistory")
	public String showOrderHistory(ModelMap model,HttpSession session)
	{
	   String userId= (String) session.getAttribute("user_session");
	   String user_session= (String) session.getAttribute("user_session");
	    
	   if(user_session!=null)
	    {  
		   logger.debug("maincontroller ....in orderhistory "+userId);
		   List<OrderDetails> orderdetails= service.showOrderHistory(userId);
	    	model.addAttribute("orderdetails",orderdetails);
		   return "orderhistory";
	    }
		return "homepage";
	}
	
	@RequestMapping(value="/signup")
	public String signup(Model model,String userName,
			String password,HttpSession session)
	{   
		System.out.println("in main controller signup");
		String user_session= (String) session.getAttribute("user_session");
	    if(user_session!=null)
	    {
	    	return "login";
	    }
	    else
	    {
		return "registeruser";
	    }
	}
	@RequestMapping(value="/registeruser")
	public String registeruser(ModelMap model,String userName,String userPassword,
			 String fullName,String address, String email, Integer phone,
			   HttpSession session)throws IOException
	{   
		System.out.println("in Maincontroller register user");
		String user_session = (String)session.getAttribute("user_session");
		if(user_session==null)
		{
		if(userName.length()>50)
		{throw new UserDefinedException("user name containes more than 50 characters", "Enter within 50 chars");
		}
		if(userPassword.length()>25)
		{throw new UserDefinedException("user password containes more than 50 characters", "enter within 50 char");
		}
		if(!email.contains("@"))
		{throw new UserDefinedException("Not a valid Email Id","Enter Valid email Id");
		}
	    service.addUser(userName,userPassword,fullName,address,email,phone);
	    logger.debug("adding user"+userName+" "+userPassword+" "+fullName);
		return "useradded";
		}
		else
		{
			return "login";
		}
	}
	
	@RequestMapping(value="/thankyou")
	public String end(HttpSession session)
	{
	  String user_session= (String) session.getAttribute("user_session");
      if(user_session!=null)
      {	  
		return "thankyou";
      }
      else
    	 return "homepage";
	}
	
	
	
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session)
	{  
		System.out.println("in logout.....");
		session.invalidate();
		return "logoutsuccess";
	
	}
	

	}
