package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.InstancaGrupePitanja;
import Model.InstancaIspita;
import Model.InstancaOdgovora;
import Model.InstancaPitanja;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PredajIspitServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InstancaIspita instancaIspita = (InstancaIspita) request.getSession().getAttribute("odabranaInstancaIspita");
         
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        if (instancaIspita != null) {
            InstancaIspita azuriranaInstancaIspita = repo.dohvatiInstancuIspitaPoIDu(instancaIspita.getIDInstancaIspita());
           azuriranaInstancaIspita.setDatumPisanjaIspita(new Date());
            azuriranaInstancaIspita.obradiRezultate();
            repo.otvoriSession();
            for(InstancaGrupePitanja igp:azuriranaInstancaIspita.getInstanceGrupaPitanja()){
                for (InstancaPitanja p:igp.getInstancePitanja()) {
                    for(InstancaOdgovora o:p.getInstanceOdgovora()){
                    repo.promjeniRucnoBezOtvaranjaIZatvaranja(o);
                    }
                    repo.promjeniRucnoBezOtvaranjaIZatvaranja(p);
                }
            repo.promjeniRucnoBezOtvaranjaIZatvaranja(igp);
            }
            repo.promjeniRucnoBezOtvaranjaIZatvaranja(azuriranaInstancaIspita);
            repo.zatvoriSession();
            request.getSession().removeAttribute("odabranaInstancaIspita");
            request.getSession().removeAttribute("odabranaInstancaPitanja");

            response.sendRedirect("KorisnikServlet");
        }

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