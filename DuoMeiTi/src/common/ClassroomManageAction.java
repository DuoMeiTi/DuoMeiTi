package common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//import Repair.RepairDAO;
//import RepairImpl.RepairDAOImpl;
import model.CheckRecord;
import model.Classroom;
import model.Repertory;
import model.RoomPicture;
import model.StudentProfile;
import model.TeachBuilding;


public class ClassroomManageAction extends ActionSupport {
	
	 
	String searchType;
	String searchParam;
	String classroomHtml;
	String status;
	
	int build_id;
	String build_name;
	
	String studentNumber;// 学号
	int studentId; // studentProfile 的id;
	List classroom_list;
	String add_classroom_num;
	String submit_type;
	int classroomId;
	
	String classroomManageJsp;
	String deleteID;
//	String url;
	
	public String makeUrl()
	{
		return ServletActionContext.getRequest().getRequestURI();
	}
	
	public String classroomDelete() throws Exception{
		System.out.println("classroomDelete"+deleteID);
		//
		try{
			
		
		int user_id = (int) ActionContext.getContext().getSession().get("user_id");
		Session session = model.Util.sessionFactory.openSession();
		Criteria repertory_criteria = session.createCriteria(Repertory.class);
		repertory_criteria.add(Restrictions.eq("rtClassroom.id", Integer.parseInt(deleteID)));
		List<Repertory> repertory_list= repertory_criteria.list();
		
		//先将教室里的设备改为备用状态
		System.out.println(repertory_list);
//		RepairDAO rdao = new RepairDAOImpl();
		for(Repertory repertory: repertory_list){//将所有设备改为备用
			util.Util.modifyDeviceStatus(session, 
										repertory.rtId, 
										user_id, 
										util.Util.DeviceBackupStatus, 
										-1 );
//			String move_device_id=Integer.toString(repertory.rtId);
//			System.out.println("设备ID："+move_device_id);
//			String ret = Integer.toString(rdao.m2alter(move_device_id, "2"));
		}
		repertory_criteria.add(Restrictions.eq("classroom.id", Integer.parseInt(deleteID)));
//		List<Repertory> repertory_list2= repertory_criteria.list();
		
		//删除对应的教室照片与数据库
		Criteria picture_criteria = session.createCriteria(RoomPicture.class);
		picture_criteria.add(Restrictions.eq("class_id", Integer.parseInt(deleteID)));
		List<RoomPicture> picture_list= picture_criteria.list();
		for(RoomPicture picture : picture_list){
			System.out.println(util.Util.RootPath+picture.path);
			util.Util.deleteFile(util.Util.RootPath+picture.path);
			session.beginTransaction();
			session.delete(picture);
			Transaction t = session.getTransaction();
			t.commit();
		}
		//删除检查记录
		Criteria check_criteria = session.createCriteria(CheckRecord.class);
		check_criteria.add(Restrictions.eq("classroom.id", Integer.parseInt(deleteID)));
		List<CheckRecord> check_list = check_criteria.list();
		for(CheckRecord checkrecord : check_list){
			System.out.println(checkrecord);
			session.beginTransaction();
			session.delete(checkrecord);
			Transaction t = session.getTransaction();
			t.commit();
		}
		//删除课表
		Criteria class_criteria = session.createCriteria(Classroom.class);
		class_criteria.add(Restrictions.eq("id", Integer.parseInt(deleteID)));
		List<Classroom> class_list = class_criteria.list();
		Classroom classroom = class_list.get(0);
		System.out.println(util.Util.RootPath+classroom.getClass_schedule_path());
		util.Util.deleteFile(util.Util.RootPath + classroom.getClass_schedule_path());
		//删除教室
		session.beginTransaction();
		session.delete(classroom);
		Transaction t = session.getTransaction();
		t.commit();
		
		status = "1";
		session.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String classroomList() throws Exception {
		
		if(makeUrl().contains(util.Const.AdminRole))			
			classroomManageJsp = "/jsp/admin/classroomManage.jsp";
		else if(makeUrl().contains(util.Const.StudentRole))
		{
			System.err.println("EEEEEEEEEEEEEEEEEEEE");
		}
		else 
			classroomManageJsp = "/jsp/homepage/classroomManage.jsp";
		
		Session session = model.Util.sessionFactory.openSession();
		
		classroom_list = util.Util.obtainClassroomList(session, build_id);
//		Criteria classroom_criteria = session.createCriteria(Classroom.class);		
//		classroom_criteria.add(Restrictions.eq("teachbuilding.build_id", build_id));
//		classroom_criteria.addOrder(Order.asc("classroom_num"));
//		classroom_list= classroom_criteria.list();
		session.close();
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH "+"  "+classroom_list.size());
		return SUCCESS;
	}
	
	public String search() throws Exception {		
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria classroom_criteria = session.createCriteria(Classroom.class);
		System.out.println(searchType);
		
		classroom_criteria.add(Restrictions.eq("teachbuilding.build_id", build_id));
		if(searchType.equals("classroomNum"))
		{
			System.out.println("&&&&&&&&*************");
			System.out.println(build_id);
			System.out.println(searchParam);
			classroom_criteria.add(Restrictions.eq("classroom_num", searchParam));
		}
		else if(searchType.equals("principal"))
		{
			System.out.println("--------------------");
			System.out.println(searchParam);			
			classroom_criteria.createAlias("principal", "principal");
			classroom_criteria.createAlias("principal.user", "user"); 
			classroom_criteria.add(Restrictions.eq("user.fullName" ,searchParam));	
		}		
		classroom_criteria.addOrder(Order.asc("classroom_num"));
		classroom_list = classroom_criteria.list();
		classroomHtml = util.Util.getJspOutput("/jsp/classroom/classroomTable.jsp");
		session.close();
		
		return SUCCESS;
	}
	
	public String queryStu() throws Exception {
		Session session = model.Util.sessionFactory.openSession();
		Criteria stu_criteria = session.createCriteria(StudentProfile.class);
		stu_criteria.add(Restrictions.eq("studentId", studentNumber));
		Object obj = stu_criteria.uniqueResult();
		if(obj == null) {
			status = "";
		}
		else {
			StudentProfile stu = (StudentProfile) obj;
			status = stu.user.fullName;
		}
		session.close();

		return SUCCESS;
	}
	
	
	// 若非空表示有错误信息
	String addClassroom_status="";
	
	public String addClassroom() throws Exception {
		
		add_classroom_num = add_classroom_num.trim();
		
		if(add_classroom_num.isEmpty())
		{
			addClassroom_status = "新的教室号为空,操作不成功";
			return SUCCESS;
		}
		
		Session session = model.Util.sessionFactory.openSession();		
		StudentProfile principalStudent = util.Util.getStudentByStudentId(session, studentNumber);
		if(submit_type.equals("add"))
		{
			for(String splitedAddedClassroomNum : add_classroom_num.split(" "))
			{
				splitedAddedClassroomNum = splitedAddedClassroomNum.trim();
				if(splitedAddedClassroomNum.isEmpty()) continue;
				
//				Classroom classroom = (Classroom)util.Util.obtainClassroomListCriteria(session, build_id)
//						 			.add(Restrictions.eq("classroom_num", splitedAddedClassroomNum)).uniqueResult();
				
				Classroom classroom = util.Util.getClassroom(session, build_id, splitedAddedClassroomNum);
				if(classroom != null)
				{
					//此教室已经存在
					addClassroom_status = addClassroom_status + splitedAddedClassroomNum + "教室已存在，无法添加\n"; 
					continue;
				}
				
				classroom = new Classroom();				
				classroom.teachbuilding = new TeachBuilding();
				classroom.teachbuilding.build_id = build_id;
				classroom.classroom_num = splitedAddedClassroomNum;
				classroom.principal = principalStudent;
				session.beginTransaction();
				session.save(classroom);
				session.getTransaction().commit();
			}
		}
		else 
		{
			Classroom classroom = util.Util.getClassroomById(session, classroomId);
			if(classroom == null)
			{				
				// 出现错误，无法编辑classroom
				addClassroom_status = "未找到对应教室ID，无法编辑";
			}
			else
			{
				
				Classroom existClassroom = util.Util.getClassroom(session, classroom.teachbuilding.build_id, add_classroom_num);
				
				if(existClassroom != null && existClassroom.id != classroomId)
				{
					// 教室号已存在,无法设置新的教室号
					addClassroom_status = "新的教室号已经存在，无法编辑";
				}
				else 
				{
					classroom.classroom_num = add_classroom_num;
					classroom.principal = principalStudent;
					session.beginTransaction();
					session.update(classroom);
					session.getTransaction().commit();
				}
				
				
				
			}
			
		}
		
		
		
		
		
		session.close();
		
//		System.out.println("size:" + classroom_list.size());
//		if(classroom_list.size() > 0 && submit_type.equals("add")) {
//			this.status = "exist";
//			
//		}
//		else {
//			Criteria build_criteria = session.createCriteria(TeachBuilding.class);
//			build_criteria.add(Restrictions.eq("build_id", build_id));
//			TeachBuilding build = (TeachBuilding) build_criteria.uniqueResult();
//			
//			Criteria stu_criteria = session.createCriteria(StudentProfile.class);
//			stu_criteria.add(Restrictions.eq("studentId", studentNumber));
//			
//			List stu_list = stu_criteria.list();
//			
//			if(!studentNumber.equals("") && stu_list.isEmpty())
//			{
//				this.status = "no_principal";
//				System.out.println("IIIII************************");
//				session.close();
//				return SUCCESS;
//			}
//			
//			StudentProfile stu = null;
//			if(!stu_list.isEmpty())
//			stu = (StudentProfile)stu_list.get(0);
//
//			System.out.println("addClassroom: update***&&" + add_classroom_num);
//			
//			Classroom classroom = null;
//			if(submit_type.equals("add"))
//				classroom = new Classroom();
//			else if(submit_type.equals("update")) 
//				classroom = (Classroom) session.createCriteria(Classroom.class).add(Restrictions.eq("id", classroomId)).uniqueResult();
//			
//			System.out.println("addClassroom: classroomId***&&" + classroomId);
//			classroom.setTeachbuilding(build);
//			classroom.setPrincipal(stu);	
//			classroom.setClassroom_num(add_classroom_num);
//			session.beginTransaction();
//			if(submit_type.equals("add"))
//				session.save(classroom);
//			else if(submit_type.equals("update"))
//				session.update(classroom);
//			
//			session.getTransaction().commit();
//			
//			this.status = "ok";
//		}
		
		
//		classroom_criteria = session.createCriteria(Classroom.class);		
//		classroom_criteria.add(Restrictions.eq("teachbuilding.build_id", build_id));
//		classroom_criteria.addOrder(Order.asc("classroom_num"));
//		classroom_list= classroom_criteria.list();
//		classroomHtml = util.Util.getJspOutput("/jsp/classroom/classroomTable.jsp");
//
//		session.close();
//		System.out.println("add ok!");
		
//		classroomList();
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getSubmit_type() {
		return submit_type;
	}

	public void setSubmit_type(String submit_type) {
		this.submit_type = submit_type;
	}

	public String getAdd_classroom_num() {
		return add_classroom_num;
	}

	public void setAdd_classroom_num(String add_classroom_num) {
		this.add_classroom_num = add_classroom_num;
	}


	public String getClassroomHtml() {
		return classroomHtml;
	}

	public void setClassroomHtml(String classroomHtml) {
		this.classroomHtml = classroomHtml;
	}

	public String getBuild_name() {
		return build_name;
	}

	public void setBuild_name(String build_name) {
		this.build_name = build_name;
	}
	public int getBuild_id() {
		return build_id;
	}
	public void setBuild_id(int build_id) {
		this.build_id = build_id;
	}

	public List getClassroom_list() {
		return classroom_list;
	}
	public void setClassroom_list(List classroom_list) {
		this.classroom_list = classroom_list;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}
	public int getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}

	public String getDeleteID() {
		return deleteID;
	}

	public void setDeleteID(String deleteID) {
		this.deleteID = deleteID;
	}

	public String getClassroomManageJsp() {
		return classroomManageJsp;
	}

	public void setClassroomManageJsp(String classroomManageJsp) {
		this.classroomManageJsp = classroomManageJsp;
	}

	public String getAddClassroom_status() {
		return addClassroom_status;
	}

	public void setAddClassroom_status(String addClassroom_status) {
		this.addClassroom_status = addClassroom_status;
	}
	
	
	
}
