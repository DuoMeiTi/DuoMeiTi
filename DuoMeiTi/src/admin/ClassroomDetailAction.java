package admin;

import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.CheckRecord;
import model.Classroom;
import model.Repertory;

import com.opensymphony.xwork2.ActionSupport;

public class ClassroomDetailAction extends ActionSupport {
	public String build_name;
	
	public int classroomId;
	
	public Classroom classroom;
	
	public String execute() {
		Session session = model.Util.sessionFactory.openSession();
		Criteria classroom_criteria = session.createCriteria(Classroom.class);
		classroom_criteria.add(Restrictions.eq("id", classroomId));
		classroom = (Classroom) classroom_criteria.uniqueResult();
		System.out.println("rt_size:" + classroom.repertorys.size());
		return SUCCESS;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	public String getBuild_name() {
		return build_name;
	}

	public void setBuild_name(String build_name) {
		this.build_name = build_name;
	}

	public int getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}

}
