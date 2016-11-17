package model;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class RepairRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
//	@ManyToOne
//	@JoinColumn
//	public Repertory device;
	
	// 此维修记录相关的设备信息：
	// 设备类型
	@Column 
	public String deviceType;
	// 设备资产编号	
	@Column
	public String deviceNumber;
	
	// 设备型号
	@Column
	public String deviceVersion;
	
	//	出厂编号
	@Column 
	public String deviceFactorynum;
	
	//	出厂日期
	@Column
	public java.sql.Timestamp deviceProdDate;
	
	//	审批日期
	@Column
	public java.sql.Timestamp deviceApprDate;

	
	
	
	
	
	
	// 填写人学生信息，可以包括多个填写人 使用逗号,分割
	// 学生的真实姓名列表
	@Column
	public String repairmanFullName;
	// 学生的电话号码列表
	@Column
	public String repairmanPhoneNumber;
	// 学生的学号列表
	@Column
	public String repairmanStudentId;
	
	
	
	
	
	
	@Column
	public String repairdetail;
	
	
	
	@Column
	public java.sql.Timestamp repairdate;
	
	
//	@ManyToOne
//	@JoinColumn
//	public model.Classroom classroom = null;
	
	@Column
	public String classroomName;
	
	@Column
	public String teachingBuildingName; 
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public model.Classroom getClassroom() {
//		return classroom;
//	}
//
//	public void setClassroom(model.Classroom classroom) {
//		this.classroom = classroom;
//	}

	public java.sql.Timestamp getRepairdate() {
		return repairdate;
	}

	public void setRepairdate(java.sql.Timestamp repairdate) {
		this.repairdate = repairdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public Repertory getDevice() {
//		return device;
//	}
//
//	public void setDevice(Repertory device) {
//		this.device = device;
//	}

//	public User getRepairman() {
//		return repairman;
//	}
//
//	public void setRepairman(User repairman) {
//		this.repairman = repairman;
//	}

	public String getRepairdetail() {
		return repairdetail;
	}

	public void setRepairdetail(String repairdetail) {
		this.repairdetail = repairdetail;
	}

	public String getRepairmanFullName() {
		return repairmanFullName;
	}

	public void setRepairmanFullName(String repairmanFullName) {
		this.repairmanFullName = repairmanFullName;
	}

	public String getRepairmanPhoneNumber() {
		return repairmanPhoneNumber;
	}

	public void setRepairmanPhoneNumber(String repairmanPhoneNumber) {
		this.repairmanPhoneNumber = repairmanPhoneNumber;
	}

	public String getClassroomName() {
		return classroomName;
	}

	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}

	public String getTeachingBuildingName() {
		return teachingBuildingName;
	}

	public void setTeachingBuildingName(String teachingBuildingName) {
		this.teachingBuildingName = teachingBuildingName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getDeviceFactorynum() {
		return deviceFactorynum;
	}

	public void setDeviceFactorynum(String deviceFactorynum) {
		this.deviceFactorynum = deviceFactorynum;
	}

	public java.sql.Timestamp getDeviceProdDate() {
		return deviceProdDate;
	}

	public void setDeviceProdDate(java.sql.Timestamp deviceProdDate) {
		this.deviceProdDate = deviceProdDate;
	}

	public java.sql.Timestamp getDeviceApprDate() {
		return deviceApprDate;
	}

	public void setDeviceApprDate(java.sql.Timestamp deviceApprDate) {
		this.deviceApprDate = deviceApprDate;
	}

	public String getRepairmanStudentId() {
		return repairmanStudentId;
	}

	public void setRepairmanStudentId(String repairmanStudentId) {
		this.repairmanStudentId = repairmanStudentId;
	}
	
	

	

//	@Override
//	public String toString() {
//		return "RepairRecord [id=" + id + ", device=" + device + ", repairman=" + repairman + ", repairdetail="
//				+ repairdetail + ", repairdate=" + repairdate + "]";
//	}
	
}
