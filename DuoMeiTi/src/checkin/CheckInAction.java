package checkin;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import util.PageGetBaseAction;
import util.Util;
import model.CheckInRecord;

import com.opensymphony.xwork2.ActionContext;
import dao.CheckInRecordDao;
import dao.DAOFactory;

public class CheckInAction extends PageGetBaseAction{
	private String username;
	private int starthour;
	private int endhour;
	private int startminute;
	private int endminute;
	private String startTime;
	private String endTime;
	private int pagesize=10;
	private int page=1;
	private String newtablestring;
	private boolean isAM=false;//上午还是下午
	private boolean isPM=false;
	private List<CheckInRecord> recordlist;

	private String result;
	private final static int MAX_PAGESIZE = 100;//一页数据上限为100
	
	public List<CheckInRecord> getRecordlist() {
		return recordlist;
	}

	public void setRecordlist(List<CheckInRecord> recordlist) {
		this.recordlist = recordlist;
	}
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String checkIn()
	{
		try {
			 String role = (String) ActionContext.getContext().getSession().get("role");
			 if(role!=util.Const.StudentRole){
				 result="你不能签到";
			 }
			 username = (String) ServletActionContext.getContext().getSession().get("username");
			CheckInRecordDao bd = (CheckInRecordDao) DAOFactory.getDao(CheckInRecord.class);
			bd.checkIn(username);
			result="签到成功";
		} catch (IllegalAccessException | InstantiationException iae) {
			// TODO Auto-generated catch block
			//ise.printStackTrace();
			iae.printStackTrace();
			result="签到失败";
		} 
		return SUCCESS;
	}
	
	public String setCheckInRule()
	{
		boolean flag=false;
		System.out.println("starthour "+starthour+" endhour "+endhour+" startminute "+startminute+" endminute "+endminute);
		java.util.Date newStartTime = TimeUtil.getUtilDate(starthour, startminute);
		java.util.Date newEndTime = TimeUtil.getUtilDate(endhour,endminute);
		if(isAM)
		{
			flag = CheckInRule.SetAmTime(newStartTime, newEndTime);
		}
		else
		{
			flag = CheckInRule.SetPmTime(newStartTime, newEndTime);
		}
		if(flag)
			result = "修改成功";
		else {
			result="修改失败";
		}
		return SUCCESS;
	}

	public String InitRecordListByname()
	{
		username = (String) ServletActionContext.getContext().getSession().get("username");
		System.out.println("InitRecordListByname");
		getCheckInRecordByUsername();
		System.out.println("recordlist size "+recordlist.size());
		return SUCCESS;
	}
	
	public String getCheckInRecordByUsername()
	{
		String result=null;
		System.out.println(" getCheckInRecordByUsername");
		if(pagesize>MAX_PAGESIZE)pagesize=MAX_PAGESIZE;
		try {
			CheckInRecordDao bd = (CheckInRecordDao) DAOFactory.getDao(CheckInRecord.class);
			recordlist=bd.getRecordByUserName(username, page, pagesize);
			newtablestring = Util.getJspOutput("/jsp/admin/widgets/checkinrecordtable.jsp");
			result=SUCCESS;
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=ERROR;
		}
		return result;
	}
	
	public String InitRecordListByDate()
	{
		Calendar calendar = Calendar.getInstance();
		Timestamp endtime = TimeUtil.getCalendartoTimestamp(calendar);
		calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-7);
		Timestamp starttime = TimeUtil.getCalendartoTimestamp(calendar);
		CheckInRecordDao bd;
		try {
			bd = (CheckInRecordDao) DAOFactory.getDao(CheckInRecord.class);
			recordlist=bd.getRecordByDate(starttime, endtime,this.getCurrentPageNum(),10);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getNewtablestring() {
		return newtablestring;
	}

	public void setNewtablestring(String newtablestring) {
		this.newtablestring = newtablestring;
	}

	public String getCheckInRecordByDate()
	{
		if(pagesize>MAX_PAGESIZE)pagesize=MAX_PAGESIZE;
			try {
				CheckInRecordDao bd = (CheckInRecordDao) DAOFactory.getDao(CheckInRecord.class);
				Timestamp starttime = TimeUtil.StringtoTimestamp(startTime);
				Timestamp endtime = TimeUtil.StringtoTimestamp(endTime);
				recordlist=bd.getRecordByDate(starttime, endtime, this.getCurrentPageNum(), pagesize);
				newtablestring = Util.getJspOutput("/jsp/admin/widgets/checkinrecordtable.jsp");
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	public int getEndminute() {
		return endminute;
	}

	public void setEdnminute(int endminute) {
		this.endminute = endminute;
	}

	public boolean getIsAM() {
		return isAM;
	}

	public boolean getIsPM() {
		return isPM;
	}

	public void setIsAM(boolean isAM) {
		this.isAM = isAM;
	}

	public void setIsPM(boolean isPM) {
		this.isPM = isPM;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
