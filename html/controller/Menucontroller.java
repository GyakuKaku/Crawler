package html.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/menu")
public class Menucontroller {

	@RequestMapping(params = "init" , method = RequestMethod.GET)  
	public String login(){ 
	    return "menu";  
	}  
}
