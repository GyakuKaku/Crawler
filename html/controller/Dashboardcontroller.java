package html.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import html.Common.utils.RegexUtil;
import html.service.Crawlehistoryservice;
import html.service.Recordsservice;

@Controller
@RequestMapping("/dashboard")
public class Dashboardcontroller {
	
	@Autowired
	Recordsservice recordsservice;
	
	@Autowired
	Crawlehistoryservice crawlehistoryservice;

	@RequestMapping(params = "init" , method = RequestMethod.GET)  
	public String init(){ 
	    return "dashboard";
	}
	
	@RequestMapping(params = "getpiedata" , method = RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> getpiedata(@RequestParam("keys") List<String> keys,@RequestParam("key_") String key_){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("key_", key_);
		List<String> temp = new ArrayList<String>();
		List<Map<String, Object>> value = new ArrayList<Map<String, Object>>();
		try {
			temp = recordsservice.getalltextbytimeandkey(par);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "false");
			result.put("value", e.toString());
			return result;
		}
		for(int i=0;i<keys.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name",keys.get(i));
			map.put("value", 0);
			value.add(map);
		}
		for(int i=0;i<temp.size();i++){
			for(int j=0;j<keys.size();j++){
				int m = RegexUtil.getSumUtil(temp.get(i), keys.get(j));
				value.get(j).put("value", (Integer)value.get(j).get("value")+m);
			}
		}
		result.put("msg", "success");
		result.put("value", value);
	    return result;
	}
	
	@RequestMapping(params = "getbardata" , method = RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> getbardata(@RequestParam("keys") List<String> keys,@RequestParam("key_") String key_){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("key_", key_);
		List<String> temp = new ArrayList<String>();
		List<Map<String, Object>> value = new ArrayList<Map<String, Object>>();
		try {
			temp = recordsservice.getalltextbytimeandkey(par);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "false");
			result.put("value", e.toString());
			return result;
		}
		for(int i=0;i<keys.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name",keys.get(i));
			map.put("value", 0);
			value.add(map);
		}
		for(int i=0;i<temp.size();i++){
			for(int j=0;j<keys.size();j++){
				int m = RegexUtil.getSumUtil(temp.get(i), keys.get(j));
				value.get(j).put("value", (Integer)value.get(j).get("value")+m);
			}
		}
		result.put("msg", "success");
		result.put("value", value);
	    return result;
	}
	
	@RequestMapping(params = "loadcrawlekeys" , method = RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> loadcrawlekeys(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> keys = new ArrayList<String>();
		try {
			keys=crawlehistoryservice.getallkey();
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "false");
			return result;
		}
		result.put("msg","success");
		result.put("value", keys);
	    return result;
	}
	
	@RequestMapping(params = "getcategorydata" , method = RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> getkeytime(@RequestParam("keys") List<String> keys,@RequestParam("key_") String key_){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> par = new HashMap<String, Object>();
		List<String> dates = new ArrayList<String>();
		List<String> xAxis = new ArrayList<String>();
		List<List<String>> records = new ArrayList<List<String>>();
		List<Map<String,Object>> yAxis = new ArrayList<Map<String,Object>>();
		par.put("key_", key_);
		try {
			dates=crawlehistoryservice.getkeytime(par);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "false");
			return result;
		}
		if(dates.size()<6){
			for(int i=0;i<dates.size();i++){
				xAxis.add(dates.get(dates.size()-i-1)) ;
			}
		}else{
			for(int i=0;i<6;i++){
				xAxis.add(dates.get(5-i));
			}
		}
		par.put("datas", xAxis);
		try {
			records = recordsservice.getcountsbytimeandkey(par);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<keys.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			int[] data = new int[xAxis.size()];
			map.put("name", keys.get(i));
			map.put("type", "line");
			for(int j=0;j<records.size();j++){
				int count = 0;
				for(int k=0;k<records.get(j).size();k++){
					count += RegexUtil.getSumUtil(records.get(j).get(k), keys.get(i));
				}
				data[j] = count;
			}
			map.put("data", data);
			yAxis.add(map);
		}
		result.put("msg","success");
		result.put("keys", keys);
		result.put("xAxis", xAxis);
		result.put("yAxis", yAxis);
	    return result;
	}
}
