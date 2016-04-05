package model;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Repertory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int rtId;
	
//	设备名称
	@Column(length=50)
	public String rtType;
	
	
//	资产编号
	@Column(length=200)
	public String rtNumber;
	
//	型号
	@Column(length=200)
	public String rtVersion;
	
//	出厂编号
	@Column(length=200)
	public String rtFactorynum;
	
	
	@Column(length=200)
	public String rtDevice;
	
//	设备状态
	@Column(length=10)
	public String rtDeviceStatus;
	
//	出厂日期
	@Column
	public java.sql.Date rtProdDate;	
	
//	审批日期
	@Column
	public java.sql.Date rtApprDate;
	
//	过滤网更换时间长度
	@Column
	public int rtFilterCleanPeriod;
	
//	被替换的时间长度
	@Column
	public int rtReplacePeriod;
	
//	频点
	@Column(length=10)
	public String rtFreqPoint;
	
	
	@ManyToOne
	@JoinColumn
	public Classroom classroom;
	
//	被替换的时间点
	@Column
	public java.sql.Date rtDeadlineData;
	
	
	
	
	
	public java.sql.Date getRtDeadlineData() {
		return rtDeadlineData;
	}

	public void setRtDeadlineData(java.sql.Date rtDeadlineData) {
		this.rtDeadlineData = rtDeadlineData;
	}

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
	
	public String getRtDevice() {
		return rtDevice;
	}

	public void setRtDevice(String rtDevice) {
		this.rtDevice = rtDevice;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	
	public String getRtDeviceStatus() {
		return rtDeviceStatus;
	}

	public void setRtDeviceStatus(String rtDeviceStatus) {
		this.rtDeviceStatus = rtDeviceStatus;
	}
	
	public java.sql.Date getRtProdDate() {
		return rtProdDate;
	}

	public void setRtProdDate(java.sql.Date rtProdDate) {
		this.rtProdDate = rtProdDate;
	}

	public java.sql.Date getRtApprDate() {
		return rtApprDate;
	}

	public void setRtApprDate(java.sql.Date rtApprDate) {
		this.rtApprDate = rtApprDate;
	}
	





	
	
	
	
	public int getRtFilterCleanPeriod() {
		return rtFilterCleanPeriod;
	}

	public void setRtFilterCleanPeriod(int rtFilterCleanPeriod) {
		this.rtFilterCleanPeriod = rtFilterCleanPeriod;
	}

	public int getRtReplacePeriod() {
		return rtReplacePeriod;
	}

	public void setRtReplacePeriod(int rtReplacePeriod) {
		this.rtReplacePeriod = rtReplacePeriod;
	}

	public String getRtFreqPoint() {
		return rtFreqPoint;
	}

	public void setRtFreqPoint(String rtFreqPoint) {
		this.rtFreqPoint = rtFreqPoint;
	}

	public String toString() {
		return this.rtType + "," + this.rtNumber + "|";
	}
	
}
