package RepairImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;

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
	
	@Override
	public List<Repertory> alterDevice(String classroom_id) {
		List<Repertory> list = null;
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "SELECT rt FROM Repertory as rt WHERE rt.rtDeviceStatus = '备用'"; //AND rt.classroom = " + classroom_id;
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
	public int m2alter(String move, String opt) {
		Transaction tx = null;
		String hql ="";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			if (opt.equals("0")) {
				hql = "UPDATE Repertory repertory SET repertory.rtDeviceStatus = '维修', repertory.classroom = NULL" +
						" where repertory.rtId = " + move;
			}
			else if(opt.equals("1")){//移入报废 1
				hql = "UPDATE Repertory repertory SET repertory.rtDeviceStatus = '报废', repertory.classroom = NULL" +
						" where repertory.rtId = " + move;
			}else{
				hql = "UPDATE Repertory repertory SET repertory.rtDeviceStatus = '备用', repertory.classroom = NULL" +
						" where repertory.rtId = " + move;
			}
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
	
	@Override
	public int addalterIm(String rtid, String classroomid) {
		Transaction tx = null;
		String hql ="";
		try {
			/*int classroomid = (int) ActionContext.getContext().getSession().get("classroom_id");
			Classroom classroom = (Classroom) ActionContext.getContext().getSession().get("classroom");*/
			
			Session session1 = model.Util.sessionFactory.openSession();
			Criteria rt_criteria = session1.createCriteria(Repertory.class);
			rt_criteria.add(Restrictions.eq("rtId", Integer.parseInt(rtid)));
			Repertory rt_per = (Repertory) rt_criteria.uniqueResult();
			session1.close();
			
			Session session2 = model.Util.sessionFactory.openSession();
			Criteria cl_criteria = session2.createCriteria(Classroom.class);
			cl_criteria.add(Restrictions.eq("id", Integer.parseInt(classroomid)));
			Classroom classroom = (Classroom) cl_criteria.uniqueResult();
			session2.close();
			
			Calendar cal=Calendar.getInstance();
			Date date=cal.getTime();
			cal.add(Calendar.DATE, rt_per.rtReplacePeriod);
			date=cal.getTime();
			java.sql.Date sqldate = new java.sql.Date(date.getTime());
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "UPDATE Repertory repertory SET repertory.rtDeviceStatus = '教室', repertory.classroom =:classroom, repertory.rtDeadlineData =:deadline" +
					" where repertory.rtId = " + rtid;
			System.out.println(hql);
			Query queryupdate=session.createQuery(hql);
			queryupdate.setEntity("classroom", classroom);
			queryupdate.setDate("deadline", sqldate);
			int ret=queryupdate.executeUpdate();
			tx.commit();
			System.out.println("这是"+ret);
			System.out.println("好了行不" +sqldate);
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
	
	@Override
	public int delalterIm(String rtid){
		Transaction tx = null;
		String hql ="";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "UPDATE Repertory repertory SET repertory.classroom = NULL" +
					" where repertory.rtId = " + rtid;
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
