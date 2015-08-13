package homepage;


import org.hibernate.Session;

import model.UserModel;

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
		if(username.equals("")) return "success";

    	
    	UserModel um = new UserModel();
		um.setUsername(username);
		um.setPassword(password);
		
		Session session = model.Util.sessionFactory.openSession();
		
		
		session.beginTransaction();		
		session.save(um);
		session.getTransaction().commit();
		session.close();


		
		
		

		

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
