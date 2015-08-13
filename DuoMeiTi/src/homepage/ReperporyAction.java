package homepage;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import model.RepertoryModel;

public class ReperporyAction extends ActionSupport{
	private int id;
	private String type;
	private String number;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String repertory() throws Exception{
		RepertoryModel rt = new RepertoryModel();
		rt.setNumber(number);
		rt.setType(type);
		
		Session session = model.Util.session;
		session.beginTransaction();
		session.save(rt);
		session.getTransaction().commit();
		session.close();
		
		System.out.println(number);
		System.out.println(type);
		
		return "success";
	}
}
