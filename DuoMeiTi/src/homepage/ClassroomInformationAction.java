package homepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import page.PageBean;
import page.PageMessage;
import model.Classroom;
import model.Repertory;
import model.StudentProfile;
import model.TeachBuilding;
import model.User;

import com.opensymphony.xwork2.ActionSupport;

import dto.T_Classroom;

public class ClassroomInformationAction extends ActionSupport implements RequestAware {
	public int build_id;
	
	public String build_name;
	
	public List<T_Classroom> classrooms;
	
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
	
	public String classroomList() throws Exception {
		Session session = model.Util.sessionFactory.openSession();
		Criteria classroom_criteria = session.createCriteria(Classroom.class).setFetchMode("repertorys", FetchMode.SELECT).setFetchMode("checkrecords", FetchMode.SELECT);

		////////////////////////
		if (currPage == 0) {
			currPage = 1;
		}
		
		long rowCount = (Long) classroom_criteria.setProjection(  
                Projections.rowCount()).uniqueResult();
		classroom_criteria.setProjection(null);
		 
		//获取分页信息
		PageBean pageBean = PageMessage.getPageMessage(currPage, (int) rowCount);

		classroom_criteria.setFirstResult(pageBean.getBeginIndex());
		classroom_criteria.setMaxResults(pageBean.getPageSize());
		/*
		 * 
		 *获取结果集,这里是和hibernate结合使用，所以参数需要传一个beginIndex
		 * pageSize写在配置文件里面，所以这里就不用当做参数传递了
		 */
		StringBuilder sb = new StringBuilder();
		
		//带有参数的URL
		sb.append("classroom_manage?build_id=").append(build_id).append("&build_name=").append(build_name).append("&");
		path = sb.toString();
//		request.put("users", list);
		request.put("path", path) ;
		request.put("pageBean", pageBean);
		/////////////////////////

		classroom_criteria.add(Restrictions.eq("teachbuilding.build_id", build_id));
		classroom_criteria.addOrder(Order.asc("classroom_num"));
		List<Classroom> classroom_list= classroom_criteria.list();
		Classroom classroom;
		classrooms = new ArrayList<T_Classroom>();
		for(int i=0;i<classroom_list.size();i++){
			classroom = classroom_list.get(i);
			T_Classroom t_classroom = new T_Classroom();
			t_classroom.id = classroom.id;
//			t_classroom.capacity = classroom.capacity;
			t_classroom.classroom_num = classroom.classroom_num;
			t_classroom.principal_name = classroom.principal == null ? "" : classroom.principal.user.getFullName();
			t_classroom.principal_stuId = classroom.principal == null ? "" : classroom.principal.getStudentId();
			StringBuilder rsb = new StringBuilder();
//			for(Repertory r : classroom.repertorys) {
//				rsb.append(r.getRtType() + "  ");
//			}
			t_classroom.repertorys = rsb.toString();
			classrooms.add(t_classroom);
		}
		session.close();
		
		
		return SUCCESS;
	}
	
	public String classroom_search() throws Exception {
		if(searchselect == null)
		{
			searchselect = "1";
		}
		Session session = model.Util.sessionFactory.openSession();
		Criteria classroom_criteria = session.createCriteria(Classroom.class).setFetchMode("repertorys", FetchMode.SELECT).setFetchMode("checkrecords", FetchMode.SELECT);
		
		if (currPage == 0) {
			currPage = 1;
		}
		
		long rowCount = (Long) classroom_criteria.setProjection(  
                Projections.rowCount()).uniqueResult();  
		classroom_criteria.setProjection(null);
		 
		//获取分页信息
		PageBean pageBean = PageMessage.getPageMessage(currPage, (int) rowCount);

		classroom_criteria.setFirstResult(pageBean.getBeginIndex());
		classroom_criteria.setMaxResults(pageBean.getPageSize());
		
		classroom_criteria.add(Restrictions.eq("teachbuilding.build_id", build_id));
		if(query_condition != null && !query_condition.equals("")) {
			if(searchselect.equals("1")){
				classroom_criteria.add(Restrictions.eq("classroom_num", query_condition));
			}
			else{
				Criteria user_criteria = session.createCriteria(User.class);
				user_criteria.add(Restrictions.eq("user.fullName", query_condition));
				List<User> userlist = user_criteria.list();
				
				Criteria stu_criteria = session.createCriteria(StudentProfile.class);
				HashMap<String, Integer> usrIdMap = new HashMap<String, Integer>();
				
				for(User u : userlist) 
					usrIdMap.put("user.id", u.getId());
				stu_criteria.add(Restrictions.allEq(usrIdMap));
				List<StudentProfile> stulist = stu_criteria.list();
				
				HashMap<String, Integer> stuIdMap = new HashMap<String, Integer>();
				for(StudentProfile s : stulist) 
					stuIdMap.put("principal.id", s.id);
				
				classroom_criteria.add(Restrictions.allEq(stuIdMap));
			}
		}
		
		List<Classroom> classroom_list= classroom_criteria.list();
		Classroom classroom;
		classrooms = new ArrayList<T_Classroom>();
		StringBuilder htmlsb = new StringBuilder();
		for(int i=0;i<classroom_list.size();i++){
			classroom = classroom_list.get(i);
			T_Classroom t_classroom = new T_Classroom();
			t_classroom.id = classroom.id;
//			t_classroom.capacity = classroom.capacity;
			t_classroom.classroom_num = classroom.classroom_num;
			t_classroom.principal_name = classroom.principal == null ? "" : classroom.principal.user.getFullName();
			t_classroom.principal_stuId = classroom.principal == null ? "" : classroom.principal.getStudentId();
			StringBuilder sb = new StringBuilder();
//			for(Repertory r : classroom.repertorys) {
//				sb.append(r.getRtType() + "  ");
//			}
			t_classroom.repertorys = sb.toString();
System.out.println(classroom.id + " " + classroom.classroom_num + " " + classroom.principal.user.getUsername() + " " + sb.toString());
			classrooms.add(t_classroom);
			htmlsb.append(util.Util.fileToString("/jsp/admin/widgets/classroominfo.html"));
		}
		this.classroominfo_html = htmlsb.toString();
		session.close();		
		this.status = "0";
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
