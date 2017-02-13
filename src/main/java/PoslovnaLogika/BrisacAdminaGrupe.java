
package PoslovnaLogika;

import DAO.Repozitorij;
import Model.Admin;
import javax.servlet.http.HttpServletRequest;

public class BrisacAdminaGrupe {
    public void obrisiAdminaGrupe(HttpServletRequest request, Repozitorij repo){
Admin odabraniAdmin = (Admin)request.getSession().getAttribute("odabraniAdminGrupa");
        if(odabraniAdmin!=null){
        repo.obrisi(odabraniAdmin);
        
       request.getSession().setAttribute("odabraniAdminGrupa",null);
        }
    }
}
