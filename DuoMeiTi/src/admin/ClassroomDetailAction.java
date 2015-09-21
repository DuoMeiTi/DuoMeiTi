package admin;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import model.CheckRecord;
import model.Classroom;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClassroomDetailAction extends ActionSupport {
	public String build_name;
	
	public int classroomId;
	
	public Classroom classroom;
	
	public List<CheckRecord> checkrecords;
	
	public String execute() {
		Session session = model.Util.sessionFactory.openSession();
		Criteria classroom_criteria = session.createCriteria(Classroom.class);
		classroom_criteria.add(Restrictions.eq("id", classroomId));
		classroom = (Classroom) classroom_criteria.uniqueResult();
		ActionContext.getContext().getSession().remove("classroom_id");
		ActionContext.getContext().getSession().put("classroom_id", classroom.id);
//System.out.println("rt_size:" + classroom.repertorys.size());
		
		Criteria checkrecord_criteria = session.createCriteria(CheckRecord.class).setFetchMode("classroom", FetchMode.SELECT).setFetchMode("checkman", FetchMode.SELECT);
		
		
		checkrecord_criteria.add(Restrictions.eq("classroom.id", classroomId));
		checkrecord_criteria.addOrder(Order.asc("checkdate"));
		long rowCount = (Long) checkrecord_criteria.setProjection(  
                Projections.rowCount()).uniqueResult();
		int start = ((int) rowCount) > 5 ? ((int) rowCount) - 5 : 0;
		checkrecord_criteria.setProjection(null);
		checkrecord_criteria.setFirstResult(start);
		checkrecord_criteria.setMaxResults(5);
		checkrecords = checkrecord_criteria.list();
System.out.println("checksize:"+checkrecords.size());
		session.close();
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
