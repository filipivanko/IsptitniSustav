package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Admin;
import Model.Kompanija;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RootAdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();

        HttpSession session = request.getSession();

        List<Kompanija> sveKompanije = repo.dohvatiSveKompanije();
        session.setAttribute("sveKompanije", sveKompanije);

        Kompanija odabranaKompanijaUSessionu = (Kompanija) session.getAttribute("odabranaKompanija");

        if (sveKompanije.size() != 0 && odabranaKompanijaUSessionu == null) {
            session.setAttribute("odabranaKompanija", sveKompanije.get(0));

            List<Admin> adminiSaOvlaštenjemKompanije = izvuciAdmineKompanije(sveKompanije.get(0).getAdminiKompanije());
            if (session.getAttribute("odabraniAdminKompanija") == null && adminiSaOvlaštenjemKompanije.size() != 0) {
                session.setAttribute("odabraniAdminKompanija", adminiSaOvlaštenjemKompanije.get(0));
            }
        }
        if (odabranaKompanijaUSessionu != null) {
            Kompanija odabranaAzuriranaKompanija = repo.dohvatiKompanijuPoIDu(odabranaKompanijaUSessionu.getIDKompanija());
            session.setAttribute("odabranaKompanija", odabranaAzuriranaKompanija);
            if (session.getAttribute("odabraniAdminKompanija") != null) {
                Admin adminUSessionu = (Admin) session.getAttribute("odabraniAdminKompanija");
                if (adminUSessionu != null) {
                    Admin azurianiAdmin = repo.dohvatiAdminaPoIDu(adminUSessionu.getIDAdmin());
                    session.setAttribute("odabraniAdminKompanija", azurianiAdmin);
                }

            }
        }
        response.sendRedirect("rootAdminStranica.jsp");

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

    private List<Admin> izvuciAdmineKompanije(List<Admin> sviAdmini) {
        List<Admin> adminiKompanija = new ArrayList<Admin>();
        Admin admin;
        for (Object o : sviAdmini) {
            admin = (Admin) o;
            if (admin.getRazinaovlasti().equals("kompanija")) {
                adminiKompanija.add(admin);
            }

        }
        return adminiKompanija;
    }

}
