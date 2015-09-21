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
public class HomeAction 
{
	
	public List file_path_list;
	
	public String execute() throws Exception
	{
		System.out.println("home page");
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(model.EgFilePathSave.class);		
		file_path_list = q.list();
		return ActionSupport.SUCCESS;
	}

	public List getFile_path_list() {
		return file_path_list;
	}

	public void setFile_path_list(List file_path_list) {
		this.file_path_list = file_path_list;
	}
	
	
}










