//package student;
//
//import java.sql.Date;
//import java.sql.Timestamp;
//
//import model.RepairRecord;
//import model.Repertory;
//import model.User;
//
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.criterion.Restrictions;
//
//import com.opensymphony.xwork2.ActionContext;
//import com.opensymphony.xwork2.ActionSupport;

//public class RepairRecordAction extends ActionSupport {
//	
//	public int deviceId;
//	
//	public String repairdetail;
//	
//	public String savestatus;
//	
//	public String repairrecordsave() {
//		Session session = null;
//		try	{
//			int user_id = (int) ActionContext.getContext().getSession().get("user_id");
//			session = model.Util.sessionFactory.openSession();
//			Criteria user_criteria = session.createCriteria(User.class);
//			user_criteria.add(Restrictions.eq("id", user_id));
//			User repairman = (User) user_criteria.uniqueResult();
//			
//			Timestamp repairdate = new Timestamp(new java.util.Date().getTime());
//			
//			Criteria repertory_criteria = session.createCriteria(Repertory.class);
//			repertory_criteria.add(Restrictions.eq("rtId", deviceId));
//			Repertory device = (Repertory) repertory_criteria.uniqueResult();
//			
//			RepairRecord repairrecord = new RepairRecord();
//			repairrecord.setDevice(device);
//			repairrecord.setRepairdate(repairdate);
//			repairrecord.setRepairdetail(repairdetail);
//			repairrecord.setRepairman(repairman);
//			
//			session.beginTransaction();
//			session.save(repairrecord);
//			session.getTransaction().commit();
//			
//			this.savestatus = "success";
//		} catch(Exception e)	{
//			this.savestatus = "fail";
//			e.printStackTrace();
//		} finally {
//			if(session != null) session.close();
//		}
//		return SUCCESS;
//	}
//
//	
//	
//	
//	
//	
//	
//	
//	
//	public int getDeviceId() {
//		return deviceId;
//	}
//
//	public void setDeviceId(int deviceId) {
//		this.deviceId = deviceId;
//	}
//
//	public String getRepairdetail() {
//		return repairdetail;
//	}
//
//	public void setRepairdetail(String repairdetail) {
//		this.repairdetail = repairdetail;
//	}
//
//	public String getSavestatus() {
//		return savestatus;
//	}
//
//	public void setSavestatus(String savestatus) {
//		this.savestatus = savestatus;
//	}
//}
