package homepage;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.StudentProfile;
import model.User;
import util.Const;

import java.io.File;

public class StudentAction {
	
	private String collegeSelect[];
	private String sexSelect[];
	private String statusSelect[];
	private String username;
	private String password;
	private String register_status;
	
	
	
	public String getRegister_status() {
		return register_status;
	}

	public void setRegister_status(String register_status) {
		this.register_status = register_status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getStatusSelect() {
		return statusSelect;
	}

	public void setStatusSelect(String[] statusSelect) {
		this.statusSelect = statusSelect;
	}

	public String[] getSexSelect() {
		return sexSelect;
	}

	public void setSexSelect(String[] sexSelect) {
		this.sexSelect = sexSelect;
	}

	public String[] getCollegeSelect() {
		return collegeSelect;
	}

	public void setCollegeSelect(String[] collegeSelect) {
		this.collegeSelect = collegeSelect;
	}
	
	public String studentRegister(){
		collegeSelect=Const.collegeSelect;
		sexSelect=Const.sexSelect;
		statusSelect=Const.statusSelect;
		return ActionSupport.SUCCESS;
	}
	
	public String studentRegisterSave() throws Exception
	{
		if(username == null || password == null)
		{
			this.register_status = "error: username or password is null";
			return ActionSupport.SUCCESS;
		}
		if(username.equals("") || password.equals(""))
		{
			this.register_status = "1";
			return ActionSupport.SUCCESS;
		}		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(StudentProfile.class).add(Restrictions.eq("username", username));
		List ul = q.list();
		if(!ul.isEmpty())
		{
			this.register_status = "2";
		}
		else
		{
			User um = new User();
			um.setUsername(username);
			um.setPassword(password);
			
			
			session.beginTransaction();
			session.save(um);
			
			session.getTransaction().commit();
			this.register_status = "0";
	//		this.user_id = um.getId();
	//		this.added_user_html = util.Util.fileToString("/jsp/homepage/widgets/added_user.html");
		}
		session.close();
		return ActionSupport.SUCCESS;
	}
	

}
