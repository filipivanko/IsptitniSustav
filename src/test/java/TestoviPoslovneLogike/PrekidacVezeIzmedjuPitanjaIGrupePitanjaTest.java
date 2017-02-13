package TestoviPoslovneLogike;

import DAO.Repozitorij;
import Model.GrupaPitanja;
import Model.Pitanje;
import PoslovnaLogika.PrekidacVezeIzmedjuPitanjaIGrupePitanja;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class PrekidacVezeIzmedjuPitanjaIGrupePitanjaTest {

    @Test
    public void testPrekidaVezeIzmedjuPitanjaIGrupePitanjaAkoJePitanjeNull() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        Pitanje pitanjeIzSessina = null;

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);

        PrekidacVezeIzmedjuPitanjaIGrupePitanja prekidac = new PrekidacVezeIzmedjuPitanjaIGrupePitanja();
        prekidac.prekiniVezuPitanjaIGrupePitanja(request, repo);
        verify(grupaPitanjaIzSessiona, never()).izbaciPitanje(any(Pitanje.class));
    }

    @Test
    public void testPrekidaVezeIzmedjuPitanjaIGrupePitanjaAkoJeGrupaPitanjaNull() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = null;
        Pitanje pitanjeIzSessina = mock(Pitanje.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);

        PrekidacVezeIzmedjuPitanjaIGrupePitanja prekidac = new PrekidacVezeIzmedjuPitanjaIGrupePitanja();
        prekidac.prekiniVezuPitanjaIGrupePitanja(request, repo);

        verify(pitanjeIzSessina, never()).izbaciGrupuPitanja(any(GrupaPitanja.class));
    }

    @Test
    public void testPrekidaVezeIzmedjuPitanjaIGrupePitanjaAkoSuIPitanjeIGrupaPitanjaUSessionuTestIzbacivanjePitanjaIzGrupe() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        Pitanje pitanjeIzSessina = mock(Pitanje.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);
        when(grupaPitanjaIzSessiona.provjeriDaLiJePovezaoPitanje(pitanjeIzSessina)).thenReturn(true);
        when(pitanjeIzSessina.provjeriDaLiJePovezanaGrupaPitanja(grupaPitanjaIzSessiona)).thenReturn(true);

        PrekidacVezeIzmedjuPitanjaIGrupePitanja prekidac = new PrekidacVezeIzmedjuPitanjaIGrupePitanja();
        prekidac.prekiniVezuPitanjaIGrupePitanja(request, repo);

        verify(grupaPitanjaIzSessiona, times(1)).izbaciPitanje(any(Pitanje.class));

    }

    @Test
    public void testPrekidaVezeIzmedjuPitanjaIGrupePitanjaAkoSuIPitanjeIGrupaPitanjaUSessionuTestPrekidanjeVezeNaPitanjuSGrupom() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        Pitanje pitanjeIzSessina = mock(Pitanje.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);
        when(grupaPitanjaIzSessiona.provjeriDaLiJePovezaoPitanje(pitanjeIzSessina)).thenReturn(true);
        when(pitanjeIzSessina.provjeriDaLiJePovezanaGrupaPitanja(grupaPitanjaIzSessiona)).thenReturn(true);

        PrekidacVezeIzmedjuPitanjaIGrupePitanja prekidac = new PrekidacVezeIzmedjuPitanjaIGrupePitanja();
        prekidac.prekiniVezuPitanjaIGrupePitanja(request, repo);

        verify(pitanjeIzSessina, times(1)).izbaciGrupuPitanja(any(GrupaPitanja.class));
    }

    @Test
    public void testPrekidaVezeIzmedjuPitanjaIGrupePitanjaAkoSuIPitanjeIGrupaPitanjaUSessionuTestSpremanjeURepo() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        Pitanje pitanjeIzSessina = mock(Pitanje.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);
        when(grupaPitanjaIzSessiona.provjeriDaLiJePovezaoPitanje(pitanjeIzSessina)).thenReturn(true);
        when(pitanjeIzSessina.provjeriDaLiJePovezanaGrupaPitanja(grupaPitanjaIzSessiona)).thenReturn(true);

        PrekidacVezeIzmedjuPitanjaIGrupePitanja prekidac = new PrekidacVezeIzmedjuPitanjaIGrupePitanja();
        prekidac.prekiniVezuPitanjaIGrupePitanja(request, repo);

        verify(repo, times(1)).otvoriSession();
        verify(repo, times(1)).promjeniRucnoBezOtvaranjaIZatvaranja(pitanjeIzSessina);
        verify(repo, times(1)).promjeniRucnoBezOtvaranjaIZatvaranja(grupaPitanjaIzSessiona);
        verify(repo, times(1)).zatvoriSession();
    }

    @Test
    public void testPrekidaVezeIzmedjuPitanjaIGrupePitanjaAkoSuIPitanjeIGrupaPitanjaUSessionuteTestProvjeraAzuriranja() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        Pitanje pitanjeIzSessina = mock(Pitanje.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);
        when(grupaPitanjaIzSessiona.getIDGrupaPitanja()).thenReturn(0);
        when(pitanjeIzSessina.getIDPitanje()).thenReturn(0);
        when(grupaPitanjaIzSessiona.provjeriDaLiJePovezaoPitanje(pitanjeIzSessina)).thenReturn(true);
        when(pitanjeIzSessina.provjeriDaLiJePovezanaGrupaPitanja(grupaPitanjaIzSessiona)).thenReturn(true);

        PrekidacVezeIzmedjuPitanjaIGrupePitanja prekidac = new PrekidacVezeIzmedjuPitanjaIGrupePitanja();
        prekidac.prekiniVezuPitanjaIGrupePitanja(request, repo);

        verify(repo, times(1)).dohvatiPitanjePoIDu(0);
        verify(repo, times(1)).dohvatiGrupuPitanjaPoIDu(0);
    }
        @Test
    public void testPrekidaVezeIzmedjuPitanjaIGrupePitanjaAkoSuIPitanjeIGrupaPitanjaUSessionuteTestProvjeraSpremanjaUSessonPitanje() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        GrupaPitanja grupaPitanjaIzRepoa = mock(GrupaPitanja.class);
        Pitanje pitanjeIzSessina = mock(Pitanje.class);
        Pitanje pitanjeIzRepoa = mock(Pitanje.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);
        when(grupaPitanjaIzSessiona.getIDGrupaPitanja()).thenReturn(0);
        when(pitanjeIzSessina.getIDPitanje()).thenReturn(0);
        when(repo.dohvatiPitanjePoIDu(0)).thenReturn(pitanjeIzRepoa);
        when(repo.dohvatiGrupuPitanjaPoIDu(0)).thenReturn(grupaPitanjaIzRepoa);
        when(grupaPitanjaIzSessiona.provjeriDaLiJePovezaoPitanje(pitanjeIzSessina)).thenReturn(true);
        when(pitanjeIzSessina.provjeriDaLiJePovezanaGrupaPitanja(grupaPitanjaIzSessiona)).thenReturn(true);

        PrekidacVezeIzmedjuPitanjaIGrupePitanja prekidac = new PrekidacVezeIzmedjuPitanjaIGrupePitanja();
        prekidac.prekiniVezuPitanjaIGrupePitanja(request, repo);

        
        verify(session,times(1)).setAttribute("odabranoPitanje", pitanjeIzRepoa);
    }
            @Test
    public void testPrekidaVezeIzmedjuPitanjaIGrupePitanjaAkoSuIPitanjeIGrupaPitanjaUSessionuteTestProvjeraSpremanjaUSessonGrupePitanja() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        GrupaPitanja grupaPitanjaIzRepoa = mock(GrupaPitanja.class);
        Pitanje pitanjeIzSessina = mock(Pitanje.class);
        Pitanje pitanjeIzRepoa = mock(Pitanje.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);
        when(grupaPitanjaIzSessiona.getIDGrupaPitanja()).thenReturn(0);
        when(pitanjeIzSessina.getIDPitanje()).thenReturn(0);
        when(repo.dohvatiPitanjePoIDu(0)).thenReturn(pitanjeIzRepoa);
        when(repo.dohvatiGrupuPitanjaPoIDu(0)).thenReturn(grupaPitanjaIzRepoa);
        when(grupaPitanjaIzSessiona.provjeriDaLiJePovezaoPitanje(pitanjeIzSessina)).thenReturn(true);
        when(pitanjeIzSessina.provjeriDaLiJePovezanaGrupaPitanja(grupaPitanjaIzSessiona)).thenReturn(true);

        PrekidacVezeIzmedjuPitanjaIGrupePitanja prekidac = new PrekidacVezeIzmedjuPitanjaIGrupePitanja();
        prekidac.prekiniVezuPitanjaIGrupePitanja(request, repo);

        
        verify(session,times(1)).setAttribute("odabranaGrupaPitanja", grupaPitanjaIzRepoa);
    }
}
