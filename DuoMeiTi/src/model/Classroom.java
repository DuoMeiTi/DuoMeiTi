package model;


import javax.persistence.*;


@Entity
public class Classroom {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@Column(length = 3)
	public String classroom_num;
	
	@Column
	public String principal;
	
	@Column
	public String size;
	
	@Column
	public String equipment;
	
	@Column
	public String manage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassroom_num() {
		return classroom_num;
	}

	public void setClassroom_num(String classroom_num) {
		this.classroom_num = classroom_num;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getManage() {
		return manage;
	}

	public void setManage(String manage) {
		this.manage = manage;
	}
	

}
