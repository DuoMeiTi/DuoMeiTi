package admin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.CheckRecord;
import model.Classroom;
import model.Repertory;
import cache.Cache;

import com.opensymphony.xwork2.ActionSupport;

public class ClassroomDetailAction extends ActionSupport {
	
	public int classroomselectIndex;
	
	public List<Repertory> repertorys;
	
	public List<CheckRecord> checkrecords;
	
	public Classroom classroom;
	
	public String execute() {
		System.out.println("detail");
		classroom = Cache.classroom_list.get(classroomselectIndex);
		System.out.println("classroom" + Cache.classroom_list.get(classroomselectIndex).getClassroom_num());
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	public int getClassroomselectIndex() {
		return classroomselectIndex;
	}

	public void setClassroomselectIndex(int classroomselectIndex) {
		this.classroomselectIndex = classroomselectIndex;
	}

	public List<Repertory> getRepertorys() {
		return repertorys;
	}

	public void setRepertorys(List<Repertory> repertorys) {
		this.repertorys = repertorys;
	}

	public List<CheckRecord> getCheckrecords() {
		return checkrecords;
	}

	public void setCheckrecords(List<CheckRecord> checkrecords) {
		this.checkrecords = checkrecords;
	}
	
	
}
