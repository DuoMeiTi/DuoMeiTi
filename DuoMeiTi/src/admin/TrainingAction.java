package admin;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import model.Training;

public class TrainingAction extends ActionSupport{
	private int trId;
	private String trContent;
	private String trStatus;
	
	public String execute() throws Exception
	{
		System.out.println("SBSBSBJSFJSKDJFJ********");
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Training.class);
//		String temp = ((Training)c.list().get(0)).getTrContent();
//		String temp2 = temp.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
//		trContent = temp2.replace("\n", "<br/>");
		trContent = ((Training)c.list().get(0)).getTrContent();
		session.close();
		return SUCCESS;
	}
	
	public String update() throws Exception
	{
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Training.class);
		Training tr = (Training)c.uniqueResult();
		tr.setTrContent(trContent);
		session.beginTransaction();
		session.update(tr);
		session.getTransaction().commit();
		session.close();
		this.trStatus = "1";
		return SUCCESS;
	}
	
	public String insert() throws Exception
	{
		Training tr = new Training();
		tr.setTrContent(trContent);
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		session.save(tr);
		session.getTransaction().commit();
		session.close();
		
		this.trStatus = "1";
		return SUCCESS;
	}

	
	
	
	
	public int getTrId() {
		return trId;
	}

	public void setTrId(int trId) {
		this.trId = trId;
	}

	public String getTrContent() {
		return trContent;
	}

	public void setTrContent(String trContent) {
		this.trContent = trContent;
	}

	public String getTrStatus() {
		return trStatus;
	}

	public void setTrStatus(String trStatus) {
		this.trStatus = trStatus;
	}
}
