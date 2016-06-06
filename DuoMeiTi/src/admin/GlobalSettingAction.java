package admin;


import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.AdminProfile;
import model.Repertory;
import model.StudentProfile;
import model.User;

public class GlobalSettingAction extends ActionSupport{
	
	
//	static
//	{
//		Session s = model.Util.sessionFactory.openSession();
//		
//		List L = s.createCriteria(model.SemesterFirstWeek.class)
//				 .list();
//		if(L.size() == 0)
//		{
//			s.beginTransaction();
//			model.SemesterFirstWeek ins = new model.SemesterFirstWeek();
//			
//			ins.date = new java.sql.Date(new java.util.Date().getTime());
//			s.save(ins);
//			s.getTransaction().commit();
//		}
//		s.close();
//
//	}
	java.util.Date date;
	public String execute() throws Exception
	{
		Session s = model.Util.sessionFactory.openSession();
		model.SemesterFirstWeek ins = (model.SemesterFirstWeek)
				s.createCriteria(model.SemesterFirstWeek.class)
				.uniqueResult();
		
		if(ins.date != null)
			date = new java.util.Date(ins.date.getTime());
		else 
			date = null;
		
		System.out.println("DATE::::");
		System.out.println(date);
		
		
		
		
		
		s.close();
		
		return ActionSupport.SUCCESS; 
		
	}
	public String save() throws Exception
	{
		try{
			
		
		System.out.println("ssssss--------");
		Session s = model.Util.sessionFactory.openSession();
		
		s.beginTransaction();
		
		model.SemesterFirstWeek ins = (model.SemesterFirstWeek)
				s.createCriteria(model.SemesterFirstWeek.class)
				.uniqueResult();
		
		if(date == null)
			ins.date = null;
		else
			ins.date = new java.sql.Date(date.getTime());
		s.update(ins);
		s.getTransaction().commit();

		
		
		s.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return ActionSupport.SUCCESS; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}
	
	
	

}
