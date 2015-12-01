package dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import model.StudentProfile;
import model.User;
import model.Util;

public class StudentProfileDao extends BaseDaoHibernate<StudentProfile>{
	private BaseDaoHibernate<StudentProfile> studentprofiledao = new BaseDaoHibernate<StudentProfile>();
	
	@Override
	public void save(StudentProfile obj) {
		// TODO Auto-generated method stub
		UserDao userdao = new UserDao();
		userdao.save(obj.user);
		studentprofiledao.save(obj);
	}
	
	@Override
	public void update(StudentProfile entity) {
		// TODO Auto-generated method stub
		UserDao userdao = new UserDao();
		userdao.update(entity.user);
		studentprofiledao.update(entity);
	}

	public StudentProfile getStudentProfileByUsername(String username)
	{
		if(username==null||username.isEmpty())
		{
			throw new NullPointerException("username is null");
		}
		UserDao userDao = new UserDao();
		int user_id =  userDao.getByusername(username).id;
		Session session =Util.sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Criteria criteriauser = session.createCriteria(StudentProfile.class).add(Restrictions.eq("user.id",user_id));
		StudentProfile student =  (StudentProfile) criteriauser.uniqueResult();
//		tx.commit();
		session.close();
		return student;
	}
	
	@Override
	public void delete(StudentProfile entity) {
		// TODO Auto-generated method stub
		UserDao userdao = new UserDao();
		userdao.delete(entity.user);
		studentprofiledao.delete(entity);
	}
}
