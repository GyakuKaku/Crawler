package html.Common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
/**
 * 正则表达式匹配两个字符串之间的内容
 * @author Administrator
 *
 */
public class RegexUtil {
	
	public static void main(String[] args) {
		String str = "萨达瓦达瓦大青蛙放弃加分";  
		String rgex = "加分";
		
//	    System.out.println((new RegexUtil()).getSubUtil(str,rgex)); 
//	    List<String> lists = (new RegexUtil()).getSubUtil(str,rgex);
//	    for (String string : lists) {
//			System.out.println(string);
//		}
	    System.out.println(getSumUtil(str, rgex));  
	}
	
	/** 
     * 正则表达式匹配两个指定字符串中间的内容 
     * @param soap 
     * @return 
     */  
    public static List<String> getSubUtil(String soap,String rgex){  
        List<String> list = new ArrayList<String>();  
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式  
        Matcher m = pattern.matcher(soap);  
        while (m.find()) {  
            int i = 1;  
            list.add(m.group(i));  
            i++;  
        }  
        return list;  
    }  
     
	/** 
     * 正则表达式匹配两个指定字符串中间的个数
     * @param soap 
     * @return 
     */  
    public static int getSumUtil(String soap,String rgex){  
    	int i=0;
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式  
        Matcher m = pattern.matcher(soap);  
        while (m.find()) {    
        	m.group();
            i++;  
        }  
        return i;  
    }  
    
    /** 
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样 
     * @param soap 
     * @param rgex 
     * @return 
     */  
    public static String getSubUtilSimple(String soap,String rgex){  
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式  
        Matcher m = pattern.matcher(soap);  
        while(m.find()){  
            return m.group(1);  
        }  
        return "";  
    }  
}
