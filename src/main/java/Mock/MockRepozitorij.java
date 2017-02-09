/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mock;

import DAO.Repozitorij;
import Model.Admin;
import Model.Grupa;
import Model.GrupaPitanja;
import Model.InstancaIspita;
import Model.InstancaOdgovora;
import Model.InstancaPitanja;
import Model.Ispit;
import Model.Kompanija;
import Model.Korisnik;
import Model.Odgovor;
import Model.Pitanje;
import com.sun.media.sound.ModelAbstractChannelMixer;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;


/**
 *
 * @author Filip
 */
public class MockRepozitorij implements Repozitorij {
    static Admin StatickiAdmin = new Admin("korisnickoIme", "zaporka", "root", new Kompanija());
    @Override
    public void otvoriSession() {

    }

    @Override
    public void zatvoriSession() {

    }

    @Override
    public <T> void spremiRucnoBezOtvaranjaIZatvaranja(T objektZaSpremiti) {

    }

    @Override
    public <T> void promjeniRucnoBezOtvaranjaIZatvaranja(T objektZaSpremiti) {

    }

    @Override
    public boolean postojiKorisnik(String korisnickoIme) {
        if (korisnickoIme == "PostojectKorisnik") {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean postojiAdmin(String korisnickoIme) {
        if (korisnickoIme == "PostojectAdmin") {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Korisnik> dohvatiSveKorisnike() {
        List<Korisnik> mockKorisnici;
        mockKorisnici = mock(List.class);
        return mockKorisnici;
    }

    @Override
    public Admin dohvatiAdminaPoKorisnickomImenu(String korisnickoIme) {
        Admin admin = mock(Admin.class);
        Kompanija kompanija  = mock(Kompanija.class);
        when(admin.getKorisnickoIme()).thenReturn("PostojectAdmin");
        when(admin.getZaporka()).thenReturn("IspravnaZaporka");
        when(admin.getZaporka()).thenReturn("IspravnaZaporka");
        when(admin.getKompanija()).thenReturn(kompanija);
        when(admin.getRazinaovlasti()).thenReturn(StatickiAdmin.getRazinaovlasti());
        return admin;
    }

    @Override
    public <T> void spremi(T objektZaSpremiti) {
    }

    @Override
    public List<Kompanija> dohvatiSveKompanije() {
        List<Kompanija> mockKompanije;
        mockKompanije = mock(List.class);
        return mockKompanije;
    }

    @Override
    public Kompanija dohvatiKompanijuPoIDu(int IDodabranaKompanija) {
        return mock(Model.Kompanija.class);
    }

    @Override
    public Admin dohvatiAdminaPoIDu(int IDodabraniAdmin) {
        return mock(Model.Admin.class);
    }

    @Override
    public void obrisiKompaniju(Kompanija kompanija) {

    }

    @Override
    public <T> void obrisi(T odabraniObjekt) {

    }

    @Override
    public Grupa dohvatiGrupuPoIDu(int idGrupa) {
        return mock(Model.Grupa.class);
    }

    @Override
    public void obrisiGrupu(Grupa grupa) {

    }

    @Override
    public Ispit dohvatiIspitPoIDu(int IDOdabraniIspit) {
        return mock(Model.Ispit.class);
    }

    @Override
    public void obrisiIspit(Ispit ispit) {

    }

    @Override
    public Pitanje dohvatiPitanjePoIDu(int IDodabranoPitanje) {
        return mock(Model.Pitanje.class);
    }

    @Override
    public GrupaPitanja dohvatiGrupuPitanjaPoIDu(int idGrupaPitanja) {
        return mock(Model.GrupaPitanja.class);
    }

    @Override
    public Odgovor dohvatiOdgovorPoIDu(int idOdgovor) {
        return mock(Model.Odgovor.class);
    }

    @Override
    public void obrisiGrupuPitanja(GrupaPitanja grupaPitanja) {

    }

    @Override
    public void obrisiOdgovor(Odgovor odgovor) {

    }

    @Override
    public void obrisiPitanje(Pitanje pitanje) {

    }

    @Override
    public Korisnik dohvatiKorisnikaPoIDu(int IDodabraniKorisnik) {
        return mock(Model.Korisnik.class);
    }

    @Override
    public void obrisiKorisnika(Korisnik odabraniKorisnk) {

    }

    @Override
    public Korisnik dohvatiKorisnikaPoKorisnickomImenu(String korisnickoIme) {
        Korisnik korisnik = mock(Model.Korisnik.class);
        when(korisnik.getKorisnickoIme()).thenReturn("PostojectKorisnik");
        when(korisnik.getZaporka()).thenReturn("IspravnaZaporka");
        return korisnik;
    }

    @Override
    public List<InstancaIspita> dohvatiKorisnikoveInstanceIspita(Korisnik korisnik) {
        List<InstancaIspita> mockInstanceIspita;
        mockInstanceIspita = mock(List.class);
        return mockInstanceIspita;
    }

    @Override
    public InstancaIspita dohvatiInstancuIspitaPoIDu(int idOdabraneInstanceIspita) {
        return mock(Model.InstancaIspita.class);
    }

    @Override
    public InstancaPitanja dohvatiInstancuPitanjaPoIDu(int idOdabraneInstancePitanja) {
        return mock(Model.InstancaPitanja.class);
    }

    @Override
    public InstancaOdgovora dohvatiInstancuOdgovoraPoIDu(int idOdabraneInstanceOdgovora) {
         return mock(Model.InstancaOdgovora.class);
    }
    public void setAdnminRazinaOvlasti (String s){
    StatickiAdmin.setRazinaovlasti(s);
    }

}
