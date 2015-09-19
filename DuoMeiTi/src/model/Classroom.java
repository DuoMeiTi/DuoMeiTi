package model;


import java.util.Set;

import javax.persistence.*;


@Entity
public class Classroom {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn
	public TeachBuilding teachbuilding;
	
	@Column(length = 10)
	public String classroom_num;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn
	public User principal;
	
//	@Column
//	public int capacity;
		

	@OneToMany(mappedBy="classroom", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Set<Repertory> repertorys;
	
//	@OneToMany(mappedBy="classroom", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
//	public Set<CheckRecord> checkrecords;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TeachBuilding getTeachbuilding() {
		return teachbuilding;
	}

	public void setTeachbuilding(TeachBuilding teachbuilding) {
		this.teachbuilding = teachbuilding;
	}

	public String getClassroom_num() {
		return classroom_num;
	}

	public void setClassroom_num(String classroom_num) {
		this.classroom_num = classroom_num;
	}

	public User getPrincipal() {
		return principal;
	}

	public void setPrincipal(User principal) {
		this.principal = principal;
	}

//	public int getCapacity() {
//		return capacity;
//	}
//
//	public void setCapacity(int capacity) {
//		this.capacity = capacity;
//	}

	public Set<Repertory> getRepertorys() {
		return repertorys;
	}

	public void setRepertorys(Set<Repertory> repertorys) {
		this.repertorys = repertorys;
	}

	/*public Set<CheckRecord> getCheckrecords() {
		return checkrecords;
	}

	public void setCheckrecords(Set<CheckRecord> checkrecords) {
		this.checkrecords = checkrecords;
	}*/
	
	
}
