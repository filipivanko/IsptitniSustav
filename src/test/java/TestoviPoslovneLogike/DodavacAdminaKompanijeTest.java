package TestoviPoslovneLogike;

import DAO.Repozitorij;
import Mock.MockRepozitorij;
import Model.Admin;
import Model.Grupa;
import Model.Kompanija;
import PoslovnaLogika.DodavacAdminaGrupe;
import PoslovnaLogika.DodavacAdminaKompanije;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class DodavacAdminaKompanijeTest {

    @Test
    public void testDodavanjaAdminaKompanijeAkoJeKompanijaIzRequestaNull() {

        Kompanija kompanija = null;
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
        when(request.getParameter("korisnickoImeNoviAdminKompanije")).thenReturn("KorisnickoIme");
        when(request.getParameter("zaporkaNoviAdminKompanije")).thenReturn("Zaporka");

        DodavacAdminaKompanije dodavac = new DodavacAdminaKompanije();

        dodavac.dodajAdminaKompanije(request, repo);
        verify(repo, never()).spremiRucnoBezOtvaranjaIZatvaranja(any(Admin.class));
    }

    @Test
    public void testDodavanjeAdminaKompanijeKompaniji() {

        Kompanija kompanija = mock(Kompanija.class);
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Admin> adminiKompanije = mock(ArrayList.class);

        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
        when(request.getParameter("korisnickoImeNoviAdminKompanije")).thenReturn("KorisnickoIme");
        when(request.getParameter("zaporkaNoviAdminKompanije")).thenReturn("Zaporka");
        when(kompanija.getAdminiKompanije()).thenReturn(adminiKompanije);

        DodavacAdminaKompanije dodavac = new DodavacAdminaKompanije();

        dodavac.dodajAdminaKompanije(request, repo);
        verify(kompanija.getAdminiKompanije(), times(1)).add(any(Admin.class));

    }

    @Test
    public void testDodavanjeAdminaURepozitorijIUpdateKompanijeSessionNijeOtvoren() {

        Kompanija kompanija = mock(Kompanija.class);
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Admin> adminiKompanije = mock(ArrayList.class);

        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
        when(request.getParameter("korisnickoImeNoviAdminKompanije")).thenReturn("KorisnickoIme");
        when(request.getParameter("zaporkaNoviAdminKompanije")).thenReturn("Zaporka");
        when(kompanija.getAdminiKompanije()).thenReturn(adminiKompanije);

        DodavacAdminaKompanije dodavac = new DodavacAdminaKompanije();

        dodavac.dodajAdminaKompanije(request, repo);
        verify(repo, times(1)).otvoriSession();
    }

    @Test
    public void testDodavanjeAdminaURepozitorijIUpdateKompanijeSessionNijeZatvoren() {

        Kompanija kompanija = mock(Kompanija.class);
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Admin> adminiKompanije = mock(ArrayList.class);

        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
        when(request.getParameter("korisnickoImeNoviAdminKompanije")).thenReturn("KorisnickoIme");
        when(request.getParameter("zaporkaNoviAdminKompanije")).thenReturn("Zaporka");
        when(kompanija.getAdminiKompanije()).thenReturn(adminiKompanije);

        DodavacAdminaKompanije dodavac = new DodavacAdminaKompanije();

        dodavac.dodajAdminaKompanije(request, repo);
        verify(repo, times(1)).zatvoriSession();

    }

    public void testDodavanjeAdminaURepozitorijIUpdateKompanijeKompanijaNijeAzurirenaURepou() {

        Kompanija kompanija = mock(Kompanija.class);
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Admin> adminiKompanije = mock(ArrayList.class);

        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
        when(request.getParameter("korisnickoImeNoviAdminKompanije")).thenReturn("KorisnickoIme");
        when(request.getParameter("zaporkaNoviAdminKompanije")).thenReturn("Zaporka");
        when(kompanija.getAdminiKompanije()).thenReturn(adminiKompanije);

        DodavacAdminaKompanije dodavac = new DodavacAdminaKompanije();

        dodavac.dodajAdminaKompanije(request, repo);
        verify(repo, times(1)).promjeniRucnoBezOtvaranjaIZatvaranja(kompanija);
    }

    public void testDodavanjeAdminaURepozitorijIUpdateKompanijeKOrisnikNijeDodanURepo() {

        Kompanija kompanija = mock(Kompanija.class);
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Admin> adminiKompanije = mock(ArrayList.class);

        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
        when(request.getParameter("korisnickoImeNoviAdminKompanije")).thenReturn("KorisnickoIme");
        when(request.getParameter("zaporkaNoviAdminKompanije")).thenReturn("Zaporka");
        when(kompanija.getAdminiKompanije()).thenReturn(adminiKompanije);

        DodavacAdminaKompanije dodavac = new DodavacAdminaKompanije();

        dodavac.dodajAdminaKompanije(request, repo);
        verify(repo, times(1)).spremiRucnoBezOtvaranjaIZatvaranja(any(Admin.class));

    }
}
