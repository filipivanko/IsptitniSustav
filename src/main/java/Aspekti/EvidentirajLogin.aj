package Aspekti;

import java.util.Calendar;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Repozitorij;
import Model.Admin;
import Model.Korisnik;

public aspect EvidentirajLogin {

	pointcut PrijavljivanjeAdmina(HttpServletRequest request):
		 args(request) && call(String PoslovnaLogika.PrijavaAdminaUSustav.PrijaviAdmina(HttpServletRequest));

	String around(HttpServletRequest request):
		PrijavljivanjeAdmina(request){

		String povratniString = proceed(request);

		HttpSession session = request.getSession();
		if (povratniString != "adminPrijava.jsp") {
			if (session.getAttribute("odabraniAdminRoot") != null) {

				Admin admin = (Admin) session.getAttribute("odabraniAdminRoot");
				request.getServletContext().log(Calendar.getInstance().getTime() + " Prijavljen Admin Root "
						+ admin.getKorisnickoIme() + " Id " + admin.getIDAdmin());
			}
			if (session.getAttribute("odabraniAdminKompanija") != null) {

				Admin admin = (Admin) session.getAttribute("odabraniAdminKompanija");
				request.getServletContext().log(Calendar.getInstance().getTime() + " Prijavljen Admin Kompanije "
						+ admin.getKorisnickoIme() + " Id " + admin.getIDAdmin());
			}
			if (session.getAttribute("odabraniAdminGrupa") != null) {

				Admin admin = (Admin) session.getAttribute("odabraniAdminGrupa");
				request.getServletContext().log(Calendar.getInstance().getTime() + " Prijavljen Admin grupe "
						+ admin.getKorisnickoIme() + " Id " + admin.getIDAdmin());
			}

		}
		return povratniString;
	}

	pointcut PrijavljivanjeKorisnika(HttpServletRequest request, HttpServletResponse response, Repozitorij repo):
		 args(request,response,repo) && call(void PoslovnaLogika.PrijavaKorisnikaUSustav.prijaviKorisnikaUSustav(HttpServletRequest,HttpServletResponse,Repozitorij));

	void around(HttpServletRequest request, HttpServletResponse response, Repozitorij repo):
		PrijavljivanjeKorisnika(request,response,repo){
		proceed(request, response, repo);
		Calendar.getInstance().getTime();
		HttpSession session = request.getSession();
		if (session.getAttribute("korisnik") != null) {
			Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
			request.getServletContext().log(Calendar.getInstance().getTime() + " Prijavljen Admin grupe "
					+ korisnik.getKorisnickoIme() + " Id " + korisnik.getIDKorisnik());
		}

	}

}
