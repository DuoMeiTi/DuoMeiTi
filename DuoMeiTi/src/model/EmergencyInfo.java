package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


//如果当info 为空时表示这个一条紧急消息， 否者表示针对某个紧急消息的评论
@Entity
public class EmergencyInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	
	@Column
	public String content;	
	
	@ManyToOne
	@JoinColumn
	public User user;
	
	@Column
	public Timestamp date;
	
	@ManyToOne
	@JoinColumn
	public EmergencyInfo info;

	
	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public EmergencyInfo getInfo() {
		return info;
	}

	public void setInfo(EmergencyInfo info) {
		this.info = info;
	}
	
}












