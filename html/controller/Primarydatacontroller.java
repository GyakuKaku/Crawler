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

import html.Common.utils.EsUtil;
import html.Common.utils.GridUtil;
import html.model.Crawlehistory;
import html.model.GridModel;
import html.model.Records;
import html.model.Tiebadate;
import html.service.Crawlehistoryservice;
import html.service.Recordsservice;

@Controller
@RequestMapping("/primarydata")
public class Primarydatacontroller {

	@Autowired
	Recordsservice recordsservice;

	@Autowired
	Crawlehistoryservice crawlehistoryservice;

	@RequestMapping(params = "init", method = RequestMethod.GET)
	public String init() {
		return "primarydata";
	}

	@RequestMapping(params = "getalltitle", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getalltitle() {
		List<String> result = new ArrayList<>();

		return result;
	}

	// @RequestMapping(params = "search")
	// @ResponseBody
	// public GridModel search(@RequestParam("key_") String
	// key_,@RequestParam("rows") int rows,@RequestParam("page") int page) {
	// Map<String, Object> par = new HashMap<String, Object>();
	// par.put("key_", key_);
	// List<Records> result = new ArrayList<Records>();
	// try {
	// result = recordsservice.searchbypage(par);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// return null;
	// }
	// GridModel gridModel = GridUtil.initialize(result,rows,page);
	// return gridModel;
	// }

	@RequestMapping(params = "find")
	@ResponseBody
	public GridModel find(@RequestParam("key_") String key_, @RequestParam("rows") int rows, @RequestParam("page") int page) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("key_", key_);
		List<Crawlehistory> result = new ArrayList<Crawlehistory>();
		try {
			result = crawlehistoryservice.find(par);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GridModel();
		}
		GridModel gridModel = GridUtil.initialize(result, rows, page);
		return gridModel;
	}

	@RequestMapping(params = "getonetext")
	@ResponseBody
	public Map<String, Object> getonetext(@RequestParam("sid") int sid, @RequestParam("title") String title) {
		Map<String, Object> par = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		List<Tiebadate> datas = new ArrayList<>();
		Crawlehistory crawlehistory = new Crawlehistory();
		par.put("sid", sid);
		try {
			crawlehistory = crawlehistoryservice.find(par).get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.put("msg", "false");
			e.printStackTrace();
			return result;
		}
		datas = EsUtil.findbypage(crawlehistory.getKey(), crawlehistory.getCrawledate());
		result.put("msg", "success");
		return result;
	}
}
