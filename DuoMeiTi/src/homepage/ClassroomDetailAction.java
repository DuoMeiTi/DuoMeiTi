package homepage;

import java.sql.Date;
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

//import db.MyHibernateSessionFactory;

public class ClassroomDetailAction extends FileUploadBaseAction{
	public String build_name;
	public String remark;
	public int picID;
	public static int classroomId;
	
	public TeachBuilding building;
	public Classroom classroom;
	
	public List<CheckRecord> checkrecords;
	
	public List<RepairRecord> repairrecords;
	public List<RoomPicture>picture_list;
	public List classroom_device_list;
	
	public List<Repertory> rtClass;
	public String class_schedule_path ;
	
	


	public String execute() {
		Session session = model.Util.sessionFactory.openSession();
		
		//query current select classroom
		classroom = common.ClassroomInformationAction.obtainClassroom(session, classroomId);
		
		
		//课程表
		class_schedule_path = classroom.class_schedule_path;
		
		classroom_device_list = 
				common.ClassroomInformationAction.obtainClassroomDeviceListCriteria(session, classroomId).list();
		
		checkrecords = common.ClassroomInformationAction.obtainCheckRecordListCriteria(session, classroomId).list();
		repairrecords = common.ClassroomInformationAction.obtainRepairRecordListCriteria(session, classroom).list();
		

		
		picture_list = session.createCriteria(RoomPicture.class).add(Restrictions.eq("class_id",classroomId)).list();


		session.close();
		
		return ActionSupport.SUCCESS;
	}
	
	
//	public void ClassroomPicture(){
//		
//		Session session = model.Util.sessionFactory.openSession();
//		session.close();
//		
//		System.out.println(picture_list);
//	}
//	
//	public String PictureUpload() {
//		System.out.println("PictureUpload!");
//		System.out.println("calssid" + classroomId);
//		System.out.println(remark);
//		
//		Session session = model.Util.sessionFactory.openSession();
//		RoomPicture nPicture = new RoomPicture();	
//		
//		nPicture.setClass_id(classroomId);
//		nPicture.setRemark(remark);
//		
//		//获取当前时间，命名照片，防止照片重复
//		java.util.Date date = new java.util.Date();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddHHmmss");
//		String curdate = simpleDateFormat.format(date);
//		String fileName = curdate+fileFileName;
//
//		if (file != null)//file没接收到的原因可能是jsp页面里面的input file的属性名不是file 
//        {
//			util.Util.saveFile(file, fileName, util.Util.RootPath + util.Util.ClassroomInfoFilePath);
//			String inserted_file_path = util.Util.ClassroomInfoFilePath +fileName;
//			nPicture.setPath(inserted_file_path);
//        }
//		
//		session.beginTransaction();
//		session.save(nPicture);
//		Transaction t = session.getTransaction();
//		t.commit();
//		
//		return ActionSupport.SUCCESS;
//	}
//	
//	
//	
//	public String PictureDelete() {
//		System.out.println("PictureDelete:");
//		System.out.println(picID);
//		
//		//从数据库中删除
//		Session session = model.Util.sessionFactory.openSession();		
//		Criteria q = session.createCriteria(RoomPicture.class).add(Restrictions.eq("id",picID)); //hibernate session创建查询
//		picture_list=q.list();
//		Collections.reverse(picture_list);
//		RoomPicture nPicture = new RoomPicture();
//		nPicture = picture_list.get(0);
//		//System.out.println(nPicture);
//		session.beginTransaction();
//		session.delete(nPicture);
//		Transaction t = session.getTransaction();
//		t.commit();
//		session.close();
//		//删除本地文件
//		String filePath = util.Util.RootPath + nPicture.getPath();
//		System.out.println(filePath);
//		util.Util.deleteFile(filePath);
//		
//		return ActionSupport.SUCCESS;
//	}
//	

	
	public String getBuild_name() {
		return build_name;
	}

	public void setBuild_name(String build_name) {
		this.build_name = build_name;
	}

	public int getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}
	public String getClass_schedule_path() {
		return class_schedule_path;
	}


	public void setClass_schedule_path(String class_schedule_path) {
		this.class_schedule_path = class_schedule_path;
	}
}
