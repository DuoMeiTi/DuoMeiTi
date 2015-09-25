package model;

import java.util.Set;

import org.hibernate.cfg.*;

import javax.persistence.*;

@Entity
public class AdminProfile {

	@OneToOne
	@JoinColumn
	public User user;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;

	@Column(length = 50)
	public String unitInfo;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(String unitInfo) {
		this.unitInfo = unitInfo;
	}
}
