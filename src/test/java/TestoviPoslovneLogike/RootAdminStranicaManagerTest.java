package TestoviPoslovneLogike;

import DAO.Repozitorij;
import Model.Admin;
import Model.Kompanija;
import PoslovnaLogika.DodavacAdminaKompanije;
import PoslovnaLogika.RootAdmnStranicaManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class RootAdminStranicaManagerTest {

    @Test
    public void testDodavanjaSvihKompanijaUSession() {

        Kompanija kompanija = mock(Kompanija.class);
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Kompanija> sveKompanije = mock(List.class);

        when(request.getSession()).thenReturn(session);
        when(repo.dohvatiSveKompanije()).thenReturn(sveKompanije);
        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);

        RootAdmnStranicaManager rootAdminStranica = new RootAdmnStranicaManager();
        rootAdminStranica.azurirajRootAdminStranicu(request, repo);

        verify(session, times(1)).setAttribute("sveKompanije", sveKompanije);

    }

    @Test
    public void testDodavanjaRootKompanijeAkoNijeUSessionu() {

        Kompanija kompanijaIzSessiona = null;
        Kompanija rootKompanija = mock(Kompanija.class);
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Kompanija> sveKompanije = mock(List.class);

        when(request.getSession()).thenReturn(session);
        when(repo.dohvatiSveKompanije()).thenReturn(sveKompanije);
        when(sveKompanije.get(0)).thenReturn(rootKompanija);
        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanijaIzSessiona);
        when(sveKompanije.size()).thenReturn(1);

        RootAdmnStranicaManager rootAdminStranica = new RootAdmnStranicaManager();
        rootAdminStranica.azurirajRootAdminStranicu(request, repo);

        verify(session, times(1)).setAttribute("odabranaKompanija", sveKompanije.get(0));



    }

    @Test
    public void testOdabirOdabranogAdminaKompanijeAkoJeKompanijaUSessionuNuliPrviDolazakIKompanijaImaBaremJednogAdminaKompanije() {

        Kompanija kompanijaIzSessiona = null;
        Kompanija rootKompanija = mock(Kompanija.class);
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Kompanija> sveKompanije = new ArrayList<Kompanija>();
        
        
        List<Admin> adminiSaOvlaštenjemKompanije = new ArrayList<Admin>();
        Admin prviAdminUKompaniji = mock(Admin.class);
        adminiSaOvlaštenjemKompanije.add(prviAdminUKompaniji);
        sveKompanije.add(rootKompanija);
        
       
        when(request.getSession()).thenReturn(session);
        when(repo.dohvatiSveKompanije()).thenReturn(sveKompanije);
        when(prviAdminUKompaniji.getRazinaovlasti()).thenReturn("kompanija");
        when(rootKompanija.getAdminiKompanije()).thenReturn(adminiSaOvlaštenjemKompanije);
        
        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanijaIzSessiona);
        when(session.getAttribute("odabraniAdminKompanija")).thenReturn(null);
        
        RootAdmnStranicaManager rootAdminStranica = new RootAdmnStranicaManager();
        rootAdminStranica.azurirajRootAdminStranicu(request, repo);

        verify(session, times(1)).setAttribute("odabraniAdminKompanija", adminiSaOvlaštenjemKompanije.get(0));
    }

        @Test
    public void testOdabirOdabranogAdminaKompanijeAkoKompanijaUSessionuNijeNullIPostojiOdabraniAdminKompanijeUSessionu() {

        Kompanija kompanijaUSessionu = mock(Kompanija.class);
        Kompanija rootKompanija = mock(Kompanija.class);
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Kompanija> sveKompanije = new ArrayList<Kompanija>();
        
        
        List<Admin> adminiSaOvlaštenjemKompanije = new ArrayList<Admin>();
        Admin adminIzSessiona = mock(Admin.class);
        Admin prviAdminUKompaniji = mock(Admin.class);
        Admin adminIzRepoaPoIDu = mock(Admin.class);
        
        adminiSaOvlaštenjemKompanije.add(prviAdminUKompaniji);
        sveKompanije.add(rootKompanija);
        
        when(kompanijaUSessionu.getIDKompanija()).thenReturn(0);
        when(request.getSession()).thenReturn(session);
        when(repo.dohvatiSveKompanije()).thenReturn(sveKompanije);
        when(repo.dohvatiKompanijuPoIDu(0)).thenReturn(kompanijaUSessionu);
        when(repo.dohvatiAdminaPoIDu(0)).thenReturn(adminIzRepoaPoIDu);
        when(prviAdminUKompaniji.getRazinaovlasti()).thenReturn("kompanija");
        when(prviAdminUKompaniji.getIDAdmin()).thenReturn(0);
        when(rootKompanija.getAdminiKompanije()).thenReturn(adminiSaOvlaštenjemKompanije);
        
        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanijaUSessionu);
        when(session.getAttribute("odabraniAdminKompanija")).thenReturn(adminIzSessiona);
        
        RootAdmnStranicaManager rootAdminStranica = new RootAdmnStranicaManager();
        rootAdminStranica.azurirajRootAdminStranicu(request, repo);
        
        verify(session, times(1)).setAttribute("odabraniAdminKompanija", adminIzRepoaPoIDu);
    }
    
            @Test
    public void testOdabirOdabranogAdminaKompanijeAkoKompanijaUSessionuNijeNullINePostojiOdabraniAdminKompanijeUSessionu() {

        Kompanija kompanijaUSessionu = mock(Kompanija.class);
        Kompanija rootKompanija = mock(Kompanija.class);
        Kompanija kompanijaIzRepoa = mock(Kompanija.class);
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Kompanija> sveKompanije = new ArrayList<Kompanija>();
        
        
        List<Admin> adminiSaOvlaštenjemKompanije = new ArrayList<Admin>();
        Admin adminIzSessiona = null;
        Admin prviAdminUKompaniji = mock(Admin.class);
        Admin adminIzRepoaPoIDu = mock(Admin.class);
        adminiSaOvlaštenjemKompanije.add(prviAdminUKompaniji);
        sveKompanije.add(rootKompanija);
        
        when(kompanijaUSessionu.getIDKompanija()).thenReturn(0);
        when(request.getSession()).thenReturn(session);
        when(repo.dohvatiSveKompanije()).thenReturn(sveKompanije);
        when(repo.dohvatiKompanijuPoIDu(0)).thenReturn(kompanijaIzRepoa);
        when(repo.dohvatiAdminaPoIDu(0)).thenReturn(adminIzRepoaPoIDu);
        when(prviAdminUKompaniji.getRazinaovlasti()).thenReturn("kompanija");
        when(prviAdminUKompaniji.getIDAdmin()).thenReturn(0);
        when(rootKompanija.getAdminiKompanije()).thenReturn(adminiSaOvlaštenjemKompanije);
        
        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanijaUSessionu);
        when(session.getAttribute("odabraniAdminKompanija")).thenReturn(adminIzSessiona);
        
        RootAdmnStranicaManager rootAdminStranica = new RootAdmnStranicaManager();
        rootAdminStranica.azurirajRootAdminStranicu(request, repo);
        
        verify(session, never()).setAttribute("odabraniAdminKompanija", adminIzRepoaPoIDu);
    }
}
