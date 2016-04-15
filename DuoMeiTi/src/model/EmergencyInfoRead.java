package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// 这个表的每条记录表示 一个user 已经阅读了一个info
@Entity
public class EmergencyInfoRead {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;

	@ManyToOne
	@JoinColumn
	public User user;
	
	@ManyToOne
	@JoinColumn
	public EmergencyInfo info;
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EmergencyInfo getInfo() {
		return info;
	}

	public void setInfo(EmergencyInfo info) {
		this.info = info;
	}
	
	
	
	
	
	
	
}
