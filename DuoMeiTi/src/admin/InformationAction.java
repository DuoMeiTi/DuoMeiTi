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

public class InformationAction extends ActionSupport{
	

	private String collegeSelect[];
	private String sexSelect[];
	
	
	public List<AdminProfile>admin_list;
	public List<User>user_list;
	
	public int user_id;
	private String username;
	private String fullName;
	private String phoneNumber;
	private String sex;
	private String unitInfo;
	private String remark;
	public int id;
	
	
	
	
	private String register_status;
	private String idCard;
	
	private String studentId;
	private String bankCard;
	
	public java.sql.Date entryTime;
	private String classrooms;
	private String college;
	private List<Repertory> repertory_list;
	public List<StudentProfile>student_list;
	
	
	
	
	
	
	
	
	


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











	public List<AdminProfile> getAdmin_list() {
		return admin_list;
	}











	public void setAdmin_list(List<AdminProfile> admin_list) {
		this.admin_list = admin_list;
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











	public String getSex() {
		return sex;
	}











	public void setSex(String sex) {
		this.sex = sex;
	}











	public String getUnitInfo() {
		return unitInfo;
	}











	public void setUnitInfo(String unitInfo) {
		this.unitInfo = unitInfo;
	}











	public String getRemark() {
		return remark;
	}











	public void setRemark(String remark) {
		this.remark = remark;
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











	public String adminInformation() throws Exception
	{
		
		
//		System.out.println("adminInformation");
		sexSelect=Const.sexSelect;
		Session session = model.Util.sessionFactory.openSession();
		Criteria c1 = session.createCriteria(User.class).add(Restrictions.eq("username",ActionContext.getContext().getSession().get("username"))); //hibernate session创建查询
		//c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		user_list = c1.list();
		System.out.println("userlist" + user_list.get(0));
		user_id = user_list.get(0).getId();

		Criteria c2 = session.createCriteria(AdminProfile.class).add(Restrictions.eq("user.id",user_id)); //hibernate session创建查询
		admin_list = c2.list();	
		session.close();
	
		fullName = admin_list.get(0).getFullName();
		phoneNumber = admin_list.get(0).getPhoneNumber();
		sex = admin_list.get(0).getSex();
		unitInfo =admin_list.get(0).getUnitInfo();
		remark = admin_list.get(0).getRemark();
		id = admin_list.get(0).getId();

		System.out.println(fullName);
		System.out.println(phoneNumber);
		System.out.println(sex);
		System.out.println(unitInfo);
		System.out.println(remark);
		System.out.println("id:"+id);
		return SUCCESS;

	}
	
	public String adminInformationChange() throws Exception
	{
		
		System.out.println("AdminAction.adminInformationChange():");
		System.out.println(username);
		System.out.println(fullName);
		System.out.println(phoneNumber);
		System.out.println(sex);
		System.out.println(unitInfo);
		System.out.println(remark);
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria c1 = session.createCriteria(User.class).add(Restrictions.eq("username",ActionContext.getContext().getSession().get("username"))); //hibernate session创建查询
		//c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		user_list = c1.list();
		

		User now_user = user_list.get(0);
		user_id = now_user.getId();
		
		Criteria c2 = session.createCriteria(AdminProfile.class).add(Restrictions.eq("user.id",user_id)); //hibernate session创建查询
		admin_list = c2.list();	
		
		AdminProfile aProfile = admin_list.get(0);
		id = aProfile.getId();
		
		System.out.println("id:"+id);
		
		
		aProfile.setUser(now_user);
		aProfile.setFullName("123");
		aProfile.setPhoneNumber(phoneNumber);
		aProfile.setProfilePhotoPath("profilePhotoPath");
		aProfile.setRemark(remark);
		aProfile.setSex(sex);
		aProfile.setUnitInfo(unitInfo);

		session.beginTransaction();
		session.save(aProfile);
		session.update(aProfile);
		Transaction t = session.getTransaction();
		t.commit();
		
		session.close();
		return ActionSupport.SUCCESS;

	}
	
	
	
}
