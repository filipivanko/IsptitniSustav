package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.GrupaPitanja;
import Model.Ispit;
import Model.Kompanija;
import Model.Odgovor;
import Model.Pitanje;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GrupaPitanjaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        HttpSession session = request.getSession();

        Kompanija odabranaKompanijaUSessionu = (Kompanija) session.getAttribute("odabranaKompanija");
        if (odabranaKompanijaUSessionu != null) {

            Kompanija azuriranaKompanija = repo.dohvatiKompanijuPoIDu(odabranaKompanijaUSessionu.getIDKompanija());
            List<GrupaPitanja> sveGrupePitanja = azuriranaKompanija.getGrupePitanjaKompanije();
            session.setAttribute("odabranaKompanija", azuriranaKompanija);

            GrupaPitanja odabranaGrupaPitanjaUSessionu = (GrupaPitanja) session.getAttribute("odabranaGrupaPitanja");

            if (odabranaGrupaPitanjaUSessionu != null) {
                GrupaPitanja odabranaAzuriranaGrupaPitanja = repo.dohvatiGrupuPitanjaPoIDu(odabranaGrupaPitanjaUSessionu.getIDGrupaPitanja());
                session.setAttribute("odabranaGrupaPitanja", odabranaAzuriranaGrupaPitanja);
            }
            if (sveGrupePitanja.size() != 0 && odabranaGrupaPitanjaUSessionu == null) {
                session.setAttribute("odabranaGrupaPitanja", sveGrupePitanja.toArray()[0]);
            }

            List<Pitanje> svaPitanja = azuriranaKompanija.getPitanjaUKompaniji();

            Pitanje odabranoPitanjeUSessionu = (Pitanje) session.getAttribute("odabranoPitanje");
            Odgovor odabraniOdgovorUSessionu = (Odgovor) session.getAttribute("odabraniOdgovor");

            if (odabranoPitanjeUSessionu != null) {
                Pitanje odabranoAzuriranoPitanje = repo.dohvatiPitanjePoIDu(odabranoPitanjeUSessionu.getIDPitanje());
                session.setAttribute("odabranoPitanje", odabranoAzuriranoPitanje);
                if (odabraniOdgovorUSessionu != null) {
                    Odgovor odabraniAzuriraniOdgovor = repo.dohvatiOdgovorPoIDu(odabraniOdgovorUSessionu.getIDOdgovor());
                    session.setAttribute("odabraniOdgovor", odabraniAzuriraniOdgovor);
                } else if (odabranoAzuriranoPitanje.getOdgovori().size() != 0) {
                    session.setAttribute("odabraniOdgovor", odabranoAzuriranoPitanje.getOdgovori().get(0));
                }
            }

            if (svaPitanja.size() != 0 && odabranoPitanjeUSessionu == null) {
                session.setAttribute("odabranoPitanje", svaPitanja.toArray()[0]);
                Pitanje prvoPitanje = (Pitanje) svaPitanja.toArray()[0];
                if (prvoPitanje.getOdgovori().size() != 0) {
                    session.setAttribute("odabraniOdgovor", prvoPitanje.getOdgovori().toArray()[0]);
                }
            }
            List<Ispit> sviIspitiKompanije = azuriranaKompanija.getIspitiKompanije();
            Ispit odabraniIspitUSessionu = (Ispit) session.getAttribute("odabraniIspit");

            if (odabraniIspitUSessionu != null) {
                Ispit odabraniAzurirniIspit = repo.dohvatiIspitPoIDu(odabraniIspitUSessionu.getIDIspit());
                session.setAttribute("odabraniIspit", odabraniAzurirniIspit);

            }

            if (sviIspitiKompanije.size() != 0 && odabraniIspitUSessionu == null) {
                session.setAttribute("odabraniIspit", sviIspitiKompanije.toArray()[0]);
            }
        }
        response.sendRedirect("grupaPitanja.jsp");
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
    }// </editor-fold>

}
