/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PoslovnaLogika;

import DAO.Repozitorij;
import Model.Admin;
import Model.Kompanija;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Filip
 */
public class DodavacAdminaKompanije {

    public void dodajAdminaKompanije(HttpServletRequest request, Repozitorij repo) {

        Kompanija kompanija = (Kompanija) request.getSession().getAttribute("odabranaKompanija");
        if (kompanija != null) {

            HttpSession session = request.getSession();

            String korisnickoIme = request.getParameter("korisnickoImeNoviAdminKompanije");
            String zaporka = request.getParameter("zaporkaNoviAdminKompanije");

            Admin noviAdmin = new Admin(korisnickoIme, zaporka, "kompanija", kompanija);
            kompanija.getAdminiKompanije().add(noviAdmin);

            repo.otvoriSession();
            repo.promjeniRucnoBezOtvaranjaIZatvaranja(kompanija);

            repo.spremiRucnoBezOtvaranjaIZatvaranja(noviAdmin);
            repo.zatvoriSession();

        }
    }
}
