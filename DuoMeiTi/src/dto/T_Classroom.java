package dto;

public class T_Classroom {
	public int id;
	
	public String classroom_num;
	
//	public int capacity;
	
	public String principal_name;
	
	public String principal_stuId;
	
	public int teachbuilding_id;
	
	public int getTeachbuilding_id(){
		return teachbuilding_id;
	}
	public void setTeachbuilding_id(int teachbuilding_id)
	{
		this.teachbuilding_id=teachbuilding_id;
	}
	
	public String getPrincipal_name() {
		return principal_name;
	}

	public void setPrincipal_name(String principal_name) {
		this.principal_name = principal_name;
	}

	public String getPrincipal_stuId() {
		return principal_stuId;
	}

	public void setPrincipal_stuId(String principal_stuId) {
		this.principal_stuId = principal_stuId;
	}

	public String repertorys;

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

//	public int getCapacity() {
//		return capacity;
//	}
//
//	public void setCapacity(int capacity) {
//		this.capacity = capacity;
//	}

	

	public String getRepertorys() {
		return repertorys;
	}

	public void setRepertorys(String repertorys) {
		this.repertorys = repertorys;
	}
	
	
}
