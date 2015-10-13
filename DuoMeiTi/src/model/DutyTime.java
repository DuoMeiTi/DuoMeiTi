package model;

import java.util.Set;

import org.hibernate.cfg.*;

import javax.persistence.*;

import model.TeachBuilding;

@Entity
public class DutyTime {
	
	//教学楼
	@ManyToOne
    @JoinColumn
    public TeachBuilding teachBuilding;
	
	//时间段
	@JoinColumn
	public int time;
	
	//值班人数
	@JoinColumn
	public int cnt;

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

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
