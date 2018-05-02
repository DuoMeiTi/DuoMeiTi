package homepage;

import java.sql.Date;
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

public class ClassroomDetailAction extends FileUploadBaseAction {

	public int classroomId;
	public Classroom classroom;
	public List<CheckRecord> checkrecords;
	public List<RepairRecord> repairrecords;
	public List<RoomPicture> picture_list;
	public List<Repertory> classroom_device_list;
	public String class_schedule_path;

	public String execute() {
		Session session = model.Util.sessionFactory.openSession();

		// query current select classroom
		classroom = common.ClassroomInformationAction.obtainClassroom(session, classroomId);

		// 课程表
		class_schedule_path = classroom.class_schedule_path;

		classroom_device_list = common.ClassroomInformationAction
				.obtainClassroomDeviceListCriteria(session, classroomId).list();

		checkrecords = common.ClassroomInformationAction.obtainCheckRecordListCriteria(session, classroomId).list();
		repairrecords = common.ClassroomInformationAction.obtainRepairRecordListCriteria(session, classroom).list();
		picture_list = session.createCriteria(RoomPicture.class).add(Restrictions.eq("class_id", classroomId)).list();
		session.close();

		return ActionSupport.SUCCESS;
	}

	public int getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
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

	public List<RoomPicture> getPicture_list() {
		return picture_list;
	}

	public void setPicture_list(List<RoomPicture> picture_list) {
		this.picture_list = picture_list;
	}

	public List getClassroom_device_list() {
		return classroom_device_list;
	}

	public void setClassroom_device_list(List classroom_device_list) {
		this.classroom_device_list = classroom_device_list;
	}

	public String getClass_schedule_path() {
		return class_schedule_path;
	}

	public void setClass_schedule_path(String class_schedule_path) {
		this.class_schedule_path = class_schedule_path;
	}

}
