package admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Classroom;
import model.RoomPicture;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import util.FileUploadBaseAction;

public class BatchImportAction extends FileUploadBaseAction {
	String status;
	String message;
	List<Classroom> classroom_list;
	int selectTeachBuilding;
	String classroomcheckbox;
	List classrooms;
//	String[] classroomsArr;
	
	public String execute() throws Exception
	{
		System.out.println("|||||"+util.Util.RootPath);
		return SUCCESS;
	}
	
	public String changeBuilding() throws Exception {	
		Session session = model.Util.sessionFactory.openSession();
		Criteria classroom_criteria = session.createCriteria(Classroom.class);	
		if(selectTeachBuilding != -1){
			classroom_criteria.add(Restrictions.eq("teachbuilding.id", selectTeachBuilding));
			classroom_criteria.addOrder(Order.asc("classroom_num"));
			try
			{
				classroom_list = classroom_criteria.list();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		classroomcheckbox = util.Util.getJspOutput("/jsp/admin/widgets/classroomcheckbox.jsp");
		session.close();
		//System.out.println(classroomcheckbox);
		return SUCCESS;
	}
	
	
	
	
	public String upload() throws Exception
	{
//		classroom_list = new ArrayList<Classroom>();
//		System.out.println("|||||"+util.Util.RootPath);
//		System.out.println(fileFileName);
		if(file == null)
		{
			this.status = "1";
			this.message = "文件未上传";
			return SUCCESS;
		}
//		System.out.println("***************");
		
		int end = fileFileName.indexOf(".");
		String cnt = fileFileName;
		if(end != -1) cnt = cnt.substring(0, end);
		else cnt = cnt.substring(0);
		
		String[] strs = cnt.split("-");
		if(strs.length != 2)
		{
			this.status = "1";
			this.message = "课表格式不正确";
			return SUCCESS;
		}
		String buildName = strs[0].trim();
		String classroomName = strs[1].trim();		
		Session session = model.Util.sessionFactory.openSession();
		List l = session.createCriteria(model.TeachBuilding.class)
			            .add(Restrictions.eq("build_name", buildName)).list();
		
//		System.out.println("COA");
//		System.out.println(fileFileName);
		if(l.isEmpty())
		{
			this.status = "1";
			this.message = "教学楼名称不存在";
			return SUCCESS;
		}		
		model.TeachBuilding build = (model.TeachBuilding)l.get(0);
		
		l = session.createCriteria(model.Classroom.class)
	               .add(Restrictions.eq("teachbuilding.build_id", build.getBuild_id()))
	               .add(Restrictions.eq("classroom_num", classroomName))
	               .list();
		if(l.isEmpty())
		{
			this.status = "1";
			this.message = "教室号不存在";
			return SUCCESS;
		}
		
		model.Classroom classroom = (model.Classroom)l.get(0);
		
		if(classroom.getClass_schedule_path() != null)
		{
			File old_class_schedule = new File(util.Util.RootPath + classroom.getClass_schedule_path());		
	    	if(!old_class_schedule.delete()) // 删除旧课表
	    	{
	    		this.status = "1";
				this.message = "系统出现致命错误！！！！！！";
				return SUCCESS;
	    	}
		} 
        
    	util.Util.saveFile(file, fileFileName, util.Util.RootPath + util.Util.ClassroomSchedulePath);
    	String inserted_file_path = util.Util.ClassroomSchedulePath + fileFileName;
    	
    	

    	
    	classroom.class_schedule_path = inserted_file_path;
    	
        session.beginTransaction();
        session.update(classroom);
        session.getTransaction().commit();    
		session.close();
		this.status = "0";
		return SUCCESS;
	}
	public String classroom(){
		classroom_list = new ArrayList<Classroom>();
		return SUCCESS;
	}
	public String classroomUpload() {	
		String[] classroomsarray = classrooms.get(0).toString().split(",");
		try{
			if(file == null){
				this.status = "1";
				this.message = "文件未上传";
				return SUCCESS;
			}
			for(int i=0;i<classroomsarray.length;i++){
				Session session = model.Util.sessionFactory.openSession();
				RoomPicture nPicture = new RoomPicture();
				nPicture.setClass_id(Integer.parseInt(classroomsarray[i]));
			
				//获取当前时间，命名照片，防止照片重复
				java.util.Date date = new java.util.Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddHHmmss");
				String curdate = simpleDateFormat.format(date);
				String fileName = curdate+fileFileName.substring(fileFileName.length()-5, fileFileName.length())+i;
				if (file != null){   //file没接收到的原因可能是jsp页面里面的input file的属性名不是file 
					util.Util.saveFile(file, fileName, util.Util.RootPath + util.Util.ClassroomInfoFilePath);
					String inserted_file_path = util.Util.ClassroomInfoFilePath +fileName;
					nPicture.setPath(inserted_file_path);
				}
				session.beginTransaction();
				session.save(nPicture);
				Transaction t = session.getTransaction();
				t.commit();
			}	
		}catch (Exception e){
			e.printStackTrace();
		}
		return ActionSupport.SUCCESS;
	}
	
	
	
	
	
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getSelectTeachBuilding() {
		return selectTeachBuilding;
	}

	public void setSelectTeachBuilding(int selectTeachBuilding) {
		this.selectTeachBuilding = selectTeachBuilding;
	}

	public String getClassroomcheckbox() {
		return classroomcheckbox;
	}

	public void setClassroomcheckbox(String classroomcheckbox) {
		this.classroomcheckbox = classroomcheckbox;
	}
	public List<Classroom> getClassroom_list() {
		return classroom_list;
	}

	public void setClassroom_list(List<Classroom> classroom_list) {
		this.classroom_list = classroom_list;
	}

	public List getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List classrooms) {
		this.classrooms = classrooms;
	}





	

	
	
	
}