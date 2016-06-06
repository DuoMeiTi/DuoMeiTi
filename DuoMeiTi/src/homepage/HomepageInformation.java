package homepage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;
import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.Classroom;
import model.StudentProfile;

public class HomepageInformation extends util.PageGetBaseAction {
	
	//获取上一周未检查教室的学生列表
//	static ArrayList<Classroom> obtainLastWeekNotCheckClassroomStudentList(Session s)
//	{
//		
//		ArrayList<Classroom> notCheckClassroomStudentList;
//		
//		ArrayList<Classroom> all_classroom = (ArrayList<Classroom>) 
//				 s.createCriteria(model.Classroom.class)
//						.add(Restrictions.isNotNull("principal"))
//						.list();
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
//			boolean empty = s.createCriteria(model.CheckRecord.class)
//				   .add(Restrictions.eq("checkman.id", c.principal.user.id))
//				   .add(Restrictions.eq("classroom.id", c.id))
//				   .add(Restrictions.between("checkdate", lastMonday, cntMonday))
//				   .list().isEmpty();
//			
//			System.out.println(empty);
//			if(empty)
//			{
//				notCheckClassroomStudentList.add(c);
//			}
//		}
//			
//		return notCheckClassroomStudentList;
//
//	}
	
	
	/*
	 * 一些static公用方法
	 */

//	根据参数日期，得到本周的周一的日期，其中一周的第一天为周一, 并且将返回的日期改为的时间部分（时分秒）全都设置为0
	static java.util.Date getCntWeekMonday(java.util.Date u)
	{
		Calendar cd = Calendar.getInstance();
		cd.setTime(u);
//		System.out.println("enter getCntWeekMonday");
//		System.out.println(u);
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK); // 因为按中国礼拜一作为第一天所以这里减1
		
		if(dayOfWeek != Calendar.SUNDAY)
		{
			cd.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		}
		else
		{
			cd.add(Calendar.DATE, -6);
		}
		cd.set(Calendar.HOUR, 0);
		cd.set(Calendar.MINUTE, 0);
		cd.set(Calendar.SECOND, 0);
		cd.set(Calendar.MILLISECOND, 0);
		return cd.getTime();
	}
	
	
//	判断两个日期是否是一天
	public static boolean isSameDay(java.util.Date date1, java.util.Date date2) 
	{
       Calendar c1 = Calendar.getInstance();
       c1.setTime(date1);

       Calendar c2 = Calendar.getInstance();
       c2.setTime(date2);

       return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && 
    		  c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && 
    		  c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
	   }

	
	static boolean isEmptyInCheckRecordList(List<model.CheckRecord> L, int stu_user_id, int classroom_id, 
										java.util.Date beg, java.util.Date end)
	{
		for(model.CheckRecord cr: L)
		{
			if(cr.checkman.id == stu_user_id && 
			   cr.classroom.id == classroom_id &&
			   cr.checkdate.after(beg) &&
			   cr.checkdate.before(end)
			   )
			{
				return false;
			}
				
		}
		return true;


	}
	
//	获取 所有周的未填写教室检查记录的同学，是逆序返回的，即从当前周到第一周
	static 
	ArrayList<ArrayList<StudentProfile> >
	obtainLastWeekNotCheckClassroomStudentList(Session s)
	{					
		ArrayList<ArrayList<StudentProfile> > allNotCheckStudentList = new ArrayList<ArrayList<StudentProfile>>();		
		
		model.SemesterFirstWeek firstWeek = (model.SemesterFirstWeek)
				s.createCriteria(model.SemesterFirstWeek.class).uniqueResult();
		
		if(firstWeek.date == null) return allNotCheckStudentList;		
		
		java.util.Date firstWeekMonday = getCntWeekMonday(firstWeek.date);		
		java.util.Date nowMonday = getCntWeekMonday(new java.util.Date());
		

		if(nowMonday.before(firstWeekMonday))
			return allNotCheckStudentList;
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstWeekMonday);
		
		
		
