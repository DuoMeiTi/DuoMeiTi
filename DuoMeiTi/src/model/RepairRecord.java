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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Repertory getDevice() {
		return device;
	}

	public void setDevice(Repertory device) {
		this.device = device;
	}

	public StudentProfile getRepairman() {
		return repairman;
	}

	public void setRepairman(StudentProfile repairman) {
		this.repairman = repairman;
	}

	public String getRepairdetail() {
		return repairdetail;
	}

	public void setRepairdetail(String repairdetail) {
		this.repairdetail = repairdetail;
	}

	public java.sql.Date getRepairdate() {
		return repairdate;
	}

	public void setRepairdate(java.sql.Date repairdate) {
		this.repairdate = repairdate;
	}
}
