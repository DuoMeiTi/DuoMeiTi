package utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.omg.CORBA.PRIVATE_MEMBER;

public class DatabaseOperation {
	
	//查询单个table多个字段所有数据的通用方法
	private String tableName;
	private List fields;
	public DatabaseOperation(String tName,List fs) {
		// TODO Auto-generated constructor stub
		this.tableName=tName;
		this.fields=fs;
	}
	public List selectOperation(){
		Session session = model.Util.sessionFactory.openSession();
		String hql="select ";
		Iterator iter=fields.iterator();
		while(iter.hasNext()){
			String field=(String)(Object)iter.next();
			hql+=field;
		}
		hql+=" from "+tableName;
		System.out.println(hql);
		Query q=session.createQuery(hql);
		List selectResult = q.list();
		session.close();
		return selectResult;
	}
}
