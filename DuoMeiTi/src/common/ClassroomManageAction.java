package common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Classroom;
import model.StudentProfile;
import model.TeachBuilding;


public class ClassroomManageAction extends ActionSupport {
	
	 
	String searchType;
	String searchParam;
	String classroomHtml;
	String status;
	
	int build_id;
	String build_name;
	
	
	
	String studentNumber;// 学号
	int studentId; // studentProfile 的id;
	

	List classroom_list;
	String add_classroom_num;
	String submit_type;
	int classroomId;
	
	String classroomManageJsp;
	String url;
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassroomManageJsp() {
		return classroomManageJsp;
	}

	public void setClassroomManageJsp(String classroomManageJsp) {
		this.classroomManageJsp = classroomManageJsp;
	}

	public String classroomList() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		System.out.println("**************JJ)))))))))))0");
		System.out.println(request.getRequestURI());

		
		url = request.getRequestURI(); 
		if(url.contains(util.Const.AdminRole))			
			classroomManageJsp = "/jsp/admin/classroomManage.jsp";
		else if(url.contains(util.Const.StudentRole))
		{
			System.err.println("EEEEEEEEEEEEEEEEEEEE");
		}
		else 
			classroomManageJsp = "/jsp/homepage/classroomManage.jsp";
		
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
		classroom_criteria.addOrder(Order.asc("classroom_num"));
		classroom_list = classroom_criteria.list();
		classroomHtml = util.Util.getJspOutput("/jsp/classroom/classroomTable.jsp");
		session.close();
		
		return SUCCESS;
	}
	
	public String queryStu() throws Exception {
		Session session = model.Util.sessionFactory.openSession();
		Criteria stu_criteria = session.createCriteria(StudentProfile.class);
		stu_criteria.add(Restrictions.eq("studentId", studentNumber));
		Object obj = stu_criteria.uniqueResult();
		if(obj == null) {
			status = "";
		}
		else {
			StudentProfile stu = (StudentProfile) obj;
			status = stu.user.fullName;
		}
		session.close();

		return SUCCESS;
	}
	
	public String addClassroom() throws Exception {
		System.out.println("addClassroom:");
		add_classroom_num = add_classroom_num.trim();
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria classroom_criteria = session.createCriteria(Classroom.class);
		classroom_criteria.add(Restrictions.eq("teachbuilding.build_id", build_id));
		classroom_criteria.add(Restrictions.eq("classroom_num", add_classroom_num));
		classroom_list = classroom_criteria.list();
		System.out.println("size:" + classroom_list.size());
		if(classroom_list.size() > 0 && submit_type.equals("add")) {
			this.status = "exist";
			
		}
		else {
			Criteria build_criteria = session.createCriteria(TeachBuilding.class);
			build_criteria.add(Restrictions.eq("build_id", build_id));
			TeachBuilding build = (TeachBuilding) build_criteria.uniqueResult();
			
			Criteria stu_criteria = session.createCriteria(StudentProfile.class);
			stu_criteria.add(Restrictions.eq("studentId", studentNumber));
			StudentProfile stu = (StudentProfile)stu_criteria.uniqueResult();
			
			System.out.println("addClassroom: update***&&" + add_classroom_num);
			
			Classroom classroom = null;
			if(submit_type.equals("add"))
				classroom = new Classroom();
			else if(submit_type.equals("update")) 
				classroom = (Classroom) session.createCriteria(Classroom.class).add(Restrictions.eq("id", classroomId)).uniqueResult();
			
			System.out.println("addClassroom: classroomId***&&" + classroomId);
			classroom.setTeachbuilding(build);
			classroom.setPrincipal(stu);	
			classroom.setClassroom_num(add_classroom_num);
			session.beginTransaction();
			if(submit_type.equals("add"))
				session.save(classroom);
			else if(submit_type.equals("update"))
				session.update(classroom);
			
			session.getTransaction().commit();
			
			this.status = "ok";
		}
		
		
		classroom_criteria = session.createCriteria(Classroom.class);		
		classroom_criteria.add(Restrictions.eq("teachbuilding.build_id", build_id));
		classroom_criteria.addOrder(Order.asc("classroom_num"));
		classroom_list= classroom_criteria.list();
		classroomHtml = util.Util.getJspOutput("/jsp/classroom/classroomTable.jsp");

		session.close();
		System.out.println("add ok!");
		
//		classroomList();
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
	public int getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
