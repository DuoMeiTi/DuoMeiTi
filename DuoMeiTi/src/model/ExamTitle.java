package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExamTitle {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int emId;
	
	@Column(length = 500)
	public String emTitle;
	

	public int getEmId() {
		return emId;
	}

	public void setEmId(int emId) {
		this.emId = emId;
	}

	public String getEmTitle() {
		return emTitle;
	}

	public void setEmTitle(String emTitle) {
		this.emTitle = emTitle;
	}
	
	
}
