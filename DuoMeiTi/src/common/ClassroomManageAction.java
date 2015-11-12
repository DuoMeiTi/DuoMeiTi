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
import model.StudentProfile;
import model.TeachBuilding;


public class ClassroomManageAction extends ActionSupport {
	
	 
	String searchType;
	String searchParam;
	String classroomHtml;
	int build_id;
	String build_name;
	String studentID;// 学号
	List classroom_list;
	String add_classroom_num;
	String submit_type;
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
		}
		else if(searchType.equals("principal"))
		{
			System.out.println("--------------------");
			System.out.println(searchParam);			
			classroom_criteria.createAlias("principal", "principal");
			classroom_criteria.createAlias("principal.user", "user"); 
			classroom_criteria.add(Restrictions.eq("user.fullName" ,searchParam));	
		}		
		classroom_list = classroom_criteria.list();
		classroomHtml = util.Util.getJspOutput("/jsp/classroom/classroomTable.jsp");
		session.close();
		
		return SUCCESS;
	}
	
	public String queryStu() throws Exception {
		Session session = model.Util.sessionFactory.openSession();
		Criteria stu_criteria = session.createCriteria(StudentProfile.class);
		stu_criteria.add(Restrictions.eq("studentId", studentID));
		Object obj = stu_criteria.uniqueResult();
		if(obj == null) {
			classroomHtml = "";
		}
		else {
			StudentProfile stu = (StudentProfile) obj;
			classroomHtml = stu.user.fullName;
		}
		session.close();

		return SUCCESS;
	}
	
	public String addClassroom() {
		System.out.println("addClassroom:");
		add_classroom_num = add_classroom_num.trim();
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria classroom_criteria = session.createCriteria(Classroom.class);
		classroom_criteria.add(Restrictions.eq("teachbuilding.build_id", build_id));
		classroom_criteria.add(Restrictions.eq("classroom_num", add_classroom_num));
		List<Classroom> classroom_list = classroom_criteria.list();
		System.out.println("size:" + classroom_list.size());
		if(classroom_list.size() > 0 && submit_type.equals("add")) {
			classroomHtml = "exist";
		}
		else {
			Criteria build_criteria = session.createCriteria(TeachBuilding.class);
			build_criteria.add(Restrictions.eq("build_id", build_id));
			TeachBuilding build = (TeachBuilding) build_criteria.uniqueResult();
			
			Criteria stu_criteria = session.createCriteria(StudentProfile.class);
			stu_criteria.add(Restrictions.eq("studentId", studentID));
			StudentProfile stu = (StudentProfile)stu_criteria.uniqueResult();


			Classroom classroom = null;
			if(submit_type.equals("add"))
				classroom = new Classroom();
			else if(submit_type.equals("update")) 
				classroom = (Classroom) classroom_criteria.uniqueResult();
			
			
			classroom.setTeachbuilding(build);
			classroom.setPrincipal(stu);	
			classroom.setClassroom_num(add_classroom_num);
			session.beginTransaction();
			if(submit_type.equals("add"))
				session.save(classroom);
			else if(submit_type.equals("update"))
				session.update(classroom);
			
			session.getTransaction().commit();
			
			classroomHtml = "ok";
		}
		session.close();
		System.out.println("add ok!");
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getSubmit_type() {
		return submit_type;
	}

	public void setSubmit_type(String submit_type) {
		this.submit_type = submit_type;
	}

	public String getAdd_classroom_num() {
		return add_classroom_num;
	}

	public void setAdd_classroom_num(String add_classroom_num) {
		this.add_classroom_num = add_classroom_num;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
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
