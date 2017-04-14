package TestoviPoslovneLogike;

import DAO.Repozitorij;
import Model.Admin;
import PoslovnaLogika.BrisacAdminaGrupe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class BrisacAdminaGrupeTest {
    
    @Test
    public void testBrisanjeAdminaGrupeAkoJeOdabraniAdminGrupeUSessionuNull() {
        
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        Admin odabraniAdminKompanijeUSessionu = null;
        
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabraniAdminGrupa")).thenReturn(odabraniAdminKompanijeUSessionu);
        
        BrisacAdminaGrupe brisac = new BrisacAdminaGrupe();
        brisac.obrisiAdminaGrupe(request, repo);
        
        verify(repo,never()).obrisi(any(Admin.class));
        
    }
        @Test
    public void testBrisanjeAdminaGrupeAkoPostojiOdabraniAdminGrupeUSessionu() {
        
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        Admin odabraniAdminKompanijeUSessionu = mock(Admin.class);
        
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabraniAdminGrupa")).thenReturn(odabraniAdminKompanijeUSessionu);
        
        BrisacAdminaGrupe brisac = new BrisacAdminaGrupe();
        brisac.obrisiAdminaGrupe(request, repo);
        
        verify(repo,times(1)).obrisi(odabraniAdminKompanijeUSessionu);
        
    }
            @Test
    public void testBrisanjeAdminaUpdateSessionaGrupeAkoPostojiOdabraniAdminGrupeUSessionu() {
        
        HttpSession session = mock(HttpSession.class);
        Repozitorij repo = mock(Repozitorij.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        Admin odabraniAdminKompanijeUSessionu = mock(Admin.class);
        
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("odabraniAdminGrupa")).thenReturn(odabraniAdminKompanijeUSessionu);
        
        BrisacAdminaGrupe brisac = new BrisacAdminaGrupe();
        brisac.obrisiAdminaGrupe(request, repo);
       
        verify(session,times(1)).setAttribute("odabraniAdminGrupa",null);
        
    }
}
