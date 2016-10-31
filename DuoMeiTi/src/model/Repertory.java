package model;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Repertory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int rtId;
	
//	设备名称(设备类型)
	@Column 
	public String rtType;
	
//	资产编号
	@Column 
	public String rtNumber;	
	
//	型号
	@Column 
	public String rtVersion;

//	出厂编号
	@Column 
	public String rtFactorynum;
	
//	出厂日期
	@Column
	public java.sql.Timestamp rtProdDate;	
	
//	审批日期
	@Column
	public java.sql.Timestamp rtApprDate;

	
	
	
////	应该尽量弃用此字段
//	@Column
//	public String rtDevice;
	
	
	
	
	
//	设备状态
	@Column
	public String rtDeviceStatus;
	

//	仅仅当rtDeviceStatus == 教室时此值才有意义，否则为null	
	@ManyToOne
	@JoinColumn
	public Classroom rtClassroom;


	
//	过滤网更换时间长度, 只有投影仪设备拥有此属性
	@Column
	public int rtFilterCleanPeriod;
//	频点 ， 只有  麦克 设备拥有此属性
	@Column
	public String rtFreqPoint;
	
	
	
//	被替换的时间长度, 只有灯泡需要此属性
	@Column
	public int rtReplacePeriod;
//	被替换的时间点
	@Column
	public java.sql.Timestamp rtDeadlineDate;
	
	
	
	
	
	public int getRtId() {
		return rtId;
	}

	public void setRtId(int rtId) {
		this.rtId = rtId;
	}

	public String getRtType() {
		return rtType;
	}

	public void setRtType(String rtType) {
		this.rtType = rtType;
	}

	public String getRtNumber() {
		return rtNumber;
	}

	public void setRtNumber(String rtNumber) {
		this.rtNumber = rtNumber;
	}

	public String getRtDeviceStatus() {
		return rtDeviceStatus;
	}

	public void setRtDeviceStatus(String rtDeviceStatus) {
		this.rtDeviceStatus = rtDeviceStatus;
	}

	public Classroom getRtClassroom() {
		return rtClassroom;
	}

	public void setRtClassroom(Classroom rtClassroom) {
		this.rtClassroom = rtClassroom;
	}

	public String getRtVersion() {
		return rtVersion;
	}

	public void setRtVersion(String rtVersion) {
		this.rtVersion = rtVersion;
	}

	public String getRtFactorynum() {
		return rtFactorynum;
	}

	public void setRtFactorynum(String rtFactorynum) {
		this.rtFactorynum = rtFactorynum;
	}

//	public String getRtDevice() {
//		return rtDevice;
//	}
//
//	public void setRtDevice(String rtDevice) {
//		this.rtDevice = rtDevice;
//	}

	public java.sql.Timestamp getRtProdDate() {
		return rtProdDate;
	}

	public void setRtProdDate(java.sql.Timestamp rtProdDate) {
		this.rtProdDate = rtProdDate;
	}

	public java.sql.Timestamp getRtApprDate() {
		return rtApprDate;
	}

	public void setRtApprDate(java.sql.Timestamp rtApprDate) {
		this.rtApprDate = rtApprDate;
	}

	public int getRtFilterCleanPeriod() {
		return rtFilterCleanPeriod;
	}

	public void setRtFilterCleanPeriod(int rtFilterCleanPeriod) {
		this.rtFilterCleanPeriod = rtFilterCleanPeriod;
	}

	public String getRtFreqPoint() {
		return rtFreqPoint;
	}

	public void setRtFreqPoint(String rtFreqPoint) {
		this.rtFreqPoint = rtFreqPoint;
	}

	public int getRtReplacePeriod() {
		return rtReplacePeriod;
	}

	public void setRtReplacePeriod(int rtReplacePeriod) {
		this.rtReplacePeriod = rtReplacePeriod;
	}

	public java.sql.Timestamp getRtDeadlineDate() {
		return rtDeadlineDate;
	}

	public void setRtDeadlineDate(java.sql.Timestamp rtDeadlineDate) {
		this.rtDeadlineDate = rtDeadlineDate;
	}

	public String toString() {
		return this.rtType + "," + this.rtNumber + "|";
	}
	
	
}
