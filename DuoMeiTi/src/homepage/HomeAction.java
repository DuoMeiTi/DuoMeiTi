package homepage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
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
	
	
	
//初始化SemesterFirstWeek表，保证SemesterFirstWeek里恰好有一条记录
	static
	{
		Session s = model.Util.sessionFactory.openSession();
		
		List L = s.createCriteria(model.SemesterFirstWeek.class)
				 .list();
		if(L.size() == 0)
		{
			s.beginTransaction();
			model.SemesterFirstWeek ins = new model.SemesterFirstWeek();
			
			ins.date = new java.sql.Date(new java.util.Date().getTime());
			s.save(ins);
			s.getTransaction().commit();
		}
		s.close();
	}
	
	
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
	
	ArrayList<ArrayList<StudentProfile>> allNotCheckStudentList;
	public String execute() throws Exception
	{ 
		System.out.println("******************");
		System.out.println(util.Util.RootPath);
		Session session = model.Util.sessionFactory.openSession();
		Session s = session;
		final int MaxRes = 8;
		

		check_list = HomepageInformation.obtainAllCheckClassroomRecordCriteria(s).setMaxResults(MaxRes).list();
		notice_list = HomepageInformation.obtainAllNoticeCriteria(s).setMaxResults(MaxRes).list();
		repair_list = HomepageInformation.obtainAllRepairRecordCriteria(s).setMaxResults(MaxRes).list();
		deviceReplaceList = HomepageInformation.obtainAllReplaceDeviceCriteria(s).setMaxResults(MaxRes).list();
		

		
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
//		notCheckClassroomStudentList = HomepageInformation.obtainLastWeekNotCheckClassroomStudentList(session);	

		allNotCheckStudentList = HomepageInformation.obtainLastWeekNotCheckClassroomStudentList(session);
		
		
//		notCheckClassroomStudentList = notCheckClassroomStudentList.subList(0, Math.min(notCheckClassroomStudentList.size(), MaxRes));
		
		
		
		
		
		
		
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
//	List<Classroom> classroomList;
	ArrayList<ArrayList<Classroom> > classroomByPrinicpalList; 
	
	Map<String, ArrayList<Classroom>> classroomMap;
	public String classroomPincipalShow() throws Exception
	{
		
		Session s = model.Util.sessionFactory.openSession();
		List<Classroom> allClassroomList = s.createCriteria(model.Classroom.class)
											.addOrder(Order.asc("classroom_num"))
											.list();
		
		classroomMap = new TreeMap<String, ArrayList<Classroom>>();
		for(Classroom c : allClassroomList)
		{
			String teachBuildingName = c.teachbuilding.build_name;
			if(!classroomMap.containsKey(teachBuildingName))
			{
				classroomMap.put(teachBuildingName, new ArrayList<Classroom>());
			}
			
			List<Classroom> cntClassroomList = classroomMap.get(teachBuildingName);
			
			cntClassroomList.add(c);
		}
		
		
		
		
		
		s.close();
		return SUCCESS;
//		Session s = model.Util.sessionFactory.openSession();
//		List<Classroom> classroomList = 
//				s.createCriteria(model.Classroom.class)
//				.addOrder(Order.desc("principal"))
//				.add(Restrictions.isNotNull("principal"))
//				.list();
//		
//		
//		classroomByPrinicpalList = new ArrayList<ArrayList<Classroom> >();
//		
//		for(int i = 0, j; i < classroomList.size(); i = j)
//		{
//			for(j = i + 1; j < classroomList.size(); ++ j)
//			{
//				StudentProfile spi = classroomList.get(i).principal;
//				StudentProfile spj = classroomList.get(j).principal;
//				
//				if( (spi == null) ^ (spj == null) == true ||
//						(spi != null && spi.id != spj.id)
//
//						
//						)
//				break;
//			}
//			
//			ArrayList<Classroom> cntList = new ArrayList<Classroom>();
//			for(int k = i; k < j; ++ k)
//			{
//				cntList.add(classroomList.get(k));
//			}
//			classroomByPrinicpalList.add(cntList);
//			
//		}
//		
//		
//		
//		
//		
//		s.close();
//		return SUCCESS;
	}
	
	
	
	
	
	
	







	public ArrayList<ArrayList<Classroom>> getClassroomByPrinicpalList() {
		return classroomByPrinicpalList;
	}



	public void setClassroomByPrinicpalList(ArrayList<ArrayList<Classroom>> classroomByPrinicpalList) {
		this.classroomByPrinicpalList = classroomByPrinicpalList;
	}

	
	
	
	
	
	

//	public List<Classroom> getClassroomList() {
//		return classroomList;
//	}
//
//
//
//	public void setClassroomList(List<Classroom> classroomList) {
//		this.classroomList = classroomList;
//	}
//


	public Map<String, ArrayList<Classroom>> getClassroomMap() {
		return classroomMap;
	}



	public void setClassroomMap(Map<String, ArrayList<Classroom>> classroomMap) {
		this.classroomMap = classroomMap;
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



	public ArrayList<ArrayList<StudentProfile>> getAllNotCheckStudentList() {
		return allNotCheckStudentList;
	}



	public void setAllNotCheckStudentList(ArrayList<ArrayList<StudentProfile>> allNotCheckStudentList) {
		this.allNotCheckStudentList = allNotCheckStudentList;
	}
	
	
	


	
	
	
}










