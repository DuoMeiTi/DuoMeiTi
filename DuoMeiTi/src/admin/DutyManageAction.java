package admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//import common.DutyInfo;
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
//import model.ChooseClassSwitch;
import model.DutyPlace;
import model.User;

public class DutyManageAction extends ActionSupport {

	static {

		Session session = model.Util.sessionFactory.openSession();
		List L = session.createCriteria(model.DutyChooseSwitch.class).list();
		if (L.size() == 0) {
			session.beginTransaction();

			model.DutyChooseSwitch dcs = new model.DutyChooseSwitch();
			dcs.setIsOpen(false);
			session.save(dcs);

			session.getTransaction().commit();
		}
		session.close();

	}

	List dutyPlaceList;
//	String newDutyPlace = "";
	boolean dutyChooseSwitchIsOpen;

	public boolean getDutyChooseSwitchIsOpen() {
		return dutyChooseSwitchIsOpen;
	}

	public void setDutyChooseSwitchIsOpen(boolean dutyChooseSwitchIsOpen) {
		this.dutyChooseSwitchIsOpen = dutyChooseSwitchIsOpen;
	}

	public String execute() throws Exception {
//		System.out.println("GGGG");
		// if(ServletActionContext.getRequest().getMethod().equalsIgnoreCase("get"))
		// {
		// return ActionSupport.SUCCESS;
		// }
		Session session = model.Util.sessionFactory.openSession();

		dutyPlaceList = session.createCriteria(model.DutyPlace.class).list();

//		System.out.println(this.newDutyPlace);

		model.DutyChooseSwitch dutyChooseSwitch = (model.DutyChooseSwitch) session
				.createCriteria(model.DutyChooseSwitch.class).list().get(0);

		dutyChooseSwitchIsOpen = dutyChooseSwitch.getIsOpen();

		session.close();
		return SUCCESS;
	}

	public String switchDutyChoose() throws Exception {

		Session session = model.Util.sessionFactory.openSession();

		session.beginTransaction();
		model.DutyChooseSwitch dcs = (model.DutyChooseSwitch) session.createCriteria(model.DutyChooseSwitch.class)
				.list().get(0);

		dcs.setIsOpen(!dcs.getIsOpen());
		session.update(dcs);
		session.getTransaction().commit();
		session.close();
		return this.SUCCESS;

	}

	
	String addDutyPlace_placeName; //in
	String addDutyPlace_status; //out
	public String addDutyPlace() throws Exception {

		String newDutyPlace = addDutyPlace_placeName;
		
		if (newDutyPlace.isEmpty()) {
			addDutyPlace_status = "输入的新值班地点名称为空";
			return SUCCESS;
		}
			
		
		Session session = model.Util.sessionFactory.openSession();
		boolean isExist = util.Util.isExistWithOneEqualRestriction(session, DutyPlace.class, "placeName",  newDutyPlace);
		
		if(isExist)
		{
			addDutyPlace_status = "输入的新值班地点已经存在";
		}
		else
		{
			model.DutyPlace dp = new model.DutyPlace();
			dp.setPlaceName(newDutyPlace);
			session.beginTransaction();
			session.save(dp);

			for (int i = 0; i < model.DutyPiece.TimeNumber; ++i) {
				DutyPiece dt = new DutyPiece();
				dt.setNumberOfDuty(4); // 当前时间段的值班个数容纳总量
				dt.setDutyLeft(4); // 当前时间段的值班个数的剩余量
				dt.setDutyPlace(dp);
				dt.setTime(i);
				session.save(dt);
			}

			session.getTransaction().commit();
			session.close();
			
			addDutyPlace_status ="";

		}
		
		


		return SUCCESS;
	}

	int deletedDutyPlaceId;

	public String deleteDutyPlace() throws Exception {

		System.out.println(this.deletedDutyPlaceId);
		int id = deletedDutyPlaceId;

		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		DutyPlace dp = (DutyPlace) session.createCriteria(model.DutyPlace.class).add(Restrictions.eq("id", id))
				.uniqueResult();

		List<DutyPiece> dutyTimeList = (List<DutyPiece>) session.createCriteria(model.DutyPiece.class)
				.add(Restrictions.eq("dutyPlace.id", id)).list();

		for (model.DutyPiece dt : dutyTimeList) {
			session.delete(dt);
		}

		session.delete(dp);
		session.getTransaction().commit();
		session.close();
		return SUCCESS;
	}

