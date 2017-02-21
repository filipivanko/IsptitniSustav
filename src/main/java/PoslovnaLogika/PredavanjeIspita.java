package PoslovnaLogika;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Repozitorij;
import Model.InstancaGrupePitanja;
import Model.InstancaIspita;
import Model.InstancaOdgovora;
import Model.InstancaPitanja;

public class PredavanjeIspita {
	public void predajIspit(HttpServletRequest request, HttpServletResponse response, Repozitorij repo)
			throws IOException {
		InstancaIspita instancaIspita = (InstancaIspita) request.getSession().getAttribute("odabranaInstancaIspita");
		if (instancaIspita != null) {
			InstancaIspita azuriranaInstancaIspita = repo
					.dohvatiInstancuIspitaPoIDu(instancaIspita.getIDInstancaIspita());
			azuriranaInstancaIspita.setDatumPisanjaIspita(new Date());
			azuriranaInstancaIspita.obradiRezultate();
			repo.otvoriSession();
			for (InstancaGrupePitanja igp : azuriranaInstancaIspita.getInstanceGrupaPitanja()) {
				for (InstancaPitanja p : igp.getInstancePitanja()) {
					for (InstancaOdgovora o : p.getInstanceOdgovora()) {
						repo.promjeniRucnoBezOtvaranjaIZatvaranja(o);
					}
					repo.promjeniRucnoBezOtvaranjaIZatvaranja(p);
				}
				repo.promjeniRucnoBezOtvaranjaIZatvaranja(igp);
			}
			repo.promjeniRucnoBezOtvaranjaIZatvaranja(azuriranaInstancaIspita);
			repo.zatvoriSession();
			request.getSession().removeAttribute("odabranaInstancaIspita");
			request.getSession().removeAttribute("odabranaInstancaPitanja");

			response.sendRedirect("KorisnikServlet");
		}

	}

}
