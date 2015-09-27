package homepage;

import java.util.Collections;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.mysql.fabric.xmlrpc.base.Data;
import com.opensymphony.xwork2.ActionSupport;

import model.StudentProfile;
import model.User;
import util.Const;

public class StudentAction {
	
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
	public java.sql.Date entryTime;
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

	public java.sql.Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(java.sql.Date entryTime) {
		this.entryTime = entryTime;
	}
	
	
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
	
	public String studentRegisterSave() throws Exception
	{
		System.out.println("AdminAction.adminRegisterSave()");
		
		if(username.equals("") || password.equals(""))
		{
			this.register_status = "1";
			return ActionSupport.SUCCESS;
		}	
		
		Session session = model.Util.sessionFactory.openSession();
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
			session.save(um);//因为user是外键，所以commit StudentProfile之前需要先save user；
			
			StudentProfile stupro=new StudentProfile();
			stupro.setUser(um);
			stupro.setIdCard(idCard);
			stupro.setBankCard(bankCard);
			stupro.setEntryTime(entryTime);
			um.setPhoneNumber(phoneNumber);
			um.setSex(sex);
			stupro.setStudentId(studentId);
			stupro.setCollege(college);
			um.setFullName(fullName);
			
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
