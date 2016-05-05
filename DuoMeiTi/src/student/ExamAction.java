package student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

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
	

	
	// 所有考试题目的题干列表
	ArrayList<ExamTitle> examTitleList;
	
	//所有题目的选项列表：
	ArrayList<ArrayList<ExamOption>> examOptionList ;
	
	// 当前学生选择的选项列表
	ArrayList<ArrayList<Boolean>> studentOptoinList;
	
	

	private String status;


	private int opId;
	private boolean checked;
	
	
	public char intToChar(int i)
	{
		char ch = (char)(i + 'A');
		return ch;

	}
	
	
	private int getStudentId()
	{
		return (int)ActionContext.getContext().getSession().get("student_id");
		
	}
	private StudentProfile getStudent(Session session)
	{
		int student_id = getStudentId();
		return (StudentProfile)session.createCriteria(model.StudentProfile.class)
		.add(Restrictions.eq("id", student_id))
		.list()
		.get(0);
	}
	
	private static  boolean isPassExam(int studentScore, int examTitleNum)
	{
		return studentScore * 100 >= examTitleNum * 80;
	}


	public String execute() throws Exception
	{		


		
		try{
		
		
		Session s = model.Util.sessionFactory.openSession();
		StudentProfile sp = getStudent(s);
		if(sp.isPassed != StudentProfile.Passed)
		{
			s.close();
			return ActionSupport.ERROR;
		}
		System.out.println("SSSSSSSSSSSss");
		int student_id = getStudentId();
		
		examTitleList = (ArrayList<model.ExamTitle>) s.createCriteria(ExamTitle.class).list();
		
		List<ExamStuScore> stuScoreList = (List<ExamStuScore>)
									s.createCriteria(model.ExamStuScore.class)
									.add(Restrictions.eq("stuPro.id", student_id))
									.list();
		
		
		
		
		
		
		examOptionList =  new ArrayList< ArrayList<ExamOption>>();
		studentOptoinList = new ArrayList< ArrayList<Boolean>>();
		
//		s.beginTransaction();
		for(int i = 0; i < examTitleList.size(); i++)
		{
			ArrayList<ExamOption> L = (ArrayList<ExamOption>)
					s.createCriteria(ExamOption.class)
					.add(Restrictions.eq("emTitle.emId", examTitleList.get(i).emId))
					.list();			
			examOptionList.add( L);
			
			ArrayList<Boolean> cntChecked = new ArrayList<Boolean>(); 
			for(int j = 0; j < L.size(); ++j)
			{
				ExamOption op = (ExamOption)L.get(j);
				List tmpL = s.createCriteria(ExamStuOption.class)
						    .add(Restrictions.eq("emoption.emId", op.emId))
						    .add(Restrictions.eq("stuPro.id", student_id))
						    .list();
				if(tmpL.isEmpty())
					cntChecked.add(false);
				else 
					cntChecked.add(true);
			}
			studentOptoinList.add(cntChecked);
		}	
		
//		s.getTransaction().commit();
			
		s.close();
		
		System.out.println(stuScoreList);
		
		int stuScoreListSize = stuScoreList.size();
		if(!stuScoreList.isEmpty() 
				&& isPassExam(stuScoreList.get(stuScoreListSize - 1).score, examTitleList.size()) 
				)
		{
			
			this.status = "0您答对了" + stuScoreList.get(stuScoreListSize - 1).score + "道题目，您已通过考试";			
			
		}
		else 
		{
			this.status = "1";
		}
		
		
		
		}catch(Exception e){ // catch
			e.printStackTrace();
		}
		
		
		
		return SUCCESS;
	}
	
	public String checkChange() throws Exception{		
		
		try{
		
		int student_id = (int)ActionContext.getContext().getSession().get("student_id");
		
		Session s = model.Util.sessionFactory.openSession();
		s.beginTransaction();
		
		List l = s.createCriteria(ExamStuOption.class)
				.add(Restrictions.eq("stuPro.id", student_id))
				.add(Restrictions.eq("emoption.emId", opId))
				.list();

		if(checked == false)
		{
			System.out.println("Flase");
			if(l.isEmpty())
			{
				this.status="这个选项之前怎么没选过";
				
			}	
			else 
			{
				this.status="去除选项成功";
				s.delete(l.get(0));
			}
			
		}
		else 
		{
			if(!l.isEmpty())
			{
				this.status="这个选项之前已经添加了";
			}
			else 
			{
				ExamStuOption op= new ExamStuOption();
				op.setEmoption((ExamOption)s.createCriteria(ExamOption.class).add(Restrictions.eq("emId", opId) ).list().get(0));
				op.setStuPro(getStudent(s));
				s.save(op);
				this.status="添加选项成功";
			}
			
		}
		s.getTransaction().commit();
		s.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String submit() throws Exception {
		
		System.out.println("JFJFJJFJJ");
		
		Session s = model.Util.sessionFactory.openSession();
		examTitleList = (ArrayList<ExamTitle>)s.createCriteria(ExamTitle.class).list();
		
		int sid = getStudentId();
		int score = 0;

		for(int i = 0; i < examTitleList.size(); ++ i)
		{
			ExamTitle cntTitle = (ExamTitle)examTitleList.get(i);
			List cntOptionList = s.createCriteria(ExamOption.class)
							 .add(Restrictions.eq("emTitle.emId", cntTitle.emId   ))
							 .list();
			boolean isRight = true;
			for(int j = 0; j < cntOptionList.size(); ++ j)
			{
				ExamOption op = (ExamOption)cntOptionList.get(j);
				List checked = s.createCriteria(ExamStuOption.class)
						.add(Restrictions.eq("stuPro.id", sid))
						.add(Restrictions.eq("emoption.emId", op.getEmId()))
						.list();
				

				boolean ok = op.emCheck.equals("true") ^ !checked.isEmpty();
				if(ok)
				{
					isRight = false;
					break;
				}				
			}
			if(isRight) score ++;
		}
		
		
		if(isPassExam(score, examTitleList.size()) )
		{
			this.status = "0您答对了" + score + "道题目，您已通过考试";			
		}
		else 
		{
			this.status = "1您答对了" + score + "道题目，正确率不足，还未通过考试， 请重新答题";
			List<ExamStuOption> opList = (List<ExamStuOption>)
					s.createCriteria(ExamStuOption.class)
					.add(Restrictions.eq("stuPro.id", sid))					
					.list();
			s.beginTransaction();
			for(ExamStuOption eso: opList)
			{
				s.delete(eso);
			}
			s.getTransaction().commit();

		}
		
		ExamStuScore stuScore = new ExamStuScore();
		stuScore.setScore(score);
		stuScore.setStuPro(getStudent(s));		
		
		s.beginTransaction();
		s.save(stuScore);
		s.getTransaction().commit();
		

		s.close();
		return SUCCESS;
	}
	


	public ArrayList<ExamTitle> getExamTitleList() {
		return examTitleList;
	}
	public void setExamTitleList(ArrayList<ExamTitle> examTitleList) {
		this.examTitleList = examTitleList;
	}
	public ArrayList<ArrayList<ExamOption>> getExamOptionList() {
		return examOptionList;
	}
	public void setExamOptionList(ArrayList<ArrayList<ExamOption>> examOptionList) {
		this.examOptionList = examOptionList;
	}
	public ArrayList<ArrayList<Boolean>> getStudentOptoinList() {
		return studentOptoinList;
	}
	public void setStudentOptoinList(ArrayList<ArrayList<Boolean>> studentOptoinList) {
		this.studentOptoinList = studentOptoinList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getOpId() {
		return opId;
	}
	public void setOpId(int opId) {
		this.opId = opId;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
}
