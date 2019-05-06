package html.quartz.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import html.Common.utils.DateUtil;
import html.htmlunit.utils.TiebaCrawler;
import html.model.Crawlehistory;
import html.model.Task;
import html.service.Crawlehistoryservice;
import html.service.Taskservice;

public class CrawlOnTimeJob{

	@Autowired
	Taskservice taskservice;
	
	@Autowired
	Crawlehistoryservice crawlehistoryservice;
	
	public void execute() throws JobExecutionException {
		TiebaCrawler t = new TiebaCrawler();
		Date crawler_time = new Date();
		List<Task> tasklist = new ArrayList<Task>();
		try {
			tasklist = taskservice.selectall();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		for(Task task:tasklist){
			String key_ = task.getKey_();
			Map<String, Object> par = new HashMap<>();
			par.put("key_", key_);
			par.put("crawledate", crawler_time);
			crawlehistoryservice.inserthistory(par);
			List<String> list = t.getlinks(key_);
			t.getrecords(list,key_,crawler_time);
		}
        return;
	}

}
