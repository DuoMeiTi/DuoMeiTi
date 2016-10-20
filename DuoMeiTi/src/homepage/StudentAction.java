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
	
	/*
	 * status 0: OK
	 * 		  1: username 或者password 为空
	 * 		  2: username 重复
	 * 		  3: password两次不一致
	 * 		  4:姓名为空
	 * 		  5：学号为空
	 * 		  6:学号已经存在
	 */
	
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
	
	
	/*
	 * 判断学号是否重复，如果重复，返回true
	 */
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
	public String studentRegisterSave() throws Exception
	{
		System.out.println("haha");
		System.out.println(username);
		System.out.println(phoneNumber);
		if(username.equals("") || password.equals("") || fullName.equals("") || studentId.equals(""))
		{
			this.register_status = "1";
			return ActionSupport.SUCCESS;
		}
		/*if(studentId.equals(""))
		{
			this.register_status="5";
			return ActionSupport.SUCCESS;
		}
		if(fullName.equals(""))
		{
			this.register_status="4";
			return ActionSupport.SUCCESS;
		}*/
		if(!password.equals(passwordAgain))
		{
			System.out.println(!password.equals(passwordAgain));
			System.out.println(password);
			System.out.println(passwordAgain);
			this.register_status="3";
			return ActionSupport.SUCCESS;
		}
		
		Session session = model.Util.sessionFactory.openSession();
		//判断学号是否重复
		if(isRepeat(session, studentId)){
			System.out.println(studentId+"学号已经存在");
			this.register_status="6";
			return ActionSupport.SUCCESS;
		}
		Criteria q= session.createCriteria(User.class).add(Restrictions.eq("username", username));
		List ul = q.list();
		if(!ul.isEmpty())
		{
			System.out.println("err");
			this.register_status = "2";
			return ActionSupport.SUCCESS;
		}
		else
		{
			User um = new User();
			um.setUsername(username);
			um.setPassword(password);
			um.setPhoneNumber(phoneNumber);
			um.setSex(sex);
			um.setFullName(fullName);
			session.save(um);//因为user是外键，所以commit StudentProfile之前需要先save user；
			
			StudentProfile stupro=new StudentProfile();
			stupro.setUser(um);
			stupro.setIdCard(idCard);
			stupro.setBankCard(bankCard);
			
			stupro.setStudentId(studentId);
			stupro.setCollege(college);
			
			java.util.Date now = new java.util.Date();
			java.sql.Date sql_now = new java.sql.Date(now.getTime());
			stupro.setEntryTime(sql_now);
//			stupro.setEntryTime(entryTime);
			
			
			if (file != null)
			{
				util.Util.saveFile(file, fileFileName, util.Util.RootPath + util.Util.ProfilePhotoPath);
				String inserted_file_path = util.Util.ProfilePhotoPath + fileFileName;
				um.setProfilePhotoPath(inserted_file_path);
			}
			
			
			session.beginTransaction();
			session.save(stupro);
			
			Transaction t = session.getTransaction();
			t.commit();
			this.register_status = "0";
	//		this.user_id = um.getId();
	//		this.added_user_html = util.Util.fileToString("/jsp/homepage/widgets/added_user.html");
		}
		session.close();
		return ActionSupport.SUCCESS;
	}
	
}
