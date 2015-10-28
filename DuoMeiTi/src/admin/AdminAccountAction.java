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
import model.StudentProfile;
import model.User;
import model.Util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAccountAction extends ActionSupport{

	/**
	 *该Action用于处理管理员用户的添加和删除
	 */
	private static final long serialVersionUID = 1L;
	private List<AdminProfile> allAdminProfilelist;
	private int id;
	private String rmID;
	private String username;
	private String password;
	private String fullName;
	private String sex; 
	private String profilePhotoPath;
	private String phoneNumber;
	private String remark;
	private String unitInfo;
	private String password2;
	private User user;
	private AdminProfile edit_admin;
	private String result="success";
	
	public String getRmID() {
		return rmID;
	}

	public void setRmID(String rmID) {
		this.rmID = rmID;
	}

	public AdminProfile getEdit_admin() {
		return edit_admin;
	}

	public void setEdit_admin(AdminProfile edit_admin) {
		this.edit_admin = edit_admin;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setAllAdminProfilelist(List<AdminProfile> allAdminProfilelist) {
		this.allAdminProfilelist = allAdminProfilelist;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(String unitInfo) {
		this.unitInfo = unitInfo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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
		else
		{
			for(AdminProfile admin:allAdminProfilelist)
			{
				System.out.println("fullName: "+admin.user.fullName);
			}
		}
		session.close();
		return SUCCESS;
	}
	
	public String deleteAdminProfile(){
		int deleteid=(int) ServletActionContext.getContext().getSession().get("user_id");
		javax.servlet.http.HttpServletResponse response = ServletActionContext.getResponse();
		
		System.out.println("deleteAdminProfile "+"rmID "+rmID);
		id=Integer.valueOf(rmID).intValue();
		if(deleteid==this.id)
		{
			result="error";
			return ERROR;
		}
		Session session =Util.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			
			Criteria criteriaadmin = session.createCriteria(AdminProfile.class).add(Restrictions.eq("id",id ));
			List<AdminProfile> listadmin = criteriaadmin.list();
			for(AdminProfile ap:listadmin)
			{
				session.delete(ap);
				Criteria criteriauser = session.createCriteria(User.class).add(Restrictions.eq("id",ap.user.id ));
				List<User> listuser = criteriauser.list();
				for(User user:listuser)
				{
					session.delete(user);
				}
			}
			tx.commit();
			result="success";
		}catch(HibernateException e)
		{
			tx.rollback();
			return ERROR;
		}finally{
			session.close();
		}
		return SUCCESS;
	}
	public String getAdminAccountinfo()
	{
		for(AdminProfile admin : allAdminProfilelist){
			if(admin.getId()==Integer.parseInt(rmID)){
				edit_admin = admin;
				break;
			}
		}
		//查找对应的user
		if(allAdminProfilelist==null||allAdminProfilelist.isEmpty())
		{
			initallAdminProfilelist();
		}
		return SUCCESS;
	}
	
	public String addNewAdminProfile()
	{
		System.out.println("hehe");
		Session session =Util.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("fullname: "+fullName+"  "+"password: "+password+" username "+username);
		try{
			Criteria criteria = session.createCriteria(AdminProfile.class);
			Criteria criteriauser = session.createCriteria(User.class).add(Restrictions.eq("username",username));
			if(criteriauser.list().size()>0){
				result="用户已存在";
				return ERROR;
			}
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
			result="success";
			System.out.println("end");
		}catch(HibernateException e)
		{
			System.out.println("AdminAccount 64 addNewAdminProfile Exception");
			tx.rollback();
			result="error";
		}finally{
			session.close();
		}
		return ActionSupport.SUCCESS;
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

	
	
	
//	public void setallAdminProfilelist(List<AdminProfile> allAdminProfilelist) {
//		this.allAdminProfilelist = allAdminProfilelist;
//	}

}
