package homepage;
import java.util.Collections;
import java.util.List;
import java.sql.Date;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.Contacts;
import model.Notice;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
public class ContactsAction extends ActionSupport {
	public String username;
	public String telnumber;
	public int id;
	public List<Contacts> contacts;
	public Contacts contacts2;
	public String status;
	
    
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


	public Contacts getContacts2() {
		return contacts2;
	}


	public void setContacts2(Contacts contacts2) {
		this.contacts2 = contacts2;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String wrContacts() throws Exception {
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Contacts.class);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		contacts = c.list();
		Collections.reverse(contacts);
		session.close();
		return ActionSupport.SUCCESS;
	}
	
	public String add_contacts(){
		System.out.println("add_contacts()");
		Session session = model.Util.sessionFactory.openSession();
		Contacts contacts3 = new Contacts();
		contacts3.setUsername(username);
		contacts3.setTelnumber(telnumber);
		session.beginTransaction();
		session.save(contacts3);
		session.getTransaction().commit();
		status ="ok";
		return ActionSupport.SUCCESS;
	}
    
	public String delete_contacts(){
		System.out.println("delete_contacts()");
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(Contacts.class).add(Restrictions.eq("id", id));
		List paramList = q.list();
		if (paramList.isEmpty()){
			status = "1";//删除的数据不存在，删除失败
		}
		else{
			session.beginTransaction();
			session.delete(paramList.get(0));
			session.getTransaction().commit();
			status = "0";//删除成功
		}
		return ActionSupport.SUCCESS;
	}
}
