package PoslovnaLogika;

import DAO.Repozitorij;
import Model.Admin;
import Model.Grupa;
import Model.Ispit;
import Model.Korisnik;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AzuriravateljStraniceGrupaAdminStranica {

    public void azurirajGrupaAdminStranicu(HttpServletRequest request, HttpServletResponse response, Repozitorij repo) throws IOException {
        HttpSession session = request.getSession();

        Admin odabraniAdminUSessionu = (Admin) session.getAttribute("odabraniAdminGrupa");
        session.removeAttribute("odabranaGrupa");

        if (odabraniAdminUSessionu != null) {
            Admin odabraniAzuriraniAdmin = repo.dohvatiAdminaPoIDu(odabraniAdminUSessionu.getIDAdmin());
            session.setAttribute("odabraniAdminGrupa", odabraniAzuriraniAdmin);

            List<Grupa> sveAdminoveGrupe = odabraniAzuriraniAdmin.getGrupeKojePripadajuAdminu();
            List<Ispit> sviIspitiGrupe;
            Grupa odabranaGrupa;
            if (sveAdminoveGrupe.size() != 0) {

                odabranaGrupa = (Grupa) sveAdminoveGrupe.toArray()[0];
                session.setAttribute("odabranaGrupa", odabranaGrupa);

                sviIspitiGrupe = odabranaGrupa.getIspitiGrupe();

                List<Korisnik> korisniciGrupe = odabranaGrupa.getClanoviGrupe();

                if (korisniciGrupe.size() != 0) {
                    session.setAttribute("odabraniKorisnk", korisniciGrupe.toArray()[0]);
                    request.getSession().setAttribute("korisnik", korisniciGrupe.toArray()[0]);
                }

                if (sviIspitiGrupe.size() != 0) {
                    session.setAttribute("odabraniIspit", sviIspitiGrupe.toArray()[0]);
                }
            }
        }
                response.sendRedirect("grupaAdminStranica.jsp");
    }
}
