package admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.BuildingsInfo;
import model.AdminProfile;
import model.Repertory;
import model.Rules;
import model.StudentProfile;
import model.User;
import util.Const;
import utility.DatabaseOperation;

import model.DutyTime;
import model.DutySchedule;
import common.DutyInfo;

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
	private String search_select;
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
	
	private String ruleText;//规章制度的内容,jsp页面传过来的内容
	private String textShow;//规章制度的内容，显示给jsp页面的内容
	private Date time;//规章制度的修改时间
	
	private List<BuildingsInfo> teahBuildings;
	private List<DutyInfo> dutySchedule; 
	private int teachBuildingId;
	
	public List<DutyInfo> getDutySchedule() {
		return dutySchedule;
	}


	public void setDutySchedule(List<DutyInfo> dutySchedule) {
		this.dutySchedule = dutySchedule;
	}


	public int getTeachBuildingId() {
		return teachBuildingId;
	}


	public void setTeachBuildingId(int teachBuildingId) {
		this.teachBuildingId = teachBuildingId;
	}


	public List<BuildingsInfo> getTeahBuildings() {
		return teahBuildings;
	}


	public void setTeahBuildings(List<BuildingsInfo> teahBuildings) {
		this.teahBuildings = teahBuildings;
	}


	public String dutyManager() throws Exception{
		List fields = new ArrayList<String>();
		fields.add("build_id,");
		fields.add("build_name");
		DatabaseOperation getBuildings = new DatabaseOperation("TeachBuilding", fields);
		List result=getBuildings.selectOperation();
		teahBuildings =new ArrayList<BuildingsInfo>();
		Iterator iter =result.iterator();
		while(iter.hasNext()){
			Object[] temp=(Object[])iter.next();
			String name=(String)temp[1];
			Integer id=(Integer)temp[0];
			teahBuildings.add(new BuildingsInfo(name,id));
		}
		return SUCCESS;
	}
	
	public String getDutyTable() throws Exception{
		Session session = model.Util.sessionFactory.openSession();
		String hql="select ds.student.id,ds.student.user.username,ds.dutyTime.time from DutySchedule ds where ds.dutyTime.teachBuilding.build_id="
					+teachBuildingId+" order by ds.dutyTime.time";
		List temp =session.createQuery(hql).list();
		Iterator iter=temp.iterator();
		dutySchedule = new ArrayList<DutyInfo>();
		while(iter.hasNext()){
			Object [] tmp= (Object[]) iter.next();
			dutySchedule.add(new DutyInfo((Integer)tmp[0],(String)tmp[1],(Integer)tmp[2]));
		}
		session.close();
		return SUCCESS;
	}


	public String searchStudentInformation() throws Exception
	{
		
		System.out.println("searchStudentInformation():");
		System.out.println("id:"+name_id);
		System.out.println("s:"+search_select);
	
		if(search_select.equals("2")){//按学号查找
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
			
				
				
				fullName = edit_user.getFullName();
				sex = edit_user.getSex();
				phoneNumber = edit_user.getPhoneNumber();
				college = edit_student.getCollege();
				studentId = edit_student.getStudentId();
				isUpgradePrivilege = edit_student.getIsUpgradePrivilege();
				bankCard = edit_student.getBankCard();
				idCard = edit_student.getIdCard();
				
				
				
				System.out.println(student_profile_id);
				System.out.println(fullName);
				System.out.println(studentId);
				System.out.println(college);
				System.out.println(phoneNumber);
				System.out.println(isUpgradePrivilege);
			}
		}
		
		else{//按姓名查找
			System.out.println("xingming");
			
			Session session=model.Util.sessionFactory.openSession();
			Criteria q1 = session.createCriteria(User.class).add(Restrictions.eq("fullName", name_id));
			user_list=q1.list();
			
			if(user_list.isEmpty()){
				System.out.println("empty");
				isEmpty = "0";
				session.close();
			}
			else{
				isEmpty = "1";
				Collections.reverse(user_list);
				edit_user = user_list.get(0);
//				System.out.println(edit_user);
//				System.out.println(edit_user.getId());
				Criteria q2 = session.createCriteria(StudentProfile.class).add(Restrictions.eq("user.id", edit_user.getId()));
				student_list=q2.list();
				Collections.reverse(student_list);
				session.close();
				edit_student = student_list.get(0);
				System.out.println("list:"+student_list);
				
				fullName = edit_user.getFullName();
				sex = edit_user.getSex();
				phoneNumber = edit_user.getPhoneNumber();
				college = edit_student.getCollege();
				studentId = edit_student.getStudentId();
				isUpgradePrivilege = edit_student.getIsUpgradePrivilege();
				bankCard = edit_student.getBankCard();
				idCard = edit_student.getIdCard();
				
				
				System.out.println(student_profile_id);
				System.out.println(fullName);
				System.out.println(studentId);
				System.out.println(college);
				System.out.println(phoneNumber);
				System.out.println(isUpgradePrivilege);
			}
			
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
		edit_user.setSex(sex);
		edit_user.setPhoneNumber(phoneNumber);
		edit_student.setStudentId(studentId);
		edit_student.setCollege(college);
		edit_student.setIsUpgradePrivilege(isUpgradePrivilege);
		edit_student.setBankCard(bankCard);
		edit_student.setIdCard(idCard);
		
		
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
		sex = edit_user.getSex();
		phoneNumber = edit_user.getPhoneNumber();
		college = edit_student.getCollege();
		studentId = edit_student.getStudentId();
		isUpgradePrivilege = edit_student.getIsUpgradePrivilege();
		bankCard = edit_student.getBankCard();
		idCard = edit_student.getIdCard();
		
		
//		System.out.println(fullName);
//		System.out.println(sex);
//		System.out.println(studentId);
//		System.out.println(college);
//		System.out.println(phoneNumber);
//		System.out.println(isUpgradePrivilege);
//		System.out.println(bankCard);
//		System.out.println(idCard);
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
		collegeSelect=Const.collegeSelect;
		sexSelect=Const.sexSelect;
		
		Session session=model.Util.sessionFactory.openSession();
		Criteria q=session.createCriteria(StudentProfile.class);
		student_list=q.list();
		Collections.reverse(student_list);
		session.close();
		System.out.println("student_list:");
		System.out.println(student_list);
		return SUCCESS;

	}
	//编辑规章制度
	public String editRules() throws Exception{
		System.out.println("StudentManageAction.editRules()");
		
		Session session = model.Util.sessionFactory.openSession();
		//将前台传过来的文字 更新到数据库
		Criteria q = session.createCriteria(Rules.class);
		Rules rules = new Rules();
		
		if (q.list().size() == 0 ) {//如果现在表是空的，就插入到数据库中
			rules.setText(ruleText);
			rules.setTime(new Date(new java.util.Date().getTime()));
			session.beginTransaction();
			session.save(rules);
			session.getTransaction().commit();
			session.close();
			
		}
		else {//如果不是空的，就更新数据库
			q.add(Restrictions.eq("id",1));//默认就一条数据，所以id设为1
			rules = (Rules)q.uniqueResult();
			rules.setText(ruleText);
			rules.setTime(new Date(new java.util.Date().getTime()));
			session.beginTransaction();
			session.save(rules);
			session.getTransaction().commit();
			session.close();
		}
//		Criteria q = session.createCriteria(Rules.class).add(Restrictions.eq("id",
//					 1));//默认只有一条数据
		
		return ActionSupport.SUCCESS;
	}
	//显示规章制度
	public String showRules() throws Exception{
		System.out.println("StudentManageAction.showRules()");
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(Rules.class);
		Rules temp;
		if (q.list().size() > 0) {
			temp = (Rules)q.list().get(0);//
			textShow = temp.getText();
		}
		else {
			textShow =" ";
		}
		session.close();
		return SUCCESS;
	}
	
	
	
	
	
	public String getName_id() {
		return name_id;
	}



	public String getSearch_select() {
		return search_select;
	}


	public void setSearch_select(String search_select) {
		this.search_select = search_select;
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
	
	



	
	

	public String getRuleText() {
		return ruleText;
	}


	public void setRuleText(String ruleText) {
		this.ruleText = ruleText;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	public String getTextShow() {
		return textShow;
	}


	public void setTextShow(String textShow) {
		this.textShow = textShow;
	}
	
	
	
	
	
	





	
}
