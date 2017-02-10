/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PoslovnaLogika;

import DAO.Repozitorij;
import Model.Admin;
import Model.Grupa;
import Model.Kompanija;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Filip
 */
public class DodavacAdminaGrupe {

    public void dodajAdmina(HttpServletRequest request, Repozitorij repo) {
        Grupa grupa = (Grupa) request.getSession().getAttribute("odabranaGrupa");
        Kompanija kompanija = (Kompanija) request.getSession().getAttribute("odabranaKompanija");
        HttpSession session = request.getSession();

        if (grupa != null && kompanija != null) {

            String korisnickoIme = request.getParameter("korisnickoIme");
            String zaporka = request.getParameter("zaporka");
            Admin noviAdmin = new Admin(korisnickoIme, zaporka, "grupa", kompanija);
            kompanija.getAdminiKompanije().add(noviAdmin);

            repo.otvoriSession();

            repo.spremiRucnoBezOtvaranjaIZatvaranja(noviAdmin);
            repo.promjeniRucnoBezOtvaranjaIZatvaranja(kompanija);
            repo.zatvoriSession();

            request.getSession().setAttribute("odabraniAdminGrupa", noviAdmin);
        }
    }
}
