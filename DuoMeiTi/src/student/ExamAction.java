package student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.ExamOption;
import model.ExamStuOption;
import model.ExamStuScore;
import model.ExamTitle;
import model.StudentProfile;



public class ExamAction extends ActionSupport {
	private List<ExamTitle> qtitle;
	private List<List<ExamOption> > qoption = new ArrayList< List<ExamOption>>();
	private List<List<String> > qstuOption = new ArrayList< List<String>>();
	

	/*
	 * mergeList 表示题目列表，对于每一道题目格式为：题目ID，选项数目，各个选项 
	 */
	private List mergeList;
	private String status;
	private int score;
	private int newNum;
	private int titleId;
	
	private int opId;
	private boolean checked;
	
	
	
	private int getStudentId()
	{
		return (int)ActionContext.getContext().getSession().get("student_id");
	}
	private StudentProfile getStudent(Session session)
	{
		int student_id = getStudentId();
		
		return (StudentProfile)session.createCriteria(StudentProfile.class)		
					           .add(Restrictions.eq("id",  student_id))
					           .list()
					           .get(0);

	}


	private void calNewNum(Session session)
	{
		int student_id = getStudentId();
		Criteria c = session.createCriteria(ExamStuScore.class).add(Restrictions.eq("stuPro.id", student_id))
				.setProjection(Projections.rowCount());				
		newNum = ((Long)c.uniqueResult()).intValue();
		
		newNum ++;
		
	}

	public String execute() throws Exception
	{
		Session session = model.Util.sessionFactory.openSession();
		calNewNum(session);
		
		int student_id = getStudentId();
		StudentProfile student = getStudent(session);
		if(student.isPassed != StudentProfile.Passed)
		{
			session.close();
			
			return ActionSupport.ERROR; 
		}
		
		
		
		
		
		Criteria c = session.createCriteria(ExamTitle.class);
		qtitle = c.list();
		qoption.clear();
		qstuOption.clear();
		for(int i = 0; i < qtitle.size(); i++)
		{
			Criteria c2 = session.createCriteria(ExamOption.class).add(Restrictions.eq("emTitle.emId", qtitle.get(i).getEmId()));
			List l = c2.list();
			qoption.add(l);
			ArrayList<String> cntChecked = new ArrayList<String>(); 
			for(int j = 0; j < l.size(); ++j)
			{
				ExamOption op = (ExamOption)l.get(j);
				List tmpL = session.createCriteria(ExamStuOption.class)
						    .add(Restrictions.eq("emoption.emId", op.getEmId()))
						    .add(Restrictions.eq("esNums", newNum))
						    .add(Restrictions.eq("stuPro.id", student_id)).list();
				if(tmpL.isEmpty())
					cntChecked.add("");
				else 
					cntChecked.add("checked");
			}
			qstuOption.add(cntChecked);			
		}
		
		session.close();
		
		return SUCCESS;
	}
	
