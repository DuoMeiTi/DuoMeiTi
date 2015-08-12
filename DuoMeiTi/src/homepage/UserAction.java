package homepage;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

public class UserAction
{
	private String username= "";
	private String password="";
	
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String s)
	{
		username = s;
	}
	
	public String register() throws Exception
	{

		System.out.println("DSDLFKJ:::::::");
		System.out.println(username);
		System.out.println(password);
//		Map request1 = (Map)ActionContext.getContext().get("request");
//        Map session1 = ActionContext.getContext().getSession();
//        System.out.println(request1);
//        System.out.println(session1);
        

		return "success";
	}
}
