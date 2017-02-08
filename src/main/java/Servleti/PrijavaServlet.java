
package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Helper.PronalazacIzvoraRequesta;
import Model.Admin;
import Model.Grupa;
import Model.GrupaPitanja;
import Model.InstancaIspita;
import Model.Ispit;
import Model.Kompanija;
import Model.Korisnik;
import Model.Odgovor;
import Model.Pitanje;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class PrijavaServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   // napuniBazu();
       
          
        HttpSession session = request.getSession();
        
        RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();

        String korisnickoIme = request.getParameter("korisnickoIme");
        String zaporka = request.getParameter("zaporka");
        

        Korisnik korisnik=null;
        boolean ispravanUpis;
        if (repo.postojiKorisnik(korisnickoIme)) {
            korisnik = repo.dohvatiKorisnikaPoKorisnickomImenu(korisnickoIme);
            if (korisnik.getZaporka().equals(zaporka)) {
                request.getSession().setAttribute("korisnik", korisnik);
                ispravanUpis=true;
                
            } else {
                request.getSession().setAttribute("greška", "Pogrešna Zaporka");
                ispravanUpis=false;
            }
            
        } else {
            request.getSession().setAttribute("greška", "Ne postoji user");
            ispravanUpis=false;

        }
        
        response.sendRedirect(porslijediNaKorisnickuStranicu(ispravanUpis));
        
    }
    
     private String porslijediNaKorisnickuStranicu(boolean ispravan) {

        if(ispravan){
            return "KorisnikServlet";
        }else{
        return "korisnikPrijava.jsp";
        } 
    }
    
    private void napuniBazu() {
      
        
        RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
      
       Kompanija kompanija = new Kompanija("Kompanija 1", "Zagrab", "12345678910"); 
       Grupa grupa = new Grupa("Osnovna Grupa", kompanija);
       Korisnik korisnik = new Korisnik("ime", "prezime", "adresa", "strucnaSprema", "zanimanje", "oib", "korisnik", "korisnik", kompanija);
       Admin adminGrupe = new Admin("adminGrupe", "adminGrupe", "grupa",kompanija);
       Admin adminKompanije = new Admin("adminKompanije", "adminKompanije", "kompanija",kompanija);
       Admin adminRoot = new Admin("adminRoot", "adminRoot", "root",kompanija);
       
       Ispit ispit1 = new Ispit("Ispiit 1", 0.5d,kompanija,2);

       korisnik.getGrupeKojimaKorisnikPripada().add(grupa);
       grupa.getClanoviGrupe().add(korisnik);
       
       
       kompanija.getIspitiKompanije().add(ispit1);
       ispit1.setKompanijaKojojIspitPripada(kompanija);
       
       grupa.getIspitiGrupe().add(ispit1);
       ispit1.getGrupeKojeKoristeIspit().add(grupa);
       
       kompanija.getAdminiKompanije().add(adminKompanije);
       adminKompanije.setKompanija(kompanija);
       
       kompanija.getAdminiKompanije().add(adminGrupe);
       adminGrupe.setKompanija(kompanija);
       
       kompanija.getAdminiKompanije().add(adminRoot);
       adminRoot.setKompanija(kompanija);
       
       
       Pitanje pitanje = new Pitanje("Koje je Ovo pitnje", "", "radio",kompanija);
       GrupaPitanja grupaPitanja = new GrupaPitanja("Glupa Pitanja",1,kompanija);
       
       pitanje.getGrupePitanjaKojaSadrzePitanje().add(grupaPitanja);
       grupaPitanja.getPitanjaUGrupi().add(pitanje);
       
       ispit1.getGrupePitanjaUIspitu().add(grupaPitanja);
       grupaPitanja.getIspitiKojiSadrzeGrupuPitanja().add(ispit1);

       
       Odgovor odgovor = new Odgovor("text", "glupi odgovoer", "", pitanje, "tocan");
       pitanje.getOdgovori().add(odgovor);
       
       
       
      
       kompanija.getKorisniciKompanije().add(korisnik);
       grupa.getClanoviGrupe().add(korisnik);
       grupa.getAdminiGrupe().add(adminGrupe);
       adminGrupe.getGrupeKojePripadajuAdminu().add(grupa);
      
       repo.otvoriSession();
       repo.spremiRucnoBezOtvaranjaIZatvaranja(odgovor);
       repo.spremiRucnoBezOtvaranjaIZatvaranja(pitanje);
       repo.spremiRucnoBezOtvaranjaIZatvaranja(grupaPitanja);
       repo.spremiRucnoBezOtvaranjaIZatvaranja(ispit1);
       repo.spremiRucnoBezOtvaranjaIZatvaranja(kompanija);
       repo.spremiRucnoBezOtvaranjaIZatvaranja(grupa);
       repo.spremiRucnoBezOtvaranjaIZatvaranja(korisnik);
       repo.spremiRucnoBezOtvaranjaIZatvaranja(adminGrupe);
       repo.spremiRucnoBezOtvaranjaIZatvaranja(adminKompanije);
       repo.spremiRucnoBezOtvaranjaIZatvaranja(adminRoot);
       repo.zatvoriSession();
       InstancaIspita instancaIspita =  new InstancaIspita(ispit1,korisnik,repo);
        
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
