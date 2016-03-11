package admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.DutyInfo;
import model.AdminProfile;
import model.Repertory;
import model.Rules;
import model.StudentProfile;
import model.User;
import util.Const;
import utility.DatabaseOperation;

import model.DutyPiece;
import model.ExamStuScore;
import model.DutySchedule;
import model.ChooseClassSwitch;
import model.DutyPlace;
import model.User;

public class DutyManageAction extends ActionSupport {
	
	
	static
	{
//		Session session = model.Util.sessionFactory.openSession();
//		if(session.createCriteria(model.DutyTime.class).list().size() == 0)
//		{
//			session.beginTransaction();
//			
//			
//			session.getTransaction().commit();
//			
//		}
		
		
	}
	
	
	

	List dutyPlaceList;
	String newDutyPlace = "";

	public String execute() throws Exception {
		System.out.println("GGGG");
		// if(ServletActionContext.getRequest().getMethod().equalsIgnoreCase("get"))
		// {
		// return ActionSupport.SUCCESS;
		// }
		Session session = model.Util.sessionFactory.openSession();

		dutyPlaceList = session.createCriteria(model.DutyPlace.class).list();

		session.close();

		System.out.println(this.newDutyPlace);

		return SUCCESS;
	}

	public String addDutyPlace() throws Exception {

		System.out.println(this.newDutyPlace);

		if (this.newDutyPlace.equals("")) {
			return SUCCESS;
		}

		model.DutyPlace dp = new model.DutyPlace();
		dp.setPlaceName(this.newDutyPlace);

		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		session.save(dp);
		
		for(int i = 0; i < model.DutyPiece.TimeNumber; ++ i)
		{
			DutyPiece dt = new DutyPiece();
			dt.setNumberOfDuty(4); // 当前时间段的值班个数容纳总量
			dt.setDutyLeft(4);// 当前时间段的值班个数的剩余量
			dt.setDutyPlace(dp);
			dt.setTime(i);
			session.save(dt);
		}
		
		
		session.getTransaction().commit();

		session.close();

		return SUCCESS;
	}

	int deletedDutyPlaceId;
	public String deleteDutyPlace() throws Exception {

		System.out.println(this.deletedDutyPlaceId);
		int id = deletedDutyPlaceId;

		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		DutyPlace dp = (DutyPlace) session.createCriteria(model.DutyPlace.class)
								.add(Restrictions.eq("id", id))
								.uniqueResult();
		
		List<DutyPiece> dutyTimeList = (List<DutyPiece>)session.createCriteria(model.DutyPiece.class)
							.add(Restrictions.eq("dutyPlace.id", id))
							.list();
		
		for(model.DutyPiece dt: dutyTimeList)
		{
			session.delete(dt);
		}
				
				
				
				
		session.delete(dp);
		session.getTransaction().commit();
		session.close();
		return SUCCESS;
	}
	
	
	
	
	int obtainDutyTable_dutyPlaceId;
	List<DutySchedule> obtainDutyTable_dutyScheduleList;
	public String obtainDutyTable() throws Exception {
		System.out.println("SBSBSBSB||||");;
		
		int id = obtainDutyTable_dutyPlaceId;
		
		Session session = model.Util.sessionFactory.openSession();
		
		
		List<DutyPiece> dutyTimeList = (List<DutyPiece>)session.createCriteria(model.DutyPiece.class)
				.add(Restrictions.eq("dutyPlace.id", id))
				.list();
		
		obtainDutyTable_dutyScheduleList = new ArrayList<DutySchedule>();
		
		for(DutyPiece dp: dutyTimeList)
		{
			List dutyScheduleList = session
									.createCriteria(model.DutySchedule.class)
									.add(Restrictions.eq("dutyPiece.id", dp.getId()))
									.list();
			
			obtainDutyTable_dutyScheduleList.addAll(dutyScheduleList);
			
		}
		System.out.println(obtainDutyTable_dutyScheduleList);;
		System.out.println("SBSBSBSB||||");;
		
		
		
//		String hql="select ds.student.id,ds.student.user.fullName,ds.dutyTime.time from DutySchedule ds where ds.dutyTime.teachBuilding.build_id="
//					+id+" order by ds.dutyTime.time";
//		List temp = session.createQuery(hql).list();
//		Iterator iter=temp.iterator();
//		dutySchedule = new ArrayList<DutyInfo>();
//		while(iter.hasNext()){
//			Object [] tmp= (Object[]) iter.next();
//			dutySchedule.add(new DutyInfo((Integer)tmp[0],(String)tmp[1],(Integer)tmp[2]));
//		}
//		System.out.println(dutySchedule.get(0).studentName);
		session.close();
		return SUCCESS;
	}

	
	
	
	
	
	
	

	public int getObtainDutyTable_dutyPlaceId() {
		return obtainDutyTable_dutyPlaceId;
	}

	public void setObtainDutyTable_dutyPlaceId(int obtainDutyTable_dutyPlaceId) {
		this.obtainDutyTable_dutyPlaceId = obtainDutyTable_dutyPlaceId;
	}

	public List getDutyPlaceList() {
		return dutyPlaceList;
	}

	public void setDutyPlaceList(List dutyPlaceList) {
		this.dutyPlaceList = dutyPlaceList;
	}

	public String getNewDutyPlace() {
		return newDutyPlace;
	}

	public void setNewDutyPlace(String newDutyPlace) {
		this.newDutyPlace = newDutyPlace;
	}

	public int getDeletedDutyPlaceId() {
		return deletedDutyPlaceId;
	}

	public void setDeletedDutyPlaceId(int deletedDutyPlaceId) {
		this.deletedDutyPlaceId = deletedDutyPlaceId;
	}
	

}
