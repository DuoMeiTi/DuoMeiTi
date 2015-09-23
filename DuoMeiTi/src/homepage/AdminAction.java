package homepage;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.opensymphony.xwork2.ActionSupport;

import model.AdminProfile;
import model.User;
import util.Const;

public class AdminAction {
	private String sexSelect[];// 性别
	private String sex;

	private String username;
	private String password;
	private String fullName;
	private String phoneNumber;
	private String profilePhotoPath;
	private String remark;
	private String unitInfo;
	private String register_status;

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

	public String getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(String unitInfo) {
		this.unitInfo = unitInfo;
	}

	public String[] getSexSelect() {
		return sexSelect;
	}

	public void setSexSelect(String[] sexSelect) {
		this.sexSelect = sexSelect;
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

	public String getRegister_status() {
		return register_status;
	}

	public void setRegister_status(String register_status) {
		this.register_status = register_status;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String adminRegister() {
		
		sexSelect = Const.sexSelect;
		return ActionSupport.SUCCESS;
	}

	public String adminRegisterSave() throws Exception {
//		if (username == null || password == null) {
//			this.register_status = "1";
//			return ActionSupport.SUCCESS;
//		}
//		if (username.equals("") || password.equals("")) {
//			this.register_status = "1";
//			return ActionSupport.SUCCESS;
//		}
		 Session session = model.Util.sessionFactory.openSession();

		 		 Criteria q =
		 session.createCriteria(User.class).add(Restrictions.eq("username",
		 username));
		 		 //user.id
		 List ul = q.list();
		 if(!ul.isEmpty())
		 {
			 System.out.println("err");
			 this.register_status = "2";//用户名注册重复
			 return ActionSupport.SUCCESS;
			 
			 
		 
		 }
//		 
//		 User user0 = (User)ul.get(0);
//		 
//		 session.createCriteria(AdminProfile.class).add(Restrictions.eq("user.id",user0.getId()));
//		 System.out.println("1");
//		 List ul = q.list();
//		 System.out.println("2");
//		 if(!ul.isEmpty())
//		 {
//			 System.out.println("AdminAction.adminRegisterSave()1");
//		 this.register_status = "2";//用户名注册重复
//		 }
		 else
		{
			System.out.println("AdminAction.adminRegisterSave()2");
			User um = new User();
			um.setUsername(username);
			um.setPassword(password);
			session.save(um);//因为user是外键，所以在commit aProfile之前需要先save user；

			AdminProfile aProfile = new AdminProfile();
			aProfile.setUser(um);
			aProfile.setFullName(fullName);
			aProfile.setPhoneNumber(phoneNumber);
			aProfile.setProfilePhotoPath(profilePhotoPath);
			aProfile.setRemark(remark);
			aProfile.setSex(sex);
			aProfile.setUnitInfo(unitInfo);

			session.beginTransaction();
			session.save(aProfile);

			Transaction t = session.getTransaction();
			t.commit();
			this.register_status = "0";
		}
		session.close();
		return ActionSupport.SUCCESS;
	}
}
