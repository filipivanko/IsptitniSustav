package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Ispit;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OdaberiIspitNaGrupiPitanjaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("IDOdabraniIspit") != null) {

            int IDodabranaiIspit = Integer.parseInt(request.getParameter("IDOdabraniIspit"));
            RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
            Repozitorij repo = repoFactoriy.stvoriRepozitorij();
            Ispit ispit = repo.dohvatiIspitPoIDu(IDodabranaiIspit);
            request.getSession().setAttribute("odabraniIspit", ispit);
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
