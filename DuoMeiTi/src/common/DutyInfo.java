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
}
