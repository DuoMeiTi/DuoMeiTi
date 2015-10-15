package model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Exam {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int exId;
	
	@Column(length=1000)
	public String exTitle;
	
	@Column(length=1000)
	public String exA;
	
	@Column(length=1000)
	public String exB;
	
	@Column(length=1000)
	public String exC;
}
