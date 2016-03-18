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

import Repair.RepairDAO;
import RepairImpl.RepairDAOImpl;
import db.MyHibernateSessionFactory;

public class ClassroomDetailAction extends FileUploadBaseAction{
	public String build_name;
	public String remark;
	public int picID;
	public static int classroomId;
	
	public TeachBuilding building;
	public Classroom classroom;
	public String schedulePath;
	public List<CheckRecord> checkrecords;
	public List<RepairRecord> repairrecords;
	public List<Repertory> repertory_list;
	public List<RoomPicture>picture_list;
	public List classroom_repertory_list;
	
	public String repairrecord_jsp;
	public String checkdetail;
	public String checkrecord_jsp;
	public String classroomid;
	public String savestatus;
	public int deviceId;
	public String rtID;
	public String repairdetail;
	public String move_device_id;
	public String move_class_id;
	public String device_jsp;
	public String alterdevice_jsp;

	
	public List<Repertory> rtClass;
	
	public String execute() {
		System.out.println("admin.classroomaction:");
		Session session = model.Util.sessionFactory.openSession();
		//query current select classroom
		Criteria classroom_criteria = session.createCriteria(Classroom.class);
		Criteria building_criteria = session.createCriteria(TeachBuilding.class);
		classroom_criteria.add(Restrictions.eq("id", classroomId));
		classroom = (Classroom) classroom_criteria.uniqueResult();
		building_criteria.add(Restrictions.eq("build_id", classroom.teachbuilding.build_id));
		building = (TeachBuilding) building_criteria.uniqueResult();
		
		ActionContext.getContext().getSession().remove("classroom_id");
		//ActionContext.getContext().getSession().remove("classroom");
		ActionContext.getContext().getSession().put("classroom_id", classroom.id);
		//ActionContext.getContext().getSession().put("classroom", classroom);
		
		/*String hql = "SELECT rt FROM Repertory rt WHERE rt.classroom = " + classroomId;
		Query query = session.createQuery(hql);
		rtClass = query.list();
		for (int i = 0; i < rtClass.size(); i++) {
			System.out.println("输出++++++++++++++++++++++++++++++++");
			System.out.println(rtClass.get(i));
		}*/
		
		Transaction tx = null;
		String hql ="";
		try {
//			Session session1 = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "SELECT rt FROM Repertory rt WHERE rt.rtDeviceStatus = '教室' AND rt.classroom = " + classroomId;
			System.out.println(hql);
			Query query = session.createQuery(hql);
			rtClass = query.list();
			for (int i = 0; i < rtClass.size(); i++) {
				System.out.println("输出++++++++++++++++++++++++++++++++");
				System.out.println(rtClass.get(i));
			}
			tx.commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			tx.commit();
		}
		finally {
			if (tx != null) {
				if (tx != null) {
					tx = null;
				}
			}
		}

		//query at most 5 checkrecord
		Criteria checkrecord_criteria = session.createCriteria(CheckRecord.class).setFetchMode("classroom", FetchMode.SELECT).setFetchMode("checkman", FetchMode.SELECT);
		
		checkrecord_criteria.add(Restrictions.eq("classroom.id", classroomId));
		checkrecord_criteria.addOrder(Order.desc("id"));
		
//		checkrecord_criteria.addOrder(Order.asc("checkdate"));
//		long check_rowCount = (Long) checkrecord_criteria.setProjection(  
//                Projections.rowCount()).uniqueResult();
//		int checkrecord_start = ((int) check_rowCount) > 5 ? ((int) check_rowCount) - 5 : 0;
//		checkrecord_criteria.setProjection(null);
//		checkrecord_criteria.setFirstResult(checkrecord_start);
		checkrecord_criteria.setMaxResults(5);
		checkrecords = checkrecord_criteria.list();
//System.out.println("checksize:"+checkrecords.size());
		
		
		
		//query repairrecord
		long repair_rowCount = (Long) session.createQuery("select count(*) from RepairRecord as rd left join rd.device as ry left join ry.classroom as cm where cm.id=" + classroomId).uniqueResult();
		int repairrecord_start = ((int) repair_rowCount) > 5 ? ((int) repair_rowCount) - 5 : 0;
//		System.out.println("s:"+repairrecord_start);
		repairrecords = (List) session.createQuery("select rd "
												+ "from RepairRecord as rd "
												+ "left join rd.device as ry "
												+ "left join ry.classroom as cm  "
												+ "where cm.id=" + classroomId + " order by rd.id desc")
				
//				.setFirstResult(repairrecord_start)
				.setMaxResults(5).list();
//		System.out.println("repairsize:"+repairrecords.size());
//		for(RepairRecord r : repairrecords) {
//			System.out.println(r.repairman);
//		}
		
		
		
		
//		List classroom_repertory_list;
		
		classroom_repertory_list = session.createCriteria(model.Repertory.class).add(Restrictions.eq("classroom.id", classroomId)).list();
		
		System.out.println("JJ");
//		System.out.println(classroom.repertorys);
		System.out.println(classroom_repertory_list);
		
		session.close();
		ClassroomPicture();
		
		
		
		return ActionSupport.SUCCESS;
	}
	
	
	//备用设备
	public String alterdevice(){
		System.out.println("alterdevice:");
		System.out.println(classroomid);
		RepairDAO rdao = new RepairDAOImpl();
		repertory_list = rdao.alterDevice(classroomid);
		for (int i = 0; i < repertory_list.size(); i++) {
			System.out.println("输出++++++++++++++++++++++++++++++++");
			System.out.println(repertory_list.get(i).getRtDevice());
		}
		alterdevice_jsp = util.Util.getJspOutput("/jsp/classroom/alterdevice.jsp");
		return ActionSupport.SUCCESS;
	}
	
