package html.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import html.service.Userservice;


@Controller
@RequestMapping("/")
public class Logincontroller {
	
	@Resource
	Userservice userservice;
	
	@RequestMapping(value = "/login" , method = RequestMethod.GET)  
	public String login(){ 
	    return "index";  
	}  
	
	@RequestMapping(value = "/login" ,params = "psd" , method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> psd(@RequestParam("account") String account,@RequestParam("pw") String psd){ 
		Map<String, Object> result = new HashMap<String, Object>();
	    String rel_psd = "";
		try {
			rel_psd = userservice.getpsd(account);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("msg", "false");
			result.put("code", e.toString());
			return result;
		}  
	    if(psd.equals(rel_psd)){
			result.put("msg", "success");
			result.put("code", "0");
		}else{
			result.put("msg", "false");
			result.put("code", "1");
		}
	    return result;
	} 
	
}
