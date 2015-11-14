package dao;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import util.PageGetBaseAction;
import model.CheckInRecord;
import model.StudentProfile;
import model.Util;

public class CheckInRecordDao extends BaseDaoHibernate<CheckInRecord>{ 
	BaseDaoHibernate<CheckInRecord> checkindao = new BaseDaoHibernate<CheckInRecord>();
	
	@Override
	public void save(CheckInRecord obj) {
		// TODO Auto-generated method stub
		checkindao.save(obj);
	}

	public void checkIn(String username)
	{
		StudentProfileDao studentdao = new StudentProfileDao();
		System.out.println("checkindao username "+username);
		StudentProfile student = studentdao.getStudentProfileByUsername(username);
		CheckInRecord record = new CheckInRecord();
		record.student=student;
		record.recordtime=new Timestamp(System.currentTimeMillis());
		checkindao.save(record);
	}
	
	public List<CheckInRecord> getRecordByDate(Timestamp starttime,Timestamp endtime,int page,int pagesize)
	{
		List<CheckInRecord> list = new LinkedList<CheckInRecord>();;
		Session session =Util.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria criteria = session.createCriteria(CheckInRecord.class);
			criteria.add(Restrictions.between("recordtime", starttime, endtime));
			criteria.addOrder(Order.desc("recordtime"));
			System.out.println("starttime "+starttime.toString()+" endtime "+endtime.toString());
			PageGetBaseAction pba =new PageGetBaseAction();
			pba.setCurrentPageNum(page);
			list = pba.makeCurrentPageList(criteria, pagesize);
			System.out.println("list "+list.size());
			tx.commit();
		}catch(HibernateException e)
		{
			tx.rollback();
		}finally{
			session.close();
		}
		return list;
	}
	
	public List<CheckInRecord> getRecordByUserName(String username,int page,int pagesize)
	{
		List<CheckInRecord> list = new LinkedList<CheckInRecord>();
		Session session =Util.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			StudentProfile student = (new StudentProfileDao()).getStudentProfileByUsername(username);
			Criteria criteria = session.createCriteria(CheckInRecord.class).add(Restrictions.eq("student.id",student.id ));
			criteria.addOrder(Order.desc("recordtime"));
			PageGetBaseAction pba =new PageGetBaseAction();
			pba.setCurrentPageNum(page);
			list = pba.makeCurrentPageList(criteria, pagesize);
			System.out.println("checkinDao list "+list.size());
		}catch(HibernateException e)
		{
			e.printStackTrace();
			System.out.println("HibernateException e");
			tx.rollback();
		}finally{
			session.close();
		}
		return list;
	}
}
