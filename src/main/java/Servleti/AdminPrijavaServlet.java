package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Admin;
import Model.Kompanija;
import Model.Korisnik;
import PoslovnaLogika.PrijavaAdminaUSustav;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminPrijavaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrijavaAdminaUSustav prijava = new PrijavaAdminaUSustav();
        response.sendRedirect(prijava.PrijaviAdmina(request));
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
