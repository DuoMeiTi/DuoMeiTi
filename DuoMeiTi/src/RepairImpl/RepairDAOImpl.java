package RepairImpl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Repair.RepairDAO;
import db.MyHibernateSessionFactory;
import model.*;

public class RepairDAOImpl implements RepairDAO{
	public List<Repair> queryRepair() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		List<Repair> list = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "SELECT new model.Repair( "
							+ "sP.studentId, "
							+ "u.fullName, "
							+ "rR.device.rtType, "
							+ "rR.repairdetail, "
							+ "rR.device.classroom.classroom_num, "
							+ "rR.device.classroom.teachbuilding.build_name, "
							+ "rR.repairdate"
							+ ") "
				+ "FROM "
							+ "RepairRecord rR, StudentProfile sP, User u "
				+ "WHERE "
							+ "rR.repairman = sP.user and rR.repairman = u";
			Query query = session.createQuery(hql);
			
			list = query.list();
			tx.commit();
			System.out.println(hql);
			return list;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			tx.commit();
			return list;
		}
		finally {
			if (tx != null) {
				tx = null;
			}
		}
	}
	
	@Override
	public boolean update_rtstate(Repertory rt) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(rt);
			tx.commit();
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			tx.commit();
			return false;
		}
		finally {
			if (tx != null) {
				if (tx != null) {
					tx = null;
				}
			}
		}
	}
	
}
