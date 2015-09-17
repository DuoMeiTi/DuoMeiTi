package homepage;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.Action;

public class LoginInterceptor extends AbstractInterceptor 
{  
	  
    @Override  
    public String intercept(ActionInvocation invocation) throws Exception 
    {
    	
    	String role = (String)invocation.getInvocationContext().getSession().get("role");
    	
    	System.out.println("拦截器");
    	if(role != null)
    		System.out.println(role);
    	if(role != null && role.equals(util.Const.AdminRole))
    	{
    		System.out.println("he can login");
    		return invocation.invoke();
    	}
    	
  
        // 取得请求相关的ActionContext实例  
//        ActionContext ctx = invocation.getInvocationContext();  
//        Map<String, Object> session = ctx.getSession();  
//        String username = (String) session.get("username");  
  
        // 如果没有登陆，或者登陆所有的用户名不是yuewei，都返回重新登陆  
  
//        if (username != null && username.equals("lz")) {  
//            System.out.println("test");  
//            return invocation.invoke();  
//        }  
  
//        ctx.put("tip", "你还没有登录");
    	System.out.println("he can not login");
        return "not_login";  
  
    }  
  
}  
