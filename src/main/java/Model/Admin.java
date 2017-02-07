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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Admin implements Serializable, Comparable<Admin> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int IDAdmin;
    private String korisnickoIme;
    private String zaporka;
    private String razinaovlasti;

    @ManyToOne
    private Kompanija kompanija;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch (FetchMode.SELECT)
    private List<Grupa> grupeKojePripadajuAdminu = new ArrayList<Grupa>();

    public Admin() {
    }

    public Admin(String korisnickoIme, String zaporka, String razinaovlasti, Kompanija kompanija) {
        this.korisnickoIme = korisnickoIme;
        this.zaporka = zaporka;
        this.razinaovlasti = razinaovlasti;
        this.kompanija = kompanija;
    }

    /**
     * @return the IDAdmin
     */
    public int getIDAdmin() {
        return IDAdmin;
    }

    /**
     * @param IDAdmin the IDAdmin to set
     */
    public void setIDAdmin(int IDAdmin) {
        this.IDAdmin = IDAdmin;
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

    /**
     * @return the razinaovlasti
     */
    public String getRazinaovlasti() {
        return razinaovlasti;
    }

    /**
     * @param razinaovlasti the razinaovlasti to set
     */
    public void setRazinaovlasti(String razinaovlasti) {
        this.razinaovlasti = razinaovlasti;
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
        for (Grupa gr : getGrupeKojePripadajuAdminu()) {
            if (gr.getIDGrupa() == grupa.getIDGrupa()) {
                return true;
            }

        }
        return false;
    }

    public void ukloniGrupuPoIDu(int idGrupa) {
        Grupa grupaZaUkloniti = null;
        for (Grupa g : getGrupeKojePripadajuAdminu()) {
            if (g.getIDGrupa() == idGrupa) {
                grupaZaUkloniti = g;
            }
        }
        if (grupaZaUkloniti != null) {
            getGrupeKojePripadajuAdminu().remove(grupaZaUkloniti);
        }
    }

 

    @Override
    public int compareTo(Admin t) {
        if (this.getIDAdmin() > t.getIDAdmin()) {
            return 1;
        } else {
            return -1;
        }
    }

    public void izbaciGrupu(Grupa grupa) {
        Grupa grupaZaUkloniti = null;
        for (Grupa g : getGrupeKojePripadajuAdminu()) {
            if (g.getIDGrupa() == grupa.getIDGrupa()) {
                grupaZaUkloniti = g;
            }
        }
        if (grupaZaUkloniti != null) {
            getGrupeKojePripadajuAdminu().remove(grupaZaUkloniti);
        }
    }

    public boolean povjeriDaLiAdministriragrupu(Grupa odabranaGrupa) {
        for (Grupa g : getGrupeKojePripadajuAdminu()) {
            if (g.getIDGrupa() == odabranaGrupa.getIDGrupa()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the grupeKojePripadajuAdminu
     */
    public List<Grupa> getGrupeKojePripadajuAdminu() {
        return grupeKojePripadajuAdminu;
    }

    /**
     * @param grupeKojePripadajuAdminu the grupeKojePripadajuAdminu to set
     */
    public void setGrupeKojePripadajuAdminu(List<Grupa> grupeKojePripadajuAdminu) {
        this.grupeKojePripadajuAdminu = grupeKojePripadajuAdminu;
    }

}
