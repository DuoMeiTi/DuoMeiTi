package checkin;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class CheckInRule {
	static class Time{
		int hour;
		int minute;
		void setHours(int hour){
			this.hour=hour;
		}
		int getHours(){
			return hour;
		}
		void setMinutes(int min){
			minute=min;
		}
		int getMinutes(){
			return minute;
		}
		protected Time clone()
		{
			Time t = new Time();
			t.hour=this.hour;
			t.minute=this.minute;
			return t;
		}
	}
	private static Time amStartTime=new Time();
	private static Time amEndTime=new Time();
	private static Time pmStartTime=new Time();
	private static Time pmEndTime=new Time();
	private static final int amDeadLine=12;//上午签到时间务必设置在中午12点之前
	private static final int pmDeadLine=23;//晚上签到时间务必设置在晚上11点之前
	private static final String FILENAME = "checkinrule.properties";
	private static final String[] KEY = {"amStartTime_hour","amStartTime_minute","amEndTime_hour","amEndTime_minute",
		"pmStartTime_hour","pmStartTime_minute","pmEndTime_hour","pmEndTime_mintue"};
	
	static{
		System.out.println("CheckInRule init");
		int[] time = new int[8];
		Properties prop =  new  Properties();    
		InputStream in = CheckInRule.class.getResourceAsStream(FILENAME);
		if(in==null){
			System.out.println("InputStream null");
		}
		try {
			prop.load(in);
			for(int i=0;i<8;i++)
			{
				time[i]=Integer.valueOf((String) prop.get(KEY[i]));
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
				if(in!=null)in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static Time getAmStartTime() {
		Time time = (Time) amStartTime.clone();
		return time;
	}

	public static Time getAmEndTime() {
		Time date =  (Time) amEndTime.clone();
		return date;
	}
	
	public static Time getPmStartTime() {
		Time time =  (Time) pmStartTime.clone();
		return time;
	}
	
	public static Time getPmEndTime() {
		Time time =  (Time) pmEndTime.clone();
		return time;
	}
	
	public static boolean isCheckInTime(Calendar ca){
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		Date startTime;
		Date endTime;
		if(hour <= 12)
		{
			startTime = TimeUtil.getSqlDate(amStartTime.hour, amStartTime.minute);
			endTime = TimeUtil.getSqlDate(amEndTime.hour, amEndTime.minute);
		}
		else
		{
			startTime = TimeUtil.getSqlDate(pmStartTime.hour, pmStartTime.minute);
			endTime = TimeUtil.getSqlDate(pmEndTime.hour, pmEndTime.minute);
		}
		if(ca.after(startTime)&&ca.before(endTime))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static synchronized boolean SetAmTime(Date newStartTime,Date newEndTime)
	{
		Calendar cstart = Calendar.getInstance();
		cstart.setTime(newStartTime);
		Calendar cend = Calendar.getInstance();
		cend.setTime(newEndTime);
		if(cend.get(Calendar.HOUR_OF_DAY)>amDeadLine||cstart.get(Calendar.HOUR_OF_DAY)>amDeadLine)
		{
			return false;
		}
		if(newStartTime.before(newEndTime))
		{
			amStartTime.hour = cstart.get(Calendar.HOUR_OF_DAY);
			amStartTime.minute = cstart.get(Calendar.MINUTE);
			amEndTime.hour = cend.get(Calendar.HOUR_OF_DAY);
			amEndTime.minute = cend.get(Calendar.MINUTE);
			try {
				saveAmTime();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
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
			pmStartTime.hour=newStartTime.getHours();
			pmEndTime.minute= newEndTime.getMinutes();
			try {
				savePmTime();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
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
	
	private static void saveAmTime() throws IOException
	{
		Properties pro = new Properties();
		InputStream in = CheckInRule.class.getResourceAsStream(FILENAME);
		pro.load(in);
		in.close();
		OutputStream fos=null;
		try {
			String path = CheckInRule.class.getResource(FILENAME).getPath();
			if(path==null)path="FILENAME isnull";
			System.out.println(path);
			fos = new FileOutputStream(CheckInRule.class.getResource(FILENAME).getPath());
			pro.setProperty(KEY[0], String.valueOf(amStartTime.getHours()));
			pro.setProperty(KEY[1], String.valueOf(amStartTime.getMinutes()));
			pro.setProperty(KEY[2],String.valueOf(amEndTime.getHours()));
			pro.setProperty(KEY[3], String.valueOf(amEndTime.getMinutes()));
			System.out.println("hehe");
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
	
	private static void savePmTime() throws IOException
	{
		Properties pro = new Properties();
		InputStream in = CheckInRule.class.getResourceAsStream(FILENAME);
		pro.load(in);
		in.close();
		OutputStream fos=null;
		try {
			fos = new FileOutputStream(CheckInRule.class.getClassLoader().getResource(FILENAME).getPath());
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
