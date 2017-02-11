package TestoviPoslovneLogike;
import Mock.MockRepozitorij;
import Model.Admin;
import Model.Grupa;
import Model.Kompanija;
import PoslovnaLogika.DodavacAdminaGrupe;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DodavacAdminaGrupeTest {
    @Test
    public void testDodavanjaAkoJeKompanijaIzRequestaNull(){
        Grupa grupa = mock(Grupa.class);
        Kompanija kompanija = null;
        HttpSession session = mock(HttpSession.class);
        MockRepozitorij repo = new MockRepozitorij();
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Admin> adminiKompanije = mock(ArrayList.class);
        
       when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupa")).thenReturn(grupa);
        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
        when(request.getParameter("korisnickoIme")).thenReturn("KorisnickoIme");
        when(request.getParameter("zaporka")).thenReturn("Zaporka");
        
        DodavacAdminaGrupe dodavac = new DodavacAdminaGrupe();
        
        dodavac.dodajAdmina(request, repo);
        verify(session,never()).setAttribute(anyString(), any(Admin.class));
    }
 
      @Test
    public void testDodavanjaAkoJeGrupaIzRequestaNull(){
        Grupa grupa = null;
        Kompanija kompanija = mock(Kompanija.class);
        HttpSession session = mock(HttpSession.class);
        MockRepozitorij repo = new MockRepozitorij();
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Admin> adminiKompanije = mock(ArrayList.class);
        
       when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupa")).thenReturn(grupa);
        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
        when(request.getParameter("korisnickoIme")).thenReturn("KorisnickoIme");
        when(request.getParameter("zaporka")).thenReturn("Zaporka");
        
        DodavacAdminaGrupe dodavac = new DodavacAdminaGrupe();
        
        dodavac.dodajAdmina(request, repo);
        verify(session,never()).setAttribute(anyString(), any(Admin.class));
    }
          @Test
    public void testDodavanjaAkoSuIGrupaIKompanijaUSessionu(){
        Grupa grupa = mock(Grupa.class);
        Kompanija kompanija = mock(Kompanija.class);
        HttpSession session = mock(HttpSession.class);
        MockRepozitorij repo = new MockRepozitorij();
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Admin> adminiKompanije = mock(ArrayList.class);
        
       when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupa")).thenReturn(grupa);
        when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
        when(request.getParameter("korisnickoIme")).thenReturn("KorisnickoIme");
        when(request.getParameter("zaporka")).thenReturn("Zaporka");
        
        DodavacAdminaGrupe dodavac = new DodavacAdminaGrupe();
        
        dodavac.dodajAdmina(request, repo);
        verify(session,times(1)).setAttribute(anyString(), any(Admin.class));
    }
}