	public String checkChange() throws Exception{
		System.out.println("JFJJFJFJJ");
		System.out.println(newNum);
		System.out.println(titleId);
		System.out.println(checked);
		System.out.println(opId);
		
		int student_id = (int)ActionContext.getContext().getSession().get("student_id");
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria c = session.createCriteria(ExamStuOption.class)
				.add(Restrictions.eq("stuPro.id", student_id))
				.add(Restrictions.eq("emoption.emId", opId))
				.add(Restrictions.eq("esNums", newNum));
		List l = c.list();
		if(checked == false)
		{
			System.out.println("Flase");
			if(l.isEmpty())
			{
				this.status="这个选项之前怎么没选过";
				return SUCCESS;
			}			
			this.status="去除选项成功";
			session.delete(l.get(0));
		}
		else 
		{
			System.out.println("TTTT");
			if(!l.isEmpty())
			{
				this.status="这个选项之前已经添加了";
				return SUCCESS;
			}
			ExamStuOption op= new ExamStuOption();
			op.setEmoption((ExamOption)session.createCriteria(ExamOption.class).add(Restrictions.eq("emId", opId) ).list().get(0));
			op.setEsNums(newNum);
			op.setStuPro((StudentProfile)session.createCriteria(StudentProfile.class).add(Restrictions.eq("id", student_id) ).list().get(0));
			session.save(op);
			this.status="添加选项成功";
		}		
		session.getTransaction().commit();
		session.close();
		
		return SUCCESS;
	}
	public String submit() throws Exception {
		System.out.println("JFJFJJFJJ");
		
		Session session = model.Util.sessionFactory.openSession();
		calNewNum(session);
		System.out.println("JFJFJJFJJ");
		qtitle = session.createCriteria(ExamTitle.class).list();
		score = 0;
		System.out.println("BBBBBBBBBBB");
		for(int i = 0; i < qtitle.size(); ++ i)
		{
			ExamTitle cntTitle = (ExamTitle)qtitle.get(i);
			List cntOptions = session.createCriteria(ExamOption.class)
							 .add(Restrictions.eq("emTitle.emId",cntTitle.getEmId()))
							 .list();
			boolean isRight = true;
			for(int j = 0; j < cntOptions.size(); ++ j)
			{
				ExamOption op = (ExamOption)cntOptions.get(j);
				List checked = session.createCriteria(ExamStuOption.class)
						.add(Restrictions.eq("esNums", newNum))
						.add(Restrictions.eq("emoption.emId", op.getEmId()))
						.list();
				
				boolean ok = op.getEmCheck().equals("true") ^ !checked.isEmpty();
				if(ok)
				{
					isRight = false;
					break;
				}				
			}
			if(isRight) score ++;
		}
		System.out.println("AAAAAAAAAAAA");
		
		int student_id = getStudentId();
		
		if(score * 100 >= qtitle.size() * 80)
		{
			this.status = "您已通过考试";
			ExamStuScore stuScore = new ExamStuScore();
			stuScore.setScore(score);
			stuScore.setStuPro(getStudent(session));
			session.beginTransaction();
			session.save(stuScore);
			session.getTransaction().commit();
		}
		else 
		{
			this.status = "您还未通过考试";
		}
		
		
		
		System.out.println("SBSBSB");
		
		session.close();
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	public String insert() throws Exception {
//		
//		Session session = model.Util.sessionFactory.openSession();
//		session.beginTransaction();
//		int student_id = (int)ActionContext.getContext().getSession().get("student_id");
//		Criteria cstu =  session.createCriteria(model.StudentProfile.class);
//		List qstu = cstu.add((Restrictions.eq("id", student_id))).list();
//		
//		for(int i = 1 ; i < mergeList.size(); i += 2)
//		{
//			int len = Integer.parseInt((String)mergeList.get(i));
//			for(int j = 0; j < len; j++)
//			{
//				i++;
//				//System.out.println(j + "KKKKKKKKKKKKKKKKKKKKKKK" + mergeList.get(i));
//				Criteria cop = session.createCriteria(ExamOption.class);
//				List qop = cop.add(Restrictions.eq("emId", Integer.valueOf((String)mergeList.get(i)).intValue() )).list();
//				ExamStuOption eso = new ExamStuOption();
//				eso.setEmoption((model.ExamOption)qop.get(0));
//				eso.setStuPro((model.StudentProfile)qstu.get(0));
//				session.save(eso);
//			}
//		}
//		session.getTransaction().commit();
//		session.close();
//		this.countScore();
//		this.status = "1";
//		return SUCCESS;
//	}
//
//	public String countScore() throws Exception{
//		Session session = model.Util.sessionFactory.openSession();
//		score = 0;
//		for(int i = 0; i < mergeList.size(); i++)
//		{
//			Criteria ct = session.createCriteria(ExamOption.class)
//					.add(Restrictions.eq("emTitle.emId", Integer.parseInt((String)mergeList.get(i))))
//					.add(Restrictions.eq("emCheck", "true"));
//			List temp = ct.list();
//			List<Integer> qOpTrue = new ArrayList();
//			for(int k = 0; k < temp.size(); k++)
//			{
//				qOpTrue.add(((ExamOption)temp.get(k)).getEmId());
//			}
//			
//			List<Integer> qOpStu = new ArrayList();
//			i++;
//		
//			int nu = Integer.valueOf(mergeList.get(i).toString()).intValue();
//			for(int j = 0; j < nu; j++)
//			{
//				++ i;
//				qOpStu.add(Integer.valueOf(mergeList.get(i).toString()));
//			}
//			Collections.sort(qOpTrue);
//			Collections.sort(qOpStu);
//			boolean flag = false;
//			if(qOpTrue.size() == qOpStu.size())
//			{
//				for(int k = 0; k < qOpTrue.size(); k++)
//				{
//					if(qOpTrue.get(k).equals(qOpStu.get(k)))
//					{
//						flag = true;
//					}
//					else
//					{
//						flag = false;
//						break;
//					}
//				}
//			}
//			if(flag == true) score++;
//		}
//		session.close();
//		this.status = "1";
//		return SUCCESS;
//	}
	
	
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
	
	public int getNewNum() {
		return newNum;
	}




	public void setNewNum(int newNum) {
		this.newNum = newNum;
	}




	public int getTitleId() {
		return titleId;
	}




	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getOpId() {
		return opId;
	}

	public void setOpId(int opId) {
		this.opId = opId;
	}

	
	
	
	
	public List<List<String>> getQstuOption() {
		return qstuOption;
	}

	public void setQstuOption(List<List<String>> qstuOption) {
		this.qstuOption = qstuOption;
	}

	
	
}
