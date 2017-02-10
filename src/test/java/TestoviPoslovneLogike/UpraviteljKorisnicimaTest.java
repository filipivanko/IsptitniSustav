package TestoviPoslovneLogike;
import Mock.MockRepozitorij;
import Model.Grupa;
import Model.Kompanija;
import Model.Korisnik;
import PoslovnaLogika.UpraviteljKorisnicima;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class UpraviteljKorisnicimaTest {
    
    @Test
    public void OdabranaKompanujaUSessionuJeNullPozivKorisnikaTest(){
     MockRepozitorij repo = new MockRepozitorij();
     HttpSession session = mock(HttpSession.class);
     when(session.getAttribute("odabranaKompanija")).thenReturn(null);
     HttpServletRequest request = mock(HttpServletRequest.class);
     when(request.getSession()).thenReturn(session); 
     
     UpraviteljKorisnicima upravitelj = new UpraviteljKorisnicima();
     upravitelj.azurirajKorisnika(request, repo);
     verify(session,never()).getAttribute("odabraniKorisnk");
    }
    
    @Test
     public void OdabranaKompanujaUSessionuJeNullPozivOdabraneGrupeTest(){
     MockRepozitorij repo = new MockRepozitorij();
     HttpSession session = mock(HttpSession.class);
     when(session.getAttribute("odabranaKompanija")).thenReturn(null);
     HttpServletRequest request = mock(HttpServletRequest.class);
     when(request.getSession()).thenReturn(session); 
     
     UpraviteljKorisnicima upravitelj = new UpraviteljKorisnicima();
     upravitelj.azurirajKorisnika(request, repo);
     verify(session,never()).getAttribute("odabranaGrupa");
    }
     
    @Test
    public void AzuriranjeKompanijeTest(){
     MockRepozitorij repo = new MockRepozitorij();
     HttpSession session = mock(HttpSession.class);
     when(session.getAttribute("odabranaKompanija")).thenReturn(mock(Kompanija.class));
     HttpServletRequest request = mock(HttpServletRequest.class);
     when(request.getSession()).thenReturn(session); 
     
     
     UpraviteljKorisnicima upravitelj = new UpraviteljKorisnicima();
     upravitelj.azurirajKorisnika(request, repo);
     verify(session,times(1)).setAttribute("odabranaKompanija", repo.dohvatiKompanijuPoIDu(0));
    }
    
    @Test
    public void PostavljanjeOdabraneGrupeAkoJePostavlejnaGrupaUSessionuNullTest(){
     MockRepozitorij repo = new MockRepozitorij();
     HttpSession session = mock(HttpSession.class);
     Kompanija kompanija =repo.dohvatiKompanijuPoIDu(0);
     List<Grupa>sveGrupeUKompaniji = new ArrayList<Grupa>();
     Grupa prvaGrupa = mock(Grupa.class);
     sveGrupeUKompaniji.add(prvaGrupa);
     
     when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
     when(session.getAttribute("odabranaGrupa")).thenReturn(null);
     
     HttpServletRequest request = mock(HttpServletRequest.class);
     when(request.getSession()).thenReturn(session); 
     when(kompanija.getGrupeUKompaniji()).thenReturn(sveGrupeUKompaniji);
     
     
     UpraviteljKorisnicima upravitelj = new UpraviteljKorisnicima();
     upravitelj.azurirajKorisnika(request, repo);
     verify(session,times(1)).setAttribute("odabranaGrupa",kompanija.getGrupeUKompaniji().get(0));
    }
    
        @Test
    public void PostavljanjeOdabraneGrupeAkoJePostavlejnaGrupaUSessionuPostojiTest(){
     MockRepozitorij repo = new MockRepozitorij();
     HttpSession session = mock(HttpSession.class);
     Kompanija kompanija =repo.dohvatiKompanijuPoIDu(0);
     Grupa postojecaGrupa = mock(Grupa.class);
     Grupa azuriranaGrupa = repo.dohvatiGrupuPoIDu(0);

     
     when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
     when(session.getAttribute("odabranaGrupa")).thenReturn(postojecaGrupa);
     
     HttpServletRequest request = mock(HttpServletRequest.class);
     when(request.getSession()).thenReturn(session); 

     UpraviteljKorisnicima upravitelj = new UpraviteljKorisnicima();
     upravitelj.azurirajKorisnika(request, repo);
     verify(session,times(1)).setAttribute("odabranaGrupa",azuriranaGrupa);
    }
    
    @Test
    public void PostavljanjeOdabraneKorisnikaAkoJePostavlejniKorisnikUSessionuNullTest(){
     MockRepozitorij repo = new MockRepozitorij();
     HttpSession session = mock(HttpSession.class);
     
     Kompanija kompanija =repo.dohvatiKompanijuPoIDu(0);
     List<Korisnik>sviKorisniciUKompaniji = new ArrayList<Korisnik>();
     Korisnik PrviKorisnik = mock(Korisnik.class);
     sviKorisniciUKompaniji.add(PrviKorisnik);
     
     when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
     when(session.getAttribute("odabraniKorisnk")).thenReturn(null);
     
     HttpServletRequest request = mock(HttpServletRequest.class);
     when(request.getSession()).thenReturn(session); 
     when(kompanija.getKorisniciKompanije()).thenReturn(sviKorisniciUKompaniji);
     
     
     UpraviteljKorisnicima upravitelj = new UpraviteljKorisnicima();
     upravitelj.azurirajKorisnika(request, repo);
     verify(session,times(1)).setAttribute("odabraniKorisnk",kompanija.getKorisniciKompanije().get(0));
    }
    
    @Test
    public void PostavljanjeOdabranogKorisnikaAkoJePostavlejnKorisnikUSessionuPostojiTest(){
     MockRepozitorij repo = new MockRepozitorij();
     HttpSession session = mock(HttpSession.class);
     Kompanija kompanija =repo.dohvatiKompanijuPoIDu(0);
     Korisnik postojeciKorisnik = mock(Korisnik.class);
     Korisnik azuriraniKorisnik = repo.dohvatiKorisnikaPoIDu(0);

     
     when(session.getAttribute("odabranaKompanija")).thenReturn(kompanija);
     when(session.getAttribute("odabraniKorisnk")).thenReturn(postojeciKorisnik);
     
     HttpServletRequest request = mock(HttpServletRequest.class);
     when(request.getSession()).thenReturn(session); 

     UpraviteljKorisnicima upravitelj = new UpraviteljKorisnicima();
     upravitelj.azurirajKorisnika(request, repo);
     verify(session,times(1)).setAttribute("odabraniKorisnk",azuriraniKorisnik);
    }
}
