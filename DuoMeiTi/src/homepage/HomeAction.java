package homepage;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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

import model.DutySchedule;
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
	public String execute() throws Exception
	{ 
		System.out.println("******************");
		System.out.println(util.Util.RootPath);
		Session session = model.Util.sessionFactory.openSession();
		
		final int MaxRes = 8;
		check_list = session.createCriteria(model.CheckRecord.class)
							.setMaxResults(MaxRes)
							.addOrder(Order.desc("id"))
							.list();
		notice_list = session.createCriteria(model.Notice.class)
							 .setMaxResults(MaxRes)
							 .addOrder(Order.desc("id"))
							 .list();
		repair_list = session.createCriteria(model.RepairRecord.class)
							 .setMaxResults(MaxRes)
							 .addOrder(Order.desc("id"))
							 .list();
		
		java.util.Date now = new java.util.Date();
		java.sql.Date sql_now = new java.sql.Date(now.getTime());		
		deviceReplaceList = session.createCriteria(model.Repertory.class)
						.add(Restrictions.le("rtDeadlineDate", sql_now))
						.add(Restrictions.eq("rtDeviceStatus", "教室"))
						.setMaxResults(MaxRes)
						.addOrder(Order.desc("id"))
						.list();
		

		
		
		
		
		
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
				
				
//				dutyStudentList.add(ds.student);
//				dutyStudentList.add(ds.student);
//				System.out.println((ds.student.user.fullName));
			}

		}
		
		
		
		
		
		
		
		
		
		
		System.out.println("&&&&&&&&&&");

		session.close();
		return ActionSupport.SUCCESS;
	}
	


	public String resourceFile() throws Exception
	{	
		Session session = model.Util.sessionFactory.openSession();
		
		Criteria q = session.createCriteria(model.ResourceFilePath.class);
		
		file_path_list = this.makeCurrentPageList(q, 10); // 根据代表总体的Criteria 获取当前页元素的List，这个效率高，应尽量使用这个
//		file_path_list = this.makeCurrentPageList(q.list(), 10); //根据代表总体的List 获取当前页元素的List
		
		session.close();
		if(this.getIsAjaxTransmission()) // 这是ajax 传输
		{
			file_path_html = util.Util.getJspOutput("/jsp/admin/HomepageModify/ResourceFileTable.jsp");				
			return "getPage";
		}
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
//		String s = "我wo";
//		System.out.println(s.length());

		return ActionSupport.SUCCESS;
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
	
	
	
	
}










