package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoomPicture {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;

	@Column
	public String unitInfo;

	@Column
	public String remark;
    
    @Column
    public String path;
	
    @Column
    public int class_id;
	

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String toString()
	{
		return this.remark + ", " + this.path ;
	}
	
	
	
	
}
