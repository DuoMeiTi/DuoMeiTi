package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class Client {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = null;
        Transaction t = null;

        try{
            //准备数据
            UserModel um = new UserModel();
            
//            um.setId("1");
            
            um.setName("name1");
            um.setPwd("!@#!@#");
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
