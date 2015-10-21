package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class ExamOption {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int emId;
	
	@Column(length = 500)
	public String emOption;
	
	@ManyToOne
	@JoinColumn
	@Fetch(FetchMode.SELECT)
	public ExamTitle emTitle;

	public int getEmId() {
		return emId;
	}

	public void setEmId(int emId) {
		this.emId = emId;
	}

	public String getEmOption() {
		return emOption;
	}

	public void setEmOption(String emOption) {
		this.emOption = emOption;
	}

	public ExamTitle getEmTitle() {
		return emTitle;
	}

	public void setEmTitle(ExamTitle emTitle) {
		this.emTitle = emTitle;
	}
	
	
}
