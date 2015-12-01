package dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	void save(T obj);
	T get(Class<T> entityClass,Serializable id);
	void update(T entity);
	void delete(T entity);
	List<T> findALL(Class<T> entityClass);
	int getAmount(Class<T> entityClass);
}
