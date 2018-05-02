package homepage;

import java.util.Collections;
import java.util.List;
import java.sql.Date;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.Commonproblem;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

public class CommonProRecord extends ActionSupport {
	private List cmpShow;// 规章制度的内容，显示给jsp页面的内容
	private Date time;// 规章制度的修改时间

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List getCmpShow() {
		return cmpShow;
	}

	public void setCmpShow(List cmpShow) {
		this.cmpShow = cmpShow;
	}

	public String showRecord() throws Exception {
		// System.out.println(1111);
		System.out.println("CommonProRecord.showRecord()");
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(Commonproblem.class);
		// Commonproblem temp;
		if (q.list().size() > 0) {
			cmpShow = q.list();//
			// cmpShow = temp.getContent();
		} else {
			// cmpShow =" ";
		}
		session.close();
		return ActionSupport.SUCCESS;
	}

}
