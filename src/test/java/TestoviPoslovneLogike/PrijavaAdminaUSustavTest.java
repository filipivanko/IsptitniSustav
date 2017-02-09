package TestoviPoslovneLogike;

import Mock.MockRepozitorij;
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

        repo.setAdnminRazinaOvlasti("grupa");
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

        repo.setAdnminRazinaOvlasti("kompanija");
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

        repo.setAdnminRazinaOvlasti("root");
        when(request.getParameter("korisnickoIme")).thenReturn("PostojectAdmin");
        when(request.getParameter("zaporka")).thenReturn("IspravnaZaporka");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        assertEquals("RootAdminServlet", prijava.PrijaviAdmina(request, repo));
    }
}
