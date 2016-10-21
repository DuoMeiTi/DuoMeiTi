package student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.DutyPiece;
import model.DutySchedule;



public class DutyManageAction {
	

	
	
	List dutyPlaceList;
	
	public String dutyManage() throws Exception{
		
		Session session = model.Util.sessionFactory.openSession();
		
		dutyPlaceList = session.createCriteria(model.DutyPlace.class).list();
		session.close();
		return ActionSupport.SUCCESS;
	}

	int obtainDutyTable_dutyPlaceId;
	List obtainDutyTable_dutyPieceList;
	int obtainDutyTable_studentId;
	List obtainDutyTable_dutyChoosedPieceList;
	public String obtainDutyTable() throws Exception{
		
		int id = obtainDutyTable_dutyPlaceId;
		int studentId = obtainDutyTable_studentId;
		
		Session session = model.Util.sessionFactory.openSession();

		obtainDutyTable_dutyPieceList =  session.createCriteria(model.DutyPiece.class)
				.add(Restrictions.eq("dutyPlace.id", id)).list();
		
		List<model.DutySchedule> L = 
						(List<model.DutySchedule>)session.createCriteria(model.DutySchedule.class)
						.add(Restrictions.eq("student.id", studentId))
						.list();
		
		
		obtainDutyTable_dutyChoosedPieceList = new ArrayList<model.DutyPiece>();
		for(model.DutySchedule dp: L)
		{
			if(dp.getDutyPiece().getDutyPlace().getId() == id)
				obtainDutyTable_dutyChoosedPieceList.add(dp.getDutyPiece());
		}


		session.close();
		return ActionSupport.SUCCESS;
	}
	
	int deleteSchedule_dutyPieceId;
	int deleteSchedule_studentId;
	int deleteSchedule_dutyLeft;
	String status;
	static boolean dutyChooseSwitchIsOpen(Session session) throws Exception{
		
		
		model.DutyChooseSwitch dcs = (model.DutyChooseSwitch)
				session.createCriteria(model.DutyChooseSwitch.class).uniqueResult();
		return dcs.isOpen;
		
	}
	
	/** TODO: make it better！ */
	public String deleteSchedule() throws Exception {
		
		Session session = model.Util.sessionFactory.openSession();
		
		if(!dutyChooseSwitchIsOpen(session))
		{
			this.status = "4管理员未打开选课开关，不能删除当前选班";
			session.close();
			return ActionSupport.SUCCESS;
		}
		
		
		
		
		
		session.beginTransaction();
		model.DutySchedule ds = (DutySchedule)session.createCriteria(model.DutySchedule.class)
				.add(Restrictions.eq("dutyPiece.id", deleteSchedule_dutyPieceId))
				.add(Restrictions.eq("student.id", deleteSchedule_studentId))
				.uniqueResult();
		model.DutyPiece dp = ds.dutyPiece;
		if(ds != null)
		{
			
			dp.dutyLeft ++;
			session.update(dp);
			session.delete(ds);
			this.status = "0删除成功";
					
		}
		else 
		{
			this.status = "1此选课时间段已经被删除，删除失败";
		}
		
		session.getTransaction().commit();
		deleteSchedule_dutyLeft = dp.dutyLeft;
		
			
		
		session.close();
		return ActionSupport.SUCCESS;
	}

	
	
	
	
