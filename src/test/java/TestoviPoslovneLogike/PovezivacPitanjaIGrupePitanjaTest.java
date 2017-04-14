package TestoviPoslovneLogike;

import DAO.Repozitorij;
import Model.GrupaPitanja;
import Model.Pitanje;
import PoslovnaLogika.PovezivacPitanjaIGrupePitanja;
import PoslovnaLogika.PrekidacVezeIzmedjuPitanjaIGrupePitanja;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class PovezivacPitanjaIGrupePitanjaTest {

    @Test
    public void testPovezivanjaPitanjaIGrupePitanjaAkoJePitanjeUSessionuNull() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        List<Pitanje> listaPitanjaUGrupi = mock(List.class);
        Pitanje pitanjeIzSessina = null;

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);
        when(grupaPitanjaIzSessiona.getPitanjaUGrupi()).thenReturn(listaPitanjaUGrupi);
        when(grupaPitanjaIzSessiona.provjeriDaLiJePovezaoPitanje(any(Pitanje.class))).thenReturn(true);
        PovezivacPitanjaIGrupePitanja povezivac = new PovezivacPitanjaIGrupePitanja();
        povezivac.poveziPitanjeIGrupuPitanja(request, repo);

        verify(listaPitanjaUGrupi, never()).add(any(Pitanje.class));
    }

    @Test
    public void testPovezivanjaPitanjaIGrupePitanjaAkoJeGrupaPitanjaIzSessionaNull() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = null;
        List<Pitanje> listaPitanjaUGrupi = mock(List.class);
        List<GrupaPitanja> GrupeKojeSadrzaPitanje = mock(List.class);
        Pitanje pitanjeIzSessina = mock(Pitanje.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);

        when(pitanjeIzSessina.getGrupePitanjaKojaSadrzePitanje()).thenReturn(GrupeKojeSadrzaPitanje);
        when(pitanjeIzSessina.provjeriDaLiJePovezanaGrupaPitanja(any(GrupaPitanja.class))).thenReturn(true);
        PovezivacPitanjaIGrupePitanja povezivac = new PovezivacPitanjaIGrupePitanja();
        povezivac.poveziPitanjeIGrupuPitanja(request, repo);

        verify(GrupeKojeSadrzaPitanje, never()).add(any(GrupaPitanja.class));
    }

    @Test
    public void testPovezivanjaPitanjaIGrupePitanjaAkoSuIPitanjeIGrupaPitanjaUSessionuTestDodavanjaPitanjaGrupi() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        List<Pitanje> listaPitanjaUGrupi = mock(List.class);
        List<GrupaPitanja> GrupeKojeSadrzaPitanje = mock(List.class);
        Pitanje pitanjeIzSessina = mock(Pitanje.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);
        when(grupaPitanjaIzSessiona.getPitanjaUGrupi()).thenReturn(listaPitanjaUGrupi);
        when(pitanjeIzSessina.getGrupePitanjaKojaSadrzePitanje()).thenReturn(GrupeKojeSadrzaPitanje);
        when(grupaPitanjaIzSessiona.provjeriDaLiJePovezaoPitanje(any(Pitanje.class))).thenReturn(false);
        when(pitanjeIzSessina.provjeriDaLiJePovezanaGrupaPitanja(any(GrupaPitanja.class))).thenReturn(false);
        PovezivacPitanjaIGrupePitanja povezivac = new PovezivacPitanjaIGrupePitanja();
        povezivac.poveziPitanjeIGrupuPitanja(request, repo);

        verify(listaPitanjaUGrupi, times(1)).add(any(Pitanje.class));
    }

    @Test
    public void testPovezivanjaPitanjaIGrupePitanjaAkoSuIPitanjeIGrupaPitanjaUSessionuTestDodavanjeGrupePitanjaPitanju() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        List<Pitanje> listaPitanjaUGrupi = mock(List.class);
        List<GrupaPitanja> GrupeKojeSadrzaPitanje = mock(List.class);
        Pitanje pitanjeIzSessina = mock(Pitanje.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);
        when(grupaPitanjaIzSessiona.getPitanjaUGrupi()).thenReturn(listaPitanjaUGrupi);
        when(pitanjeIzSessina.getGrupePitanjaKojaSadrzePitanje()).thenReturn(GrupeKojeSadrzaPitanje);
        when(grupaPitanjaIzSessiona.provjeriDaLiJePovezaoPitanje(any(Pitanje.class))).thenReturn(false);
        when(pitanjeIzSessina.provjeriDaLiJePovezanaGrupaPitanja(any(GrupaPitanja.class))).thenReturn(false);
        PovezivacPitanjaIGrupePitanja povezivac = new PovezivacPitanjaIGrupePitanja();
        povezivac.poveziPitanjeIGrupuPitanja(request, repo);

        verify(GrupeKojeSadrzaPitanje, times(1)).add(any(GrupaPitanja.class));
    }

    @Test
    public void testPovezivanjaPitanjaIGrupePitanjaAkoSuIPitanjeIGrupaPitanjaUSessionuProvjeraSpremanjaURepo() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        List<Pitanje> listaPitanjaUGrupi = mock(List.class);
        List<GrupaPitanja> GrupeKojeSadrzaPitanje = mock(List.class);
        Pitanje pitanjeIzSessina = mock(Pitanje.class);
        Pitanje pitanjeIzRepoa = mock(Pitanje.class);
        GrupaPitanja grupaPitanjaIzRepoa = mock(GrupaPitanja.class);

        when(repo.dohvatiGrupuPitanjaPoIDu(anyInt())).thenReturn(grupaPitanjaIzRepoa);
        when(repo.dohvatiPitanjePoIDu(anyInt())).thenReturn(pitanjeIzRepoa);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);
        when(grupaPitanjaIzSessiona.getPitanjaUGrupi()).thenReturn(listaPitanjaUGrupi);
        when(pitanjeIzSessina.getGrupePitanjaKojaSadrzePitanje()).thenReturn(GrupeKojeSadrzaPitanje);
        when(grupaPitanjaIzSessiona.provjeriDaLiJePovezaoPitanje(any(Pitanje.class))).thenReturn(false);
        when(pitanjeIzSessina.provjeriDaLiJePovezanaGrupaPitanja(any(GrupaPitanja.class))).thenReturn(false);
        PovezivacPitanjaIGrupePitanja povezivac = new PovezivacPitanjaIGrupePitanja();
        povezivac.poveziPitanjeIGrupuPitanja(request, repo);

        verify(repo, times(1)).otvoriSession();
        verify(repo, times(1)).promjeniRucnoBezOtvaranjaIZatvaranja(pitanjeIzSessina);
        verify(repo, times(1)).promjeniRucnoBezOtvaranjaIZatvaranja(grupaPitanjaIzSessiona);
        verify(repo, times(1)).zatvoriSession();
    }
        @Test
    public void testPovezivanjaPitanjaIGrupePitanjaAkoSuIPitanjeIGrupaPitanjaUSessionuProvjeraSpremanjaAzuriranihIPovezanihPitanja() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        List<Pitanje> listaPitanjaUGrupi = mock(List.class);
        List<GrupaPitanja> GrupeKojeSadrzaPitanje = mock(List.class);
        Pitanje pitanjeIzSessina = mock(Pitanje.class);
        Pitanje pitanjeIzRepoa = mock(Pitanje.class);
        GrupaPitanja grupaPitanjaIzRepoa = mock(GrupaPitanja.class);

        
        when(pitanjeIzSessina.getIDPitanje()).thenReturn(0);
        when(grupaPitanjaIzRepoa.getIDGrupaPitanja()).thenReturn(0);
        when(repo.dohvatiGrupuPitanjaPoIDu(0)).thenReturn(grupaPitanjaIzRepoa);
        when(repo.dohvatiPitanjePoIDu(0)).thenReturn(pitanjeIzRepoa);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);
        when(grupaPitanjaIzSessiona.getPitanjaUGrupi()).thenReturn(listaPitanjaUGrupi);
        when(pitanjeIzSessina.getGrupePitanjaKojaSadrzePitanje()).thenReturn(GrupeKojeSadrzaPitanje);

        when(grupaPitanjaIzSessiona.provjeriDaLiJePovezaoPitanje(any(Pitanje.class))).thenReturn(false);
        when(pitanjeIzSessina.provjeriDaLiJePovezanaGrupaPitanja(any(GrupaPitanja.class))).thenReturn(false);
        PovezivacPitanjaIGrupePitanja povezivac = new PovezivacPitanjaIGrupePitanja();
        povezivac.poveziPitanjeIGrupuPitanja(request, repo);

        verify(session, times(1)).setAttribute("odabranoPitanje", pitanjeIzRepoa);
        
    }
            @Test
    public void testPovezivanjaPitanjaIGrupePitanjaAkoSuIPitanjeIGrupaPitanjaUSessionuProvjeraSpremanjaAzuriranihIPovezanihGrupaPitanja() {

        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        GrupaPitanja grupaPitanjaIzSessiona = mock(GrupaPitanja.class);
        List<Pitanje> listaPitanjaUGrupi = mock(List.class);
        List<GrupaPitanja> GrupeKojeSadrzaPitanje = mock(List.class);
        Pitanje pitanjeIzSessina = mock(Pitanje.class);
        Pitanje pitanjeIzRepoa = mock(Pitanje.class);
        GrupaPitanja grupaPitanjaIzRepoa = mock(GrupaPitanja.class);

        
        when(pitanjeIzSessina.getIDPitanje()).thenReturn(0);
        when(grupaPitanjaIzRepoa.getIDGrupaPitanja()).thenReturn(0);
        when(repo.dohvatiGrupuPitanjaPoIDu(0)).thenReturn(grupaPitanjaIzRepoa);
        when(repo.dohvatiPitanjePoIDu(0)).thenReturn(pitanjeIzRepoa);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabranaGrupaPitanja")).thenReturn(grupaPitanjaIzSessiona);
        when(session.getAttribute("odabranoPitanje")).thenReturn(pitanjeIzSessina);
        when(grupaPitanjaIzSessiona.getPitanjaUGrupi()).thenReturn(listaPitanjaUGrupi);
        when(pitanjeIzSessina.getGrupePitanjaKojaSadrzePitanje()).thenReturn(GrupeKojeSadrzaPitanje);

        when(grupaPitanjaIzSessiona.provjeriDaLiJePovezaoPitanje(any(Pitanje.class))).thenReturn(false);
        when(pitanjeIzSessina.provjeriDaLiJePovezanaGrupaPitanja(any(GrupaPitanja.class))).thenReturn(false);
        PovezivacPitanjaIGrupePitanja povezivac = new PovezivacPitanjaIGrupePitanja();
        povezivac.poveziPitanjeIGrupuPitanja(request, repo);

        verify(session, times(1)).setAttribute("odabranaGrupaPitanja", grupaPitanjaIzRepoa);
    }
}
