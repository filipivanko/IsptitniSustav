package PoslovnaLogika;

import DAO.Repozitorij;
import Model.GrupaPitanja;
import Model.Pitanje;
import javax.servlet.http.HttpServletRequest;


public class PrekidacVezeIzmedjuPitanjaIGrupePitanja {
    public void prekiniVezuPitanjaIGrupePitanja(HttpServletRequest request,Repozitorij repo){
 GrupaPitanja grupaPitanja = (GrupaPitanja) request.getSession().getAttribute("odabranaGrupaPitanja");
        Pitanje pitanje = (Pitanje) request.getSession().getAttribute("odabranoPitanje");
 
        if (grupaPitanja != null && pitanje != null) {
            if (grupaPitanja.provjeriDaLiJePovezaoPitanje(pitanje)) {
                grupaPitanja.izbaciPitanje(pitanje);
            }
            if (pitanje.provjeriDaLiJePovezanaGrupaPitanja(grupaPitanja)) {
               pitanje.izbaciGrupuPitanja(grupaPitanja);
            }
            repo.otvoriSession();
            repo.promjeniRucnoBezOtvaranjaIZatvaranja(pitanje);
            repo.promjeniRucnoBezOtvaranjaIZatvaranja(grupaPitanja);
            repo.zatvoriSession();

            Pitanje azuriranoPitanje = repo.dohvatiPitanjePoIDu(pitanje.getIDPitanje());
            GrupaPitanja azuriranaGrupaPitanja = repo.dohvatiGrupuPitanjaPoIDu(grupaPitanja.getIDGrupaPitanja());

            request.getSession().setAttribute("odabranaGrupaPitanja", azuriranaGrupaPitanja);
            request.getSession().setAttribute("odabranoPitanje", azuriranoPitanje);
        }
     
}
}
