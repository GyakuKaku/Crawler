package html.htmlunit.utils;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.Keyboard;

public class LinkToSina {
    private WebClient client=null;
    private HtmlPage page3=null;
    private Keyboard keyboard = new Keyboard();
    private Keyboard keyboard2= new Keyboard();
    private int count=0;
    public void browserdown(){
    	client.close();
    }
	public int LinkLogin(){
		client = new WebClient(BrowserVersion.FIREFOX_52);
		client.getOptions().setJavaScriptEnabled(true);//默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
		client.getOptions().setCssEnabled(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.waitForBackgroundJavaScript(10000);
		HtmlPage page=null;
		try {
			page = client.getPage("https://passport.weibo.cn/signin/login?entry=mweibo&res=wel&wm=3349&r=https%3A%2F%2Fm.weibo.cn%2F%3Fcategory%3D0%26category%3D0%26jumpfrom%3Dweibocom");
		} catch (FailingHttpStatusCodeException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
//登录
		HtmlInput ln = page.getFirstByXPath("//input[@id='loginName']");
		HtmlInput pwd = page.getFirstByXPath("//input[@id='loginPassword']");
		 
		

		ln.setAttribute("value", "179978466@qq.com");
		pwd.setAttribute("value", "chen263378");

		try {
			((HtmlElement) page.getFirstByXPath("//a[@id='loginAction']")).click();
			client.waitForBackgroundJavaScript(50000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
}
