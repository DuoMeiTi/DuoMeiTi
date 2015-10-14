package student;

import com.opensymphony.xwork2.ActionSupport;

import model.TeachBuilding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utility.DatabaseOperation;

import com.opensymphony.xwork2.ActionContext;

public class StudentAction extends ActionSupport{
	
	private List<BuildingsInfo> teahBuildings;
	
	public class BuildingsInfo{
		public String buildingName;
		public int buildingId;
		BuildingsInfo(String name,Integer id){
			this.buildingName=name;
			this.buildingId=id.intValue();
		}
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
	
	public List getTeahBuildings() {
		return teahBuildings;
	}

	public void setTeahBuildings(List teahBuildings) {
		this.teahBuildings = teahBuildings;
	}
	
}
