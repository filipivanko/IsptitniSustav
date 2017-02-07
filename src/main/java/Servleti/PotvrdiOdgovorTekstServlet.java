package Servleti;

import DAO.DohvatiRepozitorijFactory;
import DAO.Repozitorij;
import DAO.RepozitorijFactoriy;
import Model.InstancaGrupePitanja;
import Model.InstancaIspita;
import Model.InstancaOdgovora;
import Model.InstancaPitanja;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PotvrdiOdgovorTekstServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
         RepozitorijFactoriy repoFactoriy = DohvatiRepozitorijFactory.dohvati();
        Repozitorij repo =repoFactoriy.stvoriRepozitorij();
        
        String odgovor = request.getParameter("odgovor");
        InstancaPitanja odabranaInstancaPitanja = (InstancaPitanja) request.getSession().getAttribute("odabranaInstancaPitanja");
        if (request.getParameter("odgovor") != null && odabranaInstancaPitanja != null) {
            InstancaPitanja azurirnaInstancaPitanja = repo.dohvatiInstancuPitanjaPoIDu(odabranaInstancaPitanja.getIDInstancaPitanja());

            InstancaOdgovora tocanOdgovor = nadjiTocanOdgovor(odabranaInstancaPitanja);
            
            odgovor = normalizirajOdgovor(odgovor);
            azurirnaInstancaPitanja.setKorisnikovOdgovor(odgovor);
            if(tocanOdgovor!=null){ 
                azurirnaInstancaPitanja.setTocanOdgovor(tocanOdgovor.getTekst());
            }
           
            boolean test = tocanOdgovor.getTekst().equals(odgovor);
            if (tocanOdgovor != null && tocanOdgovor.getTekst().equals(odgovor)) {
                postaviPitanjeKaoTocnoOdgovoreno(azurirnaInstancaPitanja);
            } else {
                postaviPitanjeKaoPogresnoOdgovoreno(azurirnaInstancaPitanja);

            }
            repo.otvoriSession();
            
            repo.promjeniRucnoBezOtvaranjaIZatvaranja(azurirnaInstancaPitanja);
            for(InstancaOdgovora o: azurirnaInstancaPitanja.getInstanceOdgovora()){
            repo.promjeniRucnoBezOtvaranjaIZatvaranja(o);
            }
            repo.zatvoriSession();
            
    InstancaIspita azururanaInstancaIspita = repo.dohvatiInstancuIspitaPoIDu(
                    azurirnaInstancaPitanja.
                    getInstancaGrupePitanja().
                    getInstancaIspita().
                    getIDInstancaIspita());
            request.getSession().setAttribute("odabranaInstancaIspita", azururanaInstancaIspita);
            odaberiSlijedecePitanje(azururanaInstancaIspita, request);
        }
        response.sendRedirect("ispit.jsp");
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

    private InstancaOdgovora nadjiTocanOdgovor(InstancaPitanja odabranaInstancaPitanja) {
        List<InstancaOdgovora> sviOdgovori = odabranaInstancaPitanja.getInstanceOdgovora();
        InstancaOdgovora tocanOdgovor = null;
        for (InstancaOdgovora i : sviOdgovori) {
            if (i.getTocan().equals("tocan")) {
                tocanOdgovor = i;
                break;

            }

        }
        return tocanOdgovor;
    }

    private String normalizirajOdgovor(String odgovor) {
        odgovor = odgovor.trim();
        odgovor = odgovor.toLowerCase();

        return odgovor;
    }

    private void postaviPitanjeKaoTocnoOdgovoreno(InstancaPitanja instancaPitanja) {
        List<InstancaOdgovora> sviOdgovoriNaPitanje = instancaPitanja.getInstanceOdgovora();
        for (InstancaOdgovora io : sviOdgovoriNaPitanje) {
            io.setTocnoOdgovoren(true);
        }
    }

    private void postaviPitanjeKaoPogresnoOdgovoreno(InstancaPitanja instancaPitanja) {
        List<InstancaOdgovora> sviOdgovoriNaPitanje = instancaPitanja.getInstanceOdgovora();
        for (InstancaOdgovora io : sviOdgovoriNaPitanje) {
            io.setTocnoOdgovoren(false);
        }
    }
        private void odaberiSlijedecePitanje(InstancaIspita azururanaInstancaIspita, HttpServletRequest request) {
        InstancaPitanja instancaPitanja = (InstancaPitanja) request.getSession().getAttribute("odabranaInstancaPitanja");
        
        if (instancaPitanja != null) {
            InstancaGrupePitanja igp = instancaPitanja.getInstancaGrupePitanja();
            List<InstancaPitanja> svaPitanja = igp.getInstancePitanja();
            int indexPitanja = svaPitanja.lastIndexOf(instancaPitanja);
            if (indexPitanja < svaPitanja.size() - 1) {
                request.getSession().setAttribute("odabranaInstancaPitanja", svaPitanja.get(indexPitanja + 1));
            } else {
                InstancaIspita i = igp.getInstancaIspita();
                List<InstancaGrupePitanja> sveGrupePitanja = i.getInstanceGrupaPitanja();
                int indexGrupePitanja = sveGrupePitanja.lastIndexOf(igp);
                if (indexGrupePitanja < sveGrupePitanja.size() - 1) {
                    request.getSession().setAttribute("odabranaInstancaPitanja", sveGrupePitanja.get(indexGrupePitanja + 1).getInstancePitanja().get(0));
                }
                
            }
            
        }
    }

}
