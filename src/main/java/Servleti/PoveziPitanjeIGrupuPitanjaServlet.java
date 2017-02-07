
package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.GrupaPitanja;
import Model.Pitanje;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PoveziPitanjeIGrupuPitanjaServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            GrupaPitanja grupaPitanja = (GrupaPitanja)request.getSession().getAttribute("odabranaGrupaPitanja");
        Pitanje pitanje = (Pitanje)request.getSession().getAttribute("odabranoPitanje");
       
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        if (grupaPitanja!=null && pitanje!=null) {
            
            if (!grupaPitanja.provjeriDaLiJePovezaoPitanje(pitanje)) {
                 grupaPitanja.getPitanjaUGrupi().add(pitanje);
                
            }
            if (!pitanje.provjeriDaLiJePovezanaGrupaPitanja(grupaPitanja)) {
                 pitanje.getGrupePitanjaKojaSadrzePitanje().add(grupaPitanja);
            }
           repo.otvoriSession();
           repo.promjeniRucnoBezOtvaranjaIZatvaranja(pitanje);
           repo.promjeniRucnoBezOtvaranjaIZatvaranja(grupaPitanja);
           repo.zatvoriSession();
           
           Pitanje azuriranoPitanje = repo.dohvatiPitanjePoIDu(pitanje.getIDPitanje());
           GrupaPitanja azuriranaGrupaPitanja = repo.dohvatiGrupuPitanjaPoIDu(grupaPitanja.getIDGrupaPitanja());
           
            request.getSession().setAttribute("odabranaGrupaPitanja", azuriranaGrupaPitanja);
            request.getSession().setAttribute("odabranoPitanje",azuriranoPitanje);

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
