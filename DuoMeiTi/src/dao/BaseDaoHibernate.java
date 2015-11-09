package dao;

import java.io.Serializable;
import java.util.List;

import model.Util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

public class  BaseDaoHibernate<T> implements BaseDao<T>{
	@Override
	public void save(T obj) {
		// TODO Auto-generated method stub
		Session session = Util.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public T get(Class entityClass, Serializable id) {
		// TODO Auto-generated method stub
		Session session = Util.sessionFactory.openSession();
		session.beginTransaction();
	 	T t = (T) session.get(entityClass, id);
		session.getTransaction().commit();
		session.close();
		return t;
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		Session session = Util.sessionFactory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		Session session = Util.sessionFactory.openSession();
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<T> findALL(Class entityClass) {
		// TODO Auto-generated method stub
		Session session = Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(entityClass);
		List<T> list = criteria.list();
		session.close();
		return list;
	}

	@Override
	public int getAmount(Class entityClass) {
		// TODO Auto-generated method stub
		Session session = Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(entityClass);
		int total = (int) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return total;
	}

}
