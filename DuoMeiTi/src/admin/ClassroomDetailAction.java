package admin;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import model.AdminProfile;
import model.CheckRecord;
import model.Classroom;
import model.RepairRecord;
import model.Repertory;
import model.RoomPicture;
import model.TeachBuilding;
import model.User;
import util.FileUploadBaseAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class ClassroomDetailAction extends FileUploadBaseAction{

	public String remark;
	
	public int picID;

	public int classroomId;
	

	public Classroom classroom;
	public String schedulePath;
	public List<CheckRecord> checkrecords;
	public List<RepairRecord> repairrecords;
	public List<Repertory> repertory_list;
	public List<RoomPicture>picture_list;
	public List classroom_repertory_list;
	


	
	public List<Repertory> rtClass;
	
	public String execute() {
		System.out.println("admin detail Action:");
		Session session = model.Util.sessionFactory.openSession();
		Object[] ans = common.ClassroomInformationAction.obtainAllInfo(session, classroomId);
		
		classroom = (Classroom)ans[0];
		rtClass = (List)ans[1];
		checkrecords = (List)ans[2];
		repairrecords = (List)ans[3];

		session.close();
		ClassroomPicture();
		return ActionSupport.SUCCESS;
	}
	
	

	
	
	public void ClassroomPicture(){

		Session session = model.Util.sessionFactory.openSession();
		Criteria c1 = session.createCriteria(RoomPicture.class).add(Restrictions.eq("class_id",classroomId)); //hibernate session创建查询
		picture_list = c1.list();
		
		Criteria q = session.createCriteria(Classroom.class).add(Restrictions.eq("id",classroomId)); //hibernate session创建查询
		List<Classroom> class_list = q.list();
		Collections.reverse(class_list);
		schedulePath = class_list.get(0).getClass_schedule_path();
		session.close();		
	}
	
	public String PictureUpload() {
		System.out.println("PictureUpload!");
		System.out.println("calssid" + classroomId);
		System.out.println(remark);
		
		Session session = model.Util.sessionFactory.openSession();
		RoomPicture nPicture = new RoomPicture();	
		
		nPicture.setClass_id(classroomId);
		nPicture.setRemark(remark);
		
		//获取当前时间，命名照片，防止照片重复
		java.util.Date date = new java.util.Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddHHmmss");
		String curdate = simpleDateFormat.format(date);
		String fileName = curdate+fileFileName.substring(fileFileName.length()-5, fileFileName.length());
		if (file != null)//file没接收到的原因可能是jsp页面里面的input file的属性名不是file 
        {
			util.Util.saveFile(file, fileName, util.Util.RootPath + util.Util.ClassroomInfoFilePath);
			String inserted_file_path = util.Util.ClassroomInfoFilePath +fileName;
			nPicture.setPath(inserted_file_path);
        }
		
		session.beginTransaction();
		session.save(nPicture);
		Transaction t = session.getTransaction();
		t.commit();
		
		return ActionSupport.SUCCESS;
	}
	
	
	
	public String PictureDelete() {
		System.out.println("PictureDelete:");
		System.out.println(picID);
		
		//从数据库中删除
		Session session = model.Util.sessionFactory.openSession();		
		Criteria q = session.createCriteria(RoomPicture.class).add(Restrictions.eq("id",picID)); //hibernate session创建查询
		picture_list=q.list();
		Collections.reverse(picture_list);
		RoomPicture nPicture = new RoomPicture();
		nPicture = picture_list.get(0);
		//System.out.println(nPicture);
		session.beginTransaction();
		session.delete(nPicture);
		Transaction t = session.getTransaction();
		t.commit();
		session.close();
		//删除本地文件
		String filePath = util.Util.RootPath + nPicture.getPath();
		System.out.println(filePath);
		util.Util.deleteFile(filePath);
		
		return ActionSupport.SUCCESS;
	}
	
	public String ScheduleUpload(){
		System.out.println("ScheduleUpload:");
		System.out.println("classroomId:" + classroomId);
		System.out.println("fileFileName:" + fileFileName);
		
		Session session = model.Util.sessionFactory.openSession();
		
		Classroom classroom = (Classroom)session
						.createCriteria(Classroom.class)
						.add(Restrictions.eq("id",classroomId) )
						.uniqueResult();
		
		
		util.Util.saveClassroomScheduleFile(session, classroom, file, fileFileName);
		session.close();
		return ActionSupport.SUCCESS;
		
//		Criteria q = session.createCriteria(Classroom.class).add(Restrictions.eq("id",classroomId)); //hibernate session创建查询
//		List<Classroom> class_list = q.list();
//		Collections.reverse(class_list);
//		Classroom nClass = class_list.get(0);
//
//		if (file != null)//file没接收到的原因可能是jsp页面里面的input file的属性名不是file 
//        {
//			File old_class_schedule = new File(util.Util.RootPath + nClass.getClass_schedule_path());
//	    	if(!old_class_schedule.delete()) // 删除旧课表
//	    	{
////	    		this.status = "1";
////				this.message = "系统出现致命错误！！！！！！";
//	    		System.out.println("系统出现致命错误！！！！！！");
////				return SUCCESS;
//	    	}
//	    	System.out.println("**"+nClass.getClassroom_num()+"**");
//			String newFileName = nClass.getTeachbuilding().getBuild_name() + "-" + nClass.getClassroom_num() 
//								 + fileFileName.substring(fileFileName.lastIndexOf("."));
//			
//			util.Util.saveFile(file, newFileName,  util.Util.RootPath + util.Util.ClassroomSchedulePath);
//			String inserted_file_path = util.Util.ClassroomSchedulePath +newFileName;
//			nClass.setClass_schedule_path(inserted_file_path);
//			System.out.println("inserted_file_path" + inserted_file_path);
//        }
//	
//		session.beginTransaction();
//		session.update(nClass);
//		Transaction t = session.getTransaction();
//		t.commit();
		
	}


	
	
	
	



	public String getRemark() {
		return remark;
	}





	public void setRemark(String remark) {
		this.remark = remark;
	}





	public int getPicID() {
		return picID;
	}





	public void setPicID(int picID) {
		this.picID = picID;
	}





	public int getClassroomId() {
		return classroomId;
	}





	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}





	public String getSchedulePath() {
		return schedulePath;
	}





	public void setSchedulePath(String schedulePath) {
		this.schedulePath = schedulePath;
	}





	public List<CheckRecord> getCheckrecords() {
		return checkrecords;
	}





	public void setCheckrecords(List<CheckRecord> checkrecords) {
		this.checkrecords = checkrecords;
	}





	public List<RepairRecord> getRepairrecords() {
		return repairrecords;
	}





	public void setRepairrecords(List<RepairRecord> repairrecords) {
		this.repairrecords = repairrecords;
	}





	public List<Repertory> getRepertory_list() {
		return repertory_list;
	}





	public void setRepertory_list(List<Repertory> repertory_list) {
		this.repertory_list = repertory_list;
	}





	public List<RoomPicture> getPicture_list() {
		return picture_list;
	}





	public void setPicture_list(List<RoomPicture> picture_list) {
		this.picture_list = picture_list;
	}





	public List getClassroom_repertory_list() {
		return classroom_repertory_list;
	}





	public void setClassroom_repertory_list(List classroom_repertory_list) {
		this.classroom_repertory_list = classroom_repertory_list;
	}





	public List<Repertory> getRtClass() {
		return rtClass;
	}





	public void setRtClass(List<Repertory> rtClass) {
		this.rtClass = rtClass;
	}





	public Classroom getClassroom() {
		return classroom;
	}





	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	
}
