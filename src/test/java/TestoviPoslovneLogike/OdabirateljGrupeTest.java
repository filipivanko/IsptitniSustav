package TestoviPoslovneLogike;
import DAO.Repozitorij;
import Model.Admin;
import Model.Grupa;
import PoslovnaLogika.BrisacAdminaGrupe;
import PoslovnaLogika.OdabirateljGrupe;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class OdabirateljGrupeTest {
        @Test
    public void testOdabiraGrupeAkoNemaGrupeUSessionu() {
        
        HttpSession session = mock(HttpSession.class);
        Grupa grupaIzRepoa = mock(Grupa.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        Grupa odabranaGrupaUSessionu = null;
        
        when(repo.dohvatiGrupuPoIDu(anyInt())).thenReturn(grupaIzRepoa);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("IDodabranaGrupa")).thenReturn(null);
        
        OdabirateljGrupe odabirac = new OdabirateljGrupe();
        odabirac.odaberiGrupu(request, repo);
        
        verify(session,never()).setAttribute("odabranaGrupa", grupaIzRepoa);
        
    }
    
            @Test
    public void testOdabiraGrupeAkoPostojiGrupaUSessionu() {
        
        HttpSession session = mock(HttpSession.class);
        Grupa grupaIzRepoa = mock(Grupa.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        Grupa odabranaGrupaUSessionu = mock(Grupa.class);
        
        when(repo.dohvatiGrupuPoIDu(anyInt())).thenReturn(grupaIzRepoa);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("IDodabranaGrupa")).thenReturn("0");
        
        OdabirateljGrupe odabirac = new OdabirateljGrupe();
        odabirac.odaberiGrupu(request, repo);
        
        verify(session,times(1)).setAttribute("odabranaGrupa", grupaIzRepoa);
        
    }
    @Test
        public void testOdabiraGrupeAkoPostojiGrupaUSessionuAliJeNemaURepou() {
        
        HttpSession session = mock(HttpSession.class);
        Grupa grupaIzRepoa = null;
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        Grupa odabranaGrupaUSessionu = mock(Grupa.class);
        
        when(repo.dohvatiGrupuPoIDu(anyInt())).thenReturn(grupaIzRepoa);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("IDodabranaGrupa")).thenReturn("0");
        
        OdabirateljGrupe odabirac = new OdabirateljGrupe();
        odabirac.odaberiGrupu(request, repo);
        
        verify(session,times(1)).setAttribute("odabranaGrupa", null);
        
    }
}
