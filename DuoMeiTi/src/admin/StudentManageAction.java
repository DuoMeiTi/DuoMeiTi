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
import model.Classroom;
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

public class StudentManageAction extends ActionSupport {

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

	private List<StudentProfile> student_list;
	private String studenttable_jsp;

	List<model.ExamStuScore> studentScoreList;
	private String studentScoreJsp;

	private String isRepeat; // 标记学号是否重复
	// private String isException;

	private String ruleText;// 规章制度的内容,jsp页面传过来的内容
	private String textShow;// 规章制度的内容，显示给jsp页面的内容
	private Date time;// 规章制度的修改时间

	
//	public static Criteria getRegisterPassedStudentProfileCriteria(Session s)
//	{
//		return s.createCriteria(StudentProfile.class).add(Restrictions.eq("isPassed", model.StudentProfile.Passed));
//	}
//	
//	// 根据key和value查询满足条件的学生列表，排除了注册未通过的学生
//	public static List<StudentProfile> getRegisterPassedStudentProfileList(Session s, String queryKey, Object queryValue)
//	{
//		Criteria c = getRegisterPassedStudentProfileCriteria( s);
//		return util.Util.addOneEqualRestriction(c, queryKey, queryValue).list();
//	}
	
	
	String query_key; // in
	String query_value; // in
	List<StudentProfile> query_studentProfileList; // out
	/**for ajax 传输，只获取注册通过的学生*/
	public String query() throws Exception {
		Session s = model.Util.sessionFactory.openSession();
		query_studentProfileList = util.Util.getRegisterPassedStudentProfileList(s, query_key, query_value);
		s.close();
		return SUCCESS;
	}
	
	String queryUniqueStudent_key; // in
	String queryUniqueStudent_value; // in
	StudentProfile queryUniqueStudent_student; //out
	String queryUniqueStudent_status; //out
	/** 如果有超过一个或者没有，则queryUniqueStudent_status为有相应提示信息，否则为空*/
	public String queryUniqueStudent() throws Exception 
	{
		Session s = model.Util.sessionFactory.openSession();
		List<StudentProfile> tmp = util.Util.getRegisterPassedStudentProfileList(s, queryUniqueStudent_key, queryUniqueStudent_value);
				
		queryUniqueStudent_student = null;
		if(tmp.size() == 1)
		{
			queryUniqueStudent_student = tmp.get(0);
			queryUniqueStudent_status = "";
		}
		else if(tmp.isEmpty())
		{
			queryUniqueStudent_status = "无此学生";
		}
		else 
		{
			queryUniqueStudent_status = "超过一个学生";
		}		
				
		s.close();
		return SUCCESS;
	}
	
	
	
	
	// 排除注册未通过学生,通过学号查询
	public static List<StudentProfile> searchStudentByStudentNumber(Session s, String studentId) {
		return  util.Util.getRegisterPassedStudentProfileCriteria( s)

		.add(Restrictions.eq("studentId", studentId)).list();
	}

	// 排除注册未通过学生
	public static List<StudentProfile> searchStudentByFullname(Session s, String fullName) {
		return util.Util.getRegisterPassedStudentProfileCriteria( s)
				.createAlias("user", "user").add(Restrictions.eq("user.fullName", fullName)).list();
	}

	public String watchScore() throws Exception {
		Session s = model.Util.sessionFactory.openSession();

		studentScoreList = s.createCriteria(model.ExamStuScore.class)
				.add(Restrictions.eq("stuPro.id", this.studentDatabaseId)).list();
		studentScoreJsp = util.Util.getJspOutput("/jsp/admin/student_manage/studentScoreTable.jsp");
		s.close();

		return SUCCESS;
	}


