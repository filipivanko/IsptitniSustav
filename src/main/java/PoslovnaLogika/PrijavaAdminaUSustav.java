/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PoslovnaLogika;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Admin;
import Model.Kompanija;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Filip
 */
public class PrijavaAdminaUSustav {
    public String PrijaviAdmina(HttpServletRequest request,Repozitorij repo){
    
        String korisnickoIme = request.getParameter("korisnickoIme");
        String zaporka = request.getParameter("zaporka");
        
        Admin admin = null;
        if (repo.postojiAdmin(korisnickoIme)) {
            admin = repo.dohvatiAdminaPoKorisnickomImenu(korisnickoIme);
            if (admin.getZaporka().equals(zaporka)) {
                request.getSession().setAttribute("admin", admin);
                
            } else {
                request.getSession().setAttribute("greška", "Pogrešna Zaporka");
                return "adminPrijava.jsp";
            }
            
        } else {
            request.getSession().setAttribute("greška", "Ne postoji user");
            return "adminPrijava.jsp";

        }
        
        HttpSession session = request.getSession(); 
        if(admin == null){
            return "adminPrijava.jsp";
        }
        if (admin.getRazinaovlasti().equals("grupa")) {
            session.setAttribute("odabraniAdminGrupa", admin);
            return "GrupaAdminPrviDolazakServlet";
            
        }
        if (admin.getRazinaovlasti().equals("kompanija")) {

        Kompanija odabranaAzuriranaKompanija = repo.dohvatiKompanijuPoIDu(admin.getKompanija().getIDKompanija());
        session.setAttribute("odabranaKompanija", odabranaAzuriranaKompanija);
            return "KompanijaAdminServlet";
        }
        if (admin.getRazinaovlasti().equals("root")) {
            return "RootAdminServlet";
        }
        return "adminPrijava.jsp";
    }
}
