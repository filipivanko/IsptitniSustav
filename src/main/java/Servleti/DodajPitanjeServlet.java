package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Kompanija;
import Model.Pitanje;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DodajPitanjeServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        HttpSession session = request.getSession();
        Kompanija kompanija = (Kompanija)session.getAttribute("odabranaKompanija");
        if(kompanija!=null){
            
        String tekstPitanja = request.getParameter("textPitanja");
        String adresaSlike = request.getParameter("adresaSlike");
        String vrstaOdgovora = request.getParameter("vrstaOdgovora");
        
          
        Pitanje novoPitanje = new Pitanje(tekstPitanja, adresaSlike, vrstaOdgovora, kompanija);
        kompanija.getPitanjaUKompaniji().add(novoPitanje);
        
        repo.otvoriSession();
        session.setAttribute("odabranoPitanje", novoPitanje);
        
        repo.promjeniRucnoBezOtvaranjaIZatvaranja(kompanija);
        repo.spremiRucnoBezOtvaranjaIZatvaranja(novoPitanje);
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
