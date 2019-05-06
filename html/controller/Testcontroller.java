package html.controller;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import html.Common.utils.EsUtil;
import html.Common.utils.writeout;
import html.htmlunit.utils.TiebaCrawler;
import html.service.Crawlehistoryservice;

@Controller
@RequestMapping("/test")
public class Testcontroller {
	
	public final static String HOST = "39.105.155.193";
    
    public final static int PORT = 9300;
    
    @Autowired
	Crawlehistoryservice crawlehistoryservice;
	
	@RequestMapping(params = "t")
	@ResponseBody
	public String getplatforms() throws UnknownHostException{
		TransportClient client = new PreBuiltTransportClient(Settings.builder().put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", false).build())
                .addTransportAddress(new TransportAddress(new InetSocketAddress("39.105.155.193", 9300)));;
        GetResponse response = client.prepareGet("tieba-data", "test", "yuBd9ZZY").get();
        System.out.println(response.getSourceAsString());
		return response.getSourceAsString();
	}
	
	@RequestMapping(params = "add")
	@ResponseBody
	public String add() throws UnknownHostException{
		TransportClient client = new PreBuiltTransportClient(Settings.builder().put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", false).build())
                .addTransportAddress(new TransportAddress(new InetSocketAddress("39.105.155.193", 9300)));;
      
		Map<String, Object> params = new HashMap<>();
		params.put("title", "test2");
		params.put("date", System.currentTimeMillis());
		params.put("text", "text2");
		IndexResponse response = client.prepareIndex("tieba-data", "blog", "3").setSource(params).get();
		
		return "";
	}
	
	@RequestMapping(params = "crawle")
	@ResponseBody
	public String crawle() throws UnknownHostException{
		Map<String, Object> par = new HashMap<>();
		par.put("key_", "test");
		par.put("crawledate", new Date());
		crawlehistoryservice.inserthistory(par);
		return "";
	}
	
	@RequestMapping(params = "em")
	@ResponseBody
	public String esmatchall() throws UnknownHostException{
		List<String> foo = EsUtil.selectall();
		for(int i=0;i<foo.size();i++){
			writeout.write(foo.get(i), 5);
		}
		System.out.println("ok");
		return "";
	}
}
