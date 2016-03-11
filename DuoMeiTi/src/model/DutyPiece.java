package model;

import java.util.Set;

import org.hibernate.cfg.*;

import javax.persistence.*;

import model.TeachBuilding;

@Entity
public class DutyPiece {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	
//	//教学楼
//	@ManyToOne
//    @JoinColumn
//    public TeachBuilding teachBuilding;
	
	
	//值班地点
	@ManyToOne
    @JoinColumn
    public model.DutyPlace dutyPlace;

	
	
	

	//表示time字段可能取值的个数为35个，范围是0-34
	public final static int TimeNumber = 35;
	
	//时间段, 0-6 表示周一到周七，  0-4 表示5个时间段， time%7得到周数，time/7 表示 时间段,
	//总共有35个值，范围是0-34;
	@Column
	public int time;
	
	//值班人数
	@Column
	public int numberOfDuty;
	
	//剩余
	@Column
	public int dutyLeft;
	
	

	
	
	
	
	
	
	
	
	
	
	
	
//	public TeachBuilding getTeachBuilding() {
//		return teachBuilding;
//	}
//
//	public void setTeachBuilding(TeachBuilding teachBuilding) {
//		this.teachBuilding = teachBuilding;
//	}

	public model.DutyPlace getDutyPlace() {
		return dutyPlace;
	}

	public void setDutyPlace(model.DutyPlace dutyPlace) {
		this.dutyPlace = dutyPlace;
	}

	public int getDutyLeft() {
		return dutyLeft;
	}

	public void setDutyLeft(int dutyLeft) {
		this.dutyLeft = dutyLeft;
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
