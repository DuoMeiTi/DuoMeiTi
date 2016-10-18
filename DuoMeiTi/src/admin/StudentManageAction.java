package admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//import common.BuildingsInfo;
import model.AdminProfile;
import model.Repertory;
import model.Rules;
import model.StudentProfile;
import model.User;
import util.Const;
//import utility.DatabaseOperation;

import model.DutyPiece;
import model.ExamStuScore;
import model.DutySchedule;
//import common.DutyInfo;
//import model.ChooseClassSwitch;
import model.User;
//import common.StudentInfo;

public class StudentManageAction extends ActionSupport{
	
	private String collegeSelect[];
	private static String sexSelect[];

	
	private String username;	
	public String password;
	
	
	private String idCard;
	private String sex;
	private int studentDatabaseId; // 学生ID
	private String studentId; // 学号
	private String bankCard;
	private String phoneNumber;
	public java.sql.Date entryTime;
	private String fullName;
	private String college;
	private int isUpgradePrivilege;

	
	private String name_id;
	private String search_select;

	private  List<StudentProfile> student_list;
	private String studenttable_jsp;
	
	List<model.ExamStuScore> studentScoreList;
	private String studentScoreJsp;
	
	private String isRepeat; //标记学号是否重复
	private String isException;
	
	
	
	private String ruleText;//规章制度的内容,jsp页面传过来的内容
	private String textShow;//规章制度的内容，显示给jsp页面的内容
	private Date time;//规章制度的修改时间
	
	
	


	
	

	
	
	
	
	//排除注册未通过学生,通过学号查询
	public static List<StudentProfile> searchStudentByStudentNumber(Session s, String studentId)
	{		
		return s.createCriteria(StudentProfile.class)				
					.add(Restrictions.eq("isPassed", model.StudentProfile.Passed))
					
					.add(Restrictions.eq("studentId", studentId))
					.list();
	}
	//排除注册未通过学生
	public static List<StudentProfile> searchStudentByFullname(Session s, String fullName)
	{
		
//		StudentProfile sp;
//		sp.user.fullName
		return s.createCriteria(StudentProfile.class)				
					.add(Restrictions.eq("isPassed", model.StudentProfile.Passed))
					.createAlias("user", "user")
					.add(Restrictions.eq("user.fullName", fullName))
					.list();
	}

	
	
	public String watchScore() throws Exception
	{
		Session s = model.Util.sessionFactory.openSession();
		
		 studentScoreList =
				s.createCriteria(model.ExamStuScore.class)
				.add(Restrictions.eq("stuPro.id", this.studentDatabaseId))
				.list();		
		 studentScoreJsp = util.Util.getJspOutput("/jsp/admin/student_manage/studentScoreTable.jsp");		 
		 s.close();
		
		return SUCCESS;
	}
	
	
	public String searchByFullNameOrStudentId() throws Exception {
		Session s = model.Util.sessionFactory.openSession();
		
		
		
		
		if(!studentId.isEmpty())
		{
			student_list = searchStudentByStudentNumber(s, studentId); 
		}
		else 
		{
			student_list = searchStudentByFullname(s, fullName); 
		}
		
		s.close();
		
//		student_list = searchStudentByFullname(s, name_id);
		return SUCCESS;
	}
	
