package dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import model.User;
import model.Util;

public class UserDao extends BaseDaoHibernate<User>{
	private BaseDaoHibernate<User> userdao = new BaseDaoHibernate<User>();
	
	@Override
	public void save(User obj) {
		// TODO Auto-generated method stub
		userdao.save(obj);
	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub
		userdao.update(entity);
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		userdao.delete(entity);
	}
	
	public User getByusername(String username)
	{
		Session session =Util.sessionFactory.openSession();
//		Transaction tx = session.beginTransaction(); 
		Criteria criteriauser = session.createCriteria(User.class).add(Restrictions.eq("username",username));
		User user =  (User) criteriauser.uniqueResult();
//		tx.commit();
		session.close();
		return user;
	}
}
