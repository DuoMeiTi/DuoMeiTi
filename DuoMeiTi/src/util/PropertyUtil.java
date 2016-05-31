package util;

//import java.io.IOException;
//
//import java.io.InputStream;
//import java.util.Properties;
//public class PropertyUtil {
//	private static Properties p = new Properties() ;
//	private static InputStream inputStream = null ;
//	static {
//	 inputStream = PropertyUtil.class.getResourceAsStream("/page.properties")  ;
//	 try {
//		p.load(inputStream) ;
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//	}
//	public static int getPageSize(){
//		return Integer.parseInt(p.getProperty("pageSize"));
//	}
//	public static int getPageNumber(){
//		return Integer.parseInt(p.getProperty("pageNumber"));
//	}
//	public static String getDownloadPath(){
//		return p.getProperty("downloadPath");
//	}
//	public static void main(String[] args) {
//		 System.out.println(getPageSize());
//	}
//	
//}