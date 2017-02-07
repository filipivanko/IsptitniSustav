
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Korisnik implements Serializable,Comparable<Korisnik> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int IDKorisnik;
    private String ime;
    private String prezime;
    private String adresa;
    private String strucnaSprema;
    private String zanimanje;
    private String oib;
    private String korisnickoIme;
    private String zaporka;
    
    @ManyToOne
    private Kompanija kompanija;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch (FetchMode.SELECT)
    private List<Grupa> grupeKojimaKorisnikPripada = new ArrayList<Grupa>();

    public Korisnik() {
    }

    public Korisnik(String ime,
            String prezime,
            String adresa,
            String strucnaSprema,
            String zanimanje,
            String oib,
            String korisnickoIme,
            String zaporka,
            Kompanija kompanija
    ) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.strucnaSprema = strucnaSprema;
        this.zanimanje = zanimanje;
        this.oib = oib;
        this.korisnickoIme = korisnickoIme;
        this.zaporka = zaporka;
        this.kompanija = kompanija;
    }



    public void ukloniGrupuPoIDu(int idGrupa) {
        Grupa grupaZaUkloniti = null;
        for (Grupa g : getGrupeKojimaKorisnikPripada()) {
            if (g.getIDGrupa() == idGrupa) {
                grupaZaUkloniti = g;
            }
        }
        if (grupaZaUkloniti != null) {
            getGrupeKojimaKorisnikPripada().remove(grupaZaUkloniti);
        }
    }

    /**
     * @return the IDKorisnik
     */
    public int getIDKorisnik() {
        return IDKorisnik;
    }

    /**
     * @param IDKorisnik the IDKorisnik to set
     */
    public void setIDKorisnik(int IDKorisnik) {
        this.IDKorisnik = IDKorisnik;
    }

    /**
     * @return the ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * @param ime the ime to set
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * @return the prezime
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * @param prezime the prezime to set
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * @return the adresa
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * @param adresa the adresa to set
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     * @return the strucnaSprema
     */
    public String getStrucnaSprema() {
        return strucnaSprema;
    }

    /**
     * @param strucnaSprema the strucnaSprema to set
     */
    public void setStrucnaSprema(String strucnaSprema) {
        this.strucnaSprema = strucnaSprema;
    }

    /**
     * @return the zanimanje
     */
    public String getZanimanje() {
        return zanimanje;
    }

    /**
     * @param zanimanje the zanimanje to set
     */
    public void setZanimanje(String zanimanje) {
        this.zanimanje = zanimanje;
    }

    /**
     * @return the oib
     */
    public String getOib() {
        return oib;
    }

    /**
     * @param oib the oib to set
     */
    public void setOib(String oib) {
        this.oib = oib;
    }

    /**
     * @return the korisnickoIme
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * @param korisnickoIme the korisnickoIme to set
     */
    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * @return the zaporka
     */
    public String getZaporka() {
        return zaporka;
    }

    /**
     * @param zaporka the zaporka to set
     */
    public void setZaporka(String zaporka) {
        this.zaporka = zaporka;
    }


    @Override
    public int compareTo(Korisnik t) {
           if(this.getIDKorisnik()>t.getIDKorisnik()){
        return 1;
        }else{
        return -1;
        }
    }

    /**
     * @return the kompanija
     */
    public Kompanija getKompanija() {
        return kompanija;
    }

    /**
     * @param kompanija the kompanija to set
     */
    public void setKompanija(Kompanija kompanija) {
        this.kompanija = kompanija;
    }

    public boolean provjeriDaLiJePovezanaGrupa(Grupa grupa) {
                for (Grupa g : getGrupeKojimaKorisnikPripada()) {
            if (g.getIDGrupa() == grupa.getIDGrupa()) {
                return true;
            }
        }
        return false;
    }

    public void izbaciGrupu(Grupa grupa) {
               Grupa grupaZaIzabaciti = null;
        for(Grupa g:  getGrupeKojimaKorisnikPripada()){
            if (g.getIDGrupa()== grupa.getIDGrupa()) {
                grupaZaIzabaciti=g;
            }
        }
        if(grupaZaIzabaciti!=null){
            getGrupeKojimaKorisnikPripada().remove(grupaZaIzabaciti);
        }
    }

    /**
     * @return the grupeKojimaKorisnikPripada
     */
    public List<Grupa> getGrupeKojimaKorisnikPripada() {
        return grupeKojimaKorisnikPripada;
    }

    /**
     * @param grupeKojimaKorisnikPripada the grupeKojimaKorisnikPripada to set
     */
    public void setGrupeKojimaKorisnikPripada(List<Grupa> grupeKojimaKorisnikPripada) {
        this.grupeKojimaKorisnikPripada = grupeKojimaKorisnikPripada;
    }

}
