package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Repertory {
	private int rtId;
	private String rtType;
	private String rtNumber;
	//private String rtVersion;
	//private Datetime
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getRtId() {
		return rtId;
	}
	public void setRtId(int rtId) {
		this.rtId = rtId;
	}
	@Column(length=50)
	public String getRtType() {
		return rtType;
	}
	public void setRtType(String rtType) {
		this.rtType = rtType;
	}
	@Column(length=200)
	public String getRtNumber() {
		return rtNumber;
	}
	public void setRtNumber(String rtNumber) {
		this.rtNumber = rtNumber;
	}
	
	public String toString() {
		return this.rtType + "," + this.rtNumber;
	}
	
}
