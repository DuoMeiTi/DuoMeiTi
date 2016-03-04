package checkin;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import util.PageGetBaseAction;
import util.Util;
import model.CheckInRecord;
import model.StudentProfile;
import checkin.CheckInRule.Time;

import com.opensymphony.xwork2.ActionContext;

import dao.CheckInRecordDao;
import dao.DAOFactory;

public class CheckInAction extends PageGetBaseAction{
	private String username = "";
	private boolean query=false;
	private int starthour;
	private int endhour;
	private int startminute;
	private int endminute;
	private String startTime="";
	private String endTime="";
	private int pagesize=10;
	private String newtablestring="";
	private boolean AM=false;//上午还是下午
	private boolean PM=false;
	private List recordlist;
	private String result="";
	private final static int MAX_PAGESIZE = 100;//一页数据上限为100

	public String checkIn()
	{
		try {
			 String role = (String) ActionContext.getContext().getSession().get("role");
			 
			 if(role!=util.Const.StudentRole){
				 result="你不能签到";
				 return SUCCESS;
			 }
			 
			 Calendar calendar = Calendar.getInstance();
			 
			 //检查当前时间是否可以签到
			 if(!CheckInRule.isCheckInTime(calendar)){
				 result="当前时间不能签到";
				 return SUCCESS;
			 }
			 
			 //检查之前是否已经签过了
			 int hour = calendar.get(Calendar.HOUR_OF_DAY);
			 int minute = calendar.get(Calendar.MINUTE);
			 Timestamp starttime=null;
			 Timestamp nowtime=null;
			 if(hour>=12){
				 Time time = CheckInRule.getAmStartTime();
				  starttime = TimeUtil.getTimestamp(time.hour,time.minute);
				  nowtime = TimeUtil.getNowTimestamp();
			 }
			 else{
				 Time time = CheckInRule.getPmStartTime();
				 starttime = TimeUtil.getTimestamp(time.hour,time.minute);
				 nowtime = TimeUtil.getNowTimestamp();
			 }
			 int times = getCheckInRecordByIdandTime(starttime,nowtime);
			 if(times>0){
				 result = "签到一次就够了";
				 return SUCCESS;
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
	
	public int getCheckInRecordByIdandTime(Timestamp starttime,Timestamp endtime){
		Session session = model.Util.sessionFactory.openSession();
		model.User user = (model.User)session.createCriteria(model.User.class)
				 .add(Restrictions.eq("username", username)).uniqueResult();
		StudentProfile student =(StudentProfile) session.createCriteria(model.StudentProfile.class)
				 .add(Restrictions.eq("user.id", user.id))
				 .uniqueResult();
		Criteria criteria = session.createCriteria(CheckInRecord.class).add(Restrictions.eq("student.id",student.id ));
		criteria.add(Restrictions.between("recordtime", starttime, endtime));
		int times = criteria.list().size();
		session.close();
		return times;
	}
	
	public String setCheckInRule()
	{
		boolean flag=false;
		System.out.println("starthour "+starthour+" endhour "+endhour+" startminute "+startminute+" endminute "+endminute);
		java.util.Date newStartTime = TimeUtil.getUtilDate(starthour, startminute);
		java.util.Date newEndTime = TimeUtil.getUtilDate(endhour,endminute);
		if(AM)
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


	

	public String getCheckInRecordByUsername() throws Exception
	{
		String username =(String)ActionContext.getContext().getSession().get("username");
		Session session = model.Util.sessionFactory.openSession();
		
		model.User user = (model.User)session.createCriteria(model.User.class)
		 .add(Restrictions.eq("username", username)).uniqueResult();
		StudentProfile student =(StudentProfile) session.createCriteria(model.StudentProfile.class)
								 .add(Restrictions.eq("user.id", user.id))
								 .uniqueResult();
		Criteria criteria = session.createCriteria(CheckInRecord.class).add(Restrictions.eq("student.id",student.id ));
		
		criteria.addOrder(Order.desc("recordtime"));
		
		recordlist = this.makeCurrentPageList(criteria, 10);
		if(this.getIsAjaxTransmission())
		{
			newtablestring = util.Util.getJspOutput("/jsp/student/studentcheckintable.jsp");
			session.close();
			if(!query)
				return "getPage";
			else
				return SUCCESS;
		}
		session.close();
		return SUCCESS;
	}
	
	public boolean isQuery() {
		return query;
	}

	public void setQuery(boolean query) {
		this.query = query;
	}

	public String getCheckInRecordByDate() throws InstantiationException, IllegalAccessException
	{
		Timestamp starttime=null;
		Timestamp endtime=null;
		if(pagesize>MAX_PAGESIZE)pagesize=MAX_PAGESIZE;
		if(startTime==null||startTime.isEmpty()||endTime==null||endTime.isEmpty())
		{
			System.out.println("HEHEHEHEHEE");
			Calendar calendar = Calendar.getInstance();
			endtime = TimeUtil.getCalendartoTimestamp(calendar);
			calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-7);
			starttime = TimeUtil.getCalendartoTimestamp(calendar);
		}
		else
		{
			String newstarttime=startTime.replaceAll("/","-")+" 00:00:00";
			String newendtime = endTime.replace("/","-") + " 23:00:00";
			starttime = TimeUtil.StringtoTimestamp(newstarttime);
			endtime = TimeUtil.StringtoTimestamp(newendtime);
		}
		Session session = model.Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(CheckInRecord.class);
		criteria.add(Restrictions.between("recordtime", starttime, endtime));
		criteria.addOrder(Order.desc("recordtime"));
		System.out.println("starttime "+starttime.toString()+" endtime "+endtime.toString());
		recordlist = this.makeCurrentPageList(criteria,10);
		session.close();
		if(this.getIsAjaxTransmission())
		{
			newtablestring = Util.getJspOutput("/jsp/admin/widgets/checkinrecordtable.jsp");
			System.out.println(newtablestring);
			if(!query)
				return "getPage";
		}
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public int getStarthour() {
		return starthour;
	}

	public int getEndhour() {
		return endhour;
	}

	public int getStartminute() {
		return startminute;
	}

	public int getEndminute() {
		return endminute;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public int getPagesize() {
		return pagesize;
	}

	public String getNewtablestring() {
		return newtablestring;
	}

	public List getRecordlist() {
		return recordlist;
	}

	public String getResult() {
		return result;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setStarthour(int starthour) {
		this.starthour = starthour;
	}

	public void setEndhour(int endhour) {
		this.endhour = endhour;
	}

	public void setStartminute(int startminute) {
		this.startminute = startminute;
	}

	public void setEndminute(int endminute) {
		this.endminute = endminute;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public void setNewtablestring(String newtablestring) {
		this.newtablestring = newtablestring;
	}

	
	
	public boolean AM() {
		return AM;
	}

	public boolean PM() {
		return PM;
	}

	public static int getMaxPagesize() {
		return MAX_PAGESIZE;
	}

	public void setAM(boolean AM) {
		this.AM = AM;
	}

	public void setPM(boolean PM) {
		this.PM = PM;
	}

	public void setRecordlist(List recordlist) {
		this.recordlist = recordlist;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
