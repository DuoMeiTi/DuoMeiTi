package model;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Util {

	public static final SessionFactory sessionFactory;  
//	public static Session  session;
//	public static Transaction transaction;
//	public static Session session;	
    static 
    {  
        try 
        {  
                  

        	Configuration cfg = new Configuration().configure();
        	StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        	StandardServiceRegistry sr = srb.build();
        	sessionFactory = cfg.buildSessionFactory(sr);        	
//        	session = model.Util.sessionFactory.openSession();
//        	transaction = session.getTransaction();
        	
//        	transaction = session.beginTransaction();

  
        } 
        catch (Throwable e) 
        {  
            throw new ExceptionInInitializerError(e);  
        }  
    }  
  

	
}
