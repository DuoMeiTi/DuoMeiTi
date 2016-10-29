package common;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import model.AdminProfile;
import model.CheckRecord;
import model.Classroom;
import model.RepairRecord;
import model.Repertory;
import model.RoomPicture;
import model.TeachBuilding;
import model.User;
import util.FileUploadBaseAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClassroomInformationAction extends FileUploadBaseAction {
	
	
	static
	{
//		Session session = model.Util.sessionFactory.openSession();
//
//		List<CheckRecord> checkRecordList = session.createCriteria(model.CheckRecord.class).list();
//		
//		for(CheckRecord cr : checkRecordList)
//		{
//			if(cr.getCheckman() != null)
//			{
//				cr.setCheckmanFullName(cr.getCheckman().getFullName());
//				cr.setCheckmanPhoneNumber(cr.getCheckman().getPhoneNumber());
//				
//				cr.setCheckman(null);
//			}
//			
//			if(cr.getClassroom() != null)
//			{
//				
//			}
//		}
//			   
//		
//		
//		
//		session.close();
	}
	
	
	
	
	
	

	public String remark;
	public int classroomId;

	public Classroom classroom;
	public List<CheckRecord> checkrecords;
	public List<RepairRecord> repairrecords;
	public List<Repertory> repertory_list;

	public String repairrecord_jsp;
	public String checkdetail;
	public String checkrecord_jsp;

	public String savestatus;
	public int deviceId;
	public String rtID;
	public String repairdetail;
	public String move_device_id;
	public String device_jsp;
	public String alterdevice_jsp;

	public List<Repertory> rtClass;

	/*
	 * 获取页面所需要的所有信息 0是classroom 1是rtClass 此教室设备列表 2是checkrecords 此教室检查记录列表
	 * 3是repairrecords 此教室维修记录列表
	 * 
	 */
	//
	public static Classroom obtainClassroom(Session s, int classroomId) {
		return (Classroom) s.createCriteria(Classroom.class).add(Restrictions.eq("id", classroomId)).uniqueResult();
	}

	public static Criteria obtainClassroomDeviceListCriteria(Session s, int classroomId) {
		return s.createCriteria(model.Repertory.class).add(Restrictions.eq("rtClassroom.id", classroomId));
	}

	public static Criteria obtainCheckRecordListCriteria(Session s, int classroomId) {
		return s.createCriteria(model.CheckRecord.class).add(Restrictions.eq("classroom.id", classroomId))
				.addOrder(Order.desc("id"));
	}

	public static Criteria obtainRepairRecordListCriteria(Session s, Classroom classroom) {
		return s.createCriteria(model.RepairRecord.class)
				.add(Restrictions.eq("teachingBuildingName", classroom.getTeachbuilding().getBuild_name()))
				.add(Restrictions.eq("classroomName", classroom.getClassroom_num())).addOrder(Order.desc("id"));
	}

	public static Object[] obtainAllInfo(Session session, int classroomId) {
		Object[] ans = new Object[4];

		// query current select classroom
		Classroom classroom = obtainClassroom(session, classroomId);

		// all devices in this classroom
		List rtClass = obtainClassroomDeviceListCriteria(session, classroomId).list();

		// query at most 5 checkrecord
		List checkrecords = obtainCheckRecordListCriteria(session, classroomId).setMaxResults(5).list();
		// checkrecord_criteria.list();

		// query at most 5 repairrecords
		List repairrecords = obtainRepairRecordListCriteria(session, classroom).setMaxResults(5).list();

		ans[0] = classroom;
		ans[1] = rtClass;
		ans[2] = checkrecords;
		ans[3] = repairrecords;
		return ans;
	}

	// 检查记录删除
	public int checkRecordId;

	public String deleteCheckRecord() {
		System.out.println("deleteCheckRecord:" + checkRecordId);
		try {
			Session s = model.Util.sessionFactory.openSession();
			CheckRecord cr = (CheckRecord) s.createCriteria(model.CheckRecord.class)
					.add(Restrictions.eq("id", checkRecordId)).uniqueResult();

			s.beginTransaction();
			s.delete(cr);
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();

		}

		return ActionSupport.SUCCESS;
	}

	// 备用设备
	public String alterdevice() {
		System.out.println("ENTER 备用设备gggggggggggggggg---------");
		Session session = model.Util.sessionFactory.openSession();
		repertory_list = session.createCriteria(model.Repertory.class)
				.add(Restrictions.eq("rtDeviceStatus", util.Util.DeviceBackupStatus)).list();

		alterdevice_jsp = util.Util.getJspOutput("/jsp/classroom/alterdevice.jsp");
		session.close();
		return ActionSupport.SUCCESS;
	}

	// 按照资产编号加入教室
	String rtNumber;

	public String move2classByRtNumber() {
		System.out.println("JJJJJ=========");

		int user_id = (int) ActionContext.getContext().getSession().get("user_id");
		Session s = model.Util.sessionFactory.openSession();

		List<Repertory> repertoryList = s.createCriteria(model.Repertory.class)
				.add(Restrictions.eq("rtNumber", rtNumber)).list();
		for (int i = 0; i < repertoryList.size(); ++i) {

			util.Util.modifyDeviceStatus(s, repertoryList.get(i).rtId, user_id, util.Util.DeviceClassroomStatus,
					classroomId);

		}

		return this.SUCCESS;
	}

	// 加入教室
	public String move2class() {

		int user_id = (int) ActionContext.getContext().getSession().get("user_id");

		Session session = model.Util.sessionFactory.openSession();
		util.Util.modifyDeviceStatus(session, Integer.parseInt(rtID), user_id, util.Util.DeviceClassroomStatus,
				classroomId);

		rtClass = session.createCriteria(model.Repertory.class).add(Restrictions.eq("rtClassroom.id", classroomId))
				.list();

		repertory_list = session.createCriteria(model.Repertory.class)
				.add(Restrictions.eq("rtDeviceStatus", util.Util.DeviceBackupStatus)).list();

		device_jsp = util.Util.getJspOutput("/jsp/classroom/device.jsp");
		alterdevice_jsp = util.Util.getJspOutput("/jsp/classroom/alterdevice.jsp");

		return ActionSupport.SUCCESS;
	}

	// 移入维修
	public String move2repair() {

		System.out.println("移入维修！！！！！！！！！");
		System.out.println(classroomId);
		int user_id = (int) ActionContext.getContext().getSession().get("user_id");
		Session session = model.Util.sessionFactory.openSession();
		util.Util.modifyDeviceStatus(session, Integer.parseInt(move_device_id),
				// classroomId,
				user_id, util.Util.DeviceRepairStatus, -1);

		rtClass = session.createCriteria(model.Repertory.class).add(Restrictions.eq("rtClassroom.id", classroomId))
				.list();

		device_jsp = util.Util.getJspOutput("/jsp/classroom/device.jsp");

		return ActionSupport.SUCCESS;
	}

	// 移入报废
	public String move2bad() {

		int user_id = (int) ActionContext.getContext().getSession().get("user_id");
		Session session = model.Util.sessionFactory.openSession();
		util.Util.modifyDeviceStatus(session, Integer.parseInt(move_device_id), user_id, util.Util.DeviceScrappedStatus,
				-1);

		rtClass = session.createCriteria(model.Repertory.class).add(Restrictions.eq("rtClassroom.id", classroomId))
				.list();
		device_jsp = util.Util.getJspOutput("/jsp/classroom/device.jsp");

		return ActionSupport.SUCCESS;
	}

	// 维修记录
	public String repairrecord_save() {
		System.out.println("admin.repairrecord:");
		Session session = null;
		try {
			int user_id = (int) ActionContext.getContext().getSession().get("user_id");
			session = model.Util.sessionFactory.openSession();

			User repairman = (User) session.createCriteria(User.class).add(Restrictions.eq("id", user_id))
					.uniqueResult();

			Repertory device = (Repertory) session.createCriteria(Repertory.class)
					.add(Restrictions.eq("rtId", deviceId)).uniqueResult();

			RepairRecord repairrecord = new RepairRecord();

			// repairrecord.setDevice(device);
//			repairrecord.setDeviceType(device.getRtType());
//			repairrecord.setDeviceNumber(device.getRtNumber());
//			repairrecord.setDeviceVersion(device.getRtVersion());
//			repairrecord.setDeviceFactorynum(device.getRtFactorynum());
//			repairrecord.setDeviceProdDate(device.getRtProdDate());
//			repairrecord.setDeviceApprDate(device.getRtApprDate());
//
//			repairrecord.setRepairdate(new Timestamp(new java.util.Date().getTime()));
//			repairrecord.setRepairdetail(repairdetail);
//
//			repairrecord.setRepairmanFullName(repairman.getFullName());
//			repairrecord.setRepairmanPhoneNumber(repairman.getPhoneNumber());
//
			Classroom classroom = (Classroom) session.createCriteria(model.Classroom.class)
					.add(Restrictions.eq("id", classroomId)).uniqueResult();
//
//			repairrecord.setClassroomName(classroom.getClassroom_num());
//			repairrecord.setTeachingBuildingName(classroom.getTeachbuilding().getBuild_name());

			
			util.Util.setRepairRecord(repairrecord, repairman, classroom, device, repairdetail);
			
			
			
			
			session.beginTransaction();
			session.save(repairrecord);
			session.getTransaction().commit();

			repairrecords = session.createCriteria(model.RepairRecord.class)
					.add(Restrictions.eq("teachingBuildingName", classroom.getTeachbuilding().getBuild_name()))
					.add(Restrictions.eq("classroomName", classroom.getClassroom_num())).addOrder(Order.desc("id"))
					.setMaxResults(5).list();

			repairrecord_jsp = util.Util.getJspOutput("/jsp/classroom/repairrecord.jsp");

			this.savestatus = "success";
		} catch (Exception e) {
			this.savestatus = "fail";
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return SUCCESS;
	}

	// 检查记录
	public String checkrecord_save() {
		System.out.println("admin.checkrecord:");
		Session session = null;
		try {
			int user_id = (int) ActionContext.getContext().getSession().get("user_id");
			session = model.Util.sessionFactory.openSession();

			User user = (User) session.createCriteria(User.class).add(Restrictions.eq("id", user_id)).uniqueResult();

			Classroom classroom = (Classroom) session.createCriteria(Classroom.class)
					.add(Restrictions.eq("id", classroomId)).uniqueResult();

			CheckRecord checkrecord = new CheckRecord();
			checkrecord.setCheckdate(new Timestamp(new java.util.Date().getTime()));
			checkrecord.setCheckdetail(checkdetail);
			checkrecord.setCheckman(user);
			checkrecord.setClassroom(classroom);

			session.beginTransaction();
			session.save(checkrecord);
			session.getTransaction().commit();

			checkrecords = session.createCriteria(CheckRecord.class).add(Restrictions.eq("classroom.id", classroomId))
					.addOrder(Order.desc("id")).setMaxResults(5).list();

			checkrecord_jsp = util.Util.getJspOutput("/jsp/classroom/checkrecord.jsp");

			this.savestatus = "success";
		} catch (Exception e) {
			this.savestatus = "fail";
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return SUCCESS;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<CheckRecord> getCheckrecords() {
		return checkrecords;
	}

	public void setCheckrecords(List<CheckRecord> checkrecords) {
		this.checkrecords = checkrecords;
	}

	public List<RepairRecord> getRepairrecords() {
		return repairrecords;
	}

	public void setRepairrecords(List<RepairRecord> repairrecords) {
		this.repairrecords = repairrecords;
	}

	public String getCheckdetail() {
		return checkdetail;
	}

	public void setCheckdetail(String checkdetail) {
		this.checkdetail = checkdetail;
	}

	public String getCheckrecord_jsp() {
		return checkrecord_jsp;
	}

	public void setCheckrecord_jsp(String checkrecord_jsp) {
		this.checkrecord_jsp = checkrecord_jsp;
	}

	public String getSavestatus() {
		return savestatus;
	}

	public void setSavestatus(String savestatus) {
		this.savestatus = savestatus;
	}

	public List<Repertory> getRtClass() {
		return rtClass;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getRepairdetail() {
		return repairdetail;
	}

	public void setRepairdetail(String repairdetail) {
		this.repairdetail = repairdetail;
	}

	public void setRtClass(List<Repertory> rtClass) {
		this.rtClass = rtClass;
	}

	public int getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}

	public String getRepairrecord_jsp() {
		return repairrecord_jsp;
	}

	public void setRepairrecord_jsp(String repairrecord_jsp) {
		this.repairrecord_jsp = repairrecord_jsp;
	}

	public String getMove_device_id() {
		return move_device_id;
	}

	public void setMove_device_id(String move_device_id) {
		this.move_device_id = move_device_id;
	}

	public String getDevice_jsp() {
		return device_jsp;
	}

	public void setDevice_jsp(String device_jsp) {
		this.device_jsp = device_jsp;
	}

	public String getAlterdevice_jsp() {
		return alterdevice_jsp;
	}

	public void setAlterdevice_jsp(String alterdevice_jsp) {
		this.alterdevice_jsp = alterdevice_jsp;
	}

	public List<Repertory> getRepertory_list() {
		return repertory_list;
	}

	public void setRepertory_list(List<Repertory> repertory_list) {
		this.repertory_list = repertory_list;
	}

	public String getRtID() {
		return rtID;
	}

	public void setRtID(String rtID) {
		this.rtID = rtID;
	}

	public String getRtNumber() {
		return rtNumber;
	}

	public void setRtNumber(String rtNumber) {
		this.rtNumber = rtNumber;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public int getCheckRecordId() {
		return checkRecordId;
	}

	public void setCheckRecordId(int checkRecordId) {
		this.checkRecordId = checkRecordId;
	}

}
