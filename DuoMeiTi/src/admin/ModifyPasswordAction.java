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
import util.Const;

public class ModifyPasswordAction extends ActionSupport {
	private String oldPsw;
	private String newPsw;
	private String rePsw;
	private String user_password;
	private int id;
	public List<User>user_list;
	private static User now_user;
	private int user_id;
	private String username;
	
	
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_password(){
		return user_password;
	}
	public void setUser_password(){
		this.user_password = user_password;
	}
	public String getOldPsw() {
		return oldPsw;
	}
	public void setOldPsw(String oldPsw) {
		this.oldPsw = oldPsw;
	}
	public String getNewPsw() {
		return newPsw;
	}
	public void setNewPsw(String newPsw) {
		this.newPsw = newPsw;
	}
	public String getRePsw() {
		return rePsw;
	}
	public void setRePsw(String rePsw) {
		this.rePsw = rePsw;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		
	}
	public List<User> getUser_list() {
		return user_list;
	}

	public void setUser_list(List<User> user_list) {
		this.user_list = user_list;
	}
	public static User getNow_user() {
		return now_user;
	}

	public static void setNow_user(User now_user) {
		ModifyPasswordAction.now_user = now_user;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getoldPassword() throws Exception
	{
		Session session = model.Util.sessionFactory.openSession();
		Criteria c1 = session.createCriteria(User.class).add(Restrictions.eq("username",ActionContext.getContext().getSession().get("username"))); //hibernate session创建查询
		user_list = c1.list();
		now_user = user_list.get(0);
//		System.out.println("user_id" + now_user.getId());
		user_password = now_user.getPassword();
		return SUCCESS;
	}
	public String modifyPassword() throws Exception {
		getoldPassword();
//		if(oldPsw == user_password){
//			now_user.setPassword(newPsw);
//		}
//		else{
//			System.out.println("你输入的原密码不正确");
//		}
		now_user.setPassword(newPsw);
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		session.update(now_user);
		Transaction t = session.getTransaction();
		t.commit();
		session.close();
		return ActionSupport.SUCCESS;
	}
	
}
	
