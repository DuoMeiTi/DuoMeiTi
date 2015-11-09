package dao;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import util.PageGetBaseAction;
import model.AdminProfile;
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
		StudentProfile student = studentdao.get(StudentProfile.class, username);
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
			criteria.add(Expression.between("recordtime", starttime, endtime));
			PageGetBaseAction pba =new PageGetBaseAction();
			pba.setCurrentPageNum(page);
			list = pba.makeCurrentPageList(criteria, pagesize);
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
			Criteria criteria = session.createCriteria(CheckInRecord.class).add(Restrictions.eq("student_id",student.id ));
			PageGetBaseAction pba =new PageGetBaseAction();
			pba.setCurrentPageNum(page);
			list = pba.makeCurrentPageList(criteria, pagesize);
		}catch(HibernateException e)
		{
			tx.rollback();
		}finally{
			session.close();
		}
		return list;
	}
}
