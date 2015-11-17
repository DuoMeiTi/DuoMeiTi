package checkin;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {
	static public Date getSqlDate(int hour,int minute)
	{
		Calendar canlendar = Calendar.getInstance();
		canlendar.set(Calendar.HOUR_OF_DAY, hour);
		canlendar.set(Calendar.MINUTE, minute);
		Date time = new java.sql.Date(canlendar.getTime().getTime());
		return time;
	}
	
	static public java.util.Date getUtilDate(int hour,int minute)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		return (java.util.Date) calendar.getTime();
	}
	
	static public Date StringtoSqlDate(String str)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = (java.sql.Date) formatter.parse(str, pos);
		return strtodate;
	}
	
	static public Timestamp getCalendartoTimestamp(Calendar ca)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(ca.getTime());
		System.out.println(str);
		Timestamp ts = Timestamp.valueOf(str+" 00:00:00");
		return ts;
	}
	
	static public Timestamp StringtoTimestamp(String str)
	{
		Timestamp time = Timestamp.valueOf(str);
		return time;
	}
	
	
}
