package student;

import java.sql.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.CheckRecord;
import model.Classroom;
import model.StudentProfile;
import model.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CheckRecordAction extends ActionSupport {
	
	public String checkdetail;
	
	public String savestatus;

	public String checkrecordsave() {
		//System.out.println("execute");

		Session session = null;
		try	{
			//System.out.println("execute");
			int user_id = (int) ActionContext.getContext().getSession().get("user_id");
			session = model.Util.sessionFactory.openSession();
			Criteria user_criteria = session.createCriteria(User.class);
			user_criteria.add(Restrictions.eq("id", user_id));
			User user = (User) user_criteria.uniqueResult();
//System.out.println(stu.getUser().getUsername());
			
			Date checkdate = new Date(new java.util.Date().getTime());
			
			int classroom_id = (int) ActionContext.getContext().getSession().get("classroom_id");
			Criteria classroom_criteria = session.createCriteria(Classroom.class);
			classroom_criteria.add(Restrictions.eq("id", classroom_id));
			Classroom classroom = (Classroom) classroom_criteria.uniqueResult();
			
			CheckRecord checkrecord = new CheckRecord();
			checkrecord.setCheckdate(checkdate);
			checkrecord.setCheckdetail(checkdetail);
			checkrecord.setCheckman(user);
			checkrecord.setClassroom(classroom);
			
			session.beginTransaction();
			session.save(checkrecord);
			session.getTransaction().commit();
			
			this.savestatus = "success";
		} catch(Exception e)	{
			this.savestatus = "fail";
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return SUCCESS;
	}

	public String getCheckdetail() {
		return checkdetail;
	}

	public void setCheckdetail(String checkdetail) {
		this.checkdetail = checkdetail;
	}

	public String getSavestatus() {
		return savestatus;
	}

	public void setSavestatus(String savestatus) {
		this.savestatus = savestatus;
	}
}
