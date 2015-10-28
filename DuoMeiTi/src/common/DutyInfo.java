package common;

public class DutyInfo {
	public int studentId;
	public String studentName;
	public int time;
	public DutyInfo(){}
	public DutyInfo(Integer id,String name, Integer time){
		this.studentId=id.intValue();
		this.studentName=name;
		this.time=time.intValue();
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	
}
