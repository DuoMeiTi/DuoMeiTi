package admin;

import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Repertory;
import model.User;
import util.Const;

public class ModifyPasswordAction extends ActionSupport {
	private String oldPsw;
	private String newPsw;
	private String user_password;   //当前用户的密码
	public List<User>user_list;
	private static User now_user;   //当前用户
	private String username;        //当前用户名
	private String cmpResult;       //比较结果
	
	
	
	public String getCmpResult() {
		return cmpResult;
	}
	public void setCmpResult(String cmpResult) {
		this.cmpResult = cmpResult;
	}
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
		session.close();
		user_password = now_user.getPassword();
		return SUCCESS;
	}
	public String modifyPassword() throws Exception {
		getoldPassword();
//		System.out.println(newPsw);
//		System.out.println(now_user.username);
		if(user_password.equals(oldPsw)){
			now_user.setPassword(newPsw);
			cmpResult="原密码输入正确";
		}
		else{
			cmpResult="原密码输入错误";
			return ActionSupport.SUCCESS;
		}
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		session.update(now_user);
		Transaction t = session.getTransaction();
		t.commit();
		session.close();
		cmpResult="修改成功";
		return ActionSupport.SUCCESS;
	}
	
//	public String execute() throws Exception{
//		return SUCCESS;
//	}
	
}
	
