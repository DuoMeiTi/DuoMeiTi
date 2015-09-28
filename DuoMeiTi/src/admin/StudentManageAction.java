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

public class StudentManageAction extends ActionSupport{
	
	private String collegeSelect[];
	private static String sexSelect[];

	
	private String username;
	private String password;
	private String register_status;
	private String idCard;
	private String sex;
	private String rtID;
	private String studentId;
	private String bankCard;
	private String phoneNumber;
	public java.sql.Date entryTime;
//	private String classrooms;
//	private String status;
//	private String remark;
	private String fullName;
	private String college;
	private String passwordAgain;
	private String strValue;
	private int isPassed;
	private int userid;
	private int isUpgradePrivilege;
	private String status;
	

	private static List<StudentProfile> student_list;
	private static List<User> user_list;
	private static StudentProfile edit_student;
	private static User edit_user;
	private String isUpgradePrivilegelist[];
	
	
	
	public String saveStudentInformation() throws Exception
	{
		
		System.out.println("saveStudentInformation():");
	
//		System.out.println(fullName);
//		System.out.println(studentId);
//		System.out.println(college);
//		System.out.println(phoneNumber);
//		System.out.println(isUpgradePrivilege);
		
		edit_user.setFullName(fullName);
		edit_user.setPhoneNumber(phoneNumber);
		edit_student.setStudentId(studentId);
		edit_student.setCollege(college);
		edit_student.setIsUpgradePrivilege(isUpgradePrivilege);
		
		
		
		//更新学生数据
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		session.update(edit_user);
		session.update(edit_student);
		Transaction t = session.getTransaction();
		t.commit();
		session.close();
		
		return SUCCESS;
	}
	
	
	
	public String getStudentInformation() throws Exception
	{
		
		System.out.println("getStudentInformation():");
		System.out.println("edit_student_id:"+rtID);
		
		//在学生列表中找到要编辑的学生
		for(StudentProfile student : student_list){
			if(student.getId()==Integer.parseInt(rtID)){
				edit_student = student;
				break;
			}
		}
	
		
		//查找对应的user
		Session session=model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(User.class).add(Restrictions.eq("username",edit_student.getUser().getUsername())); //hibernate session创建查询
		user_list=q.list();
		Collections.reverse(student_list);
		session.close();
		
		edit_user = user_list.get(0);
		
		fullName = edit_user.getFullName();
		phoneNumber = edit_user.getPhoneNumber();
		college = edit_student.getCollege();
		studentId = edit_student.getStudentId();
		isUpgradePrivilege = edit_student.getIsUpgradePrivilege();
		
		System.out.println(fullName);
		System.out.println(studentId);
		System.out.println(college);
		System.out.println(phoneNumber);
		System.out.println(isUpgradePrivilege);

		return SUCCESS;
	}
	
	
	
	
	
	
	
	public String studentInformationDelete() throws Exception
	{
		
		System.out.println("studentInformationDelete():");
		System.out.println(rtID);
		
		for(StudentProfile student : student_list){
			if(student.getId()==Integer.parseInt(rtID)){
				edit_student = student;
				break;
			}
		}
		
		System.out.println(edit_student.getId());
		
		Session session = model.Util.sessionFactory.openSession();			
		session.beginTransaction();
		
//		session.delete(edit_student);
		Transaction t = session.getTransaction();
		t.commit();
		session.close();
		
		
		
		
		return SUCCESS;
	}
	
	
	

	
	public String studentInformationEdit() throws Exception
	{
		
		System.out.println("studentInformationEdit():");
		System.out.println(rtID);
		return SUCCESS;

	}
	
	
	
	public String studentInformation() throws Exception
	{
		System.out.println("studentInformation():");
		
		Session session=model.Util.sessionFactory.openSession();
		Criteria q=session.createCriteria(StudentProfile.class);
		student_list=q.list();
		Collections.reverse(student_list);
		session.close();
//		System.out.println("student_list:");
//		System.out.println(student_list);
		return SUCCESS;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String[] getIsUpgradePrivilegelist() {
		return isUpgradePrivilegelist;
	}



	public void setIsUpgradePrivilegelist(String[] isUpgradePrivilegelist) {
		this.isUpgradePrivilegelist = isUpgradePrivilegelist;
	}



	public int getIsUpgradePrivilege() {
		return isUpgradePrivilege;
	}



	public void setIsUpgradePrivilege(int isUpgradePrivilege) {
		this.isUpgradePrivilege = isUpgradePrivilege;
	}



	public static List<User> getUser_list() {
		return user_list;
	}



	public static void setUser_list(List<User> user_list) {
		StudentManageAction.user_list = user_list;
	}



	public static StudentProfile getEdit_student() {
		return edit_student;
	}



	public static void setEdit_student(StudentProfile edit_student) {
		StudentManageAction.edit_student = edit_student;
	}



	public static User getEdit_user() {
		return edit_user;
	}



	public static void setEdit_user(User edit_user) {
		StudentManageAction.edit_user = edit_user;
	}



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



	public String getIdCard() {
		return idCard;
	}



	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getRtID() {
		return rtID;
	}



	public void setRtID(String rtID) {
		this.rtID = rtID;
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



	public java.sql.Date getEntryTime() {
		return entryTime;
	}



	public void setEntryTime(java.sql.Date entryTime) {
		this.entryTime = entryTime;
	}



	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String getCollege() {
		return college;
	}



	public void setCollege(String college) {
		this.college = college;
	}



	public String getPasswordAgain() {
		return passwordAgain;
	}



	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}



	public List<StudentProfile> getStudent_list() {
		return student_list;
	}



	public void setStudent_list(List<StudentProfile> student_list) {
		this.student_list = student_list;
	}



	public String getStrValue() {
		return strValue;
	}



	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}



	public int getIsPassed() {
		return isPassed;
	}



	public void setIsPassed(int isPassed) {
		this.isPassed = isPassed;
	}



	public int getUserid() {
		return userid;
	}



	public void setUserid(int userid) {
		this.userid = userid;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	





	
}