	//加入教室
	public String move2class(){
		System.out.println("move2class");
		System.out.println(classroomid+" "+ rtID);
		
		RepairDAO rdao = new RepairDAOImpl();
		System.out.println("============");
		System.out.println("设备id："+ rtID);
		System.out.println("加入教室："+ classroomid);
		
		String ret = Integer.toString(rdao.addalterIm(rtID, classroomid));

		Session session = null;
		session = model.Util.sessionFactory.openSession();		
		Transaction tx = null;
		String hql ="";
		try {
			tx = session.beginTransaction();
			hql = "SELECT rt FROM Repertory rt WHERE rt.rtDeviceStatus = '教室' AND rt.classroom = " + classroomid;
			System.out.println(hql);
			Query query = session.createQuery(hql);
			rtClass = query.list();
			tx.commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			tx.commit();
		}
		repertory_list = rdao.alterDevice(classroomid);
		
		device_jsp = util.Util.getJspOutput("/jsp/classroom/device.jsp");
		alterdevice_jsp = util.Util.getJspOutput("/jsp/classroom/alterdevice.jsp");
		
		return ActionSupport.SUCCESS;
	}
	
	
	//移入维修
	public String move2repair(){
		System.out.println("move2repair:");
		System.out.println(move_device_id);
		System.out.println(move_class_id);
		
		RepairDAO rdao = new RepairDAOImpl();
		String ret = Integer.toString(rdao.m2alter(move_device_id, "0"));
		
		Session session = null;
		session = model.Util.sessionFactory.openSession();
		

		
		Criteria classroom_criteria = session.createCriteria(Classroom.class);
		classroom_criteria.add(Restrictions.eq("id", Integer.parseInt(move_class_id)));
		classroom = (Classroom) classroom_criteria.uniqueResult();
		
		System.out.println(classroom.id);
		Transaction tx = null;
		String hql ="";
		try {
			tx = session.beginTransaction();
			hql = "SELECT rt FROM Repertory rt WHERE rt.rtDeviceStatus = '教室' AND rt.classroom = " + move_class_id;
			System.out.println(hql);
			Query query = session.createQuery(hql);
			rtClass = query.list();
			for (int i = 0; i < rtClass.size(); i++) {
				System.out.println("输出++++++++++++++++++++++++++++++++");
				System.out.println(rtClass.get(i));
			}
			tx.commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			tx.commit();
		}
		
		device_jsp = util.Util.getJspOutput("/jsp/classroom/device.jsp");
		
		
		return ActionSupport.SUCCESS;
	}
	
	//移入报废
	public String move2bad(){
		System.out.println("move2bad:");
		
		System.out.println(move_device_id);
		System.out.println(move_class_id);
		
		RepairDAO rdao = new RepairDAOImpl();
		//System.out.println("============");
		String ret = Integer.toString(rdao.m2alter(move_device_id, "1"));
		
		//System.out.println("怎么可能"+classroom_id);
		
		Session session = null;
		session = model.Util.sessionFactory.openSession();		
		Criteria classroom_criteria = session.createCriteria(Classroom.class);
		classroom_criteria.add(Restrictions.eq("id", Integer.parseInt(move_class_id)));
		classroom = (Classroom) classroom_criteria.uniqueResult();
		
		System.out.println(classroom.id);
		Transaction tx = null;
		String hql ="";
		try {
			tx = session.beginTransaction();
			hql = "SELECT rt FROM Repertory rt WHERE rt.rtDeviceStatus = '教室' AND rt.classroom = " + move_class_id;
			System.out.println(hql);
			Query query = session.createQuery(hql);
			rtClass = query.list();
			for (int i = 0; i < rtClass.size(); i++) {
				System.out.println("输出++++++++++++++++++++++++++++++++");
				System.out.println(rtClass.get(i));
			}
			tx.commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			tx.commit();
		}
		
		device_jsp = util.Util.getJspOutput("/jsp/classroom/device.jsp");
		
		
		return ActionSupport.SUCCESS;
	}
	
	
	
