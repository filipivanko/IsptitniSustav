package DAO;

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
import java.util.List;


public interface Repozitorij {

    public void otvoriSession();

    public void zatvoriSession();
    
    public <T> void spremiRucnoBezOtvaranjaIZatvaranja(T objektZaSpremiti) ;

    public <T> void promjeniRucnoBezOtvaranjaIZatvaranja(T objektZaSpremiti) ;

    public boolean postojiKorisnik(String korisnickoIme);

    public boolean postojiAdmin(String korisnickoIme);

    public List<Korisnik> dohvatiSveKorisnike();

    public Admin dohvatiAdminaPoKorisnickomImenu(String korisnickoIme);

    public <T> void spremi(T objektZaSpremiti);

    public List<Kompanija> dohvatiSveKompanije();

    public Kompanija dohvatiKompanijuPoIDu(int IDodabranaKompanija) ;

    public Admin dohvatiAdminaPoIDu(int IDodabraniAdmin) ;

    public void obrisiKompaniju(Kompanija kompanija);
    
    public <T> void obrisi(T odabraniObjekt);

    public Grupa dohvatiGrupuPoIDu(int idGrupa);
    
    public void obrisiGrupu(Grupa grupa);

    public Ispit dohvatiIspitPoIDu(int IDOdabraniIspit);

    public void obrisiIspit(Ispit ispit) ;

    public Pitanje dohvatiPitanjePoIDu(int IDodabranoPitanje) ;

    public GrupaPitanja dohvatiGrupuPitanjaPoIDu(int idGrupaPitanja) ;

    public Odgovor dohvatiOdgovorPoIDu(int idOdgovor) ;

    public void obrisiGrupuPitanja(GrupaPitanja grupaPitanja) ;

    public void obrisiOdgovor(Odgovor odgovor);
    
    public void obrisiPitanje(Pitanje pitanje) ;

    public Korisnik dohvatiKorisnikaPoIDu(int IDodabraniKorisnik) ;
    
    public void obrisiKorisnika(Korisnik odabraniKorisnk) ;

    public Korisnik dohvatiKorisnikaPoKorisnickomImenu(String korisnickoIme);

    public List<InstancaIspita> dohvatiKorisnikoveInstanceIspita(Korisnik korisnik);

    public InstancaIspita dohvatiInstancuIspitaPoIDu(int idOdabraneInstanceIspita) ;

    public InstancaPitanja dohvatiInstancuPitanjaPoIDu(int idOdabraneInstancePitanja) ;

    public InstancaOdgovora dohvatiInstancuOdgovoraPoIDu(int idOdabraneInstanceOdgovora) ;

}
