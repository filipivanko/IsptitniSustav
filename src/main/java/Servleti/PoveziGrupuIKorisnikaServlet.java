/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Grupa;
import Model.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Filip
 */
public class PoveziGrupuIKorisnikaServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
     Grupa grupa = (Grupa)request.getSession().getAttribute("odabranaGrupa");
        Korisnik korisnik = (Korisnik)request.getSession().getAttribute("odabraniKorisnk");
        
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();

        
        if (grupa!=null && korisnik!=null) {
            if (!grupa.provjeriDaLiJePovezanKorisnik(korisnik)) {
                 grupa.getClanoviGrupe().add(korisnik);
            }
            if (!korisnik.provjeriDaLiJePovezanaGrupa(grupa)) {
                korisnik.getGrupeKojimaKorisnikPripada().add(grupa);
            }
           repo.otvoriSession();
           repo.promjeniRucnoBezOtvaranjaIZatvaranja(korisnik);
           repo.promjeniRucnoBezOtvaranjaIZatvaranja(grupa);
           repo.zatvoriSession();
           
           Korisnik azuriraniKorisnik = repo.dohvatiKorisnikaPoIDu(korisnik.getIDKorisnik());
           Grupa azuriranaGrupa = repo.dohvatiGrupuPoIDu(grupa.getIDGrupa());
           
            request.getSession().setAttribute("odabranaGrupa", azuriranaGrupa);
            request.getSession().setAttribute("odabraniKorisnk",azuriraniKorisnik);

        }
        response.sendRedirect("AdministracijaKorisnikaServlet");
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

}
