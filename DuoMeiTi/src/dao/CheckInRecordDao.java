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

import checkin.CheckInAction;
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
	
	public List<CheckInRecord> getRecordByDate(Timestamp starttime,Timestamp endtime,CheckInAction checkinaction)
	{
		List<CheckInRecord> list = new LinkedList<CheckInRecord>();;
		Session session =Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(CheckInRecord.class);
		criteria.add(Restrictions.between("recordtime", starttime, endtime));
		criteria.addOrder(Order.desc("recordtime"));
		System.out.println("starttime "+starttime.toString()+" endtime "+endtime.toString());
		list = checkinaction.makeCurrentPageList(criteria,checkinaction.getPagesize());
		System.out.println("list "+list.size());
		session.close();
		return list;
	}
	
	public List<CheckInRecord> getRecordByUserName(String username,CheckInAction checkinaction)
	{
		List<CheckInRecord> list = new LinkedList<CheckInRecord>();
		Session session =Util.sessionFactory.openSession();
		StudentProfile student = (new StudentProfileDao()).getStudentProfileByUsername(username);
		Criteria criteria = session.createCriteria(CheckInRecord.class).add(Restrictions.eq("student.id",student.id ));
		criteria.addOrder(Order.desc("recordtime"));
		list = checkinaction.makeCurrentPageList(criteria, 10);
		session.close();
		return list;
	}
}
