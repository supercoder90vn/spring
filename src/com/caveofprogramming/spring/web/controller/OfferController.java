package com.caveofprogramming.spring.web.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.service.OffersService;

@Controller
public class OfferController{
	private OffersService offersService;

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}
	// I.a
	/*
	 * @RequestMapping("/")
	 * 
	 * public String showOffers(HttpSession session){
	 * session.setAttribute("name","Boris");
	 * 
	 * return "home"; }
	 */
	// I.b
	/*
	 * @RequestMapping("/") public ModelAndView showOffers(){ ModelAndView mv =
	 * new ModelAndView("home"); Map<String, Object> model = mv.getModel();
	 * model.put("name", "<b>River</b>"); return mv; }
	 */

	/////// ________________________\\\\\\\
	/// I.1.a.
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {
		System.out.println(id);
		return "home";
	}

	@RequestMapping("/createOffer")
	public String createOffer(Model model, Principal principal) {
		Offer offer = null;
		if (principal != null) {
			String username = principal.getName();
			offer = offersService.getOffer(username);
		}
		if (offer == null) {
			offer = new Offer();
		}
		model.addAttribute("offer", offer);
		return "createOffer";
	}

	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public String doOffers(Model model, @Valid Offer offer, 
			BindingResult result, Principal principal, 
			@RequestParam(value="delete", required=false) String delete) {
		if (result.hasErrors()) {
			return "createOffer";
		}
		
		if(delete==null){
			System.out.println("delete is null");
			String username = principal.getName();
			offer.getUser().setUsername(username);
			offersService.saveOrUpdate(offer);
			return "offerCreated";
		}else{
			offersService.delete(offer.getId());
			return "offerDeleted";
		}
	
		
		

	}
	/*
	 * @ExceptionHandler(DataAccessException.class) public String
	 * handleDatabaseException(DataAccessException ex){ return "error"; }
	 */

	// ________________________________________________________________
	public OffersService getOffersService() {
		return offersService;
	}
}