	public int obtainDutyTable_dutyPlaceId;
	public List<DutySchedule> obtainDutyTable_dutyScheduleList;
	public List<DutyPiece> obtainDutyTable_dutyPieceList;

	public String obtainDutyTable() throws Exception {

		int id = obtainDutyTable_dutyPlaceId;

		Session session = model.Util.sessionFactory.openSession();

		obtainDutyTable_dutyPieceList = (List<DutyPiece>) session.createCriteria(model.DutyPiece.class)
				.add(Restrictions.eq("dutyPlace.id", id)).list();

		obtainDutyTable_dutyScheduleList = new ArrayList<DutySchedule>();

		for (DutyPiece dp : obtainDutyTable_dutyPieceList) {
			List dutyScheduleList = session.createCriteria(model.DutySchedule.class)
					.add(Restrictions.eq("dutyPiece.id", dp.getId())).list();

			obtainDutyTable_dutyScheduleList.addAll(dutyScheduleList);

		}
		System.out.println(obtainDutyTable_dutyScheduleList);
		session.close();
		return SUCCESS;
	}

	public int deleteDutySchedule_id;

	public String deleteDutySchedule() throws Exception {
		int id = deleteDutySchedule_id;
		System.out.println(id);

		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		util.Util.deleteDutySchedule(session, id);
		session.getTransaction().commit();
		session.close();

		return SUCCESS;
	}

//	public static void addDutySchedule(Session session, int studentDatabaseId, int dutyPieceId) {
//
//	}

	
	
	
	
	
	// 指明{@code addDutySchedule_studentFullNameOrStudentIdListString} 是学生姓名还是学生学号
	String addDutySchedule_selectAddStudentType;
	// 学生的姓名或者学号的列表字符串，按照空格分隔
	String addDutySchedule_studentFullNameOrStudentIdListString;
	int addDutySchedule_dutyPlaceId;
	int addDutySchedule_dutyPieceTime;
	
	List<DutySchedule> addDutySchedule_addedDutyScheduleList;
	// 如果没有错误为空，否则有值
	String addDutySchedule_status;
	public String addDutySchedule() 
			throws Exception 
	{
		int dutyPlaceId = addDutySchedule_dutyPlaceId;
		int dutyPieceTime = addDutySchedule_dutyPieceTime;
		String selectAddStudentType = addDutySchedule_selectAddStudentType;
		String studentFullNameOrStudentIdListString = addDutySchedule_studentFullNameOrStudentIdListString;	

		Session session = model.Util.sessionFactory.openSession();

		// 查询出对应的DutyPiece
		DutyPiece dp = (DutyPiece) session.createCriteria(model.DutyPiece.class)
				.add(Restrictions.eq("dutyPlace.id", dutyPlaceId)).add(Restrictions.eq("time", dutyPieceTime))
				.uniqueResult();

		StringBuilder statusBuilder = new StringBuilder();
		List<DutySchedule> addedDutyScheduleList = new ArrayList<DutySchedule>();
		if (dp == null) {
			statusBuilder.append("值班地点与值班时间段无法找到，所有在职学生均无法选择值班时间段;\n");
		} else {
			for (String fullNameOrId : studentFullNameOrStudentIdListString.split(" +")) {
				fullNameOrId = fullNameOrId.trim();
				
				// 忽略原来的字符串是空串的
				if(fullNameOrId.isEmpty()) {
					continue;
				}
				

				Criteria studentCriteria = session.createCriteria(model.StudentProfile.class);
				if (selectAddStudentType.equals("studentFullName")) {
					studentCriteria.createAlias("user", "user").add(Restrictions.eq("user.fullName", fullNameOrId));
				} else {
					studentCriteria.add(Restrictions.eq("studentId", fullNameOrId));
				}

				List<StudentProfile> studentList = studentCriteria.list();
				if (studentList.size() == 0) {
					statusBuilder.append(fullNameOrId +" 不存在，无法添加;\n");
					continue;
				} else if (studentList.size() > 1) {
					statusBuilder.append(fullNameOrId +" 有重名，无法添加;\n");
					continue;
				}

				StudentProfile sp = studentList.get(0);

				DutySchedule ds = (DutySchedule) session.createCriteria(model.DutySchedule.class)
						.add(Restrictions.eq("dutyPiece.id", dp.getId()))
						.add(Restrictions.eq("student.id", sp.getId()))
						.uniqueResult();

				if (ds != null) {
					statusBuilder.append(fullNameOrId + "已经选择了此值班时间段， 无法添加;\n");
				} else if (dp.getDutyLeft() == 0) {
					statusBuilder.append("此时间段超出最大容纳的人数," + fullNameOrId + "无法添加;\n");
				} else {
					ds = new DutySchedule();
					ds.setDutyPiece(dp);
					ds.setStudent(sp);
					dp.dutyLeft--;
					
					try {
						session.beginTransaction();
						session.save(ds);
						session.update(dp);
						session.getTransaction().commit();
						addedDutyScheduleList.add(ds);
					} catch(Exception e) {
						session.getTransaction().rollback();
						e.printStackTrace();
						statusBuilder.append("数据库发生错误，" + fullNameOrId + " 添加不成功;\n");
					}
					
				}
			}
		}
		session.close();		
		addDutySchedule_addedDutyScheduleList = addedDutyScheduleList;		
		addDutySchedule_status = statusBuilder.toString();
		return SUCCESS;
	}
	
	
	
	
	String status; 
	int updateDutyNumber_dutyNumber;
	int updateDutyNumber_dutyPieceId;

