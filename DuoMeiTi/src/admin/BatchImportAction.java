package admin;

import java.io.File;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import util.FileUploadBaseAction;

public class BatchImportAction extends FileUploadBaseAction {
	String status;
	String message;
	
	
	public String execute() throws Exception
	{
		System.out.println("|||||"+util.Util.RootPath);
		return SUCCESS;
	}
	public String upload() throws Exception
	{
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
		
		File old_class_schedule = new File(util.Util.RootPath + classroom.getClass_schedule_path());
    	if(!old_class_schedule.delete()) // 删除旧课表
    	{
    		this.status = "1";
			this.message = "系统出现致命错误！！！！！！";
			return SUCCESS;
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

}