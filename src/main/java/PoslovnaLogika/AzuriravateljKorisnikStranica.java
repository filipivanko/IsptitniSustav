package PoslovnaLogika;

import DAO.Repozitorij;
import Model.InstancaGrupePitanja;
import Model.InstancaIspita;
import Model.InstancaOdgovora;
import Model.InstancaPitanja;
import Model.Korisnik;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AzuriravateljKorisnikStranica {

    public void azurirajKorisnikStranicu(HttpServletRequest request, HttpServletResponse response, Repozitorij repo) throws IOException {

        Korisnik korisnik = (Korisnik) request.getSession().getAttribute("korisnik");
        if (korisnik != null) {
            Korisnik azuriraniKorsinK = repo.dohvatiKorisnikaPoIDu(korisnik.getIDKorisnik());
            List<InstancaIspita> instanceIspitaKorisnika = repo.dohvatiKorisnikoveInstanceIspita(korisnik);

            List<InstancaIspita> neZavrseniIspiti = new ArrayList<InstancaIspita>();
            List<InstancaIspita> zavrseniIspiti = new ArrayList<InstancaIspita>();

            azurirajListeIspitaZaIsptieKojimaJeProsaoRok(instanceIspitaKorisnika, repo);

            for (InstancaIspita i : instanceIspitaKorisnika) {
                if (i.isZavrsen()) {
                    zavrseniIspiti.add(i);

                } else {
                    neZavrseniIspiti.add(i);
                }

            }

            request.getSession().removeAttribute("odabranaInstancaIspita");
            request.getSession().removeAttribute("odabranaInstancaPitanja");
            request.getSession().setAttribute("nezavrseniIspiti", neZavrseniIspiti);
            request.getSession().setAttribute("zavrseniIspiti", zavrseniIspiti);

        }

        response.sendRedirect("korisnikStranica.jsp");
    }
     private void azurirajListeIspitaZaIsptieKojimaJeProsaoRok(List<InstancaIspita> instanceIspitaKorisnika, Repozitorij repo) {
        Date danas = new Date();
        repo.otvoriSession();
        for (InstancaIspita i : instanceIspitaKorisnika) {
            if (danas.after(i.getKrajnjirokIspita())) {
                i.setZavrsen(true);
                i.obradiRezultate();
                for (InstancaGrupePitanja igp : i.getInstanceGrupaPitanja()) {
                    for (InstancaPitanja p : igp.getInstancePitanja()) {
                        for (InstancaOdgovora o : p.getInstanceOdgovora()) {
                            repo.promjeniRucnoBezOtvaranjaIZatvaranja(o);
                        }
                        repo.promjeniRucnoBezOtvaranjaIZatvaranja(p);
                    }
                    repo.promjeniRucnoBezOtvaranjaIZatvaranja(igp);
                }
                repo.promjeniRucnoBezOtvaranjaIZatvaranja(i);
            }
        }
        repo.zatvoriSession();
    }
}