	//维修记录
	public String repairrecordsave() {
		System.out.println("admin.repairrecord:");
		Session session = null;
		try	{
			int user_id = (int) ActionContext.getContext().getSession().get("user_id");
			session = model.Util.sessionFactory.openSession();
			Criteria user_criteria = session.createCriteria(User.class);
			user_criteria.add(Restrictions.eq("id", user_id));
			User repairman = (User) user_criteria.uniqueResult();
			
			Date repairdate = new Date(new java.util.Date().getTime());
			
			Criteria repertory_criteria = session.createCriteria(Repertory.class);
			repertory_criteria.add(Restrictions.eq("rtId", deviceId));
			Repertory device = (Repertory) repertory_criteria.uniqueResult();
			
			RepairRecord repairrecord = new RepairRecord();
			repairrecord.setDevice(device);
			repairrecord.setRepairdate(repairdate);
			repairrecord.setRepairdetail(repairdetail);
			repairrecord.setRepairman(repairman);
			
			session.beginTransaction();
			session.save(repairrecord);
			session.getTransaction().commit();
			
			repairrecords = (List) session.createQuery("select rd "
					+ "from RepairRecord as rd "
					+ "left join rd.device as ry "
					+ "left join ry.classroom as cm  "
					+ "where cm.id=" + classroomId + " order by rd.id desc")
			
			//.setFirstResult(repairrecord_start)
			.setMaxResults(5).list();
			
			System.out.println("repairsize:"+repairrecords.size());
			
			repairrecord_jsp = util.Util.getJspOutput("/jsp/classroom/repairrecord.jsp");
						
			
			this.savestatus = "success";
		} catch(Exception e)	{
			this.savestatus = "fail";
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return SUCCESS;
	}
	
	//检查记录
	public String checkrecordsave() {
		System.out.println("admin.checkrecord:");
		Session session = null;
		try	{
			int user_id = (int) ActionContext.getContext().getSession().get("user_id");
			session = model.Util.sessionFactory.openSession();
			Criteria user_criteria = session.createCriteria(User.class);
			user_criteria.add(Restrictions.eq("id", user_id));
			User user = (User) user_criteria.uniqueResult();
//System.out.println(stu.getUser().getUsername());
			
			
			
//			Date checkdate = new Date(new java.util.Date().getTime());
			Timestamp checkdate = new Timestamp(new java.util.Date().getTime());


			

			
			Criteria classroom_criteria = session.createCriteria(Classroom.class);
			classroom_criteria.add(Restrictions.eq("id", Integer.parseInt(classroomid)));
			Classroom classroom = (Classroom) classroom_criteria.uniqueResult();
			CheckRecord checkrecord = new CheckRecord();
			checkrecord.setCheckdate(checkdate);
			checkrecord.setCheckdetail(checkdetail);
			checkrecord.setCheckman(user);
			checkrecord.setClassroom(classroom);
			session.beginTransaction();
			session.save(checkrecord);
			session.getTransaction().commit();
			
			Criteria checkrecord_criteria = session.createCriteria(CheckRecord.class).setFetchMode("classroom", FetchMode.SELECT).setFetchMode("checkman", FetchMode.SELECT);
			
			checkrecord_criteria.add(Restrictions.eq("classroom.id", classroomId));
			checkrecord_criteria.addOrder(Order.desc("id"));
			checkrecord_criteria.setMaxResults(5);
			checkrecords = checkrecord_criteria.list();
			//System.out.println("checkrecord:" + checkrecords);
			
			checkrecord_jsp = util.Util.getJspOutput("/jsp/classroom/checkrecord.jsp");
			
			this.savestatus = "success";
		} catch(Exception e)	{
			this.savestatus = "fail";
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return SUCCESS;
	}
	
	
	
	public void ClassroomPicture(){
		System.out.println("ClassroomPicture");
		System.out.println(classroomId);
		
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria c1 = session.createCriteria(RoomPicture.class).add(Restrictions.eq("class_id",classroomId)); //hibernate session创建查询
		picture_list = c1.list();
		
		Criteria q = session.createCriteria(Classroom.class).add(Restrictions.eq("id",classroomId)); //hibernate session创建查询
		List<Classroom> class_list = q.list();
		Collections.reverse(class_list);
		schedulePath = class_list.get(0).getClass_schedule_path();
		session.close();
		
		
		System.out.println(picture_list);
		System.out.println(schedulePath);
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
		System.out.println("calssid" + classroomId);
		System.out.println("fileFileName" + fileFileName);
		
		Session session = model.Util.sessionFactory.openSession();		
		Criteria q = session.createCriteria(Classroom.class).add(Restrictions.eq("id",classroomId)); //hibernate session创建查询
		List<Classroom> class_list = q.list();
		Collections.reverse(class_list);
		Classroom nClass = class_list.get(0);

		if (file != null)//file没接收到的原因可能是jsp页面里面的input file的属性名不是file 
        {
			File old_class_schedule = new File(util.Util.RootPath + nClass.getClass_schedule_path());
	    	if(!old_class_schedule.delete()) // 删除旧课表
	    	{
//	    		this.status = "1";
//				this.message = "系统出现致命错误！！！！！！";
	    		System.out.println("系统出现致命错误！！！！！！");
//				return SUCCESS;
	    	}
	    	System.out.println("**"+nClass.getClassroom_num()+"**");
			String newFileName = nClass.getTeachbuilding().getBuild_name() + "-" + nClass.getClassroom_num() 
								 + fileFileName.substring(fileFileName.lastIndexOf("."));
			
			util.Util.saveFile(file, newFileName,  util.Util.RootPath + util.Util.ClassroomSchedulePath);
			String inserted_file_path = util.Util.ClassroomSchedulePath +newFileName;
			nClass.setClass_schedule_path(inserted_file_path);
			System.out.println("inserted_file_path" + inserted_file_path);
        }
	
		session.beginTransaction();
		session.update(nClass);
		Transaction t = session.getTransaction();
		t.commit();
		session.close();
		return ActionSupport.SUCCESS;
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




	public TeachBuilding getBuilding() {
		return building;
	}




	public void setBuilding(TeachBuilding building) {
		this.building = building;
	}




	public Classroom getClassroom() {
		return classroom;
	}




	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
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




	public String getCheckdetail() {
		return checkdetail;
	}




	public void setCheckdetail(String checkdetail) {
		this.checkdetail = checkdetail;
	}









	public String getCheckrecord_jsp() {
		return checkrecord_jsp;
	}

	public void setCheckrecord_jsp(String checkrecord_jsp) {
		this.checkrecord_jsp = checkrecord_jsp;
	}

	public String getClassroomid() {
		return classroomid;
	}




	public void setClassroomid(String classroomid) {
		this.classroomid = classroomid;
	}




	public String getSavestatus() {
		return savestatus;
	}




	public void setSavestatus(String savestatus) {
		this.savestatus = savestatus;
	}




	public List<Repertory> getRtClass() {
		return rtClass;
	}




	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getRepairdetail() {
		return repairdetail;
	}

	public void setRepairdetail(String repairdetail) {
		this.repairdetail = repairdetail;
	}

	public void setRtClass(List<Repertory> rtClass) {
		this.rtClass = rtClass;
	}




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

	public String getRepairrecord_jsp() {
		return repairrecord_jsp;
	}

	public void setRepairrecord_jsp(String repairrecord_jsp) {
		this.repairrecord_jsp = repairrecord_jsp;
	}


	public String getMove_device_id() {
		return move_device_id;
	}


	public void setMove_device_id(String move_device_id) {
		this.move_device_id = move_device_id;
	}


	public String getMove_class_id() {
		return move_class_id;
	}


	public void setMove_class_id(String move_class_id) {
		this.move_class_id = move_class_id;
	}


	public String getDevice_jsp() {
		return device_jsp;
	}


	public void setDevice_jsp(String device_jsp) {
		this.device_jsp = device_jsp;
	}

	public String getAlterdevice_jsp() {
		return alterdevice_jsp;
	}

	public void setAlterdevice_jsp(String alterdevice_jsp) {
		this.alterdevice_jsp = alterdevice_jsp;
	}


	public List<Repertory> getRepertory_list() {
		return repertory_list;
	}


	public void setRepertory_list(List<Repertory> repertory_list) {
		this.repertory_list = repertory_list;
	}


	public String getRtID() {
		return rtID;
	}


	public void setRtID(String rtID) {
		this.rtID = rtID;
	}
	
	

}
