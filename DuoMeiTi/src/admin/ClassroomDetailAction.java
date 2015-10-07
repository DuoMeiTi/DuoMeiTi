package admin;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import model.CheckRecord;
import model.Classroom;
import model.RepairRecord;
import model.Repertory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClassroomDetailAction extends ActionSupport {
	public String build_name;
	
	public int classroomId;
	
	public Classroom classroom;
	
	public List<CheckRecord> checkrecords;
	
	public List<RepairRecord> repairrecords;
	
	/*public List<Repertory> rtClass;*/
	
	public String execute() {
		Session session = model.Util.sessionFactory.openSession();
		//query current select classroom
		Criteria classroom_criteria = session.createCriteria(Classroom.class);
		classroom_criteria.add(Restrictions.eq("id", classroomId));
		classroom = (Classroom) classroom_criteria.uniqueResult();
		ActionContext.getContext().getSession().remove("classroom_id");
		ActionContext.getContext().getSession().put("classroom_id", classroom.id);
//System.out.println("rt_size:" + classroom.repertorys.size());
		
		/*String hql = "SELECT rt FROM Repertory rt WHERE rt.classroom = " + classroomId;
		Query query = session.createQuery(hql);
		rtClass = query.list();
		for (int i = 0; i < rtClass.size(); i++) {
			System.out.println("输出++++++++++++++++++++++++++++++++");
			System.out.println(rtClass.get(i));
		}*/

		//query at most 5 checkrecord
		Criteria checkrecord_criteria = session.createCriteria(CheckRecord.class).setFetchMode("classroom", FetchMode.SELECT).setFetchMode("checkman", FetchMode.SELECT);
		
		checkrecord_criteria.add(Restrictions.eq("classroom.id", classroomId));
		checkrecord_criteria.addOrder(Order.asc("checkdate"));
		long check_rowCount = (Long) checkrecord_criteria.setProjection(  
                Projections.rowCount()).uniqueResult();
		int checkrecord_start = ((int) check_rowCount) > 5 ? ((int) check_rowCount) - 5 : 0;
		checkrecord_criteria.setProjection(null);
		checkrecord_criteria.setFirstResult(checkrecord_start);
		checkrecord_criteria.setMaxResults(5);
		checkrecords = checkrecord_criteria.list();
//System.out.println("checksize:"+checkrecords.size());
		
		
		
		//query repairrecord
		long repair_rowCount = (Long) session.createQuery("select count(*) from RepairRecord as rd left join rd.device as ry left join ry.classroom as cm where cm.id=" + classroomId).uniqueResult();
		int repairrecord_start = ((int) repair_rowCount) > 5 ? ((int) repair_rowCount) - 5 : 0;
//		System.out.println("s:"+repairrecord_start);
		repairrecords = (List) session.createQuery("select rd "
												+ "from RepairRecord as rd "
												+ "left join rd.device as ry "
												+ "left join ry.classroom as cm  "
												+ "where cm.id=" + classroomId + " order by rd.repairdate").setFirstResult(repairrecord_start).setMaxResults(5).list();
//		System.out.println("repairsize:"+repairrecords.size());
//		for(RepairRecord r : repairrecords) {
//			System.out.println(r.repairman);
//		}
		
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
