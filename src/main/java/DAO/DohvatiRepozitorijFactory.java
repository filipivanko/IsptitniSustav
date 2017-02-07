/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Filip
 */
public class DohvatiRepozitorijFactory {
    private static RepozitorijFactoriy repoFactoriy=null;
    
    public static RepozitorijFactoriy dohvati(){
    if(repoFactoriy==null){
    repoFactoriy= new HibernateRepozitorijFactory();
    
    }  
       return repoFactoriy; 
    }
    
}
