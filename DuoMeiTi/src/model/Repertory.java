package model;

import javax.persistence.*;

@Entity
public class Repertory {
	private int rtId;
	private String rtType;
	private String rtNumber;
	//private String rtVersion;
	//private Datetime
	
	public Classroom classroom;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	
<<<<<<< HEAD
	@ManyToOne
    @JoinColumn
    public Classroom getClassroom() {
=======
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn
	public Classroom getClassroom() {
>>>>>>> origin/master
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	public String toString() {
		return this.rtType + "," + this.rtNumber;
	}
	
}
