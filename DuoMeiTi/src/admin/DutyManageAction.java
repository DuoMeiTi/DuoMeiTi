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
import utility.DatabaseOperation;

import model.DutyPiece;
import model.ExamStuScore;
import model.DutySchedule;
import model.ChooseClassSwitch;
import model.DutyPlace;
import model.User;

public class DutyManageAction extends ActionSupport {

	static {
		
		Session session = model.Util.sessionFactory.openSession();
		List L = session.createCriteria(model.DutyChooseSwitch.class).list();
		if(L.size() == 0)
		{
			session.beginTransaction();
			
			model.DutyChooseSwitch dcs = new model.DutyChooseSwitch();
			dcs.setIsOpen(false);
			session.save(dcs);
			
			session.getTransaction().commit();
		}
		session.close();


	}

	List dutyPlaceList;
	String newDutyPlace = "";
	boolean dutyChooseSwitchIsOpen;
	public boolean getDutyChooseSwitchIsOpen() {
		return dutyChooseSwitchIsOpen;
	}

	public void setDutyChooseSwitchIsOpen(boolean dutyChooseSwitchIsOpen) {
		this.dutyChooseSwitchIsOpen = dutyChooseSwitchIsOpen;
	}

	
	


	
	
	
	
	

	public String execute() throws Exception {
		System.out.println("GGGG");
		// if(ServletActionContext.getRequest().getMethod().equalsIgnoreCase("get"))
		// {
		// return ActionSupport.SUCCESS;
		// }
		Session session = model.Util.sessionFactory.openSession();

		dutyPlaceList = session.createCriteria(model.DutyPlace.class).list();

	

		System.out.println(this.newDutyPlace);
		
		model.DutyChooseSwitch 
		dutyChooseSwitch = (model.DutyChooseSwitch)session.createCriteria(model.DutyChooseSwitch.class).list().get(0);
		
		dutyChooseSwitchIsOpen = dutyChooseSwitch.getIsOpen();
	
		session.close();
		return SUCCESS;
	}
	
	
	
	public String switchDutyChoose() throws Exception {
		
		Session session = model.Util.sessionFactory.openSession();
		
		session.beginTransaction();
		model.DutyChooseSwitch dcs = 
				(model.DutyChooseSwitch)session.createCriteria(model.DutyChooseSwitch.class).list().get(0);
		
		dcs.setIsOpen(!dcs.getIsOpen());
		session.update(dcs);		
		session.getTransaction().commit();		
		session.close();
		return this.SUCCESS;
		
	}
	
	
	
	

	public String addDutyPlace() throws Exception {

		System.out.println(this.newDutyPlace);

		if (this.newDutyPlace.equals("")) {
			return SUCCESS;
		}

		model.DutyPlace dp = new model.DutyPlace();
		dp.setPlaceName(this.newDutyPlace);

		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		session.save(dp);

		for (int i = 0; i < model.DutyPiece.TimeNumber; ++i) {
			DutyPiece dt = new DutyPiece();
			dt.setNumberOfDuty(4); // 当前时间段的值班个数容纳总量
			dt.setDutyLeft(4);// 当前时间段的值班个数的剩余量
			dt.setDutyPlace(dp);
			dt.setTime(i);
			session.save(dt);
		}

		session.getTransaction().commit();

		session.close();

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
		DutySchedule ds = (DutySchedule)session
						.createCriteria(model.DutySchedule.class)
						.add(Restrictions.eq("id", id))
						.uniqueResult();
		
		if(ds != null)
		{
			DutyPiece dp = ds.dutyPiece;
			session.delete(ds);		
			dp.dutyLeft--;
			session.update(dp);
			
		}
		session.getTransaction().commit();
		
		
		session.close();
		
		return SUCCESS;
	}
	
	
	
	
	
