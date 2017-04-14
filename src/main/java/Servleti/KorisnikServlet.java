package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Helper.PronalazacIzvoraRequesta;
import Model.InstancaGrupePitanja;
import Model.InstancaIspita;
import Model.InstancaOdgovora;
import Model.InstancaPitanja;
import Model.Korisnik;
import PoslovnaLogika.AzuriravateljKorisnikStranica;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KorisnikServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

   
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        Korisnik korisnik = (Korisnik) request.getSession().getAttribute("korisnik");
        if (korisnik != null) {
            PronalazacIzvoraRequesta r = new PronalazacIzvoraRequesta(); 
            String iz = request.getRequestURI();
            String s =  r.nadjiIzvor(request);
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

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
