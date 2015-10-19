package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import model.AdminProfile;
import model.User;
import model.Util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAccountAction extends ActionSupport{

	/**
	 *该Action用于处理管理员用户的添加和删除
	 */
	private static final long serialVersionUID = 1L;
	private List allAdminProfilelist;
	private int id;
	private String username;
	private String password;
	private String fullName;
	private String sex; 
	private String profilePhotoPath;
	private String phoneNumber;
	private String remark;
	private String unitInfo;
	public String initallAdminProfilelist()
	{
		Session session = Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(AdminProfile.class);
		allAdminProfilelist = criteria.list();
		System.out.println("initalallAdminProfilelist "+allAdminProfilelist.size());
		if(allAdminProfilelist==null||allAdminProfilelist.isEmpty())
		{
			System.out.println("allAdminProfilelist is Empty");
		}
		session.close();
		return SUCCESS;
	}
	
	public String deleteAdminProfile(){
		int deleteid=(int) ServletActionContext.getContext().getSession().get("user_id");
		javax.servlet.http.HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
		if(deleteid==this.id)
		{
			out.flush();

			out.println("<script>");

			out.println("alert('账户或密码错误');");

			out.println("history.back();");

			out.println("</script>");
			return ERROR;
		}
		Session session =Util.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria criteriauser = session.createCriteria(User.class).add(Restrictions.eq("id",id ));
			Criteria criteriaadmin = session.createCriteria(AdminProfile.class).add(Restrictions.eq("id",id ));
			List<User> listuser = criteriauser.list();
			List<AdminProfile> listadmin = criteriaadmin.list();
			if(listuser.isEmpty())
			{
				return ERROR;
			}
			for(AdminProfile ap:listadmin)
			{
				session.delete(ap);
			}
			for(User user:listuser)
			{
				session.delete(user);
			}
			tx.commit();
			out.flush();
			out.println("<script>");
			out.println("alert('删除成功');");
			out.println("history.back();");
			out.println("</script>");
		}catch(HibernateException e)
		{
			tx.rollback();
		}finally{
			session.close();
		}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String addNewAdminProfile()
	{
		Session session =Util.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria criteria = session.createCriteria(AdminProfile.class);
			AdminProfile adminaccount = new AdminProfile();
			adminaccount.id=id;
			adminaccount.unitInfo=unitInfo;
			User user = new User();
			user.fullName=fullName;
			user.id=id;
			user.password=password;
			user.phoneNumber=phoneNumber;
			user.profilePhotoPath=profilePhotoPath;
			user.remark=remark;
			user.sex=sex;
			user.username=username;
			adminaccount.user=user;
			session.save(user);
			session.save(adminaccount);
			tx.commit();
		}catch(HibernateException e)
		{
			System.out.println("AdminAccount 64 addNewAdminProfile Exception");
			tx.rollback();
		}finally{
			session.close();
		}
		return SUCCESS;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProfilePhotoPath() {
		return profilePhotoPath;
	}

	public void setProfilePhotoPath(String profilePhotoPath) {
		this.profilePhotoPath = profilePhotoPath;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List getAllAdminProfilelist() {
		return allAdminProfilelist;
	}

	public void setAllAdminProfilelist(List allAdminProfilelist) {
		this.allAdminProfilelist = allAdminProfilelist;
	}
	
	
	
	
//	public void setallAdminProfilelist(List<AdminProfile> allAdminProfilelist) {
//		this.allAdminProfilelist = allAdminProfilelist;
//	}

}
