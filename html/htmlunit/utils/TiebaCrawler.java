package html.htmlunit.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.xm.tendency.word.HownetWordTendency;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import html.Common.utils.EsUtil;
import html.Common.utils.writeout;
import html.model.Tiebadate;
import html.service.Crawlehistoryservice;

public class TiebaCrawler {

	// 创建浏览器
	public WebClient LinkLogin() {
		WebClient client = null;
		client = new WebClient(BrowserVersion.FIREFOX_52);
		client.getOptions().setJavaScriptEnabled(true);// 默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
		client.getOptions().setCssEnabled(true);
		client.setAjaxController(new NicelyResynchronizingAjaxController());
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setTimeout(6000);
		return client;
	}

	// 获取连接
	public HtmlPage getindex(String key, int i) {
		WebClient client = LinkLogin();
		HtmlPage page1 = null;
		try {
			page1 = client.getPage("https://tieba.baidu.com/f?ie=utf-8&kw=" + key + "&pn=" + i * 50);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("***********************打开首页失败***********************");
			return null;
		}
		System.out.println("***********************还有" + client.waitForBackgroundJavaScript(3000)
				+ "条请求未完成***********************");
		System.out.println("***********************获取第" + (i + 1) + "页链接***********************");
		return page1;
	}

	public List<String> getlinks(String key) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < 1; i++) {
			HtmlPage page = getindex(key, i);
			if (page == null) {
				System.out.println("***********************第" + (i + 1) + "页获取失败***********************");
				break;
			}
			List<HtmlAnchor> links = new ArrayList<HtmlAnchor>();
			links = page.getByXPath("//a[@class='j_th_tit ']");
			for (int j = 0; j < links.size(); j++) {
				result.add(links.get(j).getHrefAttribute());
			}
		}
		System.out.println("***********************共获取" + result.size() + "条链接***********************");
		return result;
	}

	public void getrecords(List<String> list, String key, Date crawler_time) {
		// String timenow=TimeUtil.getCurrentUTCStringTime();
		long timenow = System.currentTimeMillis();
		HownetWordTendency hownetWordTendency = new HownetWordTendency();
		WebClient client = LinkLogin();
		List<String> result = new ArrayList<String>();
		List<Tiebadate> datas = new ArrayList<>();
		for (int i = 0; i < list.size() && i < 3; i++) {
			System.out.println("***********************正在获取第" + (i + 1) + "个帖子***********************");
			HtmlPage page = null;
			try {
				page = client.getPage("https://tieba.baidu.com" + list.get(i));
			} catch (FailingHttpStatusCodeException | IOException e) {
				// TODO Auto-generated catch block
				System.out.println("***********************获取第" + (i + 1) + "个帖子失败***********************");
				e.printStackTrace();
				continue;
			}

			if (page != null) {
				DomElement theme = null;
				try {
					theme = (DomElement) page.getByXPath("//div[@id='j_core_title_wrap']/h3").get(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					theme = null;
				}
				if(theme == null){
					try {
						theme = (DomElement) page.getByXPath("//h1[@class='core_title_txt  ']").get(0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						theme = null;
					}
					
				}
				List<HtmlElement> doms = page.getByXPath("//div[@class='l_post l_post_bright j_l_post clearfix  ']");
				if(doms.size() == 0){
					doms = page.getByXPath("//div[@class='l_post j_l_post l_post_bright  ']");
				}
				for (HtmlElement dom : doms) {
					Tiebadate data = new Tiebadate();
					DomText creater = (DomText) dom
							.getByXPath(dom.getCanonicalXPath() + "//li[@class='d_name']/a/text()").get(0);
					List<DomText> texts = dom.getByXPath(
							dom.getCanonicalXPath() + "//div[@class='d_post_content j_d_post_content ']/text()");
					if(texts.size() == 0){
						texts = dom.getByXPath(
								dom.getCanonicalXPath() + "//div[@class='d_post_content j_d_post_content  clearfix']/text()");
					}
					String text = "";
					for (DomText words : texts) {
						text += words;
					}
					String time;
					List<DomText> temp_time = dom
							.getByXPath(dom.getCanonicalXPath()
									+ "//div[@class='core_reply j_lzl_wrapper']//span[@class='tail-info'][2]/text()");
					if(temp_time.size() == 0){
						temp_time = dom
								.getByXPath(dom.getCanonicalXPath()
										+ "//ul[@class='p_tail']/li[1]/span/text()");
					}
					try {
						time = temp_time.get(0).asText();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						time = "";
					}
					data.setTitle(key);
					data.setCreater(creater.asText());
					if (theme != null) {
						data.setTheme(theme.asText());
					}
					data.setText(text);
					data.setTime(time);
					data.setDateshow(crawler_time);
					data.setTendency(hownetWordTendency.getTendency(text));
//					writeout.write(data.tostring(), 2);
					datas.add(data);
				}
			} else {
				continue;
			}
			System.out.println("***********************爬完第" + (i + 1) + "个帖子***********************");
		}
		System.out.println("***********************爬虫部分结束***********************");
		EsUtil.insert_generalization(datas);
	}

	public static void main(String[] args) throws InterruptedException {
		TiebaCrawler t = new TiebaCrawler();
		List<String> list = t.getlinks("中国");
		t.getrecords(list, "中国", new Date());
	}
}
