package homepage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
//import model.Contacts;
import model.StudentProfile;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
public class ContactsAction extends util.PageGetBaseAction {

	public List<StudentProfile> contacts_list;
//	
//	public List postGraduateStudentList;

	    //建立数据库查询
	public String wrContacts() throws Exception {
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(model.StudentProfile.class)
				.add(Restrictions.eq("isPassed", model.StudentProfile.Passed))
				.add(Restrictions.not(Restrictions.eq("isUpgradePrivilege", model.StudentProfile.DepartureStudent)))
				;
		contacts_list=q.list();
		
		
		
		
		
//		postGraduateStudentList = new ArrayList<model.StudentProfile>();
//		
//		postGraduateStudentList.addAll(contacts_list);
//		
//		postGraduateStudentList.remove(0);
		
		session.close();
		
		
		Collections.sort(contacts_list, new Comparator<StudentProfile>() {

			@Override
			public int compare(StudentProfile o1, StudentProfile o2) {
				if(o1.studentId.length() != o2.studentId.length())
				{
					if(o1.studentId.length() < o2.studentId.length()) return -1;
					else return 1;
				}
				return 0;
			}
			
			
			
		});
		
		
		
		
//		if(this.getIsAjaxTransmission())
//		{
//			contacts_list_html =util.Util.getJspOutput("/jsp/homepage/widgets/contactsTable.jsp");
//			return "getPage";
//		}
		return ActionSupport.SUCCESS;
	}

	public List<StudentProfile> getContacts_list() {
		return contacts_list;
	}

	public void setContacts_list(List<StudentProfile> contacts_list) {
		this.contacts_list = contacts_list;
	}
	
	
	


	
	
	
//
//	public List getPostGraduateStudentList() {
//		return postGraduateStudentList;
//	}
//
//
//
//	public void setPostGraduateStudentList(List postGraduateStudentList) {
//		this.postGraduateStudentList = postGraduateStudentList;
//	}
	
	


}
