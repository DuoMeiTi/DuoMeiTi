package checkin;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;


@SuppressWarnings("deprecation")
public class CheckInRule {
	private static Date amStartTime=new Date();
	private static Date amEndTime=new Date();
	private static Date pmStartTime=new Date();
	private static Date pmEndTime=new Date();
	private static final int amDeadLine=12;//上午签到时间务必设置在中午12点之前
	private static final int pmDeadLine=23;//晚上签到时间务必设置在晚上11点之前
	private static final String FILENAME = "checkinrule.properties";
	private static final String[] KEY = {"amStartTime_hour","amStartTime_minute","amEndTime_hour","amEndTime_minute",
		"pmStartTime_hour","pmStartTime_minute","pmEndTime_hour","pmEndTime_mintue"};
	
	private CheckInRule(){}
	
	static{
		int[] time = new int[KEY.length];
		Properties prop =  new  Properties();    
		InputStream in = Object.class.getClass().getResourceAsStream(FILENAME);
		try {
			prop.load(in);
			for(int i=0;i<KEY.length;i++)
			{
				time[i]=(int) prop.get(KEY[i]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			amStartTime.setHours(time[0]);
			amStartTime.setMinutes(time[1]);
			amEndTime.setHours(time[2]);
			amEndTime.setMinutes(time[3]);
			pmStartTime.setHours(time[4]);
			pmStartTime.setMinutes(time[5]);
			pmEndTime.setHours(time[6]);
			pmEndTime.setMinutes(time[7]);
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static Date getAmStartTime() {
		Date time =  (Date) amStartTime.clone();
		return time;
	}

	public static Date getAmEndTime() {
		Date date =  (Date) amEndTime.clone();
		return date;
	}
	
	public static Date getPmStartTime() {
		Date time =  (Date) pmStartTime.clone();
		return time;
	}
	
	public static Date getPmEndTime() {
		Date time =  (Date) pmEndTime.clone();
		return time;
	}
	
	public static synchronized boolean SetAmTime(Date newStartTime,Date newEndTime)
	{
		if(newStartTime.getHours()>=amDeadLine||newEndTime.getHours()>=amDeadLine)
		{
			return false;
		}
		if(newStartTime.before(newEndTime))
		{
			amStartTime=(Date) newStartTime.clone();
			amEndTime=(Date) newEndTime.clone();
			setAmTime();
			return true;
		}
		return false;
	}
	
	public static synchronized boolean SetPmTime(Date newStartTime,Date newEndTime){
		if(newStartTime.getHours()>=pmDeadLine||newEndTime.getHours()>=pmDeadLine)
		{
			return false;
		}
		if(newStartTime.before(newEndTime))
		{
			pmStartTime=(Date) newStartTime.clone();
			pmEndTime=(Date) newEndTime.clone();
			setPmTime();
			return true;
		}
		return false;
	}
	
	private static void recoveryFile()
	{
		Path p = Paths.get(FILENAME);
		if(!Files.exists(p))
		{
			try {
				p=Files.createFile(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void setAmTime()
	{
		Properties pro = new Properties();
		OutputStream fos=null;
		try {
			fos = new FileOutputStream(FILENAME);
			pro.setProperty(KEY[0], String.valueOf(amStartTime.getHours()));
			pro.setProperty(KEY[1], String.valueOf(amStartTime.getMinutes()));
			pro.setProperty(KEY[2],String.valueOf(amEndTime.getHours()));
			pro.setProperty(KEY[3], String.valueOf(amEndTime.getMinutes()));
			pro.store(fos, "update pmTime");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			recoveryFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fos!=null)
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	private static void setPmTime()
	{
		Properties pro = new Properties();
		OutputStream fos=null;
		try {
			fos = new FileOutputStream(FILENAME);
			pro.setProperty(KEY[4], String.valueOf(pmStartTime.getHours()));
			pro.setProperty(KEY[5], String.valueOf(pmStartTime.getMinutes()));
			pro.setProperty(KEY[6],String.valueOf(pmEndTime.getHours()));
			pro.setProperty(KEY[7], String.valueOf(pmEndTime.getMinutes()));
			pro.store(fos, "update amTime");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			recoveryFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fos!=null)
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
