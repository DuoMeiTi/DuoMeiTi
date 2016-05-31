//package student;

//import java.sql.Date;
//import java.util.List;
//import java.util.Set;
//
//import org.hibernate.Criteria;
//import org.hibernate.FetchMode;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//
//import model.CheckRecord;
//import model.Classroom;
//import model.RepairRecord;
//import model.Repertory;
//
//import com.opensymphony.xwork2.ActionContext;
//import com.opensymphony.xwork2.ActionSupport;
//
//import db.MyHibernateSessionFactory;

//public class ClassroomDetailAction extends ActionSupport{
//	public int build_id;
//	
//	public int classroom_id;
//	
//	public List<Repertory> repertory;
//	
//	public List<CheckRecord> checkrecords;
//	
//	public List<RepairRecord> repairrecords;
//	public List classroom_repertory_list;
//	
//	public List<Repertory> rtClass;
//	
//	public String execute() {
//		Session session = model.Util.sessionFactory.openSession();
//		//query current select classroom
//		String repertory_hql="select r from Repertory r where r.classroom="+classroom_id;
//		repertory=session.createQuery(repertory_hql).list();
//		ActionContext.getContext().getSession().remove("classroom_id");
//		ActionContext.getContext().getSession().put("classroom_id", classroom_id);
//
//		
//		//query at most 5 checkrecord
//		checkrecords = session.createCriteria(CheckRecord.class)
//				.add(Restrictions.eq("classroom.id", classroom_id))
//				.addOrder(Order.desc("id"))
//				.setMaxResults(5)
//				.list();;
//		
////		checkrecord_criteria.add(Restrictions.eq("classroom.id", classroom_id));
////		checkrecord_criteria.addOrder(Order.desc("id"));
////		long check_rowCount = (Long) checkrecord_criteria.setProjection(  
////                Projections.rowCount()).uniqueResult();
////		int checkrecord_start = ((int) check_rowCount) > 5 ? ((int) check_rowCount) - 5 : 0;
////		checkrecord_criteria.setProjection(null);
////		checkrecord_criteria.setFirstResult(checkrecord_start);
////		checkrecord_criteria.setMaxResults(5);
////		checkrecords = checkrecord_criteria.list();
//
//		long repair_rowCount = (Long) session.createQuery("select count(*) from RepairRecord as rd left join rd.device as ry left join ry.classroom as cm where cm.id=" + classroom_id).uniqueResult();
////		int repairrecord_start = ((int) repair_rowCount) > 5 ? ((int) repair_rowCount) - 5 : 0;
////		System.out.println("s:"+repairrecord_start);
//		repairrecords = (List) session.createQuery("select rd "
//												+ "from RepairRecord as rd "
//												+ "left join rd.device as ry "
//												+ "left join ry.classroom as cm  "
//												+ "where cm.id=" + classroom_id + " desc by rd.repairdate")
//										.setMaxResults(5).list();
//		
//		
//		
//		
////		List classroom_repertory_list;
//		
//		classroom_repertory_list = session.createCriteria(model.Repertory.class).add(Restrictions.eq("classroom.id", classroom_id)).list();
//		
////		System.out.println("JJ");
////		System.out.println(classroom.repertorys);
////		System.out.println(classroom_repertory_list);
//		
//		session.close();
//		return SUCCESS;
//	}
//
//	public int getBuild_id() {
//		return build_id;
//	}
//
//	public void setBuild_id(int build_id) {
//		this.build_id=build_id;
//	}
//
//	public int getClassroom_id() {
//		return classroom_id;
//	}
//
//	public void setClassroom_id(int classroom_id) {
//		this.classroom_id = classroom_id;
//	}
//
//}
