package admin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.ExamOption;
import model.ExamTitle;
import model.Training;

public class TrainingAction extends ActionSupport{
	private int trId;
	private String trContent;
	private String trStatus;
	
	private List<ExamTitle> qtitle;
	private List<List<ExamOption> > qoption = new ArrayList<List<ExamOption>>();
	
	private String emTitle;
	List optionList = new ArrayList();
	private String emTrue;
	
	public String execute() throws Exception
	{
		Session session = model.Util.sessionFactory.openSession();
		//info
		Criteria c = session.createCriteria(Training.class);
		trContent = ((Training)c.list().get(0)).getTrContent();
		//exam
		Criteria ctitle = session.createCriteria(ExamTitle.class);
		qtitle = ctitle.list();
		
		qoption.clear();
		for(int i = 0; i < qtitle.size(); i++)
		{
			Criteria coption = session.createCriteria(ExamOption.class)
							  .add(Restrictions.eq("emTitle.emId", qtitle.get(i).getEmId()  ));
			qoption.add(coption.list());
		}
		
		session.close();
		return SUCCESS;
	}
	
	public String examInsert() throws Exception
	{
		System.out.println(emTitle + " | " + optionList + " | " + emTrue + "|");
//		ExamTitle et = new ExamTitle();
		trStatus = "1";
		return SUCCESS;
	}
	
	public String infoUpdate() throws Exception
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
	
	public String infoInsert() throws Exception
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

	public List<ExamTitle> getQtitle() {
		return qtitle;
	}

	public void setQtitle(List<ExamTitle> qtitle) {
		this.qtitle = qtitle;
	}

	public List<List<ExamOption>> getQoption() {
		return qoption;
	}

	public void setQoption(List<List<ExamOption>> qoption) {
		this.qoption = qoption;
	}

	public String getEmTitle() {
		return emTitle;
	}

	public void setEmTitle(String emTitle) {
		this.emTitle = emTitle;
	}

	public List<ExamOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<ExamOption> optionList) {
		this.optionList = optionList;
	}

	public String getEmTrue() {
		return emTrue;
	}

	public void setEmTrue(String emTrue) {
		this.emTrue = emTrue;
	}
	
}
