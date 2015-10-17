package model;

import java.util.Set;

import org.hibernate.cfg.*;

import javax.persistence.*;

import model.TeachBuilding;

@Entity
public class DutyTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	
	//教学楼
	@ManyToOne
    @JoinColumn
    public TeachBuilding teachBuilding;
	
	//时间段
	@Column
	public int time;
	
	//值班人数
	@Column
	public int numberOfDuty;
	
	//剩余
	@Column
	public int dutyLeft;
	
	

	public int getDutyLeft() {
		return dutyLeft;
	}

	public void setDutyLeft(int dutyLeft) {
		this.dutyLeft = dutyLeft;
	}

	public TeachBuilding getTeachBuilding() {
		return teachBuilding;
	}

	public void setTeachBuilding(TeachBuilding teachBuilding) {
		this.teachBuilding = teachBuilding;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getNumberOfDuty() {
		return numberOfDuty;
	}

	public void setNumberOfDuty(int numberOfDuty) {
		this.numberOfDuty = numberOfDuty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
