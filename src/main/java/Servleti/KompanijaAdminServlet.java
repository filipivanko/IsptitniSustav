package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Admin;
import Model.Grupa;
import Model.Ispit;
import Model.Kompanija;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KompanijaAdminServlet extends HttpServlet {

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

           List<Admin> sviAdmini = (List<Admin>) azuriranaKompanija.getAdminiKompanije();
            Admin odabraniAdminUSessionu = (Admin) session.getAttribute("odabraniAdminGrupa");

            if (odabraniAdminUSessionu != null) {
                Admin odabraniAzuriraniAdmin = repo.dohvatiAdminaPoIDu(odabraniAdminUSessionu.getIDAdmin());
                session.setAttribute("odabraniAdminGrupa", odabraniAzuriraniAdmin);
            }

            if (sviAdmini.size() != 0 && odabraniAdminUSessionu == null) {
                List<Admin> adminiGrupa = izvuciAdmineGrupa(sviAdmini);

                if (adminiGrupa.size() != 0) {
                    session.setAttribute("odabraniAdminGrupa", adminiGrupa.get(0));
                }

            }

            List<Ispit> sviIspitiKompanije = (List<Ispit>) azuriranaKompanija.getIspitiKompanije();
            Ispit odabraniIspitUSessionu = (Ispit) session.getAttribute("odabraniIspit");

            if (odabraniIspitUSessionu != null) {
                Ispit odabraniAzurirniIspit = repo.dohvatiIspitPoIDu(odabraniIspitUSessionu.getIDIspit());
                session.setAttribute("odabraniIspit", odabraniAzurirniIspit);

            }

            if (sviIspitiKompanije.size() != 0 && odabraniIspitUSessionu == null) {
                session.setAttribute("odabraniIspit", sviIspitiKompanije.toArray()[0]);
            }

        }

        response.sendRedirect("kompanijaAdminStranica.jsp");
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

    private List<Admin> izvuciAdmineGrupa(List<Admin> sviAdmini) {
        List<Admin> adminiGrupa = new ArrayList<Admin>();
        Admin admin;
        for (Object o : sviAdmini) {
            admin = (Admin) o;
            if (admin.getRazinaovlasti().equals("grupa")) {
                adminiGrupa.add(admin);
            }

        }
        return adminiGrupa;
    }

}
