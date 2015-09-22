package admin;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import model.Repertory;
import model.StudentProfile;
import util.Const;

public class InformationAction extends ActionSupport{
	

	private String collegeSelect[];
	private String sexSelect[];
	private String username;
	private String register_status;
	private String idCard;
	private String sex;
	private String studentId;
	private String bankCard;
	private String phoneNumber;
	public java.sql.Date entryTime;
	private String classrooms;
	private String college;
	private List<Repertory> repertory_list;
	private List<StudentProfile>student_list;
	
	
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

	public String getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(String classrooms) {
		this.classrooms = classrooms;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public List<Repertory> getRepertory_list() {
		return repertory_list;
	}

	public void setRepertory_list(List<Repertory> repertory_list) {
		this.repertory_list = repertory_list;
	}

	public List<StudentProfile> getStudent_list() {
		return student_list;
	}

	public void setStudent_list(List<StudentProfile> student_list) {
		this.student_list = student_list;
	}

	public String userInformation() throws Exception
	{
		collegeSelect=Const.collegeSelect;
		sexSelect=Const.sexSelect;
		
		
		System.out.println("World");
		System.out.println("World");
		System.out.println("World");

		
//		Session session = model.Util.sessionFactory.openSession();
//		Criteria stu = session.createCriteria(StudentProfile.class);//把查询条件封装成一个Criteria对象
//		student_list = stu.list();
//		Collections.reverse(student_list);
//		session.close();	
		return "success";
	}
	
	
	
	
	
}
