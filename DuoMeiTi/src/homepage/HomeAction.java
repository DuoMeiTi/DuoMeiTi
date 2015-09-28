package homepage;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.User;
public class HomeAction 
{
	public List check_list;
	public List notice_list;
	public List repair_list;
	public List file_path_list;
	
	public String execute() throws Exception
	{ 
		

		Session session = model.Util.sessionFactory.openSession();
		
		check_list = session.createCriteria(model.CheckRecord.class).list();
		notice_list = session.createCriteria(model.Notice.class).list();
		

		repair_list = session.createCriteria(model.RepairRecord.class).list();
		
		Collections.reverse(check_list);
		Collections.reverse(notice_list);
		Collections.reverse(repair_list);
		
		
		System.out.println("&&&&&&&&&&");

		session.close();
		return ActionSupport.SUCCESS;
	}
	
	public String classroomFile() throws Exception
	{
		Session session = model.Util.sessionFactory.openSession();
		file_path_list = session.createCriteria(model.ClassroomFilePath.class).list();
		session.close();
		return ActionSupport.SUCCESS;
	}

	
	
}










