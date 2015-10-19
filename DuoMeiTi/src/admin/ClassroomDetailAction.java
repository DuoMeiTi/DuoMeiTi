package admin;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import model.CheckRecord;
import model.Classroom;
import model.RepairRecord;
import model.Repertory;
import model.TeachBuilding;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import db.MyHibernateSessionFactory;

public class ClassroomDetailAction extends ActionSupport{
	public String build_name;
	
	public int classroomId;
	
	public TeachBuilding building;
	public Classroom classroom;
	
	public List<CheckRecord> checkrecords;
	
	public List<RepairRecord> repairrecords;
	public List classroom_repertory_list;
	
	public List<Repertory> rtClass;
	
	public String execute() {
		Session session = model.Util.sessionFactory.openSession();
		//query current select classroom
		Criteria classroom_criteria = session.createCriteria(Classroom.class);
		Criteria building_criteria = session.createCriteria(TeachBuilding.class);
		classroom_criteria.add(Restrictions.eq("id", classroomId));
		classroom = (Classroom) classroom_criteria.uniqueResult();
		building_criteria.add(Restrictions.eq("build_id", classroom.teachbuilding.build_id));
		building = (TeachBuilding) building_criteria.uniqueResult();
		
		ActionContext.getContext().getSession().remove("classroom_id");
		//ActionContext.getContext().getSession().remove("classroom");
		ActionContext.getContext().getSession().put("classroom_id", classroom.id);
		//ActionContext.getContext().getSession().put("classroom", classroom);
		
		/*String hql = "SELECT rt FROM Repertory rt WHERE rt.classroom = " + classroomId;
		Query query = session.createQuery(hql);
		rtClass = query.list();
		for (int i = 0; i < rtClass.size(); i++) {
			System.out.println("输出++++++++++++++++++++++++++++++++");
			System.out.println(rtClass.get(i));
		}*/
		
		Transaction tx = null;
		String hql ="";
		try {
			Session session1 = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session1.beginTransaction();
			hql = "SELECT rt FROM Repertory rt WHERE rt.rtDeviceStatus = '教室' AND rt.classroom = " + classroomId;
			System.out.println(hql);
			Query query = session1.createQuery(hql);
			rtClass = query.list();
			for (int i = 0; i < rtClass.size(); i++) {
				System.out.println("输出++++++++++++++++++++++++++++++++");
				System.out.println(rtClass.get(i));
			}
			tx.commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			tx.commit();
		}
		finally {
			if (tx != null) {
				if (tx != null) {
					tx = null;
				}
			}
		}

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
		
		
		
		
//		List classroom_repertory_list;
		
		classroom_repertory_list = session.createCriteria(model.Repertory.class).add(Restrictions.eq("classroom.id", classroomId)).list();
		
		System.out.println("JJ");
//		System.out.println(classroom.repertorys);
		System.out.println(classroom_repertory_list);
		
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
