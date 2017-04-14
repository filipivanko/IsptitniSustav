package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public  class HibernateSessionFactorySingleton {
    public static SessionFactory sessionFactory = null;
    
    public static SessionFactory getInstance(){
    if(sessionFactory==null){
    

        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
       
    }
    return sessionFactory;
    }
}
