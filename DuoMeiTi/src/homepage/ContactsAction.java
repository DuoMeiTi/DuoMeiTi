package homepage;
import java.util.Collections;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.Contacts;
import model.StudentProfile;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
public class ContactsAction extends util.PageGetBaseAction {
	public String username;
	public String telnumber;
	public int id;
	public List<Contacts> contacts;
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


	public List<Contacts> getContacts() {
		return contacts;
	}


	public void setContacts(List<Contacts> contacts) {
		this.contacts = contacts;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

    //建立数据库查询
	public String wrContacts() throws Exception {
		/*System.out.println(username);
		System.out.println(telnumber);
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Contacts.class);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		contacts = c.list();
		Collections.reverse(contacts);
		session.close();*/
		
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
//	//将前台传入的姓名和电话数据写入数据库
//	public String addContacts(){
//		System.out.println("add_contacts()");
//		Session session = model.Util.sessionFactory.openSession();
//		Contacts contacts3 = new Contacts();
//		contacts3.setUsername(username);
//		contacts3.setTelnumber(telnumber);
//		session.beginTransaction();
//		session.save(contacts3);
//		session.getTransaction().commit();
//		status ="ok";
//		return ActionSupport.SUCCESS;
//	}
//    //定向删除数据库中的某条数据
//	public String deleteContacts(){
//		System.out.println("delete_contacts()");
//		Session session = model.Util.sessionFactory.openSession();
//		Criteria q = session.createCriteria(Contacts.class).add(Restrictions.eq("id", id));
//		List paramList = q.list();
//		if (paramList.isEmpty()){
//			status = "1";//删除的数据不存在，删除失败
//		}
//		else{
//			session.beginTransaction();
//			session.delete(paramList.get(0));
//			session.getTransaction().commit();
//			status = "0";//删除成功
//		}
//		return ActionSupport.SUCCESS;
//	}
}
