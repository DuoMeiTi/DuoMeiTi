package student;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

//import javax.websocket.Session;
import org.hibernate.Criteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Classroom;
import model.RepairRecord;
import model.Repertory;
import model.User;

public class RepairRecordManageAction extends ActionSupport{
	
	
	
	
	
	int execute_selectTeachBuilding = -1; //in
	int execute_selectClassroom = -1;//in
	List<model.TeachBuilding> execute_teachBuildingList; //out
	List<model.Classroom> execute_classroomList;//out
	
	List<Repertory> execute_devices;//out
	List<RepairRecord> execute_repairRecords;//out

	public String execute() throws Exception
	{
		Session s = model.Util.sessionFactory.openSession();
		execute_teachBuildingList = s.createCriteria(model.TeachBuilding.class).list();
		execute_classroomList = 
				util.Util.getCriteriaWithOneEqualRestriction(s, model.Classroom.class, "teachbuilding.id", execute_selectTeachBuilding)
				.addOrder(Order.asc("classroom_num")).list();
		
		if(execute_selectClassroom != -1)
		{
			Classroom classroom = 
					util.Util.getUniqueResultWithOneEqualRestriction(s, model.Classroom.class, "id", execute_selectClassroom);
			
			execute_devices = 
					util.Util.getListWithOneEqualRestriction(s, model.Repertory.class, "rtClassroom.id", execute_selectClassroom);
			
			Criteria c =  util.Util.getCriteriaWithOneEqualRestriction(s, RepairRecord.class, "classroomName", classroom.classroom_num);			
			c = util.Util.addOneEqualRestriction(c, "teachingBuildingName", classroom.teachbuilding.build_name);		
			c = c.addOrder(Order.desc("id"));
			execute_repairRecords = c.list();

		}
		
//				util.Util.getListWithOneEqualRestriction(s, model.Classroom.class, "teachbuilding.id", execute_selectTeachBuilding);
		
//		obtain();
		s.close();
		return SUCCESS;
	}
	
	
	
//	int obtain_classroomId = 250;//in
//	List<Repertory> obtain_devices;//out
//	List<RepairRecord> obtain_repairRecords;//out
//	String obtain_devicesAndrepairRecordsJsp;//out
//	//获取对应教室的设备列表和维修记录	
//	public String obtain()
//	{
//		
//		Session s = model.Util.sessionFactory.openSession();		
//		
//		
//		Classroom classroom = 
//				util.Util.getUniqueResultWithOneEqualRestriction(s, model.Classroom.class, "id", obtain_classroomId);
//		
//		obtain_devices = 
//				util.Util.getListWithOneEqualRestriction(s, model.Repertory.class, "rtClassroom.id", obtain_classroomId);
//		
//		
//		System.out.println(obtain_devices.size() + "-----------------------");
//		Criteria c =  util.Util.getCriteriaWithOneEqualRestriction(s, RepairRecord.class, "classroomName", classroom.classroom_num);			
//		c = util.Util.addOneEqualRestriction(c, "teachingBuildingName", classroom.teachbuilding.build_name);		
//		c = c.addOrder(Order.desc("id"));
//		obtain_repairRecords = c.list();
//		
//		obtain_devicesAndrepairRecordsJsp = util.Util.getJspOutput("/jsp/student/widgets/devicesAndRepairRecordsTable.jsp");
//		s.close();
//		return SUCCESS;
//	}
//	
	
