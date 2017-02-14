
package PoslovnaLogika;

import DAO.Repozitorij;
import Model.GrupaPitanja;
import Model.Pitanje;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Filip
 */
public class PovezivacPitanjaIGrupePitanja {
     public void poveziPitanjeIGrupuPitanja(HttpServletRequest request, Repozitorij repo){
      GrupaPitanja grupaPitanja = (GrupaPitanja)request.getSession().getAttribute("odabranaGrupaPitanja");
        Pitanje pitanje = (Pitanje)request.getSession().getAttribute("odabranoPitanje");
        
        if (grupaPitanja!=null && pitanje!=null) {
            
            if (!grupaPitanja.provjeriDaLiJePovezaoPitanje(pitanje)) {
                 grupaPitanja.getPitanjaUGrupi().add(pitanje);
                
            }
            if (!pitanje.provjeriDaLiJePovezanaGrupaPitanja(grupaPitanja)) {
                 pitanje.getGrupePitanjaKojaSadrzePitanje().add(grupaPitanja);
            }
           repo.otvoriSession();
           repo.promjeniRucnoBezOtvaranjaIZatvaranja(pitanje);
           repo.promjeniRucnoBezOtvaranjaIZatvaranja(grupaPitanja);
           repo.zatvoriSession();
           
           Pitanje azuriranoPitanje = repo.dohvatiPitanjePoIDu(pitanje.getIDPitanje());
           GrupaPitanja azuriranaGrupaPitanja = repo.dohvatiGrupuPitanjaPoIDu(grupaPitanja.getIDGrupaPitanja());
           
            request.getSession().setAttribute("odabranaGrupaPitanja", azuriranaGrupaPitanja);
            request.getSession().setAttribute("odabranoPitanje",azuriranoPitanje);

        }
    }
}
