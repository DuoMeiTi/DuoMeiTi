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
public class ExamStuScore {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@ManyToOne
	@JoinColumn
	@Fetch(FetchMode.SELECT)
	public StudentProfile stuPro;
	
	@Column
	public int score;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StudentProfile getStuPro() {
		return stuPro;
	}

	public void setStuPro(StudentProfile stuPro) {
		this.stuPro = stuPro;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
