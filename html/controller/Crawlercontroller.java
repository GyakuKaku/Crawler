package html.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import html.Common.utils.TimeUtil;
import html.htmlunit.utils.Baidu;
import html.htmlunit.utils.CrawlerThread;
import html.htmlunit.utils.TiebaCrawler;
import html.model.Crawlehistory;
import html.model.Records;
import html.model.Task;
import html.service.Crawlehistoryservice;
import html.service.Locksservice;
import html.service.Recordsservice;
import html.service.Taskservice;

@Controller
@RequestMapping("/crawler")
public class Crawlercontroller {
	
	@Autowired
	Recordsservice recordsservice;

	@Autowired
	Taskservice taskservice;
	
	@Autowired
	Crawlehistoryservice crawlehistoryservice;
	
	@Autowired
	Locksservice locksservice;
	
	@RequestMapping(params = "init" , method = RequestMethod.GET)  
	public String init(){ 
	    return "crawlermain";  
	} 
	
	@RequestMapping(params = "crawl" , method = RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> crawl(@RequestParam("key") String key){ 
		Map<String, Object> result = new HashMap<String, Object>();
		String str = "";
		Baidu link = new Baidu();
		try {
			str = link.getalltext(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("msg", "false");
			result.put("code", e.toString());
			return result;
		}
		result.put("msg", "success");
		result.put("code", str);
	    return result;  
	} 
	@RequestMapping(params = "insert" , method = RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> insert(@RequestParam("key") String key){
		Map<String, Object> result = new HashMap<String, Object>();
		if(locksservice.islock(1) == 1){
			result.put("msg", -1);
			return result;
		}else{
			locksservice.updatelock(1, 1);
		}
		TiebaCrawler t = new TiebaCrawler();
		List<String> list = t.getlinks("fate");
		t.getrecords(list,"fate",new Date());
//		Baidu b = new Baidu();
//		List<String> links = b.getlinks(key);
//		List<Records> records = new ArrayList<Records>();
//		b=null;
//		ThreadPoolExecutor executor =new ThreadPoolExecutor(5,7,200,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
//
//		CrawlerThread mTh1=new CrawlerThread("一号",links,0,records);  
//		CrawlerThread mTh2=new CrawlerThread("二号",links,1,records);  
//		CrawlerThread mTh3=new CrawlerThread("三号",links,2,records);  
//		CrawlerThread mTh4=new CrawlerThread("四号",links,3,records);  
//		CrawlerThread mTh5=new CrawlerThread("五号",links,4,records);
//		executor.execute(mTh1);
//		executor.execute(mTh2);
//		executor.execute(mTh3);
//		executor.execute(mTh4);
//		executor.execute(mTh5);
//		try {
//			executor.shutdown();  
//			if(!executor.awaitTermination(20,TimeUnit.MINUTES)){
//			   System.out.println(" 到达指定时间，还有线程没执行完，不再等待，关闭线程池!");
//			   executor.shutdownNow();  
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			executor.shutdownNow();  
//		}
//	
//        System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");   
//        
//        String now = TimeUtil.getCurrentUTCStringTime();
//        for(int i=0;i<records.size();i++){
//        	records.get(i).setKey(key);
//        	records.get(i).setCrawledate(now);;
//        	try {
//				recordsservice.insertrecords(records.get(i));
//				count++;
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				continue;
//			}
//        	 System.out.println("***********************存储第"+ (i+1) +"条数据***********************");  
//        }
//        if(count>=1){
//        	Crawlehistory crawlehistory = new Crawlehistory(-1,key,now);
//			crawlehistoryservice.inserthistory(crawlehistory);
//        }
        locksservice.updatelock(1, 0);
        result.put("msg", "success");
        return result;
	} 
	
	@RequestMapping(params = "addlist" , method = RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> addlist(@RequestParam("key") String key){ 
		Map<String, Object> result = new HashMap<String, Object>();
		int i = 0;
		Task task = new Task(-1,key,1);
		try {
			i = taskservice.inserttask(task);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "false");
			result.put("code", e.toString());
			return result;
		}
		result.put("msg", "success");
		result.put("code", i);
	    return result;  
	} 
	
	@RequestMapping(params = "selectalltask" , method = RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> selectalltask(){ 
		Map<String, Object> result = new HashMap<String, Object>();
		List<Task> list = new ArrayList<Task>();
		try {
			list = taskservice.selectall();
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "false");
			result.put("code", e.toString());
			return result;
		}
		result.put("msg", "success");
		result.put("code", list);
	    return result;  
	} 
	
	@RequestMapping(params = "deletetask" , method = RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> deletetask(@RequestParam("key") String key){ 
		Map<String, Object> result = new HashMap<String, Object>();
		int i = 0;
		Task task = new Task(-1,key,1);
		try {
			i = taskservice.deletetask(task);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "false");
			result.put("code", e.toString());
			return result;
		}
		result.put("msg", "success");
		result.put("code", i);
	    return result;  
	} 
}
