package homepage;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jasig.cas.client.authentication.AttributePrincipal;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.User;

/**
*@author WuJie
*@date 2018年5月18日下午2:14:05
*@version 1.0
**/
public class AdminLoginAction {
	private static final long serialVersionUID = 1L;
	public String username;
	public String password;
	public String status;
	
	public String login() {
		final String admin_login_fail = "admin_login_fail";
		final String admin_login_success = "admin_login_success";
		System.out.println("ADMIN LOGIN");
		if(ServletActionContext.getRequest().getMethod().equalsIgnoreCase("get")) {
			return ActionSupport.SUCCESS;
		}
		if(username == null || username == "")
		{
			this.status = "用户名不能为空";
			return admin_login_fail;
		}
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(User.class).add(Restrictions.eq("username", username));
		List userList = q.list();
		if (userList.isEmpty()) {
			session.close();
			return admin_login_fail;
		}
		
		User user = (User) userList.get(0);
		if (!user.getPassword().equals(password)) {
			session.close();
			return admin_login_fail;
		}
		//判断是否为管理员，只允许管理员登录
		userList = session.createCriteria(model.AdminProfile.class).add(Restrictions.eq("user.id", user.getId())).list();
		String role = "";
		if (userList != null && !userList.isEmpty()) {
			role = util.Const.AdminRole;
		} else {
			return admin_login_fail;
		}
		ActionContext.getContext().getSession().put("username", username);
		ActionContext.getContext().getSession().put("fullName", user.getFullName());
		ActionContext.getContext().getSession().put("role", role);
		ActionContext.getContext().getSession().put("user_id", user.getId());
		return admin_login_success;
	}
}