	public String updateDutyNumber() throws Exception {
		int dutyNumber = updateDutyNumber_dutyNumber;
		int dutyPieceId = updateDutyNumber_dutyPieceId;

		Session session = model.Util.sessionFactory.openSession();
		DutyPiece dp = (DutyPiece) session.createCriteria(model.DutyPiece.class).add(Restrictions.eq("id", dutyPieceId))
				.uniqueResult();

		if (dp != null) {
			int cntDuty = dp.getNumberOfDuty() - dp.getDutyLeft();
			if (cntDuty <= dutyNumber) {
				dp.setNumberOfDuty(dutyNumber);
				dp.setDutyLeft(dutyNumber - cntDuty);
				session.beginTransaction();
				session.update(dp);
				session.getTransaction().commit();
				this.status = "0更新成功";
			} else {
				this.status = "2错误：新的值班容量小于当前已有的值班数";
			}

		} else {
			this.status = "1未找到对应的选班管理单元";
		}

		session.close();
		return SUCCESS;
	}

	public List getDutyPlaceList() {
		return dutyPlaceList;
	}

	public void setDutyPlaceList(List dutyPlaceList) {
		this.dutyPlaceList = dutyPlaceList;
	}

//	public String getNewDutyPlace() {
//		return newDutyPlace;
//	}
//
//	public void setNewDutyPlace(String newDutyPlace) {
//		this.newDutyPlace = newDutyPlace;
//	}

	public int getDeletedDutyPlaceId() {
		return deletedDutyPlaceId;
	}

	public void setDeletedDutyPlaceId(int deletedDutyPlaceId) {
		this.deletedDutyPlaceId = deletedDutyPlaceId;
	}

	public int getObtainDutyTable_dutyPlaceId() {
		return obtainDutyTable_dutyPlaceId;
	}

	public void setObtainDutyTable_dutyPlaceId(int obtainDutyTable_dutyPlaceId) {
		this.obtainDutyTable_dutyPlaceId = obtainDutyTable_dutyPlaceId;
	}

	public List<DutySchedule> getObtainDutyTable_dutyScheduleList() {
		return obtainDutyTable_dutyScheduleList;
	}

	public void setObtainDutyTable_dutyScheduleList(List<DutySchedule> obtainDutyTable_dutyScheduleList) {
		this.obtainDutyTable_dutyScheduleList = obtainDutyTable_dutyScheduleList;
	}

	public List<DutyPiece> getObtainDutyTable_dutyPieceList() {
		return obtainDutyTable_dutyPieceList;
	}

	public void setObtainDutyTable_dutyPieceList(List<DutyPiece> obtainDutyTable_dutyPieceList) {
		this.obtainDutyTable_dutyPieceList = obtainDutyTable_dutyPieceList;
	}

	public int getDeleteDutySchedule_id() {
		return deleteDutySchedule_id;
	}

	public void setDeleteDutySchedule_id(int deleteDutySchedule_id) {
		this.deleteDutySchedule_id = deleteDutySchedule_id;
	}

	public int getAddDutySchedule_dutyPlaceId() {
		return addDutySchedule_dutyPlaceId;
	}

	public void setAddDutySchedule_dutyPlaceId(int addDutySchedule_dutyPlaceId) {
		this.addDutySchedule_dutyPlaceId = addDutySchedule_dutyPlaceId;
	}

