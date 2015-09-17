package admin;

import com.opensymphony.xwork2.ActionSupport;

public class ClassroomDetailAction extends ActionSupport {
	
	public String classroom_id;
	
	public String classroom_num;
	
	public String execute() {
		return SUCCESS;
	}
}