	public String search() throws Exception
	{
		System.out.println("searchStudentInformation():");
		Session s = model.Util.sessionFactory.openSession();

		try{
			
			
			if(search_select.equals("2"))
			{
				//按学号查找
				student_list = searchStudentByStudentNumber(s, name_id);
	
			}
			else
			{//按姓名查找
				student_list = searchStudentByFullname(s, name_id);
			}
			
			Collections.reverse(student_list);
			studenttable_jsp = util.Util.getJspOutput("/jsp/admin/student_manage/studenttable.jsp");
			
			
			s.close();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	


	public static StudentProfile getStudentById(Session s, int id)
	{
		return (StudentProfile)
				s.createCriteria(model.StudentProfile.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}
	
	public String save() throws Exception
	{
		
		
	try{
		
		
		System.out.println("saveStudentInformation():");		


		Session session = model.Util.sessionFactory.openSession();
		StudentProfile edit_student = getStudentById(session, studentDatabaseId);
		
		System.out.println(edit_student == null);
		isRepeat="0";
		if(!studentId.equals(edit_student.studentId))
		{
			System.out.println("修改学号");
			if(homepage.StudentAction.isRepeat(studentId)){
				System.out.println("chongfu");
				isRepeat="1";
				return SUCCESS;
			}
		}
		
		
		//更新学生数据,hql只更新部分字段		
		session.beginTransaction();
		String hql = "update StudentProfile t set t.studentId = '"+studentId
				+ "', t.college = '"+college
				+ "', t.isUpgradePrivilege = '"+isUpgradePrivilege
				+ "', t.bankCard = '"+bankCard
				+ "', t.idCard = '"+idCard
				+ "' where id = "+edit_student.getId();
		System.out.println("hql:"+hql);
		Query query = session.createQuery(hql);
		query.executeUpdate(); 
		
		
		String hql2 = "update User t set t.fullName = '"+fullName
				+ "', t.sex = '"+sex
				+ "', t.phoneNumber = '"+phoneNumber
				+ "' where id = "+edit_student.getUser().getId();
		System.out.println("hql:"+hql2);
		Query query2 = session.createQuery(hql2);
		query2.executeUpdate(); 
		
		Transaction t = session.getTransaction();
		t.commit();
		session.close();
		
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	public String obtain() throws Exception
	{
		
		System.out.println("getStudentInformation():");
		System.out.println("edit_student_id:"+studentDatabaseId);
		
		
		Session session=model.Util.sessionFactory.openSession();
		String hql = "SELECT rt FROM StudentProfile rt WHERE rt.id = " + studentDatabaseId;
		System.out.println(hql);
		Query query = session.createQuery(hql);
		student_list = query.list();
		StudentProfile edit_student=student_list.get(0);
		
		
		
		String hql2 = "SELECT rt FROM User rt WHERE rt.id = " + edit_student.getUser().getId();
		Query query2 = session.createQuery(hql2);
		System.out.println(hql2);

		session.close();
		
		
		fullName = edit_student.user.fullName;
		sex = edit_student.user.sex;
		phoneNumber = edit_student.user.getPhoneNumber();
		college = edit_student.getCollege();
		studentId = edit_student.getStudentId();
		isUpgradePrivilege = edit_student.getIsUpgradePrivilege();
		bankCard = edit_student.getBankCard();
		idCard = edit_student.getIdCard();
		username = edit_student.user.username;
		password = edit_student.user.password;
		
		return SUCCESS;
	}
	
	
	
	
	
	
	
	public String delete() throws Exception
	{
		
		System.out.println("studentInformationDelete():");
		System.out.println(studentDatabaseId);
		isException="0";
		Session session = model.Util.sessionFactory.openSession();	
		
		StudentProfile edit_student = getStudentById(session, studentDatabaseId);
		
		try{
			
			//查找student对应的user
			User user = (User)session.createCriteria(User.class)
					.add(Restrictions.eq("id",edit_student.user.id))
					.uniqueResult();		
			
			//必须同时删除student和user
			session.beginTransaction();
			session.delete(edit_student);
			session.delete(user);
		    session.getTransaction().commit();;
			 
		}catch(Exception e){
			e.printStackTrace();
            session.getTransaction().rollback();
            System.out.println("删除失败");
            isException="1";
		}
		finally{
			session.close();
		}
		
		
		return SUCCESS;
	}

//所有在职学生（包括有管理员权限的）

	public String obtainWorkingStudent() throws Exception{
		
		try
		{
			
		Session session = model.Util.sessionFactory.openSession();
		
		Criteria q = session.createCriteria(model.StudentProfile.class)
				.add(Restrictions.eq("isPassed", model.StudentProfile.Passed))
				.addOrder(Order.desc("id"))
				
//				.add(Restrictions.eq("isUpgradePrivilege", model.StudentProfile.DepartureStudent))
				
				.add(Restrictions.not(Restrictions.eq("isUpgradePrivilege", model.StudentProfile.DepartureStudent)))
				
				;
		
		
		student_list = q.list();
		studenttable_jsp = util.Util.getJspOutput("/jsp/admin/student_manage/studenttable.jsp");

		session.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ActionSupport.SUCCESS;

	}
//获取离职学生！
	public String obtainDepartureStudent() throws Exception
	{
		
		try
		{
			
		Session session = model.Util.sessionFactory.openSession();
		
		Criteria q = session.createCriteria(model.StudentProfile.class)
				.add(Restrictions.eq("isPassed", model.StudentProfile.Passed))
				.addOrder(Order.desc("id"))
				
//				.add(Restrictions.eq("isUpgradePrivilege", model.StudentProfile.DepartureStudent))
				
				.add(Restrictions.eq("isUpgradePrivilege", model.StudentProfile.DepartureStudent))
				
				;
		
		
		student_list = q.list();
		studenttable_jsp = util.Util.getJspOutput("/jsp/admin/student_manage/studenttable.jsp");

		session.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ActionSupport.SUCCESS;
	}
	
	// 页面显示
	public String studentInformation() throws Exception{
		

		collegeSelect = Const.collegeSelect;
		sexSelect = Const.sexSelect;
		
		Session session = model.Util.sessionFactory.openSession();
		
		Criteria q = session.createCriteria(model.StudentProfile.class)
				.add(Restrictions.eq("isPassed", model.StudentProfile.Passed))
				.addOrder(Order.desc("id"))
//				.add(Restrictions.eq("isUpgradePrivilege", model.StudentProfile.DepartureStudent))
				
				.add(Restrictions.not(Restrictions.eq("isUpgradePrivilege", model.StudentProfile.DepartureStudent)))
				
				;
		
		
		student_list = q.list();

		session.close();
		
		return ActionSupport.SUCCESS;
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

	public void setName_id(String name_id) {
		this.name_id = name_id;
	}

	public int getIsUpgradePrivilege() {
		return isUpgradePrivilege;
	}



	public void setIsUpgradePrivilege(int isUpgradePrivilege) {
		this.isUpgradePrivilege = isUpgradePrivilege;
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
	public List<StudentProfile> getStudent_list() {
		return student_list;
	}



	public void setStudent_list(List<StudentProfile> student_list) {
		this.student_list = student_list;
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




	public String getStudenttable_jsp() {
		return studenttable_jsp;
	}


	public void setStudenttable_jsp(String studenttable_jsp) {
		this.studenttable_jsp = studenttable_jsp;
	}


	public String getIsException() {
		return isException;
	}


	public void setIsException(String isException) {
		this.isException = isException;
	}


	public String getIsRepeat() {
		return isRepeat;
	}


	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}
	public int getStudentDatabaseId() {
		return studentDatabaseId;
	}

	public void setStudentDatabaseId(int studentDatabaseId) {
		this.studentDatabaseId = studentDatabaseId;
	}
	public List<model.ExamStuScore> getStudentScoreList() {
		return studentScoreList;
	}
	public void setStudentScoreList(List<model.ExamStuScore> studentScoreList) {
		this.studentScoreList = studentScoreList;
	}
	public String getStudentScoreJsp() {
		return studentScoreJsp;
	}
	public void setStudentScoreJsp(String studentScoreJsp) {
		this.studentScoreJsp = studentScoreJsp;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	



	
}
