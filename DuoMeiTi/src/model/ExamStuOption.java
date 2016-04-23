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
public class ExamStuOption {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int esId;
	
	@ManyToOne
	@JoinColumn
	@Fetch(FetchMode.SELECT)
	public StudentProfile stuPro;
	
	@ManyToOne
	@JoinColumn
	@Fetch(FetchMode.SELECT)
	public ExamOption emoption;
	
	
	// 弃用！
	@Column
	int esNums;

	public int getEsId() {
		return esId;
	}

	public void setEsId(int esId) {
		this.esId = esId;
	}

	public StudentProfile getStuPro() {
		return stuPro;
	}

	public void setStuPro(StudentProfile stuPro) {
		this.stuPro = stuPro;
	}

	public ExamOption getEmoption() {
		return emoption;
	}

	public void setEmoption(ExamOption emoption) {
		this.emoption = emoption;
	}

	public int getEsNums() {
		return esNums;
	}

	public void setEsNums(int esNums) {
		this.esNums = esNums;
	}
	
	
}
