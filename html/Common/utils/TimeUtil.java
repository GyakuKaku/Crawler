package html.Common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    /**
     * 取得当前时间UTC时间（即标准时区时间）
     * @return
     */
    public static String getCurrentUTCStringTime() {
        SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTimeMills = System.currentTimeMillis();
        String utcTimeString = utcFormat.format(new Date(currentTimeMills));
        return utcTimeString;
    }
    
    public static String getPastDate(int past) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);  
        Date today = calendar.getTime();  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String result = format.format(today);  
        result += " 00:00:00";
        return result;  
    }  
    
    public static ArrayList<String> getweeks() {  
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = -1; i < 7; i++) {  
            pastDaysList.add(getPastDate(i));  
        }  
        return pastDaysList;  
    } 
}
