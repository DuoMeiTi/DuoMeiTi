package homepage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;
import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.Classroom;

public class HomepageInformation extends util.PageGetBaseAction {
	
	/*
	 * 一些static公用方法
	 */
	//获取上一周未检查教室的学生列表
	static ArrayList<Classroom> obtainLastWeekNotCheckClassroomStudentList(Session s)
	{
		
		ArrayList<Classroom> notCheckClassroomStudentList;
		
		ArrayList<Classroom> all_classroom = (ArrayList<Classroom>) 
				 s.createCriteria(model.Classroom.class)
						.add(Restrictions.isNotNull("principal"))
						.list();
				
		java.util.Date lastMonday, cntMonday;
		Calendar cal = Calendar.getInstance();		
		cal.add(Calendar.DATE, -7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);		
		lastMonday = cal.getTime();
		
		cal = Calendar.getInstance();	
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cntMonday = cal.getTime();
		System.out.println("上周一！！！！！！！！");
		System.out.println(lastMonday);
		System.out.println(cntMonday);
		
		notCheckClassroomStudentList = new ArrayList<Classroom>();
		
		System.out.println(all_classroom.size());
		for(Classroom c: all_classroom)
		{
			model.StudentProfile st = c.principal;
			
			boolean empty = s.createCriteria(model.CheckRecord.class)
				   .add(Restrictions.eq("checkman.id", c.principal.user.id))
				   .add(Restrictions.eq("classroom.id", c.id))
				   .add(Restrictions.between("checkdate", lastMonday, cntMonday))
				   .list().isEmpty();
			
			System.out.println(empty);
			if(empty)
			{
				notCheckClassroomStudentList.add(c);
			}
		}
			
		return notCheckClassroomStudentList;

	}
	
	static Criteria obtainAllNoticeCriteria(Session s)
	{
		return s.createCriteria(model.Notice.class)
				.addOrder(Order.desc("id"));
	}
	static Criteria obtainAllCheckClassroomRecordCriteria(Session s)
	{
		return  s .createCriteria(model.CheckRecord.class).addOrder(Order.desc("id"));
	}
	static Criteria obtainAllRepairRecordCriteria(Session s)
	{
		return s.createCriteria(model.RepairRecord.class).addOrder(Order.desc("id"));
	}
	
	static Criteria obtainAllReplaceDeviceCriteria(Session s)
	{
		java.util.Date now = new java.util.Date();
		java.sql.Date sql_now = new java.sql.Date(now.getTime());
		
		Criteria q = s.createCriteria(model.Repertory.class)
				 .add(Restrictions.le("rtDeadlineDate", sql_now))
				 .add(Restrictions.eq("rtDeviceStatus", util.Util.DeviceClassroomStatus))
				 .add(Restrictions.eq("rtType", "灯泡"))
				 .addOrder(Order.desc("id"));
		return q;
	}

// 以上是一些common 方法	
	
	
	public List notice_list;
	public List check_list;
	public List repair_list;
	public List deviceReplaceList;
	public String notice_list_html;
	public String check_list_html;
	public String repair_list_html;
	public String device_list_html;
		

	public String  MoreAnnouncement() throws Exception{
		
		Session session = model.Util.sessionFactory.openSession();
//		Criteria q = session.createCriteria(model.Notice.class).addOrder(Order.desc("id"));
		
		
		notice_list = obtainAllNoticeCriteria(session).list();
		
//		notice_list = this.makeCurrentPageList(obtainAllNoticeCriteria(session), 10);
//		
		session.close();
//		if(this.getIsAjaxTransmission()) // 这是ajax 传输
//		{
//			notice_list_html = util.Util.getJspOutput("/jsp/homepage/widgets/more_announcementTable.jsp");				
//			return "getPage";
//		}
		return ActionSupport.SUCCESS;
	}
	
	public String MoreCheckClassroom() throws Exception {
		
		Session session = model.Util.sessionFactory.openSession();
		
//		Criteria q = session.createCriteria(model.CheckRecord.class).addOrder(Order.desc("id"));
//		check_list = this.makeCurrentPageList(q, 10);
		check_list = obtainAllCheckClassroomRecordCriteria(session).list();
		
		session.close();
//		if(this.getIsAjaxTransmission())
//		{
//			check_list_html =util.Util.getJspOutput("/jsp/homepage/widgets/more_classroomTable.jsp");
//			return "getPage";
//		}
		return ActionSupport.SUCCESS;
	}
	
	public String MoreEquipmentMaintenance() throws Exception{
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(model.RepairRecord.class).addOrder(Order.desc("id"));
//		repair_list = this.makeCurrentPageList(q, 10);
		repair_list = obtainAllRepairRecordCriteria(session).list();
		session.close();
//		if(this.getIsAjaxTransmission())
//		{
//			repair_list_html = util.Util.getJspOutput("/jsp/homepage/widgets/more_equipment_maintenanceTable.jsp");
//			return "getPage";
//		}
		return ActionSupport.SUCCESS;
	}
	
