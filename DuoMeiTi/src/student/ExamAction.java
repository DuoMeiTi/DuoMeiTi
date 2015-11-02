package student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.ExamOption;
import model.ExamStuOption;
import model.ExamTitle;
import model.StudentProfile;

public class ExamAction extends ActionSupport {
	private List<ExamTitle> qtitle;
	private List<List<ExamOption> > qoption = new ArrayList< List<ExamOption>>();
	private List mergeList;
	private String status;
	
	private int score;
//	public StudentProfile stuPro;
//	public ExamOption emoption;
//	int numsTitle[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
//	String letOption[] = {"A","B","C","D","E","F","G"};
	
	

	public String execute() throws Exception{
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(ExamTitle.class);
		qtitle = c.list();
		qoption.clear();
		for(int i = 0; i < qtitle.size(); i++)
		{
			Criteria c2 = session.createCriteria(ExamOption.class).add(Restrictions.eq("emTitle.emId", qtitle.get(i).getEmId()));
			qoption.add(c2.list());
		}
		Collections.reverse(qtitle);
		Collections.reverse(qoption);
		session.close();
		return SUCCESS;
	}

	public String insert() throws Exception {
		
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		int student_id = (int)ActionContext.getContext().getSession().get("student_id");
		Criteria cstu =  session.createCriteria(model.StudentProfile.class);
		List qstu = cstu.add((Restrictions.eq("id", student_id))).list();
		
		for(int k = 0; k < mergeList.size(); k++)
		{
			System.out.print(mergeList.get(k) + " ");
		}
		for(int i = 1 ; i < mergeList.size(); i += 2)
		{
			int len = Integer.parseInt((String)mergeList.get(i));
			//System.out.println("%^&*()||||" + len);
			for(int j = 0; j < len; j++)
			{
				i++;
				//System.out.println(j + "KKKKKKKKKKKKKKKKKKKKKKK" + mergeList.get(i));
				Criteria cop = session.createCriteria(ExamOption.class);
				List qop = cop.add(Restrictions.eq("emId", Integer.valueOf((String)mergeList.get(i)).intValue() )).list();
				ExamStuOption eso = new ExamStuOption();
				eso.setEmoption((model.ExamOption)qop.get(0));
				eso.setStuPro((model.StudentProfile)qstu.get(0));
				session.save(eso);
			}
		}
		
		
//		int student_id = (int)ActionContext.getContext().getSession().get("student_id");
//		String fullName = (String)ActionContext.getContext().getSession().get("fullName");
//		
//		Criteria cstu =  session.createCriteria(model.StudentProfile.class);
//		List qstu = cstu.add((Restrictions.eq("id", student_id))).list();
//		
//		
//		for(int i =0 ; i < optionIdList.size(); i++)
//		{
//			Criteria cop = session.createCriteria(ExamOption.class);
//			
//			List qop = cop.add(Restrictions.eq("emId", Integer.valueOf((String)optionIdList.get(i)).intValue() )).list();
//			ExamStuOption eso = new ExamStuOption();
//			eso.setEmoption((model.ExamOption)qop.get(0));
//			eso.setStuPro((model.StudentProfile)qstu.get(0));
//			session.save(eso);
//		}
		session.getTransaction().commit();
		session.close();
		this.countScore();
		this.status = "1";
		return SUCCESS;
	}

	public String countScore() throws Exception{
		Session session = model.Util.sessionFactory.openSession();
//		for(int i = 0; i < mergeList.size(); i++)
//		{
//			System.out.print(mergeList.get(i) + " ");
//		}
		score = 0;
		for(int i = 0; i < mergeList.size(); i++)
		{
			Criteria ct = session.createCriteria(ExamOption.class)
					.add(Restrictions.eq("emTitle.emId", Integer.parseInt((String)mergeList.get(i))))
					.add(Restrictions.eq("emCheck", "true"));
			List temp = ct.list();
			List<Integer> qOpTrue = new ArrayList();
			for(int k = 0; k < temp.size(); k++)
			{
				qOpTrue.add(((ExamOption)temp.get(k)).getEmId());
			}
			
//			for(int k = 0; k < qOpTrue.size(); k++)
//			{
//				System.out.print(qOpTrue.get(k) + " ");
//			}
			
			List<Integer> qOpStu = new ArrayList();
			i++;
		
			int nu = Integer.valueOf(mergeList.get(i).toString()).intValue();
			for(int j = 0; j < nu; j++)
			{
				++ i;
				qOpStu.add(Integer.valueOf(mergeList.get(i).toString()));
			}
			Collections.sort(qOpTrue);
			Collections.sort(qOpStu);
			boolean flag = false;
			if(qOpTrue.size() == qOpStu.size())
			{
				for(int k = 0; k < qOpTrue.size(); k++)
				{
					if(qOpTrue.get(k).equals(qOpStu.get(k)))
					{
						flag = true;
					}
					else{
						flag = false;
						break;
					}
				}
			}
			if(flag == true) score++;
//			for(int k = 0; k < qOpStu.size(); k++)
//			{
//				System.out.print(qOpStu.get(k) + " ");
//			}
		}
		session.close();
		this.status = "1";
		return SUCCESS;
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

	

	public List getMergeList() {
		return mergeList;
	}

	public void setMergeList(List mergeList) {
		this.mergeList = mergeList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	

	
	
	
	
	
	
	
}
