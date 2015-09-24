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
	
	public String execute() throws Exception
	{ 
		
//
//	 Criteria q =
//session.createCriteria(User.class).add(Restrictions.eq("username",
//username));
		Session session = model.Util.sessionFactory.openSession();
		
		check_list = session.createCriteria(model.CheckRecord.class).list();
		model.CheckRecord cr = (model.CheckRecord)check_list.get(0);
		
		
		System.out.println("&&&&&&&&&&");
		System.out.println(cr.classroom.classroom_num);
//		System.out.println(cr.checkman.user.username);
		
		
		
		
		return ActionSupport.SUCCESS;
	}


	
	
}










