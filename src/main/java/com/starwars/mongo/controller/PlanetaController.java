package com.starwars.mongo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.starwars.model.Planeta;
import com.starwars.mongo.PlanetaService;

@Controller
@RequestMapping("/planeta")
public class PlanetaController {

	private static Logger log = Logger.getLogger(PlanetaController.class);

	@Resource(name="planetaService")
	private PlanetaService planetaService;

	// Displaying the initial planet list.
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getPlanets(Model model) {
		
		log.debug("Request to fetch all planets from the mongo database");
		
		List<Planeta> planeta_list = planetaService.getAll();
		
		model.addAttribute("planetas", planeta_list);	
		
		return "welcome";
	}

	// Opening the add new planet form page.
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPlanet(Model model) {
		
		log.debug("Request to open the new planet form page");
		
		model.addAttribute("planetAttr", new Planeta());
		
		return "form";
	}

	// Opening the edit user form page.
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUser(@RequestParam(value="id", required=true) String id, Model model) {
		
		log.debug("Request to open the edit planet form page");	
		System.out.println(planetaService.findPlanetaId(id).get(0));
		
		model.addAttribute("planetAttr", planetaService.findPlanetaId(id).get(0));	
		
		return "form";
	}

	// Deleting the specified planet.
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value="id", required=true) String id, Model model) {
		planetaService.delete(id);
		
		System.out.println("Will delete: " + id);
		return "redirect:list";
	}

	// Find the specified planet id.
	@RequestMapping(value = "/findid", method = RequestMethod.GET)
	public String findid(@RequestParam(value="id", required=true) String id, Model model) {
		
		List<Planeta> planeta_list = new ArrayList<Planeta>();
		
		planeta_list = planetaService.findPlanetaId(id);
		
		model.addAttribute("planetas", planeta_list);
		
		return "welcome";
	}
	
	// Find the specified planet name.
	@RequestMapping(value = "/findname", method = RequestMethod.GET)
	public String findName(@RequestParam(value="nome", required=true) String nome, Model model) {
		
		List<Planeta> planeta_list = new ArrayList<Planeta>();
		
		planeta_list = planetaService.findPlanetaName(nome);
			
		model.addAttribute("planetas", planeta_list);
		
		return "welcome";
	}
	
	// Adding a new planet or updating an existing planet.
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("planetAttr") Planeta planeta, BindingResult result) {
		
		if (result.hasErrors())
		{
			return "form";
			
		} else 
		
		if(planeta.getId() != null && !planeta.getId().trim().equals("")) {
			
			planetaService.edit(planeta);
			
		} else {
			planetaService.add(planeta);
		}
		return "redirect:list";
	}
	
	// Displaying find form id
	@RequestMapping(value = "findformid", method = RequestMethod.GET)
	public String getFormFindID() {	
		
		return "findformid";
	}
	
	// Displaying find form name
	@RequestMapping(value = "findformnome", method = RequestMethod.GET)
	public String getFormFindNome() {	
			
		return "findformname";
	}
		
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(HttpServletRequest req, Exception ex)  {

		log.error("Request: " + req.getRequestURL() + " raised " + ex);
		
		ModelAndView model = new ModelAndView();
		//model.addObject("errCode", ex.getStackTrace()[0].getLineNumber());
		//model.addObject("errMsg", ex.getMessage())
		//model.addObject("url", req.getRequestURL());
		model.addObject("errCode", "404");
		model.addObject("errMsg", "Ocorreu um erro.");
		model.setViewName("error/generic_error");
		return model;

	}
		
}