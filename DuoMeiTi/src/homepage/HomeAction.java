package homepage;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Classroom;
import model.DutySchedule;
import model.StudentProfile;
import model.User;
import util.PageGetBaseAction;


public class HomeAction extends PageGetBaseAction
{
	public List check_list;
	public List notice_list;
	public List repair_list;
	public List file_path_list;
	public String file_path_html;
	public List deviceReplaceList;
	static
	{
		Session session = model.Util.sessionFactory.openSession();		
		
		
		List L = session.createCriteria(model.AdminProfile.class).list();
		if(L.size() == 0)
		{
			User user = new User();
			user.setUsername("admin");
			user.setPassword("admin");
			
			user.setFullName("full_admin");

			model.AdminProfile ap = new model.AdminProfile();
			ap.setUser(user);
			
			session.beginTransaction();
			session.save(user);
			session.save(ap);
			session.getTransaction().commit();
		}
		
		
		
		
		
		session.close();
		
	}

	
	List dutyStudentList;
	List<Classroom> notCheckClassroomStudentList;
	public String execute() throws Exception
	{ 
		System.out.println("******************");
		System.out.println(util.Util.RootPath);
		Session session = model.Util.sessionFactory.openSession();
		Session s = session;
		final int MaxRes = 8;
		
//		check_list = session.createCriteria(model.CheckRecord.class)
//							.setMaxResults(MaxRes)
//							.addOrder(Order.desc("id"))
//							.list();
		
//		notice_list = session.createCriteria(model.Notice.class)
//							 .setMaxResults(MaxRes)
//							 .addOrder(Order.desc("id"))
//							 .list();
//		repair_list = session.createCriteria(model.RepairRecord.class)
//							 .setMaxResults(MaxRes)
//							 .addOrder(Order.desc("id"))
//							 .list();
		check_list = HomepageInformation.obtainAllCheckClassroomRecordCriteria(s).setMaxResults(MaxRes).list();
		notice_list = HomepageInformation.obtainAllNoticeCriteria(s).setMaxResults(MaxRes).list();
		repair_list = HomepageInformation.obtainAllRepairRecordCriteria(s).setMaxResults(MaxRes).list();
		deviceReplaceList = HomepageInformation.obtainAllReplaceDeviceCriteria(s).setMaxResults(MaxRes).list();
		
//		java.util.Date now = new java.util.Date();
//		java.sql.Date sql_now = new java.sql.Date(now.getTime());		
//		deviceReplaceList = session.createCriteria(model.Repertory.class)
//						.add(Restrictions.le("rtDeadlineDate", sql_now))
//						.add(Restrictions.eq("rtDeviceStatus", util.Util.DeviceClassroomStatus))
//
//						.add(Restrictions.eq("rtType", "灯泡"))
//						.setMaxResults(MaxRes)
//						.addOrder(Order.desc("id"))
//						.list();
		

		
		
		
		
		
		
		
		//确定当前值班同学：
		ArrayList<model.DutySchedule> allDutyList = 
				(ArrayList<model.DutySchedule>)session.createCriteria(model.DutySchedule.class).list();
		dutyStudentList = new ArrayList<model.StudentProfile>();
		for(model.DutySchedule ds: allDutyList)
		{
			int period = util.Util.getPeriodFromDutyPieceTime(ds.dutyPiece.time);
			int week = util.Util.getWeekFromDutyPieceTime(ds.dutyPiece.time);
			
			int cmpBegin = java.time.LocalTime.now().compareTo( util.Util.dutyPeriodBeginList.get(period));
			int cmpEnd = java.time.LocalTime.now().compareTo( util.Util.dutyPeriodEndList.get(period));
			int today_week = util.Util.getDayOfWeek(new java.util.Date());
			if(cmpBegin >= 0 && cmpEnd <= 0 && today_week == week)
			{	
				dutyStudentList.add(ds.student);
			}
		}
		
		
		
		
		// 确定上一周未检查教室学生
//		notCheckClassroomStudentList
//		principal
//		ArrayList<Classroom> all_classroom = (ArrayList<Classroom>) 
//		session.createCriteria(model.Classroom.class)
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
//				if(notCheckClassroomStudentList.size() >= MaxRes) break;
//			}
//		}
		
		
		// 确定上一周未检查教室学生
		notCheckClassroomStudentList = HomepageInformation.obtainLastWeekNotCheckClassroomStudentList(session);		
		
		notCheckClassroomStudentList = notCheckClassroomStudentList.subList(0, Math.min(notCheckClassroomStudentList.size(), MaxRes));
		
		
		
		
		
		
		
		System.out.println("&&&&&&&&&&");

		session.close();
		return ActionSupport.SUCCESS;
	}
	


	public String resourceFile() throws Exception
	{	
		Session session = model.Util.sessionFactory.openSession();
		
		Criteria q = session.createCriteria(model.ResourceFilePath.class);
		
		
		file_path_list = q.list();
		
//		System.out.println("resourceFile========");
//		System.out.println(this.getCurrentPageNum());
//		file_path_list = this.makeCurrentPageList(q, 10); // 根据代表总体的Criteria 获取当前页元素的List，这个效率高，应尽量使用这个
//		file_path_list = this.makeCurrentPageList(q.list(), 10); //根据代表总体的List 获取当前页元素的List
		
		session.close();
		System.out.println("resourceFile========22222222");
//		if(this.getIsAjaxTransmission()) // 这是ajax 传输
//		{
//			System.out.println("ENTER?");
////			file_path_html = util.Util.getJspOutput("/jsp/admin/HomepageModify/ResourceFileTable.jsp");				
//			return "getPage";
//		}
		
		return ActionSupport.SUCCESS;
	}
	
	
	

