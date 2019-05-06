package html.htmlunit.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import html.Common.utils.writeout;

public class Baidu {

    private int foo = 0;
    
    public int waitjs(long timeoutMillis,WebClient client){
    	int temp = client.waitForBackgroundJavaScript(timeoutMillis);
    	return temp;
    }
	public WebClient LinkLogin(){
		WebClient client = null;
		client = new WebClient(BrowserVersion.FIREFOX_52);
		client.getOptions().setJavaScriptEnabled(true);//默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
		client.getOptions().setCssEnabled(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setTimeout(30000);
		return client;
	}
	public HtmlPage getindex(String key){
		WebClient client= LinkLogin();
		HtmlPage page1=null;
		HtmlPage page2=null;
		try {
			page1 = client.getPage("https://www.baidu.com");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("***********************打开首页失败***********************");
			return null;
		}
		foo=client.waitForBackgroundJavaScript(10000);
		if(foo !=0){
			System.out.println("***********************还有"+ foo +"条请求未完成***********************");
		}
		HtmlInput ln = page1.getFirstByXPath("//input[@id='kw']");
		HtmlInput out = page1.getFirstByXPath("//input[@id='su']");
		ln.setAttribute("value", key);
		try {
			page2=out.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("***********************打开首页失败***********************");
			return null;
		}
		foo=client.waitForBackgroundJavaScript(10000);
		if(foo !=0){
			System.out.println("***********************还有"+ foo +"条请求未完成***********************");
		}
		System.out.println("***********************打开首页成功***********************");
		return page2;
	}
	public List<String> getlinks(String key){
		WebClient client= LinkLogin();
		HtmlPage page = getindex(key);
		HtmlPage page_ = null;
		if(page == null){
			return null;
		}
		List<HtmlAnchor> links = new ArrayList<HtmlAnchor>();
		List<String> result = new ArrayList<String>();
		HtmlAnchor link=null;
		List<HtmlAnchor> pages_link = page.getByXPath("//div[@id='page']//a");
		links = page.getByXPath("//div[@class='result c-container ']/h3/a");
		foo=client.waitForBackgroundJavaScript(5000);
		if(foo !=0){
			System.out.println("***********************还有"+ foo +"条请求未完成***********************");
		}
		for(int j=0;j<links.size();j++){
			if(links.get(j).getHrefAttribute()!=null&&(!links.get(j).getHrefAttribute().isEmpty()))
				result.add(links.get(j).getHrefAttribute());
		}
		System.out.println("***********************获取第"+ "1" +"页链接***********************");
		for(int i=0;i<pages_link.size()&&i<10;i++){
			try {
				page_=pages_link.get(i).click();
				foo=client.waitForBackgroundJavaScript(5000);
				if(foo !=0){
					System.out.println("***********************还有"+ foo +"条请求未完成***********************");
				}
				links = page_.getByXPath("//div[@class='result c-container ']/h3/a");
				foo=client.waitForBackgroundJavaScript(3000);
				if(foo !=0){
					System.out.println("***********************还有"+ foo +"条请求未完成***********************");
				}
				for(int j=0;j<links.size();j++){
					if(links.get(j).getHrefAttribute()!=null&&(!links.get(j).getHrefAttribute().isEmpty()))
						result.add(links.get(j).getHrefAttribute());
				}
				System.out.println("***********************获取第"+ (i+2) +"页链接***********************");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("翻页失败");
				e.printStackTrace();
				continue;
			}
			
//			client.close();
		}
		return result;
	}
	public String getalltext(String key){
		WebClient client= LinkLogin();
		String str="";
		List<String> links = getlinks(key);
		HtmlPage temp_page = null;
		for(int i=0;i<links.size()&&i<100;i++){
			client = LinkLogin();
			try {
				temp_page = client.getPage(links.get(i));
				foo=client.waitForBackgroundJavaScript(5000);
				if(foo !=0){
					System.out.println("***********************还有"+ foo +"条请求未完成***********************");
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			str = temp_page.getBody().asText().trim().replaceAll("\t", "").replaceAll("(\r\n)", "").replaceAll(" ", "")+"\r\n*****************************\r\n";
			foo=client.waitForBackgroundJavaScript(1500);
			if(foo !=0){
				System.out.println("***********************还有"+ foo +"条请求未完成***********************");
			}
			System.out.println("***********************爬取第"+ (i+1) +"条链接***********************");
			writeout.write(str,(i/10));
			client.close();
		}
		System.out.println("***********************爬虫完成***********************");
		return str;
	}
	public static void main(String[] args) throws InterruptedException {
		Baidu a = new Baidu();
		a.getalltext("乔鲁诺");
	}
}
