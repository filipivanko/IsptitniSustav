package TestoviPoslovneLogike;

import Mock.MockRepozitorij;
import Model.Admin;
import Model.Kompanija;
import PoslovnaLogika.PrijavaAdminaUSustav;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PrijavaAdminaUSustavTest {

    @Test
    public void testPrijaveAdminaSaPogresnomLozinkom() {

        MockRepozitorij repo = new MockRepozitorij();
        HttpServletRequest request = mock(HttpServletRequest.class);
        PrijavaAdminaUSustav prijava = new PrijavaAdminaUSustav();

        
        Admin admin = repo.dohvatiAdminaPoKorisnickomImenu("");
        when(admin.getKorisnickoIme()).thenReturn("PostojectAdmin");
        when(admin.getZaporka()).thenReturn("IspravnaZaporka");
        when(admin.getKompanija()).thenReturn(repo.dohvatiKompanijuPoIDu(0));
        when(admin.getRazinaovlasti()).thenReturn("grupa");
        
        when(request.getParameter("korisnickoIme")).thenReturn("PostojectAdmin");
        when(request.getParameter("zaporka")).thenReturn("NeispravnaZaporka");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        assertEquals("adminPrijava.jsp", prijava.PrijaviAdmina(request, repo));
    }

    @Test
    public void testPrijaveAdminaSaNeispravimKorisnickimImenom() {

        MockRepozitorij repo = new MockRepozitorij();
        HttpServletRequest request = mock(HttpServletRequest.class);
        PrijavaAdminaUSustav prijava = new PrijavaAdminaUSustav();

        Admin admin = repo.dohvatiAdminaPoKorisnickomImenu("");
        when(admin.getKorisnickoIme()).thenReturn("PostojectAdmin");
        when(admin.getZaporka()).thenReturn("IspravnaZaporka");
        when(admin.getKompanija()).thenReturn(repo.dohvatiKompanijuPoIDu(0));
        when(admin.getRazinaovlasti()).thenReturn("grupa");
        
        
        when(request.getParameter("korisnickoIme")).thenReturn("NePostojectAdmin");
        when(request.getParameter("zaporka")).thenReturn("IspravnaZaporka");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        assertEquals("adminPrijava.jsp", prijava.PrijaviAdmina(request, repo));
    }

    @Test
    public void testIspravnaPrijavaAdminaGrupe() {

        MockRepozitorij repo = new MockRepozitorij();
        HttpServletRequest request = mock(HttpServletRequest.class);
        PrijavaAdminaUSustav prijava = new PrijavaAdminaUSustav();

        
        Admin admin = repo.dohvatiAdminaPoKorisnickomImenu("");
        when(admin.getKorisnickoIme()).thenReturn("PostojectAdmin");
        when(admin.getZaporka()).thenReturn("IspravnaZaporka");
        when(admin.getKompanija()).thenReturn(repo.dohvatiKompanijuPoIDu(0));
        when(admin.getRazinaovlasti()).thenReturn("grupa");
        
        
        when(request.getParameter("korisnickoIme")).thenReturn("PostojectAdmin");
        when(request.getParameter("zaporka")).thenReturn("IspravnaZaporka");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        assertEquals("GrupaAdminPrviDolazakServlet", prijava.PrijaviAdmina(request, repo));
    }

    @Test
    public void testIspravnaPrijavaAdminaKompanije() {

        MockRepozitorij repo = new MockRepozitorij();
        HttpServletRequest request = mock(HttpServletRequest.class);
        PrijavaAdminaUSustav prijava = new PrijavaAdminaUSustav();

        
        
        Admin admin = repo.dohvatiAdminaPoKorisnickomImenu("");
        when(admin.getKorisnickoIme()).thenReturn("PostojectAdmin");
        when(admin.getZaporka()).thenReturn("IspravnaZaporka");
        when(admin.getKompanija()).thenReturn(repo.dohvatiKompanijuPoIDu(0));
        when(admin.getRazinaovlasti()).thenReturn("kompanija");
        
        when(request.getParameter("korisnickoIme")).thenReturn("PostojectAdmin");
        when(request.getParameter("zaporka")).thenReturn("IspravnaZaporka");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        assertEquals("KompanijaAdminServlet", prijava.PrijaviAdmina(request, repo));
    }

    @Test
    public void testIspravnaPrijavaAdminaRoot() {

        MockRepozitorij repo = new MockRepozitorij();
        HttpServletRequest request = mock(HttpServletRequest.class);
        PrijavaAdminaUSustav prijava = new PrijavaAdminaUSustav();

        Admin admin = repo.dohvatiAdminaPoKorisnickomImenu("");
        when(admin.getKorisnickoIme()).thenReturn("PostojectAdmin");
        when(admin.getZaporka()).thenReturn("IspravnaZaporka");
        when(admin.getKompanija()).thenReturn(repo.dohvatiKompanijuPoIDu(0));
        when(admin.getRazinaovlasti()).thenReturn("root");
        
        when(request.getParameter("korisnickoIme")).thenReturn("PostojectAdmin");
        when(request.getParameter("zaporka")).thenReturn("IspravnaZaporka");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        assertEquals("RootAdminServlet", prijava.PrijaviAdmina(request, repo));
    }
}