	int addDutySchedule_dutyPlaceId;
	int addDutySchedule_studentId;
	int addDutySchedule_dutyPieceTime;
	int addDutySchedule_addeddutyScheduleId;
	String status;	// 0 OK， 1 已经添加过，2学生不合法，3其他故障 不成功
	public String addDutySchedule() 
			throws Exception
	{
		int dutyPlaceId = addDutySchedule_dutyPlaceId;
		int studentId = addDutySchedule_studentId;
		int dutyPieceTime = addDutySchedule_dutyPieceTime;
		
		
		Session session = model.Util.sessionFactory.openSession();
		
		StudentProfile sp = (StudentProfile)session.createCriteria(model.StudentProfile.class)
							.add(Restrictions.eq("id", studentId))
							.uniqueResult();
		
		DutyPiece dp = (DutyPiece)session.createCriteria(model.DutyPiece.class)
				.add(Restrictions.eq("dutyPlace.id", dutyPlaceId))
				.add(Restrictions.eq("time", dutyPieceTime))
				.uniqueResult();
		
		if(sp != null && dp != null)
		{
			DutySchedule ds = (DutySchedule)session.createCriteria(model.DutySchedule.class)
					.add(Restrictions.eq("dutyPiece.id", dp.getId()))
					.add(Restrictions.eq("student.id", sp.getId()))
					.uniqueResult();
			
			if(ds != null) 
			{
				this.status = "1已经添加过";
			}
			else if(dp.getDutyLeft() == 0)
			{
				this.status = "4此时间段超出最大容纳的人数，添加不成功";
			}
			else 
			{
				ds = new DutySchedule();
				ds.setDutyPiece(dp);
				ds.setStudent(sp);
				dp.dutyLeft --;
				
				session.beginTransaction();
				session.save(ds);
				session.update(dp);
				session.getTransaction().commit();
				
				
				addDutySchedule_addeddutyScheduleId = ds.getId();
				
				this.status = "0添加成功";
			}
			
		}
		else if(sp == null) 
		{
			this.status = "2所添加的学生不合法";
		}
		else
		{
			this.status = "3其他错误";
		}
		
		
		
		session.close();
		return SUCCESS;
	}
	
	
	
	
	
	
 
	int updateDutyNumber_dutyNumber;
	int updateDutyNumber_dutyPieceId;
	public String updateDutyNumber()  throws Exception
	{
		int dutyNumber = updateDutyNumber_dutyNumber;
		int dutyPieceId = updateDutyNumber_dutyPieceId;
		
		
		Session session = model.Util.sessionFactory.openSession();
		DutyPiece dp = (DutyPiece)session.createCriteria(model.DutyPiece.class)
				.add(Restrictions.eq("id", dutyPieceId))
				.uniqueResult();
		
		if(dp != null)
		{
			int cntDuty = dp.getNumberOfDuty() - dp.getDutyLeft();
			if(cntDuty <= dutyNumber)
			{
				dp.setNumberOfDuty(dutyNumber);
				dp.setDutyLeft(dutyNumber - cntDuty);
				session.beginTransaction();
				session.update(dp);
				session.getTransaction().commit();
				this.status = "0更新成功";
			}
			else 
			{
				this.status = "2错误：新的值班容量小于当前已有的值班数";
			}
		
			
		}
		else 
		{
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

	public String getNewDutyPlace() {
		return newDutyPlace;
	}

	public void setNewDutyPlace(String newDutyPlace) {
		this.newDutyPlace = newDutyPlace;
	}

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

	public int getAddDutySchedule_studentId() {
		return addDutySchedule_studentId;
	}

	public void setAddDutySchedule_studentId(int addDutySchedule_studentId) {
		this.addDutySchedule_studentId = addDutySchedule_studentId;
	}

	public int getAddDutySchedule_dutyPieceTime() {
		return addDutySchedule_dutyPieceTime;
	}

	public void setAddDutySchedule_dutyPieceTime(int addDutySchedule_dutyPieceTime) {
		this.addDutySchedule_dutyPieceTime = addDutySchedule_dutyPieceTime;
	}

	public int getAddDutySchedule_addeddutyScheduleId() {
		return addDutySchedule_addeddutyScheduleId;
	}

	public void setAddDutySchedule_addeddutyScheduleId(int addDutySchedule_addeddutyScheduleId) {
		this.addDutySchedule_addeddutyScheduleId = addDutySchedule_addeddutyScheduleId;
	}

	public String getStatus() {
		return status;
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
	
	
	
	
	
	
	

	
	
}
