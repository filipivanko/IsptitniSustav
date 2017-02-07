package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Kompanija;
import Model.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DodajKorisnikaServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        HttpSession session = request.getSession();
        Kompanija kompanija = (Kompanija)session.getAttribute("odabranaKompanija");
        
        if(kompanija!=null){
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        
         String adresa = request.getParameter("adresa");
         String strucnaSprema = request.getParameter("strucnaSprema");
         String zanimanje = request.getParameter("zanimanje");
         String oib = request.getParameter("oib"); 
         String korisnickoIme = request.getParameter("korisnickoIme");
         String zaporka = request.getParameter("zaporka");

            

            
        Korisnik noviKorisnik = new Korisnik(ime, prezime, adresa, strucnaSprema, zanimanje, oib, korisnickoIme, zaporka, kompanija);
        kompanija.getKorisniciKompanije().add(noviKorisnik);
        
        
        repo.otvoriSession();
        session.setAttribute("odabraniKorisnk", noviKorisnik);
        
        repo.promjeniRucnoBezOtvaranjaIZatvaranja(kompanija);
        repo.spremiRucnoBezOtvaranjaIZatvaranja(noviKorisnik);
        repo.zatvoriSession();
        }
        
        
        response.sendRedirect("AdministracijaKorisnikaServlet");
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
