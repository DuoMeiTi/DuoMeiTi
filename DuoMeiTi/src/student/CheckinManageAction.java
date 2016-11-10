package student;



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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//import checkin.TimeUtil;
import model.CheckInRecord;
import model.DutySchedule;
import model.StudentProfile;
import util.Util;




public class CheckinManageAction extends ActionSupport{
	
	
	/**
	 * 确定签到时间{@code now}对应哪个值班时间段，范围为0~4，对应5个值班时间段；
	 * 返回-1表示没有对应的值班时间段
	 */
	public static int getCloseInDutyPeriodId(java.util.Date now)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		
		java.time.LocalTime cnt = java.time.LocalTime.of(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
		int period = -1;
		for(int i = 0; i < util.Util.dutyCheckinPeriodBeginList.size(); i++)
		{			
			java.time.LocalTime beg = util.Util.dutyCheckinPeriodBeginList.get(i);
			java.time.LocalTime end = util.Util.dutyCheckinPeriodEndList.get(i);			
			if(cnt.isAfter(beg) && cnt.isBefore(end)) 
			{
				period = i; break;
			}
		}
		return period;
	}
	/**
	 * 确定值班签到签到时间{@code now}对应的Duty Piece Time. 0~34, 一共有35个Duty Piece Time. 
	 * 如果没有对应的Duty Piece Time 返回-1
	 * */
	public static int makeCloseInDutyTime(java.util.Date now)
	{		
		int week = util.Util.getDayOfWeek(now);
		int period = getCloseInDutyPeriodId(now);
		
		if(period == -1) return -1;
		return util.Util.makeDutyTime(week, period);
	}
	
	private static boolean isSameDate(java.util.Date date1, java.util.Date date2) 
	{
	       Calendar cal1 = Calendar.getInstance();
	       cal1.setTime(date1);

	       Calendar cal2 = Calendar.getInstance();
	       cal2.setTime(date2);

	       boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
	               .get(Calendar.YEAR);
	       boolean isSameMonth = isSameYear
	               && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
	       boolean isSameDate = isSameMonth
	               && cal1.get(Calendar.DAY_OF_MONTH) == cal2
	                       .get(Calendar.DAY_OF_MONTH);

	       return isSameDate;
	}
	
	/** 
	 * 检查{@code now}是否可以签到.
	 * 返回值分为标识码和相应信息
	 * 标志码为0表示可以签到，其余表示不可以签到
	 */
	static String checkCanCheckin(Session s, java.util.Date now)
	{
		
		int student_id =(int) ActionContext.getContext().getSession().get("student_id" );
//		Session s = model.Util.sessionFactory.openSession();
		
		List checkinRecordList = 
				s.createCriteria(CheckInRecord.class)
				.add(Restrictions.eq("student.id", student_id))
				.addOrder(Order.desc("id"))
				.list();
		int currentPeriodId = getCloseInDutyPeriodId(now);
		
		System.out.println("=========--------");
		System.out.println(currentPeriodId);
		// 0 表示可以签到
		String status = "0";
		
		
		// 当前时间不能签到
		if(currentPeriodId == -1)
		{
			status = "1当前时间不能签到";
		}
		else 
		{
			if(!checkinRecordList.isEmpty())
			{
				CheckInRecord checkInRecord = (CheckInRecord)checkinRecordList.get(0);
				
				if(isSameDate(now, checkInRecord.recordtime) &&
						currentPeriodId == getCloseInDutyPeriodId(checkInRecord.recordtime))
				{
					// 已经签过了
					status = "2值班时间段" + util.Util.dutyPeriodList.get(currentPeriodId) + "的已经签过了";
				}
			}
			
			if(status.charAt(0) == '0')
			{
				int cntTime = makeCloseInDutyTime(now);
				
				System.out.println("+++++++++LZ:::" + cntTime);
				List ds_list = (List)
								s.createCriteria(model.DutySchedule.class)
								.add(Restrictions.eq("student.id", student_id))
								.createAlias("dutyPiece", "dutyPiece")
								.add(Restrictions.eq("dutyPiece.time", cntTime))
								.list();
				// 当前学生选择了此时间段的选班，可以进行签到
				if(ds_list.size() > 0)
				{
					status = "0对于值班时间段"
								+ util.Util.dutyPeriodList.get(currentPeriodId) 
								+"进行签到";
				}
				else
				{
					// 当前学生没有选择此时间段的选班，不可以进行签到
					status="3当前学生没有选择" 
							+ util.Util.dutyPeriodList.get(currentPeriodId) 
							+ "时间段的选班，不可以进行签到";
				}
			}
			
		}
		return status;
	}
	
	String status;
	List checkinRecordList;
	public String execute() throws Exception	
	{
		Session s = model.Util.sessionFactory.openSession();
		status = checkCanCheckin(s, new java.util.Date());
		
		int student_id = (int) ActionContext.getContext().getSession().get("student_id" );
		
		checkinRecordList = 
				s.createCriteria(CheckInRecord.class)
				.add(Restrictions.eq("student.id", student_id))
				.addOrder(Order.desc("id"))
				.list();
		s.close();		
		return ActionSupport.SUCCESS;
	}
	