	// public int getAddDutySchedule_studentId() {
	// return addDutySchedule_studentId;
	// }
	//
	// public void setAddDutySchedule_studentId(int addDutySchedule_studentId) {
	// this.addDutySchedule_studentId = addDutySchedule_studentId;
	// }

	public int getAddDutySchedule_dutyPieceTime() {
		return addDutySchedule_dutyPieceTime;
	}

	public String getAddDutySchedule_selectAddStudentType() {
		return addDutySchedule_selectAddStudentType;
	}

	public void setAddDutySchedule_selectAddStudentType(String addDutySchedule_selectAddStudentType) {
		this.addDutySchedule_selectAddStudentType = addDutySchedule_selectAddStudentType;
	}

	public String getAddDutySchedule_studentFullNameOrIdListString() {
		return addDutySchedule_studentFullNameOrStudentIdListString;
	}

	public void setAddDutySchedule_studentFullNameOrIdListString(String addDutySchedule_studentFullNameOrIdListString) {
		this.addDutySchedule_studentFullNameOrStudentIdListString = addDutySchedule_studentFullNameOrIdListString;
	}

	public void setAddDutySchedule_dutyPieceTime(int addDutySchedule_dutyPieceTime) {
		this.addDutySchedule_dutyPieceTime = addDutySchedule_dutyPieceTime;
	}

//	public int getAddDutySchedule_addeddutyScheduleId() {
//		return addDutySchedule_addeddutyScheduleId;
//	}
//
//	public void setAddDutySchedule_addeddutyScheduleId(int addDutySchedule_addeddutyScheduleId) {
//		this.addDutySchedule_addeddutyScheduleId = addDutySchedule_addeddutyScheduleId;
//	}
	
	public String getStatus() {
		return status;
	}

//	public List<Integer> getAddDutySchedule_addedDutyScheduleIdList() {
//		return addDutySchedule_addedDutyScheduleIdList;
//	}
//
//	public void setAddDutySchedule_addedDutyScheduleIdList(List<Integer> addDutySchedule_addedDutyScheduleIdList) {
//		this.addDutySchedule_addedDutyScheduleIdList = addDutySchedule_addedDutyScheduleIdList;
//	}

	public List<DutySchedule> getAddDutySchedule_addedDutyScheduleList() {
		return addDutySchedule_addedDutyScheduleList;
	}

	public void setAddDutySchedule_addedDutyScheduleList(List<DutySchedule> addDutySchedule_addedDutyScheduleList) {
		this.addDutySchedule_addedDutyScheduleList = addDutySchedule_addedDutyScheduleList;
	}

	public String getAddDutySchedule_status() {
		return addDutySchedule_status;
	}

	public void setAddDutySchedule_status(String addDutySchedule_status) {
		this.addDutySchedule_status = addDutySchedule_status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUpdateDutyNumber_dutyNumber() {
		return updateDutyNumber_dutyNumber;
	}

	public void setUpdateDutyNumber_dutyNumber(int updateDutyNumber_dutyNumber) {
		this.updateDutyNumber_dutyNumber = updateDutyNumber_dutyNumber;
	}

	public int getUpdateDutyNumber_dutyPieceId() {
		return updateDutyNumber_dutyPieceId;
	}

	public void setUpdateDutyNumber_dutyPieceId(int updateDutyNumber_dutyPieceId) {
		this.updateDutyNumber_dutyPieceId = updateDutyNumber_dutyPieceId;
	}

	public String getAddDutySchedule_studentFullNameOrStudentIdListString() {
		return addDutySchedule_studentFullNameOrStudentIdListString;
	}

	public void setAddDutySchedule_studentFullNameOrStudentIdListString(
			String addDutySchedule_studentFullNameOrStudentIdListString) {
		this.addDutySchedule_studentFullNameOrStudentIdListString = addDutySchedule_studentFullNameOrStudentIdListString;
	}

	public String getAddDutyPlace_placeName() {
		return addDutyPlace_placeName;
	}

	public void setAddDutyPlace_placeName(String addDutyPlace_placeName) {
		this.addDutyPlace_placeName = addDutyPlace_placeName;
	}

	public String getAddDutyPlace_status() {
		return addDutyPlace_status;
	}

	public void setAddDutyPlace_status(String addDutyPlace_status) {
		this.addDutyPlace_status = addDutyPlace_status;
	}
	
	
}
