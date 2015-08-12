package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;



public class Client {
    public static void main(String[] args) {
//    	 Configuration config=new AnnotationConfiguration();
//    	config.configure();
//    	SessionFactory sessionFactory=config.buildSessionFactory();
//    	   Session session=sessionFactory.getCurrentSession();
//    	  session.beginTransaction();
//    	  Category c=(Category)session.get(Category.class, 5);
//    	  
//    	  Product p=new Product();
//    	  p.setName("计算机科学与技术");
//    	   p.setPrice("123");
//    	  p.setDescripton("计算机科学与技术,好啊，真是红啊");
//    	  
//    	  p.setCategory(c);
//    	  c.getProducts().add(p);
//    	   
//    	   session.save(p);
//    	   session.getTransaction().commit();


    	
    	Configuration cfg = new Configuration().configure();
    	StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
    	StandardServiceRegistry sr = srb.build();
    	SessionFactory sf = cfg.buildSessionFactory(sr);

    	
//    	SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = null;
        Transaction t = null;

        try{
            //准备数据
            UserModel um = new UserModel();
            
//            um.setId("1");
            um.setUsername("23423847928;jkwejsadfkj");
            um.setPassword("1123123");
//            um.setName("name1");
//            um.setPwd("!@#!@#");
            s = sf.openSession();
            t = s.beginTransaction();
            s.save(um);
            t.commit();
       }catch(Exception err){
            t.rollback();
            err.printStackTrace();
      }finally{
            s.close();
      }
    }
}
