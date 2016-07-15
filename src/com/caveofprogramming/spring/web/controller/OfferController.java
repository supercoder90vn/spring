package com.caveofprogramming.spring.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.service.OffersService;

@Controller
public class OfferController {	
	private OffersService offersService;
	
	
	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}
	//I.a
	/*
	 @RequestMapping("/")
	 
	public String showOffers(HttpSession session){
		session.setAttribute("name","Boris");
		
		return "home";
	}
	*/
	//I.b
	/*
	@RequestMapping("/")
	public ModelAndView showOffers(){
		ModelAndView mv = new ModelAndView("home");
		Map<String, Object> model = mv.getModel();
		model.put("name", "<b>River</b>");
	   return mv;
	}
	*/

	///////________________________\\\\\\\
	///				I.1.a.
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id){
		System.out.println(id);
		return "home";
	}	
	
	@RequestMapping("/offers")
	public String showOffers(Model model){	
		
		if(false){
			offersService.throwTestException();
		}
		
		List<Offer> offers = offersService.getCurrent();		
		model.addAttribute("offers",offers);		
		model.addAttribute("name", "<b>Tiffany</b>");	
				
	   return "offers";
	}
	
	@RequestMapping("/createOffer")
	public String createOffers(Model model){
		model.addAttribute("offer", new Offer());
	   return "createOffer";
	}
	@RequestMapping(value="/doCreate",method=RequestMethod.POST)
	public String doOffers(Model model,@Valid Offer offer, BindingResult result){
		System.out.println(offer);
		if(result.hasErrors()){
			System.out.println("Form does not validate.");
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error:errors){
				System.out.println(error.getDefaultMessage());
			}
			return "createOffer";
		}
		offersService.create(offer);
	   return "offerCreated";
	}
	/*
	 @ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex){
		return "error";
	}
	 */
	
	//________________________________________________________________
	public OffersService getOffersService() {
		return offersService;
	}


	
	
	
	
}
