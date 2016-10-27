package student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.StudentProfile;
import model.User;
import util.Const;
import util.FileUploadBaseAction;

public class InformationAction extends FileUploadBaseAction{
	
	
	StudentProfile execute_currentStudentProfile;
	public String execute() throws Exception
	{	
		Session session = model.Util.sessionFactory.openSession();
		StudentProfile currentStudentProfile = util.Util.getUniqueResultWithOneEqualRestriction(
				session, StudentProfile.class, "user.username", ActionContext.getContext().getSession().get("username"));		
		session.close();
		execute_currentStudentProfile = currentStudentProfile;
		return ActionSupport.SUCCESS;
	}
	
	
	StudentProfile modify_newStudentProfile;
	public String modify() throws Exception
	{	
		
		StudentProfile newStudentProfile = modify_newStudentProfile;
		
		Session session = model.Util.sessionFactory.openSession();
		
		
		StudentProfile currentStudentProfile = util.Util.getUniqueResultWithOneEqualRestriction(
				session, StudentProfile.class, "user.username", ActionContext.getContext().getSession().get("username"));
		
		String[] studentProfileList = {"studentId", "idCard", "college", "bankCard", "entryTime",
									    "user.fullName", "user.sex", "user.remark", "user.phoneNumber"};
		
		util.Util.copyWithSpecificFields(
				newStudentProfile, 
				currentStudentProfile,
				studentProfileList   );
		
		if (file != null) // file没接收到的原因可能是jsp页面里面的input file的属性名不是file 
	    {
			util.Util.setUserProfilePhoto(currentStudentProfile.user, file, fileFileName);
	    }		
		session.beginTransaction();
		session.update(currentStudentProfile);
		session.getTransaction().commit();
		session.close();
		
		return ActionSupport.SUCCESS;
	}
	
	
	public StudentProfile getExecute_currentStudentProfile() {
		return execute_currentStudentProfile;
	}
	public void setExecute_currentStudentProfile(StudentProfile execute_currentStudentProfile) {
		this.execute_currentStudentProfile = execute_currentStudentProfile;
	}
	public StudentProfile getModify_newStudentProfile() {
		return modify_newStudentProfile;
	}
	public void setModify_newStudentProfile(StudentProfile modify_newStudentProfile) {
		this.modify_newStudentProfile = modify_newStudentProfile;
	}
	
	
	
	
}

