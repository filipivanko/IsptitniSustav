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
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Kompanija implements Serializable, Comparable<Kompanija> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDKompanija;
    private String naziv;
    private String sjediste;
    private String oib;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kompanijaKojojIspitPripada")
    @Fetch (FetchMode.SELECT)
    private List<Ispit> ispitiKompanije = new ArrayList<Ispit>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kompanija")
    @Fetch (FetchMode.SELECT)
    private List<Grupa> grupeUKompaniji = new ArrayList<Grupa>();
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kompanija")
    @Fetch (FetchMode.SELECT)
    private List<Admin> adminiKompanije = new ArrayList<Admin>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kompanija")
    @Fetch (FetchMode.SELECT)
    private List<Pitanje> pitanjaUKompaniji = new ArrayList<Pitanje>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kompanija")
    @Fetch (FetchMode.SELECT)
    private List<GrupaPitanja> grupePitanjaKompanije = new ArrayList<GrupaPitanja>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kompanija")
    @Fetch (FetchMode.SELECT)
    private List<Korisnik> korisniciKompanije = new ArrayList<Korisnik>();

    public Kompanija() {
    }

    public Kompanija(String naziv, String sjediste, String oib) {
        this.naziv = naziv;
        this.sjediste = sjediste;
        this.oib = oib;
        this.grupeUKompaniji.add(new Grupa("Svi", this));

    }

    /**
     * @return the IDKompanija
     */
    public int getIDKompanija() {
        return IDKompanija;
    }

    /**
     * @param IDKompanija the IDKompanija to set
     */
    public void setIDKompanija(int IDKompanija) {
        this.IDKompanija = IDKompanija;
    }

    /**
     * @return the naziv
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * @param naziv the naziv to set
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * @return the sjediste
     */
    public String getSjediste() {
        return sjediste;
    }

    /**
     * @param sjediste the sjediste to set
     */
    public void setSjediste(String sjediste) {
        this.sjediste = sjediste;
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

    @Override
    public int compareTo(Kompanija t) {
        if (this.getIDKompanija() > t.getIDKompanija()) {
            return 1;
        } else {
            return -1;
        }

    }

    /**
     * @return the ispitiKompanije
     */
    public List<Ispit> getIspitiKompanije() {
        return ispitiKompanije;
    }

    /**
     * @param ispitiKompanije the ispitiKompanije to set
     */
    public void setIspitiKompanije(List<Ispit> ispitiKompanije) {
        this.ispitiKompanije = ispitiKompanije;
    }

    /**
     * @return the grupeUKompaniji
     */
    public List<Grupa> getGrupeUKompaniji() {
        return grupeUKompaniji;
    }

    /**
     * @param grupeUKompaniji the grupeUKompaniji to set
     */
    public void setGrupeUKompaniji(List<Grupa> grupeUKompaniji) {
        this.grupeUKompaniji = grupeUKompaniji;
    }

    /**
     * @return the adminiKompanije
     */
    public List<Admin> getAdminiKompanije() {
        return adminiKompanije;
    }

    /**
     * @param adminiKompanije the adminiKompanije to set
     */
    public void setAdminiKompanije(List<Admin> adminiKompanije) {
        this.adminiKompanije = adminiKompanije;
    }

    /**
     * @return the pitanjaUKompaniji
     */
    public List<Pitanje> getPitanjaUKompaniji() {
        return pitanjaUKompaniji;
    }

    /**
     * @param pitanjaUKompaniji the pitanjaUKompaniji to set
     */
    public void setPitanjaUKompaniji(List<Pitanje> pitanjaUKompaniji) {
        this.pitanjaUKompaniji = pitanjaUKompaniji;
    }

    /**
     * @return the grupePitanjaKompanije
     */
    public List<GrupaPitanja> getGrupePitanjaKompanije() {
        return grupePitanjaKompanije;
    }

    /**
     * @param grupePitanjaKompanije the grupePitanjaKompanije to set
     */
    public void setGrupePitanjaKompanije(List<GrupaPitanja> grupePitanjaKompanije) {
        this.grupePitanjaKompanije = grupePitanjaKompanije;
    }

    /**
     * @return the korisniciKompanije
     */
    public List<Korisnik> getKorisniciKompanije() {
        return korisniciKompanije;
    }

    /**
     * @param korisniciKompanije the korisniciKompanije to set
     */
    public void setKorisniciKompanije(List<Korisnik> korisniciKompanije) {
        this.korisniciKompanije = korisniciKompanije;
    }
}