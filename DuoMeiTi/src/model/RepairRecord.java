package model;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class RepairRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@ManyToOne
	@JoinColumn
	public Repertory device;
		
	@ManyToOne
	@JoinColumn
	public User repairman;
	
	@Column
	public String repairdetail;
	
	
	
	@Column
	public java.sql.Timestamp repairdate;
	
	
	@ManyToOne
	@JoinColumn
	public model.Classroom classroom = null;
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public model.Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(model.Classroom classroom) {
		this.classroom = classroom;
	}

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

	public Repertory getDevice() {
		return device;
	}

	public void setDevice(Repertory device) {
		this.device = device;
	}

	public User getRepairman() {
		return repairman;
	}

	public void setRepairman(User repairman) {
		this.repairman = repairman;
	}

	public String getRepairdetail() {
		return repairdetail;
	}

	public void setRepairdetail(String repairdetail) {
		this.repairdetail = repairdetail;
	}


	

	@Override
	public String toString() {
		return "RepairRecord [id=" + id + ", device=" + device + ", repairman=" + repairman + ", repairdetail="
				+ repairdetail + ", repairdate=" + repairdate + "]";
	}
	
}
