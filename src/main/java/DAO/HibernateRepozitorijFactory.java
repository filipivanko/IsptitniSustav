
package DAO;


public class HibernateRepozitorijFactory implements  RepozitorijFactoriy{
    
    public Repozitorij stvoriRepozitorij(){
    Repozitorij repo = new HibernateRepozitorij();
    
    return repo;
    
    }
}
