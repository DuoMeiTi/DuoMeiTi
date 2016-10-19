package model;

import javax.persistence.*;
//import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class DeviceStatusHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;

// 状态修改人
	@ManyToOne
	@JoinColumn
	public model.User user;
	
//	对应的设备
	@ManyToOne
	@JoinColumn
	public model.Repertory device;
	
//	只有当status==教室 ，时，此值才有意义，其他情况此值为空
	@ManyToOne
	@JoinColumn
	public model.Classroom classroom;
	
	// 更改的当前状态
	@Column
	public String status;
	
	
	// 更改日期
	@Column
	public java.sql.Timestamp date;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public model.User getUser() {
		return user;
	}


	public void setUser(model.User user) {
		this.user = user;
	}


	public model.Repertory getDevice() {
		return device;
	}


	public void setDevice(model.Repertory device) {
		this.device = device;
	}


	public model.Classroom getClassroom() {
		return classroom;
	}


	public void setClassroom(model.Classroom classroom) {
		this.classroom = classroom;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public java.sql.Timestamp getDate() {
		return date;
	}


	public void setDate(java.sql.Timestamp date) {
		this.date = date;
	}
	
	
	
	
	
}










