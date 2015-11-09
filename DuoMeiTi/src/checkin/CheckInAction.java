package checkin;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import model.CheckInRecord;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.CheckInRecordDao;
import dao.DAOFactory;

public class CheckInAction extends ActionSupport{
	private String username;
	private int starthour;
	private int endhour;
	private int startminute;
	private int ednminute;
	private String AMorPM;//上午还是下午
	private final static String AM="am";
	private final static String PM="pm";
	private List<CheckInRecord> list;
	
	private final static int MAX_PAGESIZE = 100;//一页数据上限为100条
	
	public String checkIn()
	{
		try {
			 String role = (String) ActionContext.getContext().getSession().get("role");
			 if(role!=util.Const.StudentRole)return "你不能签到";
			CheckInRecordDao bd = (CheckInRecordDao) DAOFactory.getDao(CheckInRecord.class);
			bd.checkIn(username);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String setCheckInRule()
	{
		boolean result=false;
		Calendar canlendar = Calendar.getInstance();
		Date newStartTime = (Date) canlendar.getTime();
		Date newEndTime = (Date) canlendar.getTime();
		newStartTime.setHours(starthour);
		newStartTime.setMinutes(startminute);
		newEndTime.setHours(endhour);
		newEndTime.setMinutes(ednminute);
		if(AMorPM.equals(AM))
		{
			result = CheckInRule.SetAmTime(newStartTime, newEndTime);
		}
		else if(AMorPM.equals(PM))
		{
			result = CheckInRule.SetPmTime(newStartTime, newEndTime);
		}
		if(result)
		return SUCCESS;
		else return ERROR;
	}

	public String getCheckInRecordByUsername(String username,int page,int pagesize)
	{
		String result=null;
		if(pagesize>MAX_PAGESIZE)pagesize=MAX_PAGESIZE;
		try {
			CheckInRecordDao bd = (CheckInRecordDao) DAOFactory.getDao(CheckInRecord.class);
			list=bd.getRecordByUserName(username, page, pagesize);
			result=SUCCESS;
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=ERROR;
		}
		return result;
	}
	
	public String getCheckInRecordByDate(int page,int pagesize)
	{
		if(pagesize>MAX_PAGESIZE)pagesize=MAX_PAGESIZE;
		try{
			try {
				CheckInRecordDao bd = (CheckInRecordDao) DAOFactory.getDao(CheckInRecord.class);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	list = bd.getRecordByDate(starttime, endtime, page, pagesize)
		}finally{
			
		}
		return SUCCESS;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getStarthour() {
		return starthour;
	}

	public void setStarthour(int starthour) {
		this.starthour = starthour;
	}

	public int getEndhour() {
		return endhour;
	}

	public void setEndhour(int endhour) {
		this.endhour = endhour;
	}

	public int getStartminute() {
		return startminute;
	}

	public void setStartminute(int startminute) {
		this.startminute = startminute;
	}

	public String getAMorPM() {
		return AMorPM;
	}

	public void setAMorPM(String aMorPM) {
		AMorPM = aMorPM;
	}
	
	public int getEdnminute() {
		return ednminute;
	}

	public void setEdnminute(int ednminute) {
		this.ednminute = ednminute;
	}
}
