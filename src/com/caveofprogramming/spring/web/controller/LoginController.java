package com.caveofprogramming.spring.web.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caveofprogramming.spring.web.dao.FormValidationGroup;
import com.caveofprogramming.spring.web.dao.Message;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.service.UsersService;

@Controller
public class LoginController {
	private UsersService usersService;
	@Autowired
	private MailSender mailSender;
	@Autowired	
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	//#################################################################################
	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping("/denied")
	public String showDenied(){
		return "denied";
	}
	@RequestMapping("/messages")
	public String showMessages(){
		return "messages";
	}
	@RequestMapping("/admin")
	public String showAdmin(Model model){
//		try{
			List<User> users = usersService.getAllUsers();
			model.addAttribute("users",users);
//		}catch(Exception e){
//			System.out.println("Exception: "+e.getClass());
//		}		
		return "admin";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut(){
		return "loggedout";
	}
	
	



	@RequestMapping("/newAccount")
	public String showNewAccount(Model model){
		model.addAttribute("user",new User());
		return "newAccount";
	}
	
	
	
	@RequestMapping(value="/createAccount",method=RequestMethod.POST)
	//public String doCreate(@Valid User user, BindingResult result){
	public String doCreate(@Validated(FormValidationGroup.class) User user, BindingResult result){
		if(result.hasErrors()){
			return "newAccount";
		}		
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		
		if(usersService.exists(user.getUsername())){
			System.out.println("Caught duplicate username");
			result.rejectValue("username","DuplicateKey.user.username");
			//result.rejectValue("username","DuplicateKey.user.username","_This username already exists");
			return "newAccount";
		}
		try{
			usersService.create(user);
		}catch(DuplicateKeyException e){
			//System.out.println(e.getClass());
			result.rejectValue("username","DuplicateKey.user.username");
			//result.rejectValue("username","DuplicateKey.user.username","_This username already exists");
			return "newAccount";
		}
		
	   return "accountCreated";
	}
	@RequestMapping(value="/getMessages",method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> getMessages(Principal principal){
		List<Message> messages=null;
		if(principal==null){
			messages = new ArrayList<Message>();
		}else{
			messages = usersService.getMessages(principal.getName());
		}
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());
		return data;
	}
	@RequestMapping(value="/sendMessage",method=RequestMethod.POST, 
			produces="application/json")
	@ResponseBody
	public Map<String, Object> sendMessage(Principal principal,@RequestBody Map<String,Object> data){
		String text = (String)data.get("text");
		String name = (String)data.get("name");
		String email = (String)data.get("email");
		Integer target = (Integer)data.get("target");
		
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("hongocphuc90dn@gmail.com");
		mail.setTo(email);
		mail.setSubject("Re: "+ name+ ", your message");
		mail.setText(text);
		
		try{
			mailSender.send(mail);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Can't send message");
		}
		
		Map<String, Object> rval = new HashMap<String,Object>();
		rval.put("success", true);
		rval.put("target", target);
		return data;
	}
}
