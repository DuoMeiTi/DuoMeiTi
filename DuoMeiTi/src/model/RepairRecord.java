package model;

import javax.persistence.*;

public class RepairRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="device")
	public Repertory device;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="classroom")
	public Classroom classroom;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="repairman")
	public StudentProfile repairman;
	
	@Column(length=100)
	public String repairdetail;
	
	public java.sql.Date repairdate;
}
