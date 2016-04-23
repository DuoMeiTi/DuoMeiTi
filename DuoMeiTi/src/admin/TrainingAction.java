package admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
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
	
	private int emId;
	private String emTitle;
	private List optionList;
	private List checkList;
	private String add_exam_html;
	private String exam_table;

	private String emTrue;
	
	public String execute() throws Exception
	{
		Session session = model.Util.sessionFactory.openSession();
		//info
		List L = session.createCriteria(Training.class).list();
		
		if(L.size() == 0)
		{
			trContent = "";
			Training tr = new Training();
			tr.setTrContent("");
			session.beginTransaction();
			
			
			session.save(tr);
			session.getTransaction().commit();
		}
		else 
		trContent = ((Training)L.get(0)).getTrContent();
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
//		Collections.reverse(qtitle);
//		Collections.reverse(qoption);
		session.close();
		return SUCCESS;
	}
	
	
	
	
	
	
	public String examEdit() throws Exception{		

		Session session = model.Util.sessionFactory.openSession();
		
		Criteria qt = session.createCriteria(ExamTitle.class).add(Restrictions.eq("emId", emId));
		
		qtitle = qt.list();
		if(qtitle.isEmpty())
		{
			this.trStatus = "0";
		}
		else
		{
			System.out.println("$$$$$$$$$$$$" + emId);
			
			session.beginTransaction();
			
			ExamTitle et = (ExamTitle)qtitle.get(0);
			et.setEmTitle(emTitle);
			session.update(et);
			Criteria qo = session.createCriteria(ExamOption.class).add(Restrictions.eq("emTitle.emId", emId));
			List<ExamOption> oldOptionList = qo.list();
			
			while(oldOptionList.size() > optionList.size()) 
			{
				int last = oldOptionList.size() - 1;
				
				ExamOption cntEmOp = oldOptionList.get(last);
				List<model.ExamStuOption> studentSelectOptionList =
						(List<model.ExamStuOption>) 
				session.createCriteria(model.ExamStuOption.class)
						.add(Restrictions.eq("emoption.id", cntEmOp.emId))
						.list();
				
				for(model.ExamStuOption i: studentSelectOptionList)
					session.delete(i);
				
				session.delete(oldOptionList.get(last));
				oldOptionList.remove(last);	
			}
			while(oldOptionList.size() < optionList.size()) 
			{				
				ExamOption eo = new ExamOption();
				eo.setEmTitle(qtitle.get(0));
				oldOptionList.add(eo);
				session.save(eo);
			}
			for(int i = 0; i < oldOptionList.size(); ++ i)
			{
				ExamOption oldeo = (ExamOption)oldOptionList.get(i);
				
				oldeo.setEmCheck(checkList.get(i).toString());
				oldeo.setEmOption(optionList.get(i).toString());
				
				session.update(oldeo);
			}
			
//			optionList
//			for(int i = 0; i < qoList.size(); i++)
//			{
//				session.delete(qoList.get(i));
//			}
//			session.delete(qtitle.get(0));
			session.getTransaction().commit();
				
			Criteria ctitle = session.createCriteria(ExamTitle.class);
			qtitle = ctitle.list();
			qoption.clear();
			for(int i = 0; i < qtitle.size(); i++)
			{
				Criteria coption = session.createCriteria(ExamOption.class)
								  .add(Restrictions.eq("emTitle.emId", qtitle.get(i).getEmId()  ));
				qoption.add(coption.list());
			}
//			Collections.reverse(qtitle);
//			Collections.reverse(qoption);
			exam_table = util.Util.getJspOutput("/jsp/admin/widgets/examTable.jsp");
			this.trStatus = "1";
		}
		session.close();
		
		
		System.out.println("FJFJJFJ*********");
		return SUCCESS;


	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String examInsert() throws Exception
	{
		System.out.println(emTitle + " | " + optionList + " | " + checkList + "|");
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		
		ExamTitle et = new ExamTitle();
		et.setEmTitle(emTitle);
		session.save(et);
		
		for(int i = 0; i < optionList.size(); i++)
		{
			ExamOption eo = new ExamOption();
			eo.setEmTitle(et);
			eo.setEmOption(optionList.get(i).toString());
			eo.setEmCheck(checkList.get(i).toString());
//			System.out.println("YES:INSERT");
//			System.out.println(checkList.get(i).toString());
//			System.out.println(checkList.get(i).getClass());
			
			
			
			session.save(eo);
		}
		
		session.getTransaction().commit();
		
		Criteria ctitle = session.createCriteria(ExamTitle.class);
		qtitle = ctitle.list();
		qoption.clear();
		for(int i = 0; i < qtitle.size(); i++)
		{
			Criteria coption = session.createCriteria(ExamOption.class)
							  .add(Restrictions.eq("emTitle.emId", qtitle.get(i).getEmId()  ));
			qoption.add(coption.list());
		}
//		Collections.reverse(qtitle);
//		Collections.reverse(qoption);
		
		session.close();
//		this.execute();
		exam_table = util.Util.getJspOutput("/jsp/admin/widgets/examTable.jsp");
		trStatus = "1";
		return SUCCESS;
	}
	
	public String examDelete() throws Exception
	{
		System.out.println("$$$$$$$$$$$$" + emId);
		Session session = model.Util.sessionFactory.openSession();
		Criteria qt = session.createCriteria(ExamTitle.class).add(Restrictions.eq("emId", emId));
		qtitle = qt.list();
		System.out.println(qtitle);
		if(qtitle.isEmpty())
		{
			this.trStatus = "0";
		}
		else
		{
			session.beginTransaction();
			Criteria qo = session.createCriteria(ExamOption.class).add(Restrictions.eq("emTitle.emId", emId));
			List<ExamOption> qoList = qo.list();
			for(int i = 0; i < qoList.size(); i++)
			{
				List<model.ExamStuOption> studentSelectOptionList =
						(List<model.ExamStuOption>) 
						session.createCriteria(model.ExamStuOption.class)
								.add(Restrictions.eq("emoption.id", qoList.get(i).emId))
								.list();
				for(model.ExamStuOption eso: studentSelectOptionList)
					session.delete(eso);
				session.delete(qoList.get(i));
			}
			session.delete(qtitle.get(0));
			session.getTransaction().commit();
			this.trStatus = "1";
		}
		session.close();
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

	public List getOptionList() {
		return optionList;
	}

	public void setOptionList(List optionList) {
		this.optionList = optionList;
	}

	public String getEmTrue() {
		return emTrue;
	}

	public void setEmTrue(String emTrue) {
		this.emTrue = emTrue;
	}

	public List getCheckList() {
		return checkList;
	}

	public void setCheckList(List checkList) {
		this.checkList = checkList;
	}

	public String getAdd_exam_html() {
		return add_exam_html;
	}

	public void setAdd_exam_html(String add_exam_html) {
		this.add_exam_html = add_exam_html;
	}

	public String getExam_table() {
		return exam_table;
	}

	public void setExam_table(String exam_table) {
		this.exam_table = exam_table;
	}

	public int getEmId() {
		return emId;
	}

	public void setEmId(int emId) {
		this.emId = emId;
	}

	

	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
