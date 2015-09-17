package admin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.Classroom;
import model.Repertory;
import model.TeachBuilding;

import com.opensymphony.xwork2.ActionSupport;

import dto.T_Classroom;

public class ClassroomManageAction extends ActionSupport {
	public int build_id;
	
	public String build_name;
	
	public List<T_Classroom> classrooms;
	
	public String searchselect;
	
	public String query_condition;
	
	public String status;
	
	public String classroominfo_html;
	
	public String classroomList() {
		Session session = model.Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Classroom.class);
		criteria.add(Restrictions.eq("teachbuilding.build_id", build_id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Classroom> list= criteria.list();
		Classroom classroom;
		classrooms = new ArrayList<T_Classroom>();
		for(int i=0;i<list.size();i++){
			classroom = list.get(i);
			T_Classroom t_classroom = new T_Classroom();
			t_classroom.id = classroom.id;
			t_classroom.capacity = classroom.capacity;
			t_classroom.classroom_num = classroom.classroom_num;
			t_classroom.principal = classroom.principal.user.getUsername();
			StringBuilder sb = new StringBuilder();
			for(Repertory r : classroom.repertorys) {
				sb.append(r.getRtType() + "  ");
			}
			t_classroom.repertorys = sb.toString();
			classrooms.add(t_classroom);
System.out.println(classroom.id);
		}
		session.close();
		return SUCCESS;
	}
	
	public String classroom_search() {
		if(query_condition==null || query_condition.equals(""))
		{
			this.status = "error: search key is null";
			return SUCCESS;
		}
		Session session = model.Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Classroom.class);
		criteria.add(Restrictions.eq("teachbuilding.build_id", build_id));
		if(searchselect.equals("1")){
			criteria.add(Restrictions.eq("classroom_num", query_condition));
		}
		else{
			criteria.add(Restrictions.eq("principal.id", Integer.parseInt(query_condition)));
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<Classroom> list= criteria.list();
		Classroom classroom;
		classrooms = new ArrayList<T_Classroom>();
		StringBuilder htmlsb = new StringBuilder();
		for(int i=0;i<list.size();i++){
			classroom = list.get(i);
			T_Classroom t_classroom = new T_Classroom();
			t_classroom.id = classroom.id;
			t_classroom.capacity = classroom.capacity;
			t_classroom.classroom_num = classroom.classroom_num;
			t_classroom.principal = classroom.principal.user.getUsername();
			StringBuilder sb = new StringBuilder();
			for(Repertory r : classroom.repertorys) {
				sb.append(r.getRtType() + "  ");
			}
			t_classroom.repertorys = sb.toString();
System.out.println(classroom.id + " " + classroom.capacity + " " + classroom.classroom_num + " " + classroom.principal.user.getUsername() + " " + sb.toString());
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
	
}