	ArrayList<ArrayList<ArrayList<DutySchedule>> > dutyShowList;
	List dutyPlaceList;
	public String dutyShow() throws Exception
	{	
		Session session = model.Util.sessionFactory.openSession();
		
		dutyPlaceList = session.createCriteria(model.DutyPlace.class).list();
		List dutyScheduleList = session.createCriteria(model.DutySchedule.class).list();
		
		int place_size = dutyPlaceList.size();
		
		
		
		dutyShowList = new ArrayList<ArrayList<ArrayList<DutySchedule>> >();
		
		for(int i = 0; i < place_size; ++ i) 
		{
			ArrayList<ArrayList<DutySchedule>> tmp = new ArrayList<ArrayList<DutySchedule>>();
			for(int j = 0; j < 35; ++ j)
			{
				ArrayList<DutySchedule> tmp_ds = new ArrayList<DutySchedule>();
				tmp.add(tmp_ds);
			}
			dutyShowList.add(tmp);
		}
		
		
		
		for(int i = 0; i < dutyScheduleList.size(); ++ i)
		{
			DutySchedule ds = (DutySchedule)dutyScheduleList.get(i);
		
			dutyShowList.get(dutyPlaceList.indexOf(ds.dutyPiece.dutyPlace) )
						.get(ds.dutyPiece.time)
						.add(ds);
						
		}
		
		
		session.close();


		return ActionSupport.SUCCESS;
	}

	
	
	
	
	
	
/*	
 * 教室负责人表：
 */
	List<Classroom> classroomList;
	ArrayList<ArrayList<Classroom> > classroomByPrinicpalList; 
	
	public String classroomPincipalShow() throws Exception
	{
		
		Session s = model.Util.sessionFactory.openSession();
		classroomList = 
				s.createCriteria(model.Classroom.class)
				.addOrder(Order.desc("principal"))
				.add(Restrictions.isNotNull("principal"))
				.list();
		
		
		classroomByPrinicpalList = new ArrayList<ArrayList<Classroom> >();
		
		for(int i = 0, j; i < classroomList.size(); i = j)
		{
			for(j = i + 1; j < classroomList.size(); ++ j)
			{
				StudentProfile spi = classroomList.get(i).principal;
				StudentProfile spj = classroomList.get(j).principal;
				
				if( (spi == null) ^ (spj == null) == true ||
						(spi != null && spi.id != spj.id)

						
						)
				break;
			}
			
			ArrayList<Classroom> cntList = new ArrayList<Classroom>();
			for(int k = i; k < j; ++ k)
			{
				cntList.add(classroomList.get(k));
			}
			classroomByPrinicpalList.add(cntList);
			
		}
		
		
		
		
		
		s.close();
		return SUCCESS;
	}
	
	
	
	
	
	
	







	public ArrayList<ArrayList<Classroom>> getClassroomByPrinicpalList() {
		return classroomByPrinicpalList;
	}



	public void setClassroomByPrinicpalList(ArrayList<ArrayList<Classroom>> classroomByPrinicpalList) {
		this.classroomByPrinicpalList = classroomByPrinicpalList;
	}



	public List<Classroom> getClassroomList() {
		return classroomList;
	}



	public void setClassroomList(List<Classroom> classroomList) {
		this.classroomList = classroomList;
	}



	public List getDutyStudentList() {
		return dutyStudentList;
	}



	public void setDutyStudentList(List dutyStudentList) {
		this.dutyStudentList = dutyStudentList;
	}



	public ArrayList<ArrayList<ArrayList<DutySchedule>>> getDutyShowList() {
		return dutyShowList;
	}



	public void setDutyShowList(ArrayList<ArrayList<ArrayList<DutySchedule>>> dutyShowList) {
		this.dutyShowList = dutyShowList;
	}



	public List getDutyPlaceList() {
		return dutyPlaceList;
	}



	public void setDutyPlaceList(List dutyPlaceList) {
		this.dutyPlaceList = dutyPlaceList;
	}



	public List getCheck_list() {
		return check_list;
	}

	public void setCheck_list(List check_list) {
		this.check_list = check_list;
	}

	public List getNotice_list() {
		return notice_list;
	}

	public void setNotice_list(List notice_list) {
		this.notice_list = notice_list;
	}

	public List getRepair_list() {
		return repair_list;
	}

	public void setRepair_list(List repair_list) {
		this.repair_list = repair_list;
	}

	public List getFile_path_list() {
		return file_path_list;
	}

	public void setFile_path_list(List file_path_list) {
		this.file_path_list = file_path_list;
	}

	public String getFile_path_html() {
		return file_path_html;
	}

	public void setFile_path_html(String file_path_html) {
		this.file_path_html = file_path_html;
	}



	public List getDeviceReplaceList() {
		return deviceReplaceList;
	}



	public void setDeviceReplaceList(List deviceReplaceList) {
		this.deviceReplaceList = deviceReplaceList;
	}



	public List<Classroom> getNotCheckClassroomStudentList() {
		return notCheckClassroomStudentList;
	}



	public void setNotCheckClassroomStudentList(List<Classroom> notCheckClassroomStudentList) {
		this.notCheckClassroomStudentList = notCheckClassroomStudentList;
	}



//	public ArrayList<Classroom> getNotCheckClassroomStudentList() {
//		return notCheckClassroomStudentList;
//	}
//
//
//
//	public void setNotCheckClassroomStudentList(ArrayList<Classroom> notCheckClassroomStudentList) {
//		this.notCheckClassroomStudentList = notCheckClassroomStudentList;
//	}
	
	
	
	
}










