/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.Admin;
import Model.Grupa;
import Model.Ispit;
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


public class GrupaAdminPrviDolazakServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        HttpSession session = request.getSession();

        Admin odabraniAdminUSessionu = (Admin) session.getAttribute("odabraniAdminGrupa");
        session.removeAttribute("odabranaGrupa");
        
        if (odabraniAdminUSessionu != null) {
                Admin odabraniAzuriraniAdmin = repo.dohvatiAdminaPoIDu(odabraniAdminUSessionu.getIDAdmin());
                session.setAttribute("odabraniAdminGrupa", odabraniAzuriraniAdmin);

                
                    List <Grupa> sveAdminoveGrupe = odabraniAzuriraniAdmin.getGrupeKojePripadajuAdminu();
                    List<Ispit> sviIspitiGrupe;
                    Grupa odabranaGrupa;
                    if(sveAdminoveGrupe.size()!=0){
                        
                    odabranaGrupa = (Grupa)sveAdminoveGrupe.toArray()[0];
                    session.setAttribute("odabranaGrupa", odabranaGrupa);
                    
                    sviIspitiGrupe =  odabranaGrupa.getIspitiGrupe();
                    
                    List<Korisnik> korisniciGrupe =  odabranaGrupa.getClanoviGrupe();
                    
                      if (korisniciGrupe.size() != 0) {
                        session.setAttribute("odabraniKorisnk", korisniciGrupe.toArray()[0]);
                    } 
                      
                       if (sviIspitiGrupe.size() != 0) {
                        session.setAttribute("odabraniIspit", sviIspitiGrupe.toArray()[0]);
                    } 
                       
                    }
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
