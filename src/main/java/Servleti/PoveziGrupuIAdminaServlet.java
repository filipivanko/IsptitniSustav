
package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Admin;
import Model.Grupa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PoveziGrupuIAdminaServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Grupa grupa = (Grupa)request.getSession().getAttribute("odabranaGrupa");
        Admin admin = (Admin)request.getSession().getAttribute("odabraniAdminGrupa");
        
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        if (grupa!=null && admin!=null) {
            if (!grupa.provjeriDaLiJePovezanAdmin(admin)) {
                 grupa.getAdminiGrupe().add(admin);
            }
            if (!admin.provjeriDaLiJePovezanaGrupa(grupa)) {
                admin.getGrupeKojePripadajuAdminu().add(grupa);
            }
           repo.otvoriSession();
           repo.promjeniRucnoBezOtvaranjaIZatvaranja(admin);
           repo.promjeniRucnoBezOtvaranjaIZatvaranja(grupa);
           repo.zatvoriSession();
           
           Admin azuriraniAdmin = repo.dohvatiAdminaPoIDu(admin.getIDAdmin());
           Grupa azuriranaGrupa = repo.dohvatiGrupuPoIDu(grupa.getIDGrupa());
           
            request.getSession().setAttribute("odabranaGrupa", azuriranaGrupa);
            request.getSession().setAttribute("odabraniAdminGrupa",azuriraniAdmin);

        }
        response.sendRedirect("KompanijaAdminServlet");
        
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
