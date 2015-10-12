package student;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

public class StudentAction extends ActionSupport{
	
	public String chooseClass() throws Exception{
		return ActionSupport.SUCCESS;
	}
	
}
