
package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Grupa;
import Model.Kompanija;
import Model.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdministracijaKorisnikaServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        HttpSession session = request.getSession();

        Kompanija odabranaKompanijaUSessionu = (Kompanija) session.getAttribute("odabranaKompanija");
        if (odabranaKompanijaUSessionu != null) {

            Kompanija azuriranaKompanija = repo.dohvatiKompanijuPoIDu(odabranaKompanijaUSessionu.getIDKompanija());
            List<Grupa> sveGrupe = (List<Grupa>) azuriranaKompanija.getGrupeUKompaniji();
            session.setAttribute("odabranaKompanija", azuriranaKompanija);

            Grupa odabranaGrupaUSessionu = (Grupa) session.getAttribute("odabranaGrupa");

            if (odabranaGrupaUSessionu != null) {
                Grupa odabranaAzuriranaGrupa = repo.dohvatiGrupuPoIDu(odabranaGrupaUSessionu.getIDGrupa());
                session.setAttribute("odabranaGrupa", odabranaAzuriranaGrupa);
            }
            if (sveGrupe.size() != 0 && odabranaGrupaUSessionu == null) {
                session.setAttribute("odabranaGrupa", sveGrupe.toArray()[0]);
            }

           List<Korisnik> sviKorisnici = (List<Korisnik>) azuriranaKompanija.getKorisniciKompanije();
           Korisnik odabraniKorisnikUSessionu = (Korisnik) session.getAttribute("odabraniKorisnk");
            
            if (odabraniKorisnikUSessionu != null) {
                Korisnik odabraniAzuriraniKorisnik = repo.dohvatiKorisnikaPoIDu(odabraniKorisnikUSessionu.getIDKorisnik());
                session.setAttribute("odabraniKorisnk", odabraniAzuriraniKorisnik);
            }
            
            if (sviKorisnici.size() != 0 && odabraniKorisnikUSessionu == null) {
                session.setAttribute("odabraniKorisnk", sviKorisnici.toArray()[0]);
            }

    }
response.sendRedirect("upravljanjeKorisnicima.jsp");
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
