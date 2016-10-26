package homepage;

import java.util.Collections;
import java.util.List;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import com.mysql.fabric.xmlrpc.base.Data;
import com.opensymphony.xwork2.ActionSupport;

//import model.EgFilePathSave;
import util.FileUploadBaseAction;

import model.StudentProfile;
import model.User;
import util.Const;

public class StudentAction extends FileUploadBaseAction {
	
	
	private String collegeSelect[];
	private String sexSelect[];
//	private String statusSelect[];
	private String username;
	private String password;
	private String register_status;
	private String idCard;
	private String sex;
	private String studentId;
	private String bankCard;
	private String phoneNumber;
//	public java.sql.Date entryTime;
	
	
//	private String classrooms;
//	private String status;
//	private String remark;
	private String fullName;
	private String college;
	private String passwordAgain;
	private List<StudentProfile> student_list;
	
	private int isPassed;
	private int userid;
	private String status;
	private String profilePhotoPath;
	
	
	
	public String studentRegister() throws Exception
	{
		collegeSelect=Const.collegeSelect;
		sexSelect=Const.sexSelect;
		
		try{
			Session session=model.Util.sessionFactory.openSession();
			Criteria q=session.createCriteria(StudentProfile.class);
			student_list=q.list();
			Collections.reverse(student_list);
			session.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ActionSupport.SUCCESS;
//		statusSelect=Const.statusSelect;
		
		/*System.out.println("jkjk");
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(User.class);//把查询条件封装成一个Criteria对象
		List ul = q.list();
		Collections.reverse(ul);
		session.close();	
		return "success";*/
	}
	
	
	
	
//	/*
//	 * 判断学号是否重复，如果重复，返回true
//	 */
	public static boolean isRepeat(Session session, String searchID){
//		Session session = model.Util.sessionFactory.openSession();
		Criteria q2= session.createCriteria(StudentProfile.class).add(Restrictions.eq("studentId", searchID));
		List u2 = q2.list();
//		session.close();
		if(u2.isEmpty()){
			return false;
		}
		return true;
	}
	
	
	
	/*
	 * status 0: OK
	 * 		  1: username 或者password 或者fullName 或者studentId 为空
	 * 		  2: username 已经存在
	 * 		  3: password两次不一致
	 * 		  6: 学号已经存在
	 */
	public String studentRegisterSave() throws Exception
	{
		System.out.println("haha");
		System.out.println(username);
		System.out.println(phoneNumber);
		if(username.equals("") || password.equals("") || fullName.equals("") || studentId.equals(""))
		{
			this.register_status = "含有未填项";
			return ActionSupport.SUCCESS;
		}
		
		if(username.contains(" "))
		{
			this.register_status = "用户名中含有空格";
			return ActionSupport.SUCCESS;
		}
		
		if(fullName.contains(" "))
		{
			this.register_status = "真实姓名中含有空格";
			return ActionSupport.SUCCESS;
		}
		
		if(studentId.contains(" "))
		{
			this.register_status = "学号中含有空格";
			return ActionSupport.SUCCESS;
		}

		
		

		if(!password.equals(passwordAgain))
		{
			System.out.println(!password.equals(passwordAgain));
			System.out.println(password);
			System.out.println(passwordAgain);
			this.register_status= "两次密码不一致";
			return ActionSupport.SUCCESS;
		}
		
		Session session = model.Util.sessionFactory.openSession();
		
		//判断学号是否重复		
		if(util.Util.isExistWithOneEqualRestriction(session, StudentProfile.class, "studentId", studentId))
		{
			this.register_status="学号有重复";
			return ActionSupport.SUCCESS;
		}		
		//判断用户名是否重复
		if(util.Util.isExistWithOneEqualRestriction(session, User.class, "username", username))
		{
			this.register_status = "用户名有重复";
			return ActionSupport.SUCCESS;
		}
		
		
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhoneNumber(phoneNumber);
		user.setSex(sex);
		user.setFullName(fullName);
		
		
		StudentProfile studentProfile=new StudentProfile();
		studentProfile.setUser(user);
		studentProfile.setIdCard(idCard);
		studentProfile.setBankCard(bankCard);
		studentProfile.setStudentId(studentId);
		studentProfile.setCollege(college);
		
		java.util.Date now = new java.util.Date();
		java.sql.Date sql_now = new java.sql.Date(now.getTime());
		studentProfile.setEntryTime(sql_now);
		
		if (file != null)
		{
			fileFileName = username;
			util.Util.saveFile(file, fileFileName, util.Util.RootPath + util.Util.ProfilePhotoPath);
			String inserted_file_path = util.Util.ProfilePhotoPath + fileFileName;
			user.setProfilePhotoPath(inserted_file_path);
		}
		
		
		session.beginTransaction();
		
		session.save(user);//因为user是外键，所以commit StudentProfile之前需要先save user；
		session.save(studentProfile);
		
		session.getTransaction().commit();
		this.register_status = "";
			
		session.close();
		return ActionSupport.SUCCESS;
	}
	
	public String getProfilePhotoPath() {
		return profilePhotoPath;
	}

	public void setProfilePhotoPath(String profilePhotoPath) {
		this.profilePhotoPath = profilePhotoPath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIsPassed() {
		return isPassed;
	}

	public void setIsPassed(int isPassed) {
		this.isPassed = isPassed;
	}

	public List<StudentProfile> getStudent_list() {
		return student_list;
	}

	public void setStudent_list(List<StudentProfile> student_list) {
		this.student_list = student_list;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

//	public java.sql.Date getEntryTime() {
//		return entryTime;
//	}
//
//	public void setEntryTime(java.sql.Date entryTime) {
//		this.entryTime = entryTime;
//	}
	
	
	/*public String getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(String classrooms) {
		this.classrooms = classrooms;
	}*/

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRegister_status() {
		return register_status;
	}

	public void setRegister_status(String register_status) {
		this.register_status = register_status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getSexSelect() {
		return sexSelect;
	}

	public void setSexSelect(String[] sexSelect) {
		this.sexSelect = sexSelect;
	}

	public String[] getCollegeSelect() {
		return collegeSelect;
	}

	public void setCollegeSelect(String[] collegeSelect) {
		this.collegeSelect = collegeSelect;
	}

}
