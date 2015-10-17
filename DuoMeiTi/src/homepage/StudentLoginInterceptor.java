package homepage;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class StudentLoginInterceptor extends AbstractInterceptor 
{  
	  
    @Override  
    public String intercept(ActionInvocation invocation) throws Exception 
    {
    	
    	String role = (String)invocation.getInvocationContext().getSession().get("role");
    	
    	System.out.println("拦截器");
    	
    	if(role != null && role.equals(util.Const.StudentRole))
    	{
    		System.out.println("he can login");
    		return invocation.invoke();
    	}
  
    	System.out.println("you have not login");
        return "not_login";  
  
    }  
  
}  