	public String checkin() throws Exception
	{
		System.out.println("SB(((((((");
		int student_id =(int) ActionContext.getContext().getSession().get("student_id" );
		StudentProfile sp = new StudentProfile();
		sp.id = student_id;
		
		
		
		Session s = model.Util.sessionFactory.openSession();
		
		java.util.Date now = new java.util.Date();
		
		String status = checkCanCheckin(s, now);
		if(status.charAt(0) != '0')
		{
			System.out.println("不可以签到");
			return SUCCESS;
		}
		
		CheckInRecord ci = new CheckInRecord();
		ci.recordtime = new java.sql.Timestamp(now.getTime());
		ci.student = sp;
		s.beginTransaction();
		s.save(ci);
		s.getTransaction().commit();
		s.close();
		return SUCCESS;
	}

	public List getCheckinRecordList() {
		return checkinRecordList;
	}

	public void setCheckinRecordList(List checkinRecordList) {
		this.checkinRecordList = checkinRecordList;
	}

//	public int getCurrentPeriodId() {
//		return currentPeriodId;
//	}
//
//	public void setCurrentPeriodId(int currentPeriodId) {
//		this.currentPeriodId = currentPeriodId;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
//	java.util.Date startTime;
//	java.util.Date endTime;
//	String stuName;
//	
//	String checkinRecordTable;
//	List checkinRecordList;
//	public String search() throws Exception
//	{
//		
//		System.out.println("SB-------");
//		Session session = model.Util.sessionFactory.openSession();
//		Criteria c = session.createCriteria(CheckInRecord.class)		
//							.addOrder(Order.desc("id"));
//				
//		
//		if(!stuName.equals(""))
//		{
//			c.createAlias("student", "student")
//			.createAlias("student.user", "user")
//			.add(Restrictions.eq("user.fullName", stuName));
//		}
//			
//		if(startTime != null)
//		{
//			c.add(Restrictions.ge("recordtime", startTime));
//		}
//		
//		if(endTime != null)
//		{
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(endTime);
//			cal.add(Calendar.DATE, 1);			
//			c.add(Restrictions.le("recordtime", cal.getTime()));
//		}
//		
//		checkinRecordList = c.list();
//
//		
//		checkinRecordTable = Util.getJspOutput("/jsp/admin/widgets/checkinRecordTable.jsp");
//		session.close();
//		return ActionSupport.SUCCESS;
//	}
//	
//	
//	String checkinRecordExcelPath;
//	public String exportExcel() throws Exception{
//
//		HSSFWorkbook wb = new HSSFWorkbook();
//		HSSFSheet sheet = wb.createSheet("签到记录");
//		HSSFRow row = sheet.createRow((int) 0);  
//        HSSFCellStyle style = wb.createCellStyle();  
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
//        
//        HSSFCell cell = row.createCell(0, Cell.CELL_TYPE_STRING);  
//        cell.setCellValue("姓名");  
//        cell.setCellStyle(style);  
//        cell = row.createCell(1, Cell.CELL_TYPE_STRING);  
//        cell.setCellValue("学号");  
//        cell.setCellStyle(style);  
//        cell = row.createCell(2, Cell.CELL_TYPE_STRING);  
//        cell.setCellValue("签到时间");  
//        cell.setCellStyle(style);
//
//        
//        this.search();        
//
//        
//		for(int i = 0; i < checkinRecordList.size(); ++i) {
////			System.out.println(L.get(i));
//			row = sheet.createRow((int) i + 1);  
//			CheckInRecord cr = (CheckInRecord) checkinRecordList.get(i);
//            row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(cr.getStudent().getUser().getFullName());  
//            row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(cr.getStudent().getStudentId());            
//            row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(util.Util.formatTimestamp(cr.recordtime));
//		}
//				
//		checkinRecordExcelPath = util.Util.CheckInExcelExportPath + "签到记录.xls";
//		
//		try  
//        {
//			
//            FileOutputStream fout = new FileOutputStream(util.Util.RootPath + checkinRecordExcelPath);  
//            wb.write(fout);  
//            fout.close();
//            wb.close();
//        }
//		catch (Exception e)  
//        {
//			e.printStackTrace();
//        }
//
//		
//		
//		return SUCCESS;
//	}
//
//	
//	
//	
//	
//
//	
//	public java.util.Date getStartTime() {
//		return startTime;
//	}
//
//
//	public void setStartTime(java.util.Date startTime) {
//		this.startTime = startTime;
//	}
//
//
//	public java.util.Date getEndTime() {
//		return endTime;
//	}
//
//
//	public void setEndTime(java.util.Date endTime) {
//		this.endTime = endTime;
//	}
//
//
//	public String getStuName() {
//		return stuName;
//	}
//
//
//	public void setStuName(String stuName) {
//		this.stuName = stuName;
//	}
//
//
//	public String getCheckinRecordTable() {
//		return checkinRecordTable;
//	}
//
//
//	public void setCheckinRecordTable(String checkinRecordTable) {
//		this.checkinRecordTable = checkinRecordTable;
//	}
//
//
//	public List getCheckinRecordList() {
//		return checkinRecordList;
//	}
//
//
//	public void setCheckinRecordList(List checkinRecordList) {
//		this.checkinRecordList = checkinRecordList;
//	}
//	public String getCheckinRecordExcelPath() {
//		return checkinRecordExcelPath;
//	}
//	public void setCheckinRecordExcelPath(String checkinRecordExcelPath) {
//		this.checkinRecordExcelPath = checkinRecordExcelPath;
//	}
//	
}
