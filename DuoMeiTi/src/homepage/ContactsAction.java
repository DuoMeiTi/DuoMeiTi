package homepage;
import java.util.Collections;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
//import model.Contacts;
import model.StudentProfile;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
public class ContactsAction extends util.PageGetBaseAction {
	public String username;
	public String telnumber;
	public int id;
//	public List<Contacts> contacts;
	public String status;
	public List contacts_list;
	public String contacts_list_html;
    
	
	public String getContacts_list_html() {
		return contacts_list_html;
	}


	public void setContacts_list_html(String contacts_list_html) {
		this.contacts_list_html = contacts_list_html;
	}


	public List getContacts_list() {
		return contacts_list;
	}


	public void setContacts_list(List contacts_list) {
		this.contacts_list = contacts_list;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getTelnumber() {
		return telnumber;
	}


	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


//	public List<Contacts> getContacts() {
//		return contacts;
//	}
//
//
//	public void setContacts(List<Contacts> contacts) {
//		this.contacts = contacts;
//	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

    //建立数据库查询
	public String wrContacts() throws Exception {
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(model.StudentProfile.class)
				.add(Restrictions.eq("isPassed", model.StudentProfile.Passed))
				.add(Restrictions.not(Restrictions.eq("isUpgradePrivilege", model.StudentProfile.DepartureStudent)))
				;
		contacts_list=q.list();
		session.close();
		
//		if(this.getIsAjaxTransmission())
//		{
//			contacts_list_html =util.Util.getJspOutput("/jsp/homepage/widgets/contactsTable.jsp");
//			return "getPage";
//		}
		return ActionSupport.SUCCESS;
	}
	
}
