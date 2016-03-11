package student;

import com.opensymphony.xwork2.ActionSupport;

import model.Rules;
import model.TeachBuilding;
import model.DutyPiece;
import model.DutySchedule;
import model.StudentProfile;
import model.ChooseClassSwitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import utility.DatabaseOperation;

import com.opensymphony.xwork2.ActionContext;

import common.BuildingsInfo;

public class StudentAction extends ActionSupport{
	
	private List<BuildingsInfo> teahBuildings;
	private int teachBuildingId;
	private List<DutyPiece> duties;
	private String log;
	private List<Integer> chosen;
	private String textShow;
	private int studentId;
	
		
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public List<Integer> getChosen() {
		return chosen;
	}

	public void setChosen(List<Integer> chosen) {
		this.chosen = chosen;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public List<DutyPiece> getDuties() {
		return duties;
	}

	public void setDuties(List<DutyPiece> duties) {
		this.duties = duties;
	}

	public List<BuildingsInfo> getTeahBuildings() {
		return teahBuildings;
	}

	public void setTeahBuildings(List<BuildingsInfo> teahBuildings) {
		this.teahBuildings = teahBuildings;
	}

	public int getTeachBuildingId() {
		return teachBuildingId;
	}

	public void setTeachBuildingId(int teachBuildingId) {
		this.teachBuildingId = teachBuildingId;
	}

	

	public String getTextShow() {
		return textShow;
	}

	public void setTextShow(String textShow) {
		this.textShow = textShow;
	}


	public String chooseClass() throws Exception{
		
		List fields = new ArrayList<String>();
		fields.add("build_id,");
		fields.add("build_name");
		DatabaseOperation getBuildings = new DatabaseOperation("TeachBuilding", fields);
		List result=getBuildings.selectOperation();
		teahBuildings =new ArrayList<BuildingsInfo>();
		Iterator iter =result.iterator();
		while(iter.hasNext()){
			Object[] temp=(Object[])iter.next();
			String name=(String)temp[1];
			Integer id=(Integer)temp[0];
			teahBuildings.add(new BuildingsInfo(name,id));
		}
		return ActionSupport.SUCCESS;
	}
	
	public String getDutyTime() throws Exception{
		Session session = model.Util.sessionFactory.openSession();
		String hql="from DutyTime d where d.teachBuilding.build_id="+teachBuildingId;
		
		String studentSelect="from StudentProfile s where s.id="+studentId;
		StudentProfile s=(StudentProfile)session.createQuery(studentSelect).list().get(0);
		
		String selectChosen="select ds.dutyTime.time from DutySchedule ds where ds.student.id="+s.id+" and "
							+"ds.dutyTime.teachBuilding.build_id="+teachBuildingId;
		List chosenList = session.createQuery(selectChosen).list();
		
		chosen = new ArrayList<Integer>();
		if(chosenList.size()>0){
			Iterator iter= chosenList.iterator();
			while(iter.hasNext()){
				Integer temp=(Integer)iter.next();
				chosen.add(temp);
			}
		}
		duties=session.createQuery(hql).list();
		session.close();
		return "ajaxSuccess";
	}
	
	public String reciveChoice() throws Exception{
		
		//代码太挫，留待有缘人！
		Session session = model.Util.sessionFactory.openSession();
		
		String hql="from ChooseClassSwitch ccs where ccs.id=1";
		ChooseClassSwitch f=(ChooseClassSwitch)session.createQuery(hql).list().get(0);
		if(!f.open){
			log="closed";
			session.close();
			return "ajaxSuccess";
		}
		Transaction trans;
		//选出选课的学生
		String studentSelect="from StudentProfile s where s.id="+studentId;
		StudentProfile s=(StudentProfile)session.createQuery(studentSelect).list().get(0);
		//删除dutySchedule中的该生以前选择的班次,并更新
		trans=session.beginTransaction();
		String dutySelect="select ds.dutyTime from DutySchedule ds where ds.student.id="
					 	  +studentId+" and "+"ds.dutyTime.teachBuilding.build_id="+teachBuildingId;
		List<DutyPiece> duties=session.createQuery(dutySelect).list();
		for(DutyPiece tmp:duties){
			String back= "update DutyTime d set d.dutyLeft=d.dutyLeft+1 where d.id="+tmp.id;
			session.createQuery(back).executeUpdate();
		}
		String selectDutySchedule="From DutySchedule ds where ds.student.id="+studentId+" and "
				  			  	  +"ds.dutyTime.teachBuilding.build_id="+teachBuildingId;
		List<DutySchedule> dutySchedules=session.createQuery(selectDutySchedule).list();
		for(DutySchedule tmp:dutySchedules){
			String deleteChosen = "delete from DutySchedule ds where ds.id="+tmp.id;
		  	session.createQuery(deleteChosen).executeUpdate();
		}
		trans.commit();
		
		//所选的班
		String dc="(";
		for(int i=0,end=chosen.size();i<end;i++){
			dc+="d.time="+chosen.get(i).intValue();
			if(i<end-1)dc+=" or ";
			else dc+=")";
		}
		System.out.println(dc);
		//dutyTime数据库更新
		trans=session.beginTransaction();
		String updateDutyLeft="update DutyTime d set d.dutyLeft=d.dutyLeft-1 where d.teachBuilding.build_id="
								  +teachBuildingId+" and "+dc;
		int ret=session.createQuery(updateDutyLeft).executeUpdate();
		if(ret==0){
			log="something wrong during updating database";
			return "ajaxSuccess";
		}
		trans.commit();
		
		//dutySchedule数据库更新
		String selectDutyTime="from DutyTime d where d.teachBuilding.build_id="
							  +teachBuildingId+" and "+dc;
		List<DutyPiece> dutyChosen=session.createQuery(selectDutyTime).list();
		
		for(DutyPiece t:dutyChosen){
			try{
				trans=session.beginTransaction();
				DutySchedule temp =new DutySchedule();
				temp.student=s;
				temp.dutyPiece=t;
				session.save(temp);
				trans.commit();
			}
			catch(Exception e){
				log="fail";
				return "ajaxSuccess";
			}
		}
		session.close();
		log="success";
		return "ajaxSuccess";
	}
	
	public String ruleShow() throws Exception{
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(Rules.class);
		Rules temp;
		if (q.list().size() > 0) {
			temp = (Rules)q.list().get(0);//
			textShow = temp.getText();
			System.out.println(textShow);
			textShow = textShow.replace("\n", "<br/>&nbsp;&nbsp;&nbsp;&nbsp;");
			System.out.println("***********"+textShow);
		}
		else {
			textShow =" ";
		}
		session.close();
		return SUCCESS;
	}
	
}
