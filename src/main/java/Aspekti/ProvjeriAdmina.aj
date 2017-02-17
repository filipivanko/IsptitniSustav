package Aspekti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public aspect ProvjeriAdmina {
	pointcut ProvjeriRootAdminaProcesRequest(HttpServletRequest request, HttpServletResponse response):
		 args(request , response) && call(* *.processRequest(HttpServletRequest , HttpServletResponse))&&
				 withincode(* Servleti.RootAdminServlet.* (..))&&
				 !withincode(* Servleti.AdminPrijavaServlet.* (..));

	pointcut ProvjeriKompanijaAdminaProcesRequest(HttpServletRequest request, HttpServletResponse response):
		 args(request , response) && call(* *.processRequest(HttpServletRequest , HttpServletResponse))&&
				 !withincode(* Servleti.RootAdminServlet.* (..)) &&
				 !withincode(* Servleti.AdminPrijavaServlet.* (..))&&
				 !withincode(* Servleti.GrupaAdminServlet.* (..))&&
				 !withincode(* Servleti.GrupaAdminPrviDolazakServlet.* (..))&&
				 !withincode(* Servleti.KorisnikServlet.* (..))&&
				 !withincode(* Servleti.KorisnikoviRezutatiServlet.* (..))&&
				 !withincode(* Servleti.PrijavaServlet.* (..));

	pointcut ProvjeriGrupaAdminaProcesRequest(HttpServletRequest request, HttpServletResponse response):
		 args(request , response) && call(* *.processRequest(HttpServletRequest , HttpServletResponse))&&
		 !withincode(* Servleti.AdminPrijavaServlet.* (..))&&(
				 withincode(* Servleti.AdminPrijavaServlet.* (..)) ||
				 withincode(* Servleti.GrupaAdminServlet.* (..)) ||
				 withincode(* Servleti.GrupaAdminPrviDolazakServlet.* (..)));

	pointcut ProvjeriKorisnikaProcesRequest(HttpServletRequest request, HttpServletResponse response):
		 args(request , response) && call(* *.processRequest(HttpServletRequest , HttpServletResponse))&&
				 !withincode(* Servleti.PrijavaServlet.* (..))&&
		 
				( withincode(* Servleti.KorisnikServlet.* (..)) ||
				 withincode(* Servleti.KorisnikoviRezutatiServlet.* (..)));

	void around(HttpServletRequest request, HttpServletResponse response):
		ProvjeriRootAdminaProcesRequest(request, response){

		if (request.getSession().getAttribute("odabraniAdminRoot") == null) {
			try {
				response.sendRedirect("adminPrijava.jsp");
			} catch (Exception ex) {

			}
		} else {

			proceed(request, response);

		}

	}

	void around(HttpServletRequest request, HttpServletResponse response):
		ProvjeriKompanijaAdminaProcesRequest(request, response){

		if (request.getSession().getAttribute("odabraniAdminRoot") == null
				&& request.getSession().getAttribute("odabraniAdminKompanija") == null) {
			try {
				response.sendRedirect("adminPrijava.jsp");
			} catch (Exception ex) {

			}
		} else {

			proceed(request, response);

		}

	}

	void around(HttpServletRequest request, HttpServletResponse response):
		ProvjeriGrupaAdminaProcesRequest(request, response){

		if (request.getSession().getAttribute("odabraniAdminRoot") == null
				&& request.getSession().getAttribute("odabraniAdminKompanija") == null
				&& request.getSession().getAttribute("odabraniAdminGrupa") == null) {
			try {
				response.sendRedirect("adminPrijava.jsp");
			} catch (Exception ex) {

			}
		} else {

			proceed(request, response);

		}

	}

	void around(HttpServletRequest request, HttpServletResponse response):
		ProvjeriKorisnikaProcesRequest(request, response){

		if (request.getSession().getAttribute("odabraniAdminRoot") == null
				&& request.getSession().getAttribute("odabraniAdminKompanija") == null
				&& request.getSession().getAttribute("odabraniAdminGrupa") == null
				&& request.getSession().getAttribute("korisnik") == null) {
			try {
				response.sendRedirect("korisnikPrijava.jsp");
			} catch (Exception ex) {

			}
		} else {

			proceed(request, response);

		}

	}

}
