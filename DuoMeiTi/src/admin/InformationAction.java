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
import util.FileUploadBaseAction;

public class InformationAction extends FileUploadBaseAction {
	

	private String collegeSelect[];
	private String sexSelect[];
	
	public List<User>user_list;
	private static User now_user;

	private int user_id;
	private String fullName;
	private String phoneNumber;
	private String profilePhotoPath;
	private String remark;
	private String sex;
	private String username;
	
		
	
	public List<AdminProfile>admin_list;
	private static AdminProfile now_admin;
	private String unitInfo;

	
	
	



	public String[] getCollegeSelect() {
		return collegeSelect;
	}

	public void setCollegeSelect(String[] collegeSelect) {
		this.collegeSelect = collegeSelect;
	}

	public String[] getSexSelect() {
		return sexSelect;
	}

	public void setSexSelect(String[] sexSelect) {
		this.sexSelect = sexSelect;
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
		InformationAction.now_user = now_user;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProfilePhotoPath() {
		return profilePhotoPath;
	}

	public void setProfilePhotoPath(String profilePhotoPath) {
		this.profilePhotoPath = profilePhotoPath;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<AdminProfile> getAdmin_list() {
		return admin_list;
	}

	public void setAdmin_list(List<AdminProfile> admin_list) {
		this.admin_list = admin_list;
	}

	public AdminProfile getNow_admin() {
		return now_admin;
	}

	public void setNow_admin(AdminProfile now_admin) {
		this.now_admin = now_admin;
	}

	public String getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(String unitInfo) {
		this.unitInfo = unitInfo;
	}

	
	
	
	
	
	public String adminInformation() throws Exception
	{
		
		
		System.out.println("adminInformation");
		
		sexSelect=Const.sexSelect;
		Session session = model.Util.sessionFactory.openSession();
		Criteria c1 = session.createCriteria(User.class).add(Restrictions.eq("username",ActionContext.getContext().getSession().get("username"))); //hibernate session创建查询

		user_list = c1.list();
		now_user = user_list.get(0);
//		System.out.println("user_id" + now_user.getId());
		
		user_id = now_user.getId();
		
		Criteria c2 = session.createCriteria(AdminProfile.class).add(Restrictions.eq("user.id",user_id)); //hibernate session创建查询
		admin_list = c2.list();	
		now_admin = admin_list.get(0);
		session.close();
	
		
		
		//从数据库读取当前用户的各个属性
		fullName = now_user.getFullName();
		phoneNumber = now_user.getPhoneNumber();
		profilePhotoPath = now_user.getProfilePhotoPath();
		remark = now_user.getRemark();
		sex = now_user.getSex();
		username = now_user.getUsername();
		unitInfo = now_admin.getUnitInfo();
		System.out.println(profilePhotoPath);
//		System.out.println(username);
//		System.out.println(fullName);
//		System.out.println(phoneNumber);
//		System.out.println(sex);
//		System.out.println(unitInfo);
//		System.out.println(remark);

		return ActionSupport.SUCCESS;

	}
	
	public String adminInformationChange() throws Exception
	{
		
		System.out.println("AdminAction.adminInformationChange():");
//		System.out.println("user_id" + now_user.getId());
		if (file != null)//file没接收到的原因可能是jsp页面里面的input file的属性名不是file 
        {
			//System.out.println("path"+util.Util.RootPath + now_user.getProfilePhotoPath());
			util.Util.deleteFile(util.Util.RootPath + now_user.getProfilePhotoPath());
			//保存文件时文件名前面加上用户ID，防止文件重复
			util.Util.saveFile(file, now_user.getId() + fileFileName, util.Util.RootPath + util.Util.ProfilePhotoPath);
			String inserted_file_path = util.Util.ProfilePhotoPath + now_user.getId() + fileFileName;
            now_user.setProfilePhotoPath(inserted_file_path);
        }
		
		now_user.setFullName(fullName);
		now_user.setPhoneNumber(phoneNumber);
		now_user.setRemark(remark);
		now_user.setSex(sex);
		now_admin.setUnitInfo(unitInfo);
		
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		session.update(now_user);
		session.update(now_admin);
		Transaction t = session.getTransaction();
		t.commit();
		session.close();
		
		
		return ActionSupport.SUCCESS;

	}
	
	
	
}
