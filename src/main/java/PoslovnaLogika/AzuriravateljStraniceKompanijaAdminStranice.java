/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PoslovnaLogika;

import DAO.Repozitorij;
import Model.Admin;
import Model.Grupa;
import Model.Ispit;
import Model.Kompanija;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Filip
 */
public class AzuriravateljStraniceKompanijaAdminStranice {
        public void azurirajKompanijaAdminStrianicu(HttpServletRequest request, HttpServletResponse response, Repozitorij repo) throws IOException{
    
    
            HttpSession session = request.getSession();

        Kompanija odabranaKompanijaUSessionu = (Kompanija) session.getAttribute("odabranaKompanija");
        if (odabranaKompanijaUSessionu != null) {

            Kompanija azuriranaKompanija = repo.dohvatiKompanijuPoIDu(odabranaKompanijaUSessionu.getIDKompanija());
            List<Grupa> sveGrupe = (List<Grupa>) azuriranaKompanija.getGrupeUKompaniji();
            session.setAttribute("odabranaKompanija", azuriranaKompanija);

            Grupa odabranaGrupaUSessionu = (Grupa) session.getAttribute("odabranaGrupa");

            if (odabranaGrupaUSessionu != null) {
                Grupa odabranaAzuriranaGrupa = repo.dohvatiGrupuPoIDu(odabranaGrupaUSessionu.getIDGrupa());
                session.setAttribute("odabranaGrupa", odabranaAzuriranaGrupa);
            }
            if (sveGrupe.size() != 0 && odabranaGrupaUSessionu == null) {
                session.setAttribute("odabranaGrupa", sveGrupe.toArray()[0]);
            }

           List<Admin> sviAdmini = (List<Admin>) azuriranaKompanija.getAdminiKompanije();
            Admin odabraniAdminUSessionu = (Admin) session.getAttribute("odabraniAdminGrupa");

            if (odabraniAdminUSessionu != null) {
                Admin odabraniAzuriraniAdmin = repo.dohvatiAdminaPoIDu(odabraniAdminUSessionu.getIDAdmin());
                session.setAttribute("odabraniAdminGrupa", odabraniAzuriraniAdmin);
            }

            if (sviAdmini.size() != 0 && odabraniAdminUSessionu == null) {
                List<Admin> adminiGrupa = izvuciAdmineGrupa(sviAdmini);

                if (adminiGrupa.size() != 0) {
                    session.setAttribute("odabraniAdminGrupa", adminiGrupa.get(0));
                }

            }

            List<Ispit> sviIspitiKompanije = (List<Ispit>) azuriranaKompanija.getIspitiKompanije();
            Ispit odabraniIspitUSessionu = (Ispit) session.getAttribute("odabraniIspit");

            if (odabraniIspitUSessionu != null) {
                Ispit odabraniAzurirniIspit = repo.dohvatiIspitPoIDu(odabraniIspitUSessionu.getIDIspit());
                session.setAttribute("odabraniIspit", odabraniAzurirniIspit);

            }

            if (sviIspitiKompanije.size() != 0 && odabraniIspitUSessionu == null) {
                session.setAttribute("odabraniIspit", sviIspitiKompanije.toArray()[0]);
            }

        }

        response.sendRedirect("kompanijaAdminStranica.jsp");
    
    
    }
        private List<Admin> izvuciAdmineGrupa(List<Admin> sviAdmini) {
        List<Admin> adminiGrupa = new ArrayList<Admin>();
        Admin admin;
        for (Object o : sviAdmini) {
            admin = (Admin) o;
            if (admin.getRazinaovlasti().equals("grupa")) {
                adminiGrupa.add(admin);
            }

        }
        return adminiGrupa;
    }
}
