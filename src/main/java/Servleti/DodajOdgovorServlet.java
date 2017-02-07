
package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Kompanija;
import Model.Odgovor;
import Model.Pitanje;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DodajOdgovorServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        HttpSession session = request.getSession();
        Pitanje pitanje = (Pitanje)session.getAttribute("odabranoPitanje");
        if(pitanje!=null ){

        String tekstOdgovora = request.getParameter("tekstOdgovora");
        String adresaSlike = request.getParameter("adresaSlike");
        String tocanNetocanOdgovor = request.getParameter("tocanNetocanOdgovor");
        
        
        tekstOdgovora=tekstOdgovora.trim();
        tekstOdgovora=tekstOdgovora.toLowerCase();
        
        Odgovor noviOdgovor = new Odgovor(tekstOdgovora, tekstOdgovora, adresaSlike, pitanje, tocanNetocanOdgovor);
        pitanje.getOdgovori().add(noviOdgovor);
        
        repo.otvoriSession();
        session.setAttribute("odabraniOdgovor", noviOdgovor);
        
        repo.promjeniRucnoBezOtvaranjaIZatvaranja(pitanje);
        repo.spremiRucnoBezOtvaranjaIZatvaranja(noviOdgovor);
        repo.zatvoriSession();
        }
        
        
        response.sendRedirect("GrupaPitanjaServlet");
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
