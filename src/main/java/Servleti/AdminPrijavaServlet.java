package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Admin;
import Model.Kompanija;
import Model.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Filip
 */
public class AdminPrijavaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        
        

        String odabranaAdminStranica;

        String korisnickoIme = request.getParameter("korisnickoIme");
        String zaporka = request.getParameter("zaporka");
        
        Admin admin = null;
        if (repo.postojiAdmin(korisnickoIme)) {
            admin = repo.dohvatiAdminaPoKorisnickomImenu(korisnickoIme);
            if (admin.getZaporka().equals(zaporka)) {
                request.getSession().setAttribute("admin", admin);
                
            } else {
                request.getSession().setAttribute("greška", "Pogrešna Zaporka");
            }
            
        } else {
            request.getSession().setAttribute("greška", "Ne postoji user");

        }
        response.sendRedirect(porslijediNaAdminStranicu(admin,request.getSession()));
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

    private String porslijediNaAdminStranicu(Admin admin, HttpSession session) {

        if(admin == null){
            return "adminPrijava.jsp";
        }
        if (admin.getRazinaovlasti().equals("grupa")) {
            session.setAttribute("odabraniAdminGrupa", admin);
            return "GrupaAdminPrviDolazakServlet";
            
        }
        if (admin.getRazinaovlasti().equals("kompanija")) {
            
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        Kompanija odabranaAzuriranaKompanija = repo.dohvatiKompanijuPoIDu(admin.getKompanija().getIDKompanija());
        session.setAttribute("odabranaKompanija", odabranaAzuriranaKompanija);
            return "KompanijaAdminServlet";
        }
        if (admin.getRazinaovlasti().equals("root")) {
            return "RootAdminServlet";
        }
        return "adminPrijava.jsp";
    }

}
