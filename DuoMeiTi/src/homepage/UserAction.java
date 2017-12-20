package homepage;


import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.FlushModeType;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jasig.cas.client.authentication.AttributePrincipal;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//import common.ChooseClass;

//import model.StudentProfile;


import model.User;
import model.StudentProfile;
import util.Util;

public class UserAction
{
	public String username;
	public String password;
	public String status;
	public List<User> user_list;
	public String added_user_html;
//	public String role;
	
	public int user_id;
	


	/*
	 * status 0: OK
	 * 		  1: username 或者password 为空
	 * 		  2: username 重复
	 */
	

	
	
	
	public String login() throws Exception
	{
		final String login_fail = "login_fail";
		
		this.status = "默认！！！";
		if(ServletActionContext.getRequest().getMethod().equalsIgnoreCase("get"))
		{
			return ActionSupport.SUCCESS;
		}
		
		
		
		if(username == null || username == "")
		{
			this.status = "用户名不能为空";
			return login_fail;
		}
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(User.class).add(Restrictions.eq("username", username));
		List ul = q.list();		
		
		if(ul.isEmpty())
		{
			session.close();
			return login_fail;
		}
		User u = (User)ul.get(0);		
		if(!u.getPassword().equals(password)) 
		{
			session.close();
			return login_fail;
		}
		
		String role = "";
		ul = session.createCriteria(model.AdminProfile.class).add(Restrictions.eq("user.id", u.getId())).list();
		if(!ul.isEmpty())
		{
			role = util.Const.AdminRole;
		}
		else 
		{
			ul = session.createCriteria(model.StudentProfile.class).add(Restrictions.eq("user.id", u.getId())).list();
			if(!ul.isEmpty())
			{
				model.StudentProfile loginStudent = (StudentProfile)ul.get(0);
				if(loginStudent.isPassed != model.StudentProfile.Passed)
				{
					session.close();
					return login_fail;
				}
				
				role = util.Const.StudentRole;
				if(loginStudent.getIsUpgradePrivilege() == 1)
				{
					role = util.Const.StudentToAdminRole;
				}
				
				ActionContext.getContext().getSession().put("student_id", loginStudent.getId());
			}
			else 
			{
				session.close();
				return login_fail;
			}
		}
		
		
		session.close();
//		System.out.println(u.getPassword());
//		System.out.println(u.username);
		if(!u.getPassword().equals(password)) return login_fail;

		
		ActionContext.getContext().getSession().put("username", username);
		ActionContext.getContext().getSession().put("fullName", u.getFullName());
		ActionContext.getContext().getSession().put("role", role);
		ActionContext.getContext().getSession().put("user_id", u.getId());
//		ActionContext.getContext().getSession().containsKey(key)
		
		if(role.equals(util.Const.AdminRole))
		{
//			ChooseClass.insertDataToDutyTimeTable();
//			ChooseClass.insertDataToChooseClassSwitchTable();
			return "admin_login_success";
		}
		else if(role.equals(util.Const.StudentRole) || role.equals(util.Const.StudentToAdminRole))
		{	
//			ChooseClass.insertDataToDutyTimeTable();
//			ChooseClass.insertDataToChooseClassSwitchTable();
			return "student_login_success";
		}
		System.out.println("ERROR");
		return "SB";
	
		
	
	

		
	}
	public String logout() throws Exception
	{		
	    ActionContext.getContext().getSession().remove("username");
	    ActionContext.getContext().getSession().remove("fullName");
	    ActionContext.getContext().getSession().remove("role");
	    ActionContext.getContext().getSession().remove("user_id");
	    return ActionSupport.SUCCESS;
	}
	public String register()
//			throws Exception
	{
		System.out.println("SDFSFSFAS");
		try{
			Session session = model.Util.sessionFactory.openSession();
			System.out.println("SDFSFSFAS**1");
//			session.clear();
//			session.setFlushMode(FlushMode.AUTO);
			Criteria q = session.createCriteria(User.class);//把查询条件封装成一个Criteria对象
			System.out.println("SDFSFSFAS**2");
			user_list = q.list();
			System.out.println("SDFSFSFAS**3");
			Collections.reverse(user_list);
			session.close();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "success";
	}
	public static int G = 0;
	public String save() throws Exception
	{
		G++;
//		if(G % 100 == 0)
		{
			System.out.println("SB*******UI" + G);
		}
		return ActionSupport.SUCCESS;
		
		
//		if(username == null || password == null)
//		{
//			this.status = "error: username or password is null";
//			return ActionSupport.SUCCESS;
//		}
//		if(username.equals("") || password.equals(""))
//		{
//			this.status = "1";
//			return ActionSupport.SUCCESS;
//		}		
//		Session session = model.Util.sessionFactory.openSession();
//		Criteria q = session.createCriteria(User.class).add(Restrictions.eq("username", username));
//		List ul = q.list();
//		if(!ul.isEmpty())
//		{
//			this.status = "2";
//		}
//		else
//		{
//			User um = new User();
//			um.setUsername(username);
//			um.setPassword(password);
//			
//			
//			session.beginTransaction();
//			session.save(um);
//			
//			session.getTransaction().commit();
//			this.status = "0";
//			this.user_id = um.getId();
//			this.added_user_html = util.Util.fileToString("/jsp/homepage/widgets/added_user.html");
//		}
//		session.close();
//		return ActionSupport.SUCCESS;
	}
	
	
	public String delete() throws Exception
	{
		System.out.print("gogo");
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(User.class).add(Restrictions.eq("id", user_id));//把具体的查询条件通过add方法加入到criteria实例中
		List ul = q.list();
		
		if(ul.isEmpty())
		{
			System.out.println("ISTMPETYLSDJKFLJSDFKLJ");
			this.status = "1"; // error
			
		}
		else 
		{
			System.out.println("三处：：：：：：：：：：");
			System.out.println(user_id);
			session.beginTransaction();
			session.delete(ul.get(0));
			session.getTransaction().commit();
			this.status = "0"; // ok
		}
		
		session.close();
		
		return ActionSupport.SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<User> getUser_list() {
		return user_list;
	}
	public void setUser_list(List<User> user_list) {
		this.user_list = user_list;
	}
	public String getAdded_user_html() {
		return added_user_html;
	}
	public void setAdded_user_html(String added_user_html) {
		this.added_user_html = added_user_html;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	
	
	
	
	
	


	
	
	
	
	
}