	// 仅仅为了界面搜索使用
	public String search() throws Exception {
		System.out.println("searchStudentInformation():");
		Session s = model.Util.sessionFactory.openSession();

		try {

			if (search_select.equals("2")) {
				// 按学号查找
				student_list = searchStudentByStudentNumber(s, name_id);

			} else {// 按姓名查找
				student_list = searchStudentByFullname(s, name_id);
			}

			Collections.reverse(student_list);
			studenttable_jsp = util.Util.getJspOutput("/jsp/admin/student_manage/studenttable.jsp");

			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

//	public static StudentProfile getStudentById(Session s, int id) {
//		return (StudentProfile) s.createCriteria(model.StudentProfile.class).add(Restrictions.eq("id", id))
//				.uniqueResult();
//	}

	public String save() throws Exception {
		Session session = model.Util.sessionFactory.openSession();
		try {

			System.out.println("saveStudentInformation():");

			
			StudentProfile edit_student = util.Util.getStudentByDatabaseId(session, studentDatabaseId);

			System.out.println(edit_student == null);

			if (studentId.isEmpty()) {
				isRepeat = "修改失败，学号为空！";
				session.close();
				return SUCCESS;
			}
			if (!studentId.equals(edit_student.studentId)) {
				// System.out.println("修改学号");
				if (homepage.StudentAction.isRepeat(session, studentId)) {
					// System.out.println("chongfu");
					isRepeat = "修改失败，学号已存在！";
					session.close();
					return SUCCESS;
				}
			}

			session.beginTransaction();
			User edit_user = edit_student.getUser();
			edit_user.setFullName(fullName);
			edit_user.setPhoneNumber(phoneNumber);
			edit_user.setSex(sex);
			
			edit_student.setStudentId(studentId);
			edit_student.setCollege(college);
			edit_student.setIsUpgradePrivilege(isUpgradePrivilege);
			edit_student.setBankCard(bankCard);
			edit_student.setIdCard(idCard);

			session.update(edit_user);
			session.update(edit_student);
			
			session.getTransaction().commit();

			

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		session.close();
		isRepeat = "修改成功";
		return SUCCESS;
	}

	public String obtain() throws Exception {

		System.out.println("getStudentInformation():");
		System.out.println("edit_student_id:" + studentDatabaseId);

		Session session = model.Util.sessionFactory.openSession();
		String hql = "SELECT rt FROM StudentProfile rt WHERE rt.id = " + studentDatabaseId;
		System.out.println(hql);
		Query query = session.createQuery(hql);
		student_list = query.list();
		StudentProfile edit_student = student_list.get(0);

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

	private int delete_studentDatabaseId;
	private String delete_status;

	public String delete() throws Exception {

		System.out.println("studentInformationDelete():");
		System.out.println(delete_studentDatabaseId);
		Session session = model.Util.sessionFactory.openSession();

		try {
			session.beginTransaction();
			StudentProfile edit_student = util.Util.getStudentByDatabaseId(session, delete_studentDatabaseId);

			// 删除学生的对应值班选择
			for (DutySchedule ds : (List<DutySchedule>) session.createCriteria(model.DutySchedule.class)
					.add(Restrictions.eq("student.id", delete_studentDatabaseId)).list()) {
				util.Util.deleteDutySchedule(session, ds.id);
			}

			// 删除学生的负责教室
			for (Classroom classroom : (List<Classroom>) session.createCriteria(model.Classroom.class)
					.add(Restrictions.isNotNull("principal.id")).list())

			{
				classroom.setPrincipal(null);
				session.update(classroom);
			}

			// 删除学生的考试题目选项
			for (model.ExamStuOption eso : (List<model.ExamStuOption>) session.createCriteria(model.ExamStuOption.class)
					.add(Restrictions.eq("stuPro.id", delete_studentDatabaseId)).list()) {
				session.delete(eso);
			}

			// 删除学生的考试得分记录
			for (model.ExamStuScore ess : (List<model.ExamStuScore>) session.createCriteria(model.ExamStuScore.class)
					.add(Restrictions.eq("stuPro.id", delete_studentDatabaseId)).list()) {
				session.delete(ess);
			}

			// 删除学生的签到记录
			for (model.CheckInRecord checkInRecord : (List<model.CheckInRecord>) session
					.createCriteria(model.CheckInRecord.class)
					.add(Restrictions.eq("student.id", delete_studentDatabaseId)).list()) {
				session.delete(checkInRecord);
			}

			// 删除学生的签到记录
			for (model.CheckInRecord checkInRecord : (List<model.CheckInRecord>) session
					.createCriteria(model.CheckInRecord.class)
					.add(Restrictions.eq("student.id", delete_studentDatabaseId)).list()) {
				session.delete(checkInRecord);
			}

			// 删除学生的紧急消息记录
			for (model.EmergencyInfo ei : (List<model.EmergencyInfo>) session.createCriteria(model.EmergencyInfo.class)
					.add(Restrictions.eq("user.id", edit_student.user.id)).list()) {
				session.delete(ei);
			}

			// 删除学生的紧急消息记录的阅读记录
			for (model.EmergencyInfoRead eir : (List<model.EmergencyInfoRead>) session
					.createCriteria(model.EmergencyInfoRead.class).add(Restrictions.eq("user.id", edit_student.user.id))
					.list()) {
				session.delete(eir);
			}

			// 删除学生对应的周检查记录
			for (model.CheckRecord cr : (List<model.CheckRecord>) session.createCriteria(model.CheckRecord.class)
					.add(Restrictions.eq("checkman.id", edit_student.user.id)).list()) {
				session.delete(cr);
			}

			// 删除学生对应的设备状态历史记录
			for (model.DeviceStatusHistory dsh : (List<model.DeviceStatusHistory>) session
					.createCriteria(model.DeviceStatusHistory.class)
					.add(Restrictions.eq("user.id", edit_student.user.id)).list()) {
				session.delete(dsh);
			}

			// 删除学生信息
			session.delete(edit_student);
			// 删除student对应的user
			session.delete(
					session.createCriteria(User.class).add(Restrictions.eq("id", edit_student.user.id)).uniqueResult());

			session.getTransaction().commit();
			delete_status = "删除成功";

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			delete_status = "删除失败";
		} finally {
			session.close();
		}

		return SUCCESS;
	}

	// 所有在职学生（包括有管理员权限的）

	public String obtainWorkingStudent() throws Exception {

		try {

			Session session = model.Util.sessionFactory.openSession();

			Criteria q = util.Util.getRegisterPassedStudentProfileCriteria( session).addOrder(Order.desc("id"))
			.add(Restrictions.not(Restrictions.eq("isUpgradePrivilege", model.StudentProfile.DepartureStudent)));

			student_list = q.list();
			studenttable_jsp = util.Util.getJspOutput("/jsp/admin/student_manage/studenttable.jsp");

			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ActionSupport.SUCCESS;

	}

	// 获取离职学生！
	public String obtainDepartureStudent() throws Exception {

		try {

			Session session = model.Util.sessionFactory.openSession();

			Criteria q = util.Util.getRegisterPassedStudentProfileCriteria( session).addOrder(Order.desc("id"))
			.add(Restrictions.eq("isUpgradePrivilege", model.StudentProfile.DepartureStudent));

			student_list = q.list();
			studenttable_jsp = util.Util.getJspOutput("/jsp/admin/student_manage/studenttable.jsp");

			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ActionSupport.SUCCESS;
	}

	// 页面显示
	public String studentInformation() throws Exception {

		collegeSelect = Const.collegeSelect;
		sexSelect = Const.sexSelect;

		Session session = model.Util.sessionFactory.openSession();

		Criteria q =  util.Util.getRegisterPassedStudentProfileCriteria( session).addOrder(Order.desc("id"))
				// .add(Restrictions.eq("isUpgradePrivilege",
				// model.StudentProfile.DepartureStudent))

		.add(Restrictions.not(Restrictions.eq("isUpgradePrivilege", model.StudentProfile.DepartureStudent)))

		;

		student_list = q.list();

		session.close();

		return ActionSupport.SUCCESS;
	}

	// 编辑规章制度
	public String editRules() throws Exception {
		System.out.println("StudentManageAction.editRules()");

		Session session = model.Util.sessionFactory.openSession();
		// 将前台传过来的文字 更新到数据库
		Criteria q = session.createCriteria(Rules.class);
		Rules rules = new Rules();

		if (q.list().size() == 0) {// 如果现在表是空的，就插入到数据库中
			rules.setText(ruleText);
			rules.setTime(new Date(new java.util.Date().getTime()));
			session.beginTransaction();
			session.save(rules);
			session.getTransaction().commit();
			session.close();

		} else {// 如果不是空的，就更新数据库
			q.add(Restrictions.eq("id", 1));// 默认就一条数据，所以id设为1
			rules = (Rules) q.uniqueResult();
			rules.setText(ruleText);
			rules.setTime(new Date(new java.util.Date().getTime()));
			session.beginTransaction();
			session.save(rules);
			session.getTransaction().commit();
			session.close();
		}

		return ActionSupport.SUCCESS;
	}

	// 显示规章制度
	public String showRules() throws Exception {
		System.out.println("StudentManageAction.showRules()");

		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(Rules.class);
		Rules temp;
		if (q.list().size() > 0) {
			temp = (Rules) q.list().get(0);//
			textShow = temp.getText();
		} else {
			textShow = " ";
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

	public int getDelete_studentDatabaseId() {
		return delete_studentDatabaseId;
	}

	public void setDelete_studentDatabaseId(int delete_studentDatabaseId) {
		this.delete_studentDatabaseId = delete_studentDatabaseId;
	}

	public String getDelete_status() {
		return delete_status;
	}

	public void setDelete_status(String delete_status) {
		this.delete_status = delete_status;
	}

	public String getQuery_key() {
		return query_key;
	}

	public void setQuery_key(String query_key) {
		this.query_key = query_key;
	}

	public String getQuery_value() {
		return query_value;
	}

	public void setQuery_value(String query_value) {
		this.query_value = query_value;
	}

	public List<StudentProfile> getQuery_studentProfileList() {
		return query_studentProfileList;
	}

	public void setQuery_studentProfileList(List<StudentProfile> query_studentProfileList) {
		this.query_studentProfileList = query_studentProfileList;
	}

	public String getQueryUniqueStudent_key() {
		return queryUniqueStudent_key;
	}

	public void setQueryUniqueStudent_key(String queryUniqueStudent_key) {
		this.queryUniqueStudent_key = queryUniqueStudent_key;
	}

	public String getQueryUniqueStudent_value() {
		return queryUniqueStudent_value;
	}

	public void setQueryUniqueStudent_value(String queryUniqueStudent_value) {
		this.queryUniqueStudent_value = queryUniqueStudent_value;
	}

	public StudentProfile getQueryUniqueStudent_student() {
		return queryUniqueStudent_student;
	}

	public void setQueryUniqueStudent_student(StudentProfile queryUniqueStudent_student) {
		this.queryUniqueStudent_student = queryUniqueStudent_student;
	}

	public String getQueryUniqueStudent_status() {
		return queryUniqueStudent_status;
	}

	public void setQueryUniqueStudent_status(String queryUniqueStudent_status) {
		this.queryUniqueStudent_status = queryUniqueStudent_status;
	}
	
	
}
