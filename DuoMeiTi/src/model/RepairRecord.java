package model;

import javax.persistence.*;

@Entity
public class RepairRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn
	public Repertory device;
		
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn
	public StudentProfile repairman;
	
	@Column(length=100)
	public String repairdetail;
	
	public java.sql.Date repairdate;
}
