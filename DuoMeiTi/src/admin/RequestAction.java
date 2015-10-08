package admin;
import java.io.File;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

import model.StudentProfile;
import model.User;

public class RequestAction {
	
	private int isPassed;
	private List<StudentProfile> student_list;
	private int userid;
	private String strValue;
	/*private List<User> user_list;*/
	
	
	/*public List<User> getUser_list() {
		return user_list;
	}

	public void setUser_list(List<User> user_list) {
		this.user_list = user_list;
	}*/

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getIsPassed() {
		return isPassed;
	}

	public void setIsPassed(int isPassed) {
		this.isPassed = isPassed;
	}

	public List<StudentProfile> getStudent_list() {
		return student_list;
	}

	public void setStudent_list(List<StudentProfile> student_list) {
		this.student_list = student_list;
	}

	public String studentRequest() throws Exception{
		try{
			Session session=model.Util.sessionFactory.openSession();
			Criteria q=session.createCriteria(StudentProfile.class)
//					.setFetchMode("classrooms", FetchMode.SELECT)
					
					
					;
			/*Criteria u=session.createCriteria(User.class);*/
			/*user_list=u.list();*/
			student_list=q.list();
			System.out.println(student_list);
			Collections.reverse(student_list);
			/*Collections.reverse(user_list);*/
			session.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ActionSupport.SUCCESS;
	}
	
	public String studentRequestEnsure() throws Exception
	{
		System.out.println("sddd");
		
		Session session = model.Util.sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		String hql="update StudentProfile s set s.isPassed="+
					String.valueOf(isPassed)+" where s.id="+String.valueOf(userid);
		Query q=session.createQuery(hql);
		int ret = q.executeUpdate();
		System.out.println(ret);
		trans.commit();
		
		if(ret>0){
			strValue=ActionSupport.SUCCESS;
			return ActionSupport.SUCCESS;
		}
		else return ActionSupport.ERROR;
	}
	
	public String studentRequestResult() throws Exception{
		try{
			Session session=model.Util.sessionFactory.openSession();
			Criteria q=session.createCriteria(StudentProfile.class);
			/*Criteria u=session.createCriteria(User.class);*/
			/*user_list=u.list();*/
			student_list=q.list();
			Collections.reverse(student_list);
			/*Collections.reverse(user_list);*/
			session.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ActionSupport.SUCCESS;
	}
	
	public String studentRequestChange() throws Exception{
		Session session = model.Util.sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		String hql="update StudentProfile s set s.isPassed=0"+
				" where s.id="+String.valueOf(userid);
		Query q=session.createQuery(hql);
		int ret = q.executeUpdate();
		System.out.println(ret);
		trans.commit();
		
		if(ret>0){
			strValue=ActionSupport.SUCCESS;
			return ActionSupport.SUCCESS;
		}
		else return ActionSupport.ERROR;
		
	}
}