	int addSchedule_dutyPieceId;
	int addSchedule_studentId;
	int addSchedule_dutyLeft;
	/** TODO: make it better！ */
	public String addSchedule() throws Exception {
		
		Session session = model.Util.sessionFactory.openSession();
		
		if(!dutyChooseSwitchIsOpen(session))
		{
			this.status = "4管理员未打开选课开关，不能选择当前选班";
			session.close();
			return ActionSupport.SUCCESS;
		}
		
		
		
		session.beginTransaction();
		model.DutyPiece dp = (DutyPiece)session.createCriteria(model.DutyPiece.class)
				.add(Restrictions.eq("id", addSchedule_dutyPieceId))
				.uniqueResult();
		
		model.StudentProfile sp = 
				(model.StudentProfile)session.createCriteria(model.StudentProfile.class)
									   .add(Restrictions.eq("id", addSchedule_studentId))
									   .uniqueResult();

		
		model.DutySchedule ds = (DutySchedule)session.createCriteria(model.DutySchedule.class)
				.add(Restrictions.eq("dutyPiece.id", addSchedule_dutyPieceId))
				.add(Restrictions.eq("student.id", addSchedule_studentId))
				.uniqueResult();
		
		if(dp == null || sp == null)
		{			
			this.status = "2出现严重错误！未找到对应的值班块或者学生";					
		}
		else if(ds != null)
		{
			this.status = "1此选课时间段已经被选过，不能再次选择";
		}
		else if(dp.dutyLeft <= 0)
		{
			this.status = "3此选课时间段被选满，选择失败";
		}
		else 
		{
			
	
			dp.dutyLeft --;
			
			
			DutySchedule new_ds = new DutySchedule();
			new_ds.setDutyPiece(dp);
			new_ds.setStudent(sp);
			
			session.update(dp);
			session.save(new_ds);
			this.status = "0选课成功";
			
		}
		
		session.getTransaction().commit();
		addSchedule_dutyLeft = dp.dutyLeft;
		
			
		
		session.close();
		return ActionSupport.SUCCESS;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getAddSchedule_dutyPieceId() {
		return addSchedule_dutyPieceId;
	}












	public void setAddSchedule_dutyPieceId(int addSchedule_dutyPieceId) {
		this.addSchedule_dutyPieceId = addSchedule_dutyPieceId;
	}












	public int getAddSchedule_studentId() {
		return addSchedule_studentId;
	}












	public void setAddSchedule_studentId(int addSchedule_studentId) {
		this.addSchedule_studentId = addSchedule_studentId;
	}












	public int getAddSchedule_dutyLeft() {
		return addSchedule_dutyLeft;
	}












	public void setAddSchedule_dutyLeft(int addSchedule_dutyLeft) {
		this.addSchedule_dutyLeft = addSchedule_dutyLeft;
	}












	public int getDeleteSchedule_dutyPieceId() {
		return deleteSchedule_dutyPieceId;
	}
	public void setDeleteSchedule_dutyPieceId(int deleteSchedule_dutyPieceId) {
		this.deleteSchedule_dutyPieceId = deleteSchedule_dutyPieceId;
	}
	
	public int getDeleteSchedule_studentId() {
		return deleteSchedule_studentId;
	}

	public void setDeleteSchedule_studentId(int deleteSchedule_studentId) {
		this.deleteSchedule_studentId = deleteSchedule_studentId;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getObtainDutyTable_studentId() {
		return obtainDutyTable_studentId;
	}

	public void setObtainDutyTable_studentId(int obtainDutyTable_studentId) {
		this.obtainDutyTable_studentId = obtainDutyTable_studentId;
	}
	public List getObtainDutyTable_dutyChoosedPieceList() {
		return obtainDutyTable_dutyChoosedPieceList;
	}

	public void setObtainDutyTable_dutyChoosedPieceList(List obtainDutyTable_dutyChoosedPieceList) {
		this.obtainDutyTable_dutyChoosedPieceList = obtainDutyTable_dutyChoosedPieceList;
	}

	public int getObtainDutyTable_dutyPlaceId() {
		return obtainDutyTable_dutyPlaceId;
	}


	public void setObtainDutyTable_dutyPlaceId(int obtainDutyTable_dutyPlaceId) {
		this.obtainDutyTable_dutyPlaceId = obtainDutyTable_dutyPlaceId;
	}


	public List getObtainDutyTable_dutyPieceList() {
		return obtainDutyTable_dutyPieceList;
	}




	public void setObtainDutyTable_dutyPieceList(List obtainDutyTable_dutyPieceList) {
		this.obtainDutyTable_dutyPieceList = obtainDutyTable_dutyPieceList;
	}




	public List getDutyPlaceList() {
		return dutyPlaceList;
	}

	public void setDutyPlaceList(List dutyPlaceList) {
		this.dutyPlaceList = dutyPlaceList;
	}












	public int getDeleteSchedule_dutyLeft() {
		return deleteSchedule_dutyLeft;
	}












	public void setDeleteSchedule_dutyLeft(int deleteSchedule_dutyLeft) {
		this.deleteSchedule_dutyLeft = deleteSchedule_dutyLeft;
	}
	
//	public String getDutyTime() throws Exception{
//		Session session = model.Util.sessionFactory.openSession();
//		String hql="from DutyTime d where d.teachBuilding.build_id="+teachBuildingId;
//		
//		String studentSelect="from StudentProfile s where s.id="+studentId;
//		StudentProfile s=(StudentProfile)session.createQuery(studentSelect).list().get(0);
//		
//		String selectChosen="select ds.dutyTime.time from DutySchedule ds where ds.student.id="+s.id+" and "
//							+"ds.dutyTime.teachBuilding.build_id="+teachBuildingId;
//		List chosenList = session.createQuery(selectChosen).list();
//		
//		chosen = new ArrayList<Integer>();
//		if(chosenList.size()>0){
//			Iterator iter= chosenList.iterator();
//			while(iter.hasNext()){
//				Integer temp=(Integer)iter.next();
//				chosen.add(temp);
//			}
//		}
//		duties=session.createQuery(hql).list();
//		session.close();
//		return "ajaxSuccess";
//	}
//	
//	public String reciveChoice() throws Exception{
//		
//		//代码太挫，留待有缘人！
//		Session session = model.Util.sessionFactory.openSession();
//		
//		String hql="from ChooseClassSwitch ccs where ccs.id=1";
//		ChooseClassSwitch f=(ChooseClassSwitch)session.createQuery(hql).list().get(0);
//		if(!f.open){
//			log="closed";
//			session.close();
//			return "ajaxSuccess";
//		}
//		Transaction trans;
//		//选出选课的学生
//		String studentSelect="from StudentProfile s where s.id="+studentId;
//		StudentProfile s=(StudentProfile)session.createQuery(studentSelect).list().get(0);
//		//删除dutySchedule中的该生以前选择的班次,并更新
//		trans=session.beginTransaction();
//		String dutySelect="select ds.dutyTime from DutySchedule ds where ds.student.id="
//					 	  +studentId+" and "+"ds.dutyTime.teachBuilding.build_id="+teachBuildingId;
//		List<DutyPiece> duties=session.createQuery(dutySelect).list();
//		for(DutyPiece tmp:duties){
//			String back= "update DutyTime d set d.dutyLeft=d.dutyLeft+1 where d.id="+tmp.id;
//			session.createQuery(back).executeUpdate();
//		}
//		String selectDutySchedule="From DutySchedule ds where ds.student.id="+studentId+" and "
//				  			  	  +"ds.dutyTime.teachBuilding.build_id="+teachBuildingId;
//		List<DutySchedule> dutySchedules=session.createQuery(selectDutySchedule).list();
//		for(DutySchedule tmp:dutySchedules){
//			String deleteChosen = "delete from DutySchedule ds where ds.id="+tmp.id;
//		  	session.createQuery(deleteChosen).executeUpdate();
//		}
//		trans.commit();
//		
//		//所选的班
//		String dc="(";
//		for(int i=0,end=chosen.size();i<end;i++){
//			dc+="d.time="+chosen.get(i).intValue();
//			if(i<end-1)dc+=" or ";
//			else dc+=")";
//		}
//		System.out.println(dc);
//		//dutyTime数据库更新
//		trans=session.beginTransaction();
//		String updateDutyLeft="update DutyTime d set d.dutyLeft=d.dutyLeft-1 where d.teachBuilding.build_id="
//								  +teachBuildingId+" and "+dc;
//		int ret=session.createQuery(updateDutyLeft).executeUpdate();
//		if(ret==0){
//			log="something wrong during updating database";
//			return "ajaxSuccess";
//		}
//		trans.commit();
//		
//		//dutySchedule数据库更新
//		String selectDutyTime="from DutyTime d where d.teachBuilding.build_id="
//							  +teachBuildingId+" and "+dc;
//		List<DutyPiece> dutyChosen=session.createQuery(selectDutyTime).list();
//		
//		for(DutyPiece t:dutyChosen){
//			try{
//				trans=session.beginTransaction();
//				DutySchedule temp =new DutySchedule();
//				temp.student=s;
//				temp.dutyPiece=t;
//				session.save(temp);
//				trans.commit();
//			}
//			catch(Exception e){
//				log="fail";
//				return "ajaxSuccess";
//			}
//		}
//		session.close();
//		log="success";
//		return "ajaxSuccess";
//	}

	
	
	
	
}