	int save_classroomId;//in	
	int save_deviceId;//in	
	String save_detail; //in
	String save_devicesAndrepairRecordsJsp;//out
	//保存维修记录
	public String save() {
		System.out.println("student.repairrecord:");
		Session s = null;
		try {
			int user_id = (int) ActionContext.getContext().getSession().get("user_id");
			s = model.Util.sessionFactory.openSession();

			User repairman = util.Util.getUniqueResultWithOneEqualRestriction(s, User.class,"id", user_id);
			Repertory device = util.Util.getUniqueResultWithOneEqualRestriction(s, Repertory.class, "rtId", save_deviceId);
			Classroom classroom = util.Util.getUniqueResultWithOneEqualRestriction(s, Classroom.class, "id", save_classroomId);
			RepairRecord repairRecord = new RepairRecord();
			util.Util.setRepairRecord(repairRecord, repairman, classroom, device, save_detail);



			s.beginTransaction();
			s.save(repairRecord);
			s.getTransaction().commit();
			s.close();
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
//		obtain_classroomId = save_classroomId;
//		obtain();
//		save_devicesAndrepairRecordsJsp = obtain_devicesAndrepairRecordsJsp;
		return SUCCESS;
	}

	
	
	
	public int getExecute_selectTeachBuilding() {
		return execute_selectTeachBuilding;
	}
	public void setExecute_selectTeachBuilding(int execute_selectTeachBuilding) {
		this.execute_selectTeachBuilding = execute_selectTeachBuilding;
	}
	public List<model.TeachBuilding> getExecute_teachBuildingList() {
		return execute_teachBuildingList;
	}
	public void setExecute_teachBuildingList(List<model.TeachBuilding> execute_teachBuildingList) {
		this.execute_teachBuildingList = execute_teachBuildingList;
	}
	public List<model.Classroom> getExecute_classroomList() {
		return execute_classroomList;
	}
	public void setExecute_classroomList(List<model.Classroom> execute_classroomList) {
		this.execute_classroomList = execute_classroomList;
	}





//	public int getObtain_classroomId() {
//		return obtain_classroomId;
//	}
//
//
//
//
//
//	public void setObtain_classroomId(int obtain_classroomId) {
//		this.obtain_classroomId = obtain_classroomId;
//	}
//
//
//
//
//
//	public List<Repertory> getObtain_devices() {
//		return obtain_devices;
//	}
//
//
//
//
//
//	public void setObtain_devices(List<Repertory> obtain_devices) {
//		this.obtain_devices = obtain_devices;
//	}
//
//
//
//
//
//	public List<RepairRecord> getObtain_repairRecords() {
//		return obtain_repairRecords;
//	}
//
//
//
//
//
//	public void setObtain_repairRecords(List<RepairRecord> obtain_repairRecords) {
//		this.obtain_repairRecords = obtain_repairRecords;
//	}
//
//
//
//
//
//	public String getObtain_devicesAndrepairRecordsJsp() {
//		return obtain_devicesAndrepairRecordsJsp;
//	}
//
//
//
//
//
//	public void setObtain_devicesAndrepairRecordsJsp(String obtain_devicesAndrepairRecordsJsp) {
//		this.obtain_devicesAndrepairRecordsJsp = obtain_devicesAndrepairRecordsJsp;
//	}
//



	public int getSave_classroomId() {
		return save_classroomId;
	}




	public void setSave_classroomId(int save_classroomId) {
		this.save_classroomId = save_classroomId;
	}




	public int getSave_deviceId() {
		return save_deviceId;
	}




	public void setSave_deviceId(int save_deviceId) {
		this.save_deviceId = save_deviceId;
	}




	public String getSave_detail() {
		return save_detail;
	}




	public void setSave_detail(String save_detail) {
		this.save_detail = save_detail;
	}




	public String getSave_devicesAndrepairRecordsJsp() {
		return save_devicesAndrepairRecordsJsp;
	}




	public void setSave_devicesAndrepairRecordsJsp(String save_devicesAndrepairRecordsJsp) {
		this.save_devicesAndrepairRecordsJsp = save_devicesAndrepairRecordsJsp;
	}




	public int getExecute_selectClassroom() {
		return execute_selectClassroom;
	}




	public void setExecute_selectClassroom(int execute_selectClassroom) {
		this.execute_selectClassroom = execute_selectClassroom;
	}




	public List<Repertory> getExecute_devices() {
		return execute_devices;
	}




	public void setExecute_devices(List<Repertory> execute_devices) {
		this.execute_devices = execute_devices;
	}




	public List<RepairRecord> getExecute_repairRecords() {
		return execute_repairRecords;
	}




	public void setExecute_repairRecords(List<RepairRecord> execute_repairRecords) {
		this.execute_repairRecords = execute_repairRecords;
	}
	
	
	
}
