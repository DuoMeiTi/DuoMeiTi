package homepage;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.Action;

public class AdminLoginInterceptor extends AbstractInterceptor 
{  
	  
    @Override  
    public String intercept(ActionInvocation invocation) throws Exception 
    {
    	
    	String role = (String)invocation.getInvocationContext().getSession().get("role");
    	if(role != null && role.equals(util.Const.AdminRole))
    	{
    		System.out.println("he can login");
    		return invocation.invoke();
    	}

    	System.out.println("you have not login");
        return "not_login";  
  
    }  
  
}  
