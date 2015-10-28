package student;
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

public class ModifyPasswordAction extends ActionSupport{
	public String oldPsw;
	public String newPsw;
	public List<User> user_list;
	private static User now_user; //当前用户
	private String username;      //当前用户名
	public String nowPsw;         //当前用户的密码
	public String status;         //比较结果
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getNowPsw() {
		return nowPsw;
	}

	public void setNowPsw(String nowPsw) {
		this.nowPsw = nowPsw;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String execute() throws Exception {
		System.out.println("建立Criteria查询");
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(User.class).add(Restrictions.eq("username",ActionContext.getContext().getSession().get("username")));
		user_list = c.list();
		now_user = user_list.get(0);
		session.close();
		nowPsw = now_user.getPassword();
		return ActionSupport.SUCCESS;
	}
	
	public String updatePsw(){
		System.out.println("更新密码");
		if(now_user.getPassword().equals(oldPsw)){
			System.out.println("111");
			now_user.setPassword(newPsw);
			status = "原密码输入正确";
		}
		else{
			status = "原密码输入错误";
			return SUCCESS;
		}
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		session.update(now_user);
		Transaction t = session.getTransaction();
		t.commit();
		session.close();
		status = "修改成功";
		
		return SUCCESS;
	}
	

}
