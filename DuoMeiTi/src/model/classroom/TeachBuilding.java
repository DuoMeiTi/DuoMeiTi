package model.classroom;

import javax.persistence.*;

@Entity
@Table(name="tbl_teachbuilding")
public class TeachBuilding {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int build_id;
	
	@Column(length=20)
	private String build_name;
	

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
	
}