	public String MoreEquipmentReplacement() throws Exception{
		
		Session session = model.Util.sessionFactory.openSession();
		
//		java.util.Date now = new java.util.Date();
//		java.sql.Date sql_now = new java.sql.Date(now.getTime());
//		
//		Criteria q = session.createCriteria(model.Repertory.class)
//					 .add(Restrictions.le("rtDeadlineDate", sql_now))
////					 .add(Restrictions.eq("rtDeviceStatus", "教室"))
//					.add(Restrictions.eq("rtDeviceStatus", util.Util.DeviceClassroomStatus))
//
//					.add(Restrictions.eq("rtType", "灯泡"))
//
//					 .addOrder(Order.desc("id"));
//		deviceReplaceList = this.makeCurrentPageList(q, 10);
		deviceReplaceList = obtainAllReplaceDeviceCriteria(session).list();
		
		session.close();
		
//		if(this.getIsAjaxTransmission())
//		{
//			device_list_html = util.Util.getJspOutput("/jsp/homepage/widgets/more_equipment_replacementTable.jsp");
//			return "getPage";
//		}
		
		return ActionSupport.SUCCESS;
	}
	
	
	ArrayList<Classroom> notCheckClassroomStudentList;
	public String notCheckClassroomStudent() throws Exception
	{
		
		Session s = model.Util.sessionFactory.openSession();
		
		notCheckClassroomStudentList = obtainLastWeekNotCheckClassroomStudentList(s);
		s.close();
//		ArrayList<Classroom> all_classroom = (ArrayList<Classroom>) 
//		 session.createCriteria(model.Classroom.class)
//				.add(Restrictions.isNotNull("principal"))
//				.list();
//		
//		java.util.Date lastMonday, cntMonday;
//		Calendar cal = Calendar.getInstance();		
//		cal.add(Calendar.DATE, -7);
//		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);		
//		lastMonday = cal.getTime();
//		
//		cal = Calendar.getInstance();	
//		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//		cntMonday = cal.getTime();
//		System.out.println("上周一！！！！！！！！");
//		System.out.println(lastMonday);
//		System.out.println(cntMonday);
//		
//		notCheckClassroomStudentList = new ArrayList<Classroom>();
//		
//		System.out.println(all_classroom.size());
//		for(Classroom c: all_classroom)
//		{
//			model.StudentProfile st = c.principal;
//			
//			boolean empty = session.createCriteria(model.CheckRecord.class)
//				   .add(Restrictions.eq("checkman.id", c.principal.user.id))
//				   .add(Restrictions.eq("classroom.id", c.id))
//				   .add(Restrictions.between("checkdate", lastMonday, cntMonday))
//				   .list().isEmpty();
//			
//			System.out.println(empty);
//			if(empty)
//			{
//				notCheckClassroomStudentList.add(c);
////				if(notCheckClassroomStudentList.size() >= MaxRes) break;
//			}
//		}
//		
////		deviceReplaceList = this.makeCurrentPageList(q, 10);
		
		return ActionSupport.SUCCESS;
	}
	
	
	
	

	public String getDevice_list_html() {
		return device_list_html;
	}

	public void setDevice_list_html(String device_list_html) {
		this.device_list_html = device_list_html;
	}

	public String getRepair_list_html() {
		return repair_list_html;
	}

	public void setRepair_list_html(String repair_list_html) {
		this.repair_list_html = repair_list_html;
	}

	public String getCheck_list_html() {
		return check_list_html;
	}

	public void setCheck_list_html(String check_list_html) {
		this.check_list_html = check_list_html;
	}

	public String getNotice_list_html() {
		return notice_list_html;
	}
	public void setNotice_list_html(String notice_list_html) {
		this.notice_list_html = notice_list_html;
	}
	
	public List getNotice_list() {
		return notice_list;
	}

	public void setNotice_list(List notice_list) {
		this.notice_list = notice_list;
	}

	public List getCheck_list() {
		return check_list;
	}

	public void setCheck_list(List check_list) {
		this.check_list = check_list;
	}

	public List getRepair_list() {
		return repair_list;
	}

	public void setRepair_list(List repair_list) {
		this.repair_list = repair_list;
	}


	public List getDeviceReplaceList() {
		return deviceReplaceList;
	}


	public void setDeviceReplaceList(List deviceReplaceList) {
		this.deviceReplaceList = deviceReplaceList;
	}

	public ArrayList<Classroom> getNotCheckClassroomStudentList() {
		return notCheckClassroomStudentList;
	}

	public void setNotCheckClassroomStudentList(ArrayList<Classroom> notCheckClassroomStudentList) {
		this.notCheckClassroomStudentList = notCheckClassroomStudentList;
	}
	
	
	
	
}
