package student;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import model.Training;

public class TrainAction extends ActionSupport{
	
	private String trContent;
	
	public String execute() throws Exception
	{
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Training.class);
		String temp = ((Training)c.list().get(0)).getTrContent();
		String temp2 = temp.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		String temp3 = temp2.replace(" ", "&nbsp;");
		trContent = temp3.replace("\n", "<br/>");
		System.out.println(trContent);
		session.close();
		return SUCCESS;
	}

	
	
	
	public String getTrContent() {
		return trContent;
	}

	public void setTrContent(String trContent) {
		this.trContent = trContent;
	}

}
