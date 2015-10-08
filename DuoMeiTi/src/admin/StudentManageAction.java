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
	private String idCard;
	private String sex;
	private String rtID;
	private String studentId;
	private String bankCard;
	private String phoneNumber;
	public java.sql.Date entryTime;
	private String fullName;
	private String college;
	private String passwordAgain;
	private String strValue;
	private String name_id;
	private String isEmpty;
	private String test1;
	private int isPassed;
	private int userid;
	private int student_profile_id;
	private int isUpgradePrivilege;
	private String status;
	

	private static List<StudentProfile> student_list;
	private static List<User> user_list;
	private static StudentProfile edit_student;
	private static User edit_user;
	private String isUpgradePrivilegelist[];
	
	
	
	
	
	public String searchStudentInformation() throws Exception
	{
		
		System.out.println("searchStudentInformation():");
		System.out.println("id:"+name_id);
	
		Session session=model.Util.sessionFactory.openSession();
		Criteria q1 = session.createCriteria(StudentProfile.class).add(Restrictions.eq("studentId", name_id));
		student_list=q1.list();
		if(student_list.isEmpty()){
			System.out.println("empty");
			isEmpty = "0";
			session.close();
		}
		else{
			isEmpty = "1";
			Collections.reverse(student_list);
			edit_student = student_list.get(0);
			//查找student对应的user
			Criteria q2 = session.createCriteria(User.class).add(Restrictions.eq("username",edit_student.getUser().getUsername())); //hibernate session创建查询
			user_list=q2.list();
			Collections.reverse(user_list);
			edit_user = user_list.get(0);
			session.close();
			
			System.out.println("list:"+student_list);
			System.out.println("studentid:"+student_list.get(0).studentId);
		
			
			student_profile_id = edit_student.getId();
			fullName = edit_user.getFullName();
			phoneNumber = edit_user.getPhoneNumber();
			college = edit_student.getCollege();
			studentId = edit_student.getStudentId();
			isUpgradePrivilege = edit_student.getIsUpgradePrivilege();
			
			System.out.println(student_profile_id);
			System.out.println(fullName);
			System.out.println(studentId);
			System.out.println(college);
			System.out.println(phoneNumber);
			System.out.println(isUpgradePrivilege);
		}
		
		return SUCCESS;
	}
	
	
 public int getStudent_profile_id() {
		return student_profile_id;
	}


	public void setStudent_profile_id(int student_profile_id) {
		this.student_profile_id = student_profile_id;
	}


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
		Collections.reverse(user_list);
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
		
		Session session = model.Util.sessionFactory.openSession();		
		//查找student对应的user
		Criteria q = session.createCriteria(User.class).add(Restrictions.eq("username",edit_student.getUser().getUsername())); //hibernate session创建查询
		user_list=q.list();
		Collections.reverse(user_list);
		//要删除的user
		edit_user = user_list.get(0);
		//必须同时删除student和user
		session.beginTransaction();
		session.delete(edit_student);
		session.delete(edit_user);
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
		System.out.println("student_list:");
		System.out.println(student_list);
		return SUCCESS;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String getName_id() {
		return name_id;
	}
















	public String getIsEmpty() {
		return isEmpty;
	}


	public void setIsEmpty(String isEmpty) {
		this.isEmpty = isEmpty;
	}


	public void setName_id(String name_id) {
		this.name_id = name_id;
	}















	public String getTest1() {
		return test1;
	}





	public void setTest1(String test1) {
		this.test1 = test1;
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