package Aspekti;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Repozitorij;
import Model.InstancaIspita;
import Model.Korisnik;


public aspect EvidentirajPredajuIspita {
	pointcut PredavanjeIspita(HttpServletRequest request, HttpServletResponse response,Repozitorij repo):
		 args(request,response,repo) && call(void PoslovnaLogika.PredavanjeIspita.predajIspit(HttpServletRequest , HttpServletResponse, Repozitorij));
	
	
	
	after(HttpServletRequest request, HttpServletResponse response,Repozitorij repo):PredavanjeIspita(request,response,repo){

		InstancaIspita ispit =(InstancaIspita)request.getSession().getAttribute("odabranaInstancaIspita");
		Korisnik korisnik = (Korisnik)request.getSession().getAttribute("korisnik");
		Logger.getLogger(repo.getClass().getName()).log(Level.SEVERE, null,
				"Korisnik ID: " + korisnik.getIDKorisnik()
				+"|| Ime i Prezime: "+korisnik.getIme()+" "+korisnik.getPrezime()
				+"|| predao je ispit:"+ispit.getNaziv()
				+ "|| Da li je polozio:"+ispit.isPolozen());

	}
	
	
}
