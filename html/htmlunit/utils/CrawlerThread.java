package html.htmlunit.utils;

import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import html.model.Records;

public class CrawlerThread implements Runnable{

	private String name;
	private int p;
	private List<String> links;
	
	static List<Records> records;
	
	public CrawlerThread(String name,List<String> links, int p,List<Records> records ) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.p = p;
		this.links = links;
		this.records = records;
	}
	@Override
	public void run() {
		int foo = 0;
		// TODO Auto-generated method stub
		Baidu b = new Baidu();
		WebClient client= b.LinkLogin();
		Records r = null;
		HtmlPage temp_page = null;
		
		for(int i=p;i<links.size()&&i<100;i=i+5){
			if(Thread.interrupted()){
				System.out.println("***********************\n\n\n想要停止线程\n\n\n***********************");
			}
			try {
				String str="";
				client= b.LinkLogin();
				try {
					temp_page = client.getPage(links.get(i));
					foo=client.waitForBackgroundJavaScript(5000);
					if(foo !=0){
						System.out.println("***********************还有"+ foo +"条请求未完成***********************");
					}
				}catch (Exception e) {
					continue;
				}
				try {
					str = temp_page.getBody().asText().trim().replaceAll("\t", "").replaceAll("(\r\n)", "").replaceAll(" ", "").replaceAll("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", "")+"\r\n*****************************\r\n";
				} catch (Exception e1) {
					e1.printStackTrace();
					continue;
				}
				foo=client.waitForBackgroundJavaScript(1500);
				if(foo !=0){
					System.out.println("***********************还有"+ foo +"条请求未完成***********************");
				}
				System.out.println("***********************爬取第"+ (i+1) +"条链接***********************");
				System.out.println("***********************"+ links.get(i) +"***********************");
				r = new Records(-1,"",str,"");
				if(r != null){
					try {
						synchronized(this){
							records.add(r);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						continue;
					}
				}
				r = null;
				str = null;
				client.getCurrentWindow().getJobManager().removeAllJobs();
				client.close();
				client = null;
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("***********************\n\n\n想要停止线程2\n\n\n***********************");
				break;
			}
		}
		System.out.println("***********************线程"+ name +"爬虫完成***********************");
	}
}
