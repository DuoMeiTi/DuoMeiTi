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
							+ "sP.fullName, "
							+ "rR.device.rtType, "
							+ "rR.repairdetail, "
							+ "rR.device.classroom.classroom_num, "
							+ "rR.device.classroom.teachbuilding.build_name, "
							+ "rR.repairdate"
							+ ") "
				+ "FROM "
							+ "RepairRecord rR, StudentProfile sP "
				+ "WHERE "
							+ "rR.repairman = sP.user";
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
	
}
