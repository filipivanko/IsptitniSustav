package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.GrupaPitanja;
import Model.Ispit;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PrekiniVezuIspitaIGrupaPitanjaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GrupaPitanja grupaPitanja = (GrupaPitanja)request.getSession().getAttribute("odabranaGrupaPitanja");
        Ispit ispit = (Ispit)request.getSession().getAttribute("odabraniIspit");
       
        RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        if (grupaPitanja!=null && ispit!=null) {
            if (grupaPitanja.provjeriDaLiJePovezanIspit(ispit)) {
                 grupaPitanja.izbaciIspit(ispit);
            }
            if (ispit.provjeriDaLiJePovezanaGrupaPitanja(grupaPitanja)) {
                ispit.izbaciGrupuPitanja(grupaPitanja);
            }
           repo.otvoriSession();
           repo.promjeniRucnoBezOtvaranjaIZatvaranja(ispit);
           repo.promjeniRucnoBezOtvaranjaIZatvaranja(grupaPitanja);
           repo.zatvoriSession();
           
           Ispit azuriraniIspit = repo.dohvatiIspitPoIDu(ispit.getIDIspit());
           GrupaPitanja azuriranaGrupaPitanja = repo.dohvatiGrupuPitanjaPoIDu(grupaPitanja.getIDGrupaPitanja());
           
            request.getSession().setAttribute("odabranaGrupaPitanja", azuriranaGrupaPitanja);
            request.getSession().setAttribute("odabraniIspit",azuriraniIspit);

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
