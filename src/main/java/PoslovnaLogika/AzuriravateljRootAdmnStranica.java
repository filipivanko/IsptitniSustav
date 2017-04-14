package PoslovnaLogika;

import DAO.Repozitorij;
import Model.Admin;
import Model.Kompanija;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AzuriravateljRootAdmnStranica {

    public void azurirajRootAdminStranicu(HttpServletRequest request,Repozitorij repo) throws IOException {

        HttpSession session = request.getSession();

        List<Kompanija> sveKompanije = repo.dohvatiSveKompanije();
        session.setAttribute("sveKompanije", sveKompanije);

        Kompanija odabranaKompanijaUSessionu = (Kompanija) session.getAttribute("odabranaKompanija");

        if (sveKompanije.size() != 0 && odabranaKompanijaUSessionu == null) {
            session.setAttribute("odabranaKompanija", sveKompanije.get(0));

            List<Admin> adminiSaOvlaštenjemKompanije = izvuciAdmineKompanije(sveKompanije.get(0).getAdminiKompanije());
            if (session.getAttribute("odabraniAdminKompanija") == null && adminiSaOvlaštenjemKompanije.size() != 0) {
                session.setAttribute("odabraniAdminKompanija", adminiSaOvlaštenjemKompanije.get(0));
            }
        }
        if (odabranaKompanijaUSessionu != null) {
            Kompanija odabranaAzuriranaKompanija = repo.dohvatiKompanijuPoIDu(odabranaKompanijaUSessionu.getIDKompanija());
            session.setAttribute("odabranaKompanija", odabranaAzuriranaKompanija);
            if (session.getAttribute("odabraniAdminKompanija") != null) {
                Admin adminUSessionu = (Admin) session.getAttribute("odabraniAdminKompanija");
                if (adminUSessionu != null) {
                    Admin azurianiAdmin = repo.dohvatiAdminaPoIDu(adminUSessionu.getIDAdmin());
                    session.setAttribute("odabraniAdminKompanija", azurianiAdmin);
                }

            }
        }
            
    }
        public List<Admin> izvuciAdmineKompanije(List<Admin> sviAdmini) {
        List<Admin> adminiKompanija = new ArrayList<Admin>();
        Admin admin;
        for (Object o : sviAdmini) {
            admin = (Admin) o;
            if (admin.getRazinaovlasti().equals("kompanija")) {
                adminiKompanija.add(admin);
            }

        }
        return adminiKompanija;
    }
}
