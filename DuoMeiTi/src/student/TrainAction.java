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
		String temp ="";
		List L = c.list();
		if(L.size() != 0)
			temp = ((Training)L.get(0)).getTrContent();
		temp = temp.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		temp = temp.replace(" ", "&nbsp;");
		trContent = temp.replace("\n", "<br/>");
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
