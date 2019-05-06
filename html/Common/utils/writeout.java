package html.Common.utils;

import java.io.FileOutputStream;

public class writeout {
	public static int write(String str,int i){
		 try {
	            FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\crawler\\test"+ i +".txt",true);
	            String s = str;
	            fos.write(s.getBytes());
	            fos.close();
	            return 1;
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return 0;
	        }
	}
	public static int write_2(String str){
		 try {
	            FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test2.txt");
	            String s = str;
	            fos.write(s.getBytes());
	            fos.close();
	            return 1;
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return 0;
	        }
	}
	public static void main(String[] args) throws InterruptedException {
		String s="\r\n\r\n\r\n\r\n";
		writeout.write_2(s.replaceAll("(\r\n)+", "\r\n"));
	}
}