//		得到从第一周到当前周的所有的周一 列表
		ArrayList<java.util.Date> mondayList = new ArrayList<java.util.Date>() ; 
		for(;;)
		{
			java.util.Date cnt = cal.getTime();			
			mondayList.add(cnt);
			if(isSameDay(cnt, nowMonday)) break;
			cal.add(Calendar.DATE, 7);			
		}
		

		ArrayList<Classroom> all_classroom = (ArrayList<Classroom>) 
								 s.createCriteria(model.Classroom.class)
								.add(Restrictions.isNotNull("principal"))
								.list();
		
//		得到所有大于起始周的所有检查记录
		ArrayList<model.CheckRecord> checkRecordList = 
									(ArrayList<model.CheckRecord>) 
									s.createCriteria(model.CheckRecord.class)
									.add(Restrictions.ge("checkdate", firstWeekMonday))
									.list();
//		遍历周一列表   获取每一周未填写检查记录的学生	
		for(int i = 0; i < mondayList.size() - 1; ++ i)
		{
			ArrayList<StudentProfile> notCheckStudentList = new ArrayList<StudentProfile>();
			
			HashSet<StudentProfile> set = new HashSet<StudentProfile>();			
			
			for(Classroom c: all_classroom)
			{
				model.StudentProfile st = c.principal;
				
//				boolean empty = s.createCriteria(model.CheckRecord.class)
//					   .add(Restrictions.eq("checkman.id", c.principal.user.id))
//					   .add(Restrictions.eq("classroom.id", c.id))
//					   .add(Restrictions.between("checkdate", mondayList.get(i), mondayList.get(i + 1)))
//					   .list().isEmpty();
				
				boolean empty = isEmptyInCheckRecordList(checkRecordList, c.principal.user.id, c.id,
						mondayList.get(i), mondayList.get(i + 1));
				
				if(empty)
				{
					set.add(st);
				}
			}
			notCheckStudentList.addAll(set);
			Collections.sort(notCheckStudentList,new Comparator<StudentProfile>()
			{
	            public int compare(StudentProfile a, StudentProfile b) 
	            {
	            	if(a.id > b.id) return 1;
	            	if(a.id == b.id) return 0;
	            	return -1;
	            }
	        });
			

			
			
			
			
			
			allNotCheckStudentList.add(notCheckStudentList);
		}
		Collections.reverse(allNotCheckStudentList);
		
		return allNotCheckStudentList;

	}

	
	

			
			
			
	
	static Criteria obtainAllNoticeCriteria(Session s)
	{
		return s.createCriteria(model.Notice.class)
				.addOrder(Order.desc("id"));
	}
	static Criteria obtainAllCheckClassroomRecordCriteria(Session s)
	{
		return  s 
				.createCriteria(model.CheckRecord.class)
				.addOrder(Order.desc("id"))
				.add(Restrictions.not(Restrictions.eq("checkdetail", model.CheckRecord.NoProblem)))
						
				;
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

// 以上是一些common static方法	
	
	
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
		
		
		notice_list = obtainAllNoticeCriteria(session).list();
		

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
	ArrayList<ArrayList<StudentProfile>> allNotCheckStudentList;
	public String notCheckClassroomStudent() throws Exception
	{
		
		Session s = model.Util.sessionFactory.openSession();
		
//		notCheckClassroomStudentList = obtainLastWeekNotCheckClassroomStudentList(s);
		allNotCheckStudentList = obtainLastWeekNotCheckClassroomStudentList(s);
		s.close();

		
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

	public ArrayList<ArrayList<StudentProfile>> getAllNotCheckStudentList() {
		return allNotCheckStudentList;
	}

	public void setAllNotCheckStudentList(ArrayList<ArrayList<StudentProfile>> allNotCheckStudentList) {
		this.allNotCheckStudentList = allNotCheckStudentList;
	}
	
	
	
	
}
