package admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import page.PageBean;
import page.PageMessage;
import model.Classroom;
import model.Repertory;
import model.StudentProfile;
import model.User;

import com.opensymphony.xwork2.ActionSupport;

import dto.T_Classroom;

public class ClassroomManageAction extends ActionSupport implements RequestAware {
	public int build_id;
	
	public String build_name;
	
	public List<T_Classroom> classrooms;
	
	public String searchselect;
	
	public String query_condition;
	
	public String status;
	
	public String classroominfo_html;
	
	public int currPage;
	
//	public int pageSize;
	
	
	@SuppressWarnings("unchecked")
	private Map request;
	
	private String path;
	
	public String classroomList() throws Exception {
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
		List<Classroom> classroom_list= classroom_criteria.list();
		Classroom classroom;
		classrooms = new ArrayList<T_Classroom>();
		for(int i=0;i<classroom_list.size();i++){
			classroom = classroom_list.get(i);
			T_Classroom t_classroom = new T_Classroom();
			t_classroom.id = classroom.id;
//			t_classroom.capacity = classroom.capacity;
			t_classroom.classroom_num = classroom.classroom_num;
			t_classroom.principal = classroom.principal.getUsername();
			StringBuilder rsb = new StringBuilder();
			for(Repertory r : classroom.repertorys) {
				rsb.append(r.getRtType() + "  ");
			}
			t_classroom.repertorys = rsb.toString();
			classrooms.add(t_classroom);
		}
		session.close();
		
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
		 
System.out.println("rowcount:" + rowCount);
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
				user_criteria.add(Restrictions.eq("username", query_condition));
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
			t_classroom.principal = classroom.principal.getUsername();
			StringBuilder sb = new StringBuilder();
			for(Repertory r : classroom.repertorys) {
				sb.append(r.getRtType() + "  ");
			}
			t_classroom.repertorys = sb.toString();
System.out.println(classroom.id + " " + classroom.classroom_num + " " + classroom.principal.getUsername() + " " + sb.toString());
			classrooms.add(t_classroom);
			htmlsb.append(util.Util.fileToString("/jsp/admin/widgets/classroominfo.html"));
		}
		this.classroominfo_html = htmlsb.toString();
		session.close();		
		this.status = "0";
		return SUCCESS;
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

	/*public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}*/
}
