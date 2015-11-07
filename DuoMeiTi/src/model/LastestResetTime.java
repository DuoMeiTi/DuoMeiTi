package model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class LastestResetTime {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@Column
    public Date entryTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	
}
