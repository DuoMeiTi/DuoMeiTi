package RepairImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Repair.RepairDAO;
import db.MyHibernateSessionFactory;
import model.*;

public class RepairDAOImpl implements RepairDAO{
	public List<Repair> queryRepair(String type, String val) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;
		List<Repair> list = null;
		String hql = "";
		String cond = " and ";
		if ("1".equals(type))		//教学楼
			cond += ("rR.device.classroom.teachbuilding.build_id = \'" + val + "\'");
		else if ("2".equals(type))		//设备
			cond += ("rR.device.rtType = \'" + val + "\'");
		else if ("3".equals(type))		//时间
			cond += ("rR.repairdate >= \'" + val.substring(0,val.indexOf(':')) + "\' and "+
					"rR.repairdate <= \'" + val.substring(val.indexOf(':')+1) + "\'");
		System.out.println(cond);
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
			hql += cond;
			System.out.println(hql);
			Query query = session.createQuery(hql);
			
			list = query.list();
			tx.commit();
			System.out.println(hql);
			System.out.println("长度" + list.size());
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
	public int add_rt(String id, String bh) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		String hql ="";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "UPDATE Repertory repertory SET repertory.classroom = " + id + ", repertory.rtDeviceStatus = '教室'" +
					" where repertory.rtNumber = \'" + bh + "\'" + " and repertory.rtDeviceStatus = '备用'";
			System.out.println(hql);
			Query queryupdate=session.createQuery(hql);
			int ret=queryupdate.executeUpdate();
			tx.commit();
			System.out.println("这是"+ret);
			return ret;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			tx.commit();
			return 0;
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
