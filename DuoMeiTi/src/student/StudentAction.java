package student;

import com.opensymphony.xwork2.ActionSupport;

import model.Rules;
import model.TeachBuilding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import utility.DatabaseOperation;

import com.opensymphony.xwork2.ActionContext;

public class StudentAction extends ActionSupport{
	
	private List<BuildingsInfo> teahBuildings;
	private String textShow;
	
	public class BuildingsInfo{
		public String buildingName;
		public int buildingId;
		BuildingsInfo(String name,Integer id){
			this.buildingName=name;
			this.buildingId=id.intValue();
		}
	}
	

	public List getTeahBuildings() {
		return teahBuildings;
	}

	public void setTeahBuildings(List teahBuildings) {
		this.teahBuildings = teahBuildings;
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
