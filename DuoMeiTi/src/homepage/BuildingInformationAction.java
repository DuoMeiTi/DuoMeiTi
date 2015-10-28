package homepage;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import model.TeachBuilding;

public class BuildingInformationAction extends ActionSupport{
private List<TeachBuilding> builds;
	
	public String build_name;
	
	
	public List<TeachBuilding> getBuilds() {
		return builds;
	}


	public void setBuilds(List<TeachBuilding> builds) {
		this.builds = builds;
	}


	public String execute(){
		System.out.println("建立查询");
		Session session = model.Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(TeachBuilding.class)
							.setFetchMode("classroom", FetchMode.SELECT);
		builds = criteria.list();
		for(TeachBuilding b : builds){
			System.out.println(b.getBuild_name());
		}
		session.close();
		return SUCCESS;
	}


}
