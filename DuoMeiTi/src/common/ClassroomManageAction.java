package common;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.Classroom;


public class ClassroomManageAction extends ActionSupport {
	
	 
	String searchType;
	String searchParam;
	String classroomHtml;
	int build_id;
	String build_name;
	
	List classroom_list;


	public String classroomList() throws Exception {
		Session session = model.Util.sessionFactory.openSession();
		Criteria classroom_criteria = session.createCriteria(Classroom.class);		
		classroom_criteria.add(Restrictions.eq("teachbuilding.build_id", build_id));
		classroom_criteria.addOrder(Order.asc("classroom_num"));
		classroom_list= classroom_criteria.list();
		
		session.close();
		return SUCCESS;
	}
	
	public String search() throws Exception {
		
		System.out.println("FJJFJFJFJ");
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria classroom_criteria = session.createCriteria(Classroom.class);
		System.out.println(searchType);
		
		classroom_criteria.add(Restrictions.eq("teachbuilding.build_id", build_id));
		if(searchType.equals("classroomNum"))
		{
			System.out.println("&&&&&&&&*************");
			System.out.println(build_id);
			System.out.println(searchParam);
			
			
			
			classroom_criteria.add(Restrictions.eq("classroom_num", searchParam));
			
			classroom_list = classroom_criteria.list();
			classroomHtml = util.Util.getJspOutput("/jsp/classroom/classroomTable.jsp");
		}
		else if(searchType.equals("principal"))
		{
			System.out.println("--------------------");
			System.out.println(searchParam);

			
			classroom_criteria.createAlias("principal", "principal");
			classroom_criteria.createAlias("principal.user", "user"); 
			classroom_criteria.add(Restrictions.eq("user.fullName" ,searchParam));
			
			
			classroom_list = classroom_criteria.list();
//			System.out.println(classroom_list.getClass().toString());
			classroomHtml = util.Util.getJspOutput("/jsp/classroom/classroomTable.jsp");
		}
		
		
		
		return SUCCESS;
	}
	
	
	
	
	public String getClassroomHtml() {
		return classroomHtml;
	}

	public void setClassroomHtml(String classroomHtml) {
		this.classroomHtml = classroomHtml;
	}

	public String getBuild_name() {
		return build_name;
	}

	public void setBuild_name(String build_name) {
		this.build_name = build_name;
	}
	public int getBuild_id() {
		return build_id;
	}
	public void setBuild_id(int build_id) {
		this.build_id = build_id;
	}

	public List getClassroom_list() {
		return classroom_list;
	}
	public void setClassroom_list(List classroom_list) {
		this.classroom_list = classroom_list;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}
	
	
}
