package PoslovnaLogika;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Repozitorij;
import Model.Korisnik;

public class PrijavaKorisnikaUSustav {
	  private String porslijediNaKorisnickuStranicu(boolean ispravan) {

	        if(ispravan){
	            return "KorisnikServlet";
	        }else{
	        return "korisnikPrijava.jsp";
	        } 
	    }
	    
	     public void prijaviKorisnikaUSustav(HttpServletRequest request,HttpServletResponse response,Repozitorij repo) throws IOException{
	    	 
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
}
