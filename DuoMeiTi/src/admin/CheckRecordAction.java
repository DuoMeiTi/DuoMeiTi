//package admin;
//
//import java.sql.Date;
//
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.criterion.Restrictions;
//
//import model.CheckRecord;
//import model.Classroom;
//import model.StudentProfile;
//import model.User;
//
//import com.opensymphony.xwork2.ActionContext;
//import com.opensymphony.xwork2.ActionSupport;
//
//public class CheckRecordAction extends ActionSupport {
//	
//	public String checkdetail;
//	
//	public String classroom_report;
//	
//	public String classroomid;
//	
//	public String savestatus;
//
//	public String checkrecordsave() {
//		Session session = null;
//		try	{
//			int user_id = (int) ActionContext.getContext().getSession().get("user_id");
//			session = model.Util.sessionFactory.openSession();
//			Criteria user_criteria = session.createCriteria(User.class);
//			user_criteria.add(Restrictions.eq("id", user_id));
//			User user = (User) user_criteria.uniqueResult();
////System.out.println(stu.getUser().getUsername());
//			
//			Date checkdate = new Date(new java.util.Date().getTime());
//			System.out.println("好了没有啊"+classroomid);
//			/*int classroom_id = (int) ActionContext.getContext().getSession().get("classroom_id");*/
//			Criteria classroom_criteria = session.createCriteria(Classroom.class);
//			classroom_criteria.add(Restrictions.eq("id", Integer.parseInt(classroomid)));
//			Classroom classroom = (Classroom) classroom_criteria.uniqueResult();
//			
//			CheckRecord checkrecord = new CheckRecord();
//			checkrecord.setCheckdate(checkdate);
//			checkrecord.setCheckdetail(checkdetail);
//			checkrecord.setCheckman(user);
//			checkrecord.setClassroom(classroom);
//			
//			session.beginTransaction();
//			session.save(checkrecord);
//			session.getTransaction().commit();
//			
//			classroom_report = util.Util.getJspOutput("/jsp/classroom/classroom_report.jsp");
//			
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
//	
//	public String getClassroom_report() {
//		return classroom_report;
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
//
//	public void setClassroom_report(String classroom_report) {
//		this.classroom_report = classroom_report;
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
//
//	public String getClassroomid() {
//		return classroomid;
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
//
//	public void setClassroomid(String classroomid) {
//		this.classroomid = classroomid;
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
//
//	public void setSavestatus(String savestatus) {
//		this.savestatus = savestatus;
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
//
//	public String getCheckdetail() {
//		return checkdetail;
//	}
//
//	public void setCheckdetail(String checkdetail) {
//		this.checkdetail = checkdetail;
//	}
//
//	public String getSavestatus() {
//		return savestatus;
//	}
//
//	public void setSave_status(String savestatus) {
//		this.savestatus = savestatus;
//	}
//}
