package model;

import java.util.Set;

import javax.persistence.*;

@Entity
public class TeachBuilding {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int build_id;
	
	@Column(length=20, unique= true)
	public String build_name;
	
//	@OneToMany(mappedBy="teachbuilding",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
//	public Set<Classroom> classrooms;
	
	public int getBuild_id() {
		return build_id;
	}

	public void setBuild_id(int build_id) {
		this.build_id = build_id;
	}

	public String getBuild_name() {
		return build_name;
	}

	public void setBuild_name(String build_name) {
		this.build_name = build_name;
	}

//	public Set<Classroom> getClassrooms() {
//		return classrooms;
//	}
//
//	public void setClassrooms(Set<Classroom> classrooms) {
//		this.classrooms = classrooms;
//	}
	
}
