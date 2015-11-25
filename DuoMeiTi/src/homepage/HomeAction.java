package homepage;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.User;
import util.PageGetBaseAction;


public class HomeAction extends PageGetBaseAction
{
	public List check_list;
	public List notice_list;
	public List repair_list;
	public List file_path_list;
	public String file_path_html;
	public List deviceReplaceList;
	static
	{
		Session session = model.Util.sessionFactory.openSession();		
		
		
		List L = session.createCriteria(model.AdminProfile.class).list();
		if(L.size() == 0)
		{
			User user = new User();
			user.setUsername("admin");
			user.setPassword("admin");
			user.setPassword("full_admin");
			
			model.AdminProfile ap = new model.AdminProfile();
			ap.setUser(user);
			
			session.beginTransaction();
			session.save(user);
			session.save(ap);
			session.getTransaction().commit();
		}
		
		
		
		
		
		session.close();
		
	}

	public String execute() throws Exception
	{ 
		System.out.println("******************");
		System.out.println(util.Util.RootPath);
		Session session = model.Util.sessionFactory.openSession();
		
		final int MaxRes = 8;
		check_list = session.createCriteria(model.CheckRecord.class)
							.setMaxResults(MaxRes)
							.addOrder(Order.desc("id"))
							.list();
		notice_list = session.createCriteria(model.Notice.class)
							 .setMaxResults(MaxRes)
							 .addOrder(Order.desc("id"))
							 .list();
		repair_list = session.createCriteria(model.RepairRecord.class)
							 .setMaxResults(MaxRes)
							 .addOrder(Order.desc("id"))
							 .list();
		
		java.util.Date now = new java.util.Date();
		java.sql.Date sql_now = new java.sql.Date(now.getTime());		
		deviceReplaceList = session.createCriteria(model.Repertory.class)
						.add(Restrictions.le("rtDeadlineData", sql_now))
						.add(Restrictions.eq("rtDeviceStatus", "教室"))
						.setMaxResults(MaxRes)
						.addOrder(Order.desc("id"))
						.list();
		
//		Collections.reverse(check_list);
//		Collections.reverse(notice_list);
//		Collections.reverse(repair_list);
		
		
		System.out.println("&&&&&&&&&&");

		session.close();
		return ActionSupport.SUCCESS;
	}
	


	public String resourceFile() throws Exception
	{	
		Session session = model.Util.sessionFactory.openSession();
		
		Criteria q = session.createCriteria(model.ResourceFilePath.class);
		
		file_path_list = this.makeCurrentPageList(q, 10); // 根据代表总体的Criteria 获取当前页元素的List，这个效率高，应尽量使用这个
//		file_path_list = this.makeCurrentPageList(q.list(), 10); //根据代表总体的List 获取当前页元素的List
		
		
		session.close();
		
		if(this.getIsAjaxTransmission()) // 这是ajax 传输
		{
			file_path_html = util.Util.getJspOutput("/jsp/admin/HomepageModify/ResourceFileTable.jsp");				
			return "getPage";
		}
		return ActionSupport.SUCCESS;
	}

	
	
	
	
	
	
	
	public List getCheck_list() {
		return check_list;
	}

	public void setCheck_list(List check_list) {
		this.check_list = check_list;
	}

	public List getNotice_list() {
		return notice_list;
	}

	public void setNotice_list(List notice_list) {
		this.notice_list = notice_list;
	}

	public List getRepair_list() {
		return repair_list;
	}

	public void setRepair_list(List repair_list) {
		this.repair_list = repair_list;
	}

	public List getFile_path_list() {
		return file_path_list;
	}

	public void setFile_path_list(List file_path_list) {
		this.file_path_list = file_path_list;
	}

	public String getFile_path_html() {
		return file_path_html;
	}

	public void setFile_path_html(String file_path_html) {
		this.file_path_html = file_path_html;
	}



	public List getDeviceReplaceList() {
		return deviceReplaceList;
	}



	public void setDeviceReplaceList(List deviceReplaceList) {
		this.deviceReplaceList = deviceReplaceList;
	}
	
	
	
	
}










