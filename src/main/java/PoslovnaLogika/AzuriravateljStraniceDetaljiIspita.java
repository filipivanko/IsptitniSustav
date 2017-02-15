/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PoslovnaLogika;

import DAO.Repozitorij;
import Model.InstancaIspita;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Filip
 */
public class AzuriravateljStraniceDetaljiIspita {

    public void azurirajInstancuIspita(HttpServletRequest request, HttpServletResponse response, Repozitorij repo) throws IOException {

        if (request.getParameter("IDZavrsenogIspita") != null) {
            int idOdabraneInstanceIspita = Integer.parseInt(request.getParameter("IDZavrsenogIspita"));
            InstancaIspita instancaIspita = repo.dohvatiInstancuIspitaPoIDu(idOdabraneInstanceIspita);
            request.getSession().setAttribute("odabranaInstancaIspita", instancaIspita);
        }
        response.sendRedirect("detaljiIspita.jsp");
    }
}
