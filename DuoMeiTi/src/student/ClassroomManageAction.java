package student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

//import page.PageBean;
//import page.PageMessage;
import model.Classroom;
import model.Repertory;
import model.StudentProfile;
import model.TeachBuilding;
import model.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import dto.T_Classroom;



public class ClassroomManageAction extends ActionSupport implements RequestAware {
	public int build_id;
	
	public String build_name;
	
	public List classrooms;
	
	//for classroom_manage.js ajax
	public String searchselect;
	
	public String query_condition;
	
	public String status;
	
	public String classroominfo_html;
	
	public int currPage;
	
//	public int pageSize;
	
	//for classroom_manage.js ajax
	public String queryResult;
	
	public String stuId;
	
	public String add_classroom_num;
	
	public String add_status;
	
	public String submit_type;

	@SuppressWarnings("unchecked")
	private Map request;
	
	private String path;
	
	private TeachBuilding build;
	
	public String classroomList() throws Exception {
		
		Session session = model.Util.sessionFactory.openSession();
	
		int user_id=(int)ActionContext.getContext().getSession().get("user_id");
		
		String hql = "SELECT c.id,c.classroom_num,u.id,u.fullName,t.id from Classroom c,User u,TeachBuilding t,StudentProfile s where c.teachbuilding=t and c.principal=s and s.user=u and u.id="+user_id;

		Query query = session.createQuery(hql);	
		List result= query.list();
		
		classrooms = new ArrayList<T_Classroom>();
		//教室ID，教室号，设备名，学生ID，学生名
		for(int i=0;i<result.size();i++){
			Object[] tuple = (Object[])result.get(i);
			T_Classroom t_classroom = new T_Classroom();
			t_classroom.id = (int)tuple[0];
			t_classroom.teachbuilding_id=(int)tuple[4];
			t_classroom.classroom_num = (String)tuple[1];
			t_classroom.principal_name = (String)tuple[3];
			t_classroom.principal_stuId = String.valueOf((int)tuple[2]);
			t_classroom.repertorys =null;
			classrooms.add(t_classroom);
		}
		
		session.close();
		
		return SUCCESS;
	}
	

	public String getAdd_status() {
		return add_status;
	}

	public void setAdd_status(String add_status) {
		this.add_status = add_status;
	}

	public int getBuild_id() {
		return build_id;
	}

	public void setBuild_id(int build_id) {
		this.build_id = build_id;
	}

	public String getBuild_name() {
		return build_name;
	}

	public void setBuild_name(String build_name) {
		this.build_name = build_name;
	}

	public List<T_Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List<T_Classroom> classrooms) {
		this.classrooms = classrooms;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClassroominfo_html() {
		return classroominfo_html;
	}

	public void setClassroominfo_html(String classroominfo_html) {
		this.classroominfo_html = classroominfo_html;
	}

	public String getSearchselect() {
		return searchselect;
	}

	public void setSearchselect(String searchselect) {
		this.searchselect = searchselect;
	}

	public String getQuery_condition() {
		return query_condition;
	}

	public void setQuery_condition(String query_condition) {
		this.query_condition = query_condition;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public Map getRequest() {
		return request;
	}

	public void setRequest(Map request) {
		this.request = request;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(String queryResult) {
		this.queryResult = queryResult;
	}
	
	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getAdd_classroom_num() {
		return add_classroom_num;
	}

	public void setAdd_classroom_num(String add_classroom_num) {
		this.add_classroom_num = add_classroom_num;
	}

	public String getSubmit_type() {
		return submit_type;
	}

	public void setSubmit_type(String submit_type) {
		this.submit_type = submit_type;
	}

	/*public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}*/
}
