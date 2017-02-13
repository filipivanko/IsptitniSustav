package PoslovnaLogika;
import DAO.Repozitorij;
import Model.Grupa;
import javax.servlet.http.HttpServletRequest;


public class OdabirateljGrupe {
        public void odaberiGrupu(HttpServletRequest request, Repozitorij repo) {
        if (request.getParameter("IDodabranaGrupa") != null) {

            int IDodabranaGrupa = Integer.parseInt(request.getParameter("IDodabranaGrupa"));
            Grupa odabranaGrupa = repo.dohvatiGrupuPoIDu(IDodabranaGrupa);
            request.getSession().setAttribute("odabranaGrupa", odabranaGrupa);
        }
    }
}
