package homepage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

public class CommonProRecord extends ActionSupport {
	public String showRecord() throws Exception 
	{
		return ActionSupport.SUCCESS;
	}

}
