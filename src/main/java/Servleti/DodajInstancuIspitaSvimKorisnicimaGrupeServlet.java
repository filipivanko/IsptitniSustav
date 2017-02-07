package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Grupa;
import Model.InstancaIspita;
import Model.Ispit;
import Model.Korisnik;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DodajInstancuIspitaSvimKorisnicimaGrupeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        Grupa grupa = (Grupa) request.getSession().getAttribute("odabranaGrupa");
        Ispit ispit = (Ispit) request.getSession().getAttribute("odabraniIspit");

        if (grupa != null && ispit != null) {

            for (Korisnik k : grupa.getClanoviGrupe()) {
                InstancaIspita instancaIspita = new InstancaIspita(ispit, k, repo);
            }
            if (grupa.getClanoviGrupe().size() != 0) {
                request.getSession().setAttribute("porukaIspitDodanGrupi", "Ispit " + ispit.getNaziv() + " Dodan Je Grupi " + grupa.getNaziv());
            } else {
                request.getSession().setAttribute("porukaIspitDodanGrupi", "Ispit Nije Dodan - Grupa Nema Članova");
            }

        } else {
            request.getSession().setAttribute("porukaIspitDodanGrupi", "Ispit Nije Dodan Nije Odabrqana Grupa Ili Ispit");
        }
        response.sendRedirect("grupaAdminStranica.jsp");
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
