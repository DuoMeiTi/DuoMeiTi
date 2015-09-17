package homepage;


import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//import model.StudentProfile;


import model.User;
import util.Util;

public class UserAction
{
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





	private String username;
	private String password;
	private String status;
	private List<User> user_list;
	private String added_user_html;
	
	private int user_id;


	/*
	 * status 0: OK
	 * 		  1: username 或者password 为空
	 * 		  2: username 重复
	 */
	

	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String login() throws Exception
	{		
		if(username == null || username == "")
		{
			return ActionSupport.SUCCESS;
		}
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		
		session.put("username", username);
		return "login_success";
	}
	public String logout() throws Exception
	{		
	    Map session = ActionContext.getContext().getSession();  
	    session.remove("username");
	    return ActionSupport.SUCCESS;
	}
	public String register() throws Exception
	{    	
		

		
		Session session = model.Util.sessionFactory.openSession();
//        Query q = session.createSQLQuery("select * from usermodel").addEntity(UserModel.class);
		
        
		Criteria q = session.createCriteria(User.class);//把查询条件封装成一个Criteria对象
		
		user_list = q.list();
		Collections.reverse(user_list);
		session.close();	
		
		
		
		
		



//		String u = util.Util.fileToString("/jsp/homepage/widgets/added_user.html");
//		System.out.println(u);
		
		

		return "success";
	}

	public String save() throws Exception
	{
		if(username == null || password == null)
		{
			this.status = "error: username or password is null";
			return ActionSupport.SUCCESS;
		}
		if(username.equals("") || password.equals(""))
		{
			this.status = "1";
			return ActionSupport.SUCCESS;
		}		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(User.class).add(Restrictions.eq("username", username));
		List ul = q.list();
		if(!ul.isEmpty())
		{
			this.status = "2";
		}
		else
		{
			User um = new User();
			um.setUsername(username);
			um.setPassword(password);
			
			
			session.beginTransaction();
			session.save(um);
			
			session.getTransaction().commit();
			this.status = "0";
			this.user_id = um.getId();
			this.added_user_html = util.Util.fileToString("/jsp/homepage/widgets/added_user.html");
		}
		session.close();
		return ActionSupport.SUCCESS;
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
}
