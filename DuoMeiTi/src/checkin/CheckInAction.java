package checkin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
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


/*
 * 此类已经被弃用！！！
 */
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
	private String amCheckIn;
	private String pmCheckIn;
	
	private java.util.Date sTime;
	private java.util.Date eTime;
	
	private String checkInRecordExcelPath;
	private String stuName;
	
	private static ArrayList<Integer> getDutyPieceOfStu(Calendar calendar) {
		int studentId = (int) ActionContext.getContext().getSession().get("student_id");
		System.out.println("studentId:" + studentId);
		Session session = model.Util.sessionFactory.openSession();
		List<model.DutySchedule> L = 
				(List<model.DutySchedule>)session.createCriteria(model.DutySchedule.class)
				.add(Restrictions.eq("student.id", studentId))
				.list();


		ArrayList<Integer> pieceList = new ArrayList<Integer>();
		int curdayofweek = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;
		System.out.println("*************************"+curdayofweek + " " + CheckInRule.getCheckInTime());
		for(model.DutySchedule dp: L)
		{
			System.out.println(".." + dp.getDutyPiece().getTime());
			if(Util.getWeekFromDutyPieceTime(dp.getDutyPiece().getTime()) == curdayofweek) {
				
				pieceList.add(Util.getPeriodFromDutyPieceTime(dp.getDutyPiece().getTime()));
			}
		}
		return pieceList;
	}
	
	
	public String checkIn()
	{
		try {
			 String role = (String) ActionContext.getContext().getSession().get("role");
			 
			 Calendar calendar = Calendar.getInstance();
			 
			 ArrayList<Integer> pieceList = getDutyPieceOfStu(calendar);
			 
			 final List<java.time.LocalTime> dutyPeriodBeginList = Util.dutyPeriodBeginList;
			 System.out.println("==========================");
			 for(Integer i : pieceList) {
				 System.out.println(dutyPeriodBeginList.get(i).toString());
			 }
			 System.out.println("==========================");
			 java.time.LocalDateTime peroid;
			 
			 //检查当前时间是否可以签到
			 if((peroid = CheckInRule.isCheckInTime(calendar, pieceList)) == null){
				 result="当前时间不能签到";
				 System.out.println("1111111");
				 return SUCCESS;
			 }
			 
			 //检查之前是否已经签过了
//			 int hour = calendar.get(Calendar.HOUR_OF_DAY);
//			 int minute = calendar.get(Calendar.MINUTE);
//			 Timestamp starttime=null;
//			 Timestamp nowtime=null;
//			
//			 if(hour>=12){
//				 Time time = CheckInRule.getAmStartTime();
//				  starttime = TimeUtil.getTimestamp(time.hour,time.minute);
//				  nowtime = TimeUtil.getNowTimestamp();
//			 }
//			 else{
//				 Time time = CheckInRule.getPmStartTime();
//				 starttime = TimeUtil.getTimestamp(time.hour,time.minute);
//				 nowtime = TimeUtil.getNowTimestamp();
//			 }
			 ZoneId zone = ZoneId.systemDefault();
			 int times = getCheckInRecordByIdandTime(new Timestamp(peroid.minusHours(CheckInRule.getCheckInTime()).atZone(zone).toInstant().toEpochMilli()),
					 									new Timestamp(peroid.atZone(zone).toInstant().toEpochMilli()));
			
			 if(times>0){
				 result = "签到一次就够了";
				 System.out.println("222222");
				 return SUCCESS;
			 }
			 System.out.println("cao ni mei a");
			username = (String) ServletActionContext.getContext().getSession().get("username");
			CheckInRecordDao bd = (CheckInRecordDao) DAOFactory.getDao(CheckInRecord.class);
			bd.checkIn(username);
			result="签到成功";
		} catch (Exception iae) {
			// TODO Auto-generated catch block
			//ise.printStackTrace();
			iae.printStackTrace();
			System.out.println("444444");
			result="签到失败";
			return SUCCESS;
		} 
		System.out.println("333333");
		return SUCCESS;
	}
	
	public int getCheckInRecordByIdandTime(Timestamp starttime,Timestamp endtime){
		Session session = model.Util.sessionFactory.openSession();
		
		int studentId = (int) ActionContext.getContext().getSession().get("student_id");
		Criteria criteria = session.createCriteria(CheckInRecord.class).add(Restrictions.eq("student.id",studentId ));
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
		if(stuName != null && !stuName.equals("")) {
			criteria
			.createAlias("student", "student")
			.createAlias("student.user", "user")
			.add(Restrictions.eq("user.fullName", stuName));
		}
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
	
		
	public String exportExcel() {
		System.out.println("cao");
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("签到记录");
		HSSFRow row = sheet.createRow((int) 0);  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell(0, Cell.CELL_TYPE_STRING);  
        cell.setCellValue("姓名");  
        cell.setCellStyle(style);  
        cell = row.createCell(1, Cell.CELL_TYPE_STRING);  
        cell.setCellValue("学号");  
        cell.setCellStyle(style);  
        cell = row.createCell(2, Cell.CELL_TYPE_STRING);  
        cell.setCellValue("签到时间");  
        cell.setCellStyle(style);
        System.out.println("*****************" + sTime + " "  + eTime);
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(CheckInRecord.class);
		c
		.add(Restrictions.between("recordtime", new Timestamp(sTime.getTime()), new Timestamp(eTime.getTime())))
		.addOrder(Order.desc("recordtime"));
		if(stuName != null && !stuName.equals("")) {
			c
			.createAlias("student", "student")
			.createAlias("student.user", "user")
			.add(Restrictions.eq("user.fullName", stuName));
		}
        
        List<CheckInRecord> L = c.list();
        System.out.println("==================="+L.size() + " " + stuName);
		for(int i = 0; i < L.size(); ++i) {
			System.out.println(L.get(i));
			row = sheet.createRow((int) i + 1);  
			CheckInRecord cr = (CheckInRecord) L.get(i);
            row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(cr.getStudent().getUser().getFullName());  
            row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(cr.getStudent().getStudentId());  
            row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(cr.getRecordtime().getTime())));
		}
		
		//获取当前时间，命名照片，防止照片重复
		java.util.Date date = new java.util.Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddHHmmss");
		String curdate = simpleDateFormat.format(date);
		String fileName = stuName + "_" + curdate + "签到记录.xls";
		
		checkInRecordExcelPath = util.Util.CheckInExcelExportPath + fileName;
		
		try  
        {
			
            FileOutputStream fout = new FileOutputStream(util.Util.RootPath + util.Util.CheckInExcelExportPath + fileName);  
            wb.write(fout);  
            fout.close();  
        }
		catch (Exception e)  
        {  
			checkInRecordExcelPath = "error";
            e.printStackTrace();
        }
		finally
		{
			try {
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	
	
	public boolean getAM() {
		return AM;
	}

	public boolean getPM() {
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

	public String getAmCheckIn() {
		Time startTime = CheckInRule.getAmStartTime();
		Time endTime = CheckInRule.getAmEndTime();
		amCheckIn = startTime.getHours()+":"+startTime.getMinutes()+" - "+endTime.hour+":"+endTime.minute;
		return amCheckIn;
	}

	public String getPmCheckIn() {
		Time startTime = CheckInRule.getPmStartTime();
		Time endTime = CheckInRule.getPmEndTime();
		pmCheckIn = startTime.getHours()+":"+startTime.getMinutes()+" - "+endTime.hour+":"+endTime.minute;
		return pmCheckIn;
	}

	public void setAmCheckIn(String amCheckIn) {
		this.amCheckIn = amCheckIn;
	}

	public void setPmCheckIn(String pmCheckIn) {
		this.pmCheckIn = pmCheckIn;
	}


	public String getCheckInRecordExcelPath() {
		return checkInRecordExcelPath;
	}


	public void setCheckInRecordExcelPath(String checkInRecordExcelPath) {
		this.checkInRecordExcelPath = checkInRecordExcelPath;
	}


	public String getStuName() {
		return stuName;
	}


	public void setStuName(String stuName) {
		this.stuName = stuName;
	}


	public java.util.Date getSTime() {
		return sTime;
	}


	public void setSTime(java.util.Date sTime) {
		this.sTime = sTime;
	}


	public java.util.Date getETime() {
		return eTime;
	}


	public void setETime(java.util.Date eTime) {
		this.eTime = eTime;
	}



}
