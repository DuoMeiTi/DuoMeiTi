package homepage;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

public class UserAction extends ActionSupport
{
	public String user_name= "sb";
	
	public void SetUser_name(String s)
	{
		user_name = s;
	}
	public String register() throws Exception
	{

		System.out.println("DSDLFKJ:::::::");
		System.out.println(user_name);
		Map request1 = (Map)ActionContext.getContext().get("request");
        Map session1 = ActionContext.getContext().getSession();
        System.out.println(request1);
        System.out.println(session1);
        

		return "success";
	}
}
