package student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.StudentProfile;
import model.User;
import util.Const;
import util.FileUploadBaseAction;

public class InformationAction extends FileUploadBaseAction{
	
	private String sexSelect[];
	private String collegeSelect[];
	public List<User>user_list;
//	private static User now_user;
	private int user_id;
	public List<StudentProfile>student_list;
//	private static StudentProfile now_student;
	
	private String bankCard;
	private String idCard;
	private String studentId;
	private java.sql.Date entryTime;
	private String college;
	
	private String fullName;
	private String phoneNumber;
	private String profilePhotoPath;
	private String remark;
	private String sex;
	private String username;
	
	private String time;
	
	
	

	public String studentInformation() throws Exception
	{
		
		sexSelect = Const.sexSelect;
		collegeSelect = Const.collegeSelect;
		Session session = model.Util.sessionFactory.openSession();
		Criteria c1 = session.createCriteria(User.class).add(Restrictions.eq("username", ActionContext.getContext().getSession().get("username")));
		
		user_list = c1.list();
		User now_user = user_list.get(0);
		
		user_id = now_user.getId();
		
		System.out.println(user_id);
		
		Criteria c2=session.createCriteria(StudentProfile.class).add(Restrictions.eq("user.id", user_id));
		student_list=c2.list();
		StudentProfile now_student=student_list.get(0);
		session.close();
		
		
		
		//从数据库读取当前用户的各个属性
		username=now_user.getUsername();
		fullName=now_user.getFullName();
		phoneNumber=now_user.getPhoneNumber();
		profilePhotoPath=now_user.getProfilePhotoPath();
		remark=now_user.getRemark();
		sex=now_user.getSex();
		
		bankCard=now_student.getBankCard();
		idCard=now_student.getIdCard();
		studentId=now_student.getStudentId();
		entryTime=now_student.getEntryTime();
		college=now_student.getCollege();
		if(entryTime != null){
			time = entryTime.toString();
		}
		System.out.println("time:"+entryTime);

		
		return ActionSupport.SUCCESS;
	}
	
	
	StudentProfile modify_newStudentProfile;
	public String modify() throws Exception
	{	
		
		StudentProfile newStudentProfile = modify_newStudentProfile;
		
		Session session = model.Util.sessionFactory.openSession();
		
		
		StudentProfile currentStudentProfile = util.Util.getUniqueResultWithOneEqualRestriction(
				session, StudentProfile.class, "user.username", ActionContext.getContext().getSession().get("username"));
		
		String[] studentProfileList = {"studentId", "idCard", "college", "bankCard", "entryTime",
									    "user.fullName", "user.sex", "user.remark", "user.phoneNumber"};
		
		util.Util.copyWithSpecificFields(
				newStudentProfile, 
				currentStudentProfile,
				studentProfileList   );
		
		if (file != null) //file没接收到的原因可能是jsp页面里面的input file的属性名不是file 
	    {
			fileFileName = currentStudentProfile.user.username + "_" + fileFileName;
			util.Util.deleteFile(util.Util.RootPath + currentStudentProfile.user.profilePhotoPath);
			util.Util.saveFile(file,  fileFileName, util.Util.RootPath + util.Util.ProfilePhotoPath);
			currentStudentProfile.user.profilePhotoPath = util.Util.ProfilePhotoPath +  fileFileName;
	    }		
		session.beginTransaction();
		session.update(currentStudentProfile.user);
		session.update(currentStudentProfile);
		session.getTransaction().commit();;
		session.close();
		
		return ActionSupport.SUCCESS;
	}
	
	
	
	

	


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public java.sql.Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(java.sql.Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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

//	public static StudentProfile getNow_student() {
//		return now_student;
//	}

//	public static void setNow_student(StudentProfile now_student) {
//		InformationAction.now_student = now_student;
//	}

	public List<StudentProfile> getStudent_list() {
		return student_list;
	}

	public void setStudent_list(List<StudentProfile> student_list) {
		this.student_list = student_list;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

//	public static User getNow_user() {
//		return now_user;
//	}

//	public static void setNow_user(User now_user) {
//		InformationAction.now_user = now_user;
//	}

	public List<User> getUser_list() {
		return user_list;
	}

	public void setUser_list(List<User> user_list) {
		this.user_list = user_list;
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








	public StudentProfile getModify_newStudentProfile() {
		return modify_newStudentProfile;
	}








	public void setModify_newStudentProfile(StudentProfile modify_newStudentProfile) {
		this.modify_newStudentProfile = modify_newStudentProfile;
	}







//
//	public StudentProfile getNewStudentProfile() {
//		return newStudentProfile;
//	}
//
//
//
//
//
//
//
//
//	public void setNewStudentProfile(StudentProfile newStudentProfile) {
//		this.newStudentProfile = newStudentProfile;
//	}
//
	
	
}

