package homepage;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.StudentProfile;
import model.User;
import util.Const;

public class StudentAction {
	
	private String collegeSelect[];
	private String sexSelect[];
	private String statusSelect[];
	private String username;
	private String password;
	private String register_status;
	private String idCard;
	private String sex;
	private String studentId;
	private String bankCard;
	private String phoneNumber;
	public java.sql.Date entryTime;
	private String classrooms;
	private String status;
	private String remark;
	private String college;
	private String passwordAgain;
	private List<StudentProfile>student_list;
	
	
	
	
	public List<StudentProfile> getStudent_list() {
		return student_list;
	}

	public void setStudent_list(List<StudentProfile> student_list) {
		this.student_list = student_list;
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

	public String getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(String classrooms) {
		this.classrooms = classrooms;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String[] getStatusSelect() {
		return statusSelect;
	}

	public void setStatusSelect(String[] statusSelect) {
		this.statusSelect = statusSelect;
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
	
	/*public String checkPassword(){
		if(!(getPassword().equals(passwordAgain))){
			alert("两次密码输入不一致，请重新输入");
		}
		return ActionSupport.SUCCESS;
	}*/
	
	/*public String studentRegister(){
		collegeSelect=Const.collegeSelect;
		sexSelect=Const.sexSelect;
		statusSelect=Const.statusSelect;
		return ActionSupport.SUCCESS;
	}*/
	
	public String studentRegister() throws Exception
	{
		collegeSelect=Const.collegeSelect;
		sexSelect=Const.sexSelect;
		statusSelect=Const.statusSelect;
		
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(StudentProfile.class);//把查询条件封装成一个Criteria对象
		student_list = q.list();
		Collections.reverse(student_list);
		session.close();	
		return "success";
	}
	
	public String studentRegisterSave() throws Exception
	{
		if(username == null || password == null)
		{
			this.register_status = "error: username or password is null";
			return ActionSupport.SUCCESS;
		}
		if(username.equals("") || password.equals(""))
		{
			this.register_status = "1";
			return ActionSupport.SUCCESS;
		}		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(StudentProfile.class).add(Restrictions.eq("username", username));
		List ul = q.list();
		if(!ul.isEmpty())
		{
			this.register_status = "2";
		}
		else
		{
			User um = new User();
			um.setUsername(username);
			um.setPassword(password);
			
			StudentProfile stupro=new StudentProfile();
			stupro.setUser(um);
			stupro.setIdCard(idCard);
			stupro.setBankCard(bankCard);
			stupro.setEntryTime(entryTime);
			stupro.setPhoneNumber(phoneNumber);
			stupro.setSex(sex);
			stupro.setStudentId(studentId);
			stupro.setCollege(college);
			stupro.setRemark(remark);
			stupro.setStatus(status);
			
			session.beginTransaction();
			session.save(um);
			session.save(stupro);
			
			session.getTransaction().commit();
			this.register_status = "0";
	//		this.user_id = um.getId();
	//		this.added_user_html = util.Util.fileToString("/jsp/homepage/widgets/added_user.html");
		}
		session.close();
		return ActionSupport.SUCCESS;
	}
	

}
