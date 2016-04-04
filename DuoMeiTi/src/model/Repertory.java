package model;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Repertory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int rtId;
	
	@Column(length=50)
	public String rtType;
	
	@Column(length=200)
	public String rtNumber;
	
	@Column(length=200)
	public String rtVersion;
	
	@Column(length=200)
	public String rtFactorynum;
	
	@Column(length=200)
	public String rtDevice;
	
	@Column(length=10)
	public String rtDeviceStatus;
	
	@Column
	public java.sql.Date rtProdDate;	
	
	@Column
	public java.sql.Date rtApprDate;
	
	@Column
	public int rtFilterCleanPeriod;
	
	@Column
	public int rtReplacePeriod;
	
	@Column(length=10)
	public String rtFreqPoint;
	
	
	@ManyToOne
	@JoinColumn
	public Classroom classroom;
	
	
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
