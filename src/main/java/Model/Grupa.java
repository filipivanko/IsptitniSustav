package Model;

import com.sun.javafx.scene.control.skin.VirtualFlow;
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
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Grupa implements Serializable,Comparable<Grupa> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int IDGrupa;
    private String naziv;
    @ManyToOne
    private Kompanija kompanija;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "grupeKojimaKorisnikPripada")
    @Fetch (FetchMode.SELECT)
    private List<Korisnik> clanoviGrupe = new ArrayList<Korisnik>();
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "grupeKojePripadajuAdminu")
    @Fetch (FetchMode.SELECT)
    private List<Admin> adminiGrupe = new ArrayList<Admin>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "grupeKojeKoristeIspit")
    @Fetch (FetchMode.SELECT)
    private List<Ispit> ispitiGrupe = new ArrayList<Ispit>();

    public Grupa() {
    }

    public Grupa(String naziv, Kompanija kompanija) {
        this.naziv = naziv;
        this.kompanija = kompanija;
    }


    public boolean provjeriDaLiJePovezanAdmin(Admin admin) {
        for (Admin ad : getAdminiGrupe()) {
            if (ad.getIDAdmin() == admin.getIDAdmin()) {
                return true;
            }
        }
        return false;
    }

    
        public boolean provjeriDaLiJePovezanIspit(Ispit ispit) {
        for (Ispit is : getIspitiGrupe()) {
            if (is.getIDIspit() == ispit.getIDIspit()) {
                return true;
            }
        }
        return false;
    }
        
        public void izbaciIspit(Ispit ispit){
        Ispit ispitZaIzabaciti = null;
        for(Ispit i: getIspitiGrupe()){
            if (i.getIDIspit() == ispit.getIDIspit()) {
                ispitZaIzabaciti=i;
            }
        }
        if(ispitZaIzabaciti!=null){
            getIspitiGrupe().remove(ispitZaIzabaciti);
        }
        
        }
    /**
     * @return the IDGrupa
     */
    public int getIDGrupa() {
        return IDGrupa;
    }

    /**
     * @param IDGrupa the IDGrupa to set
     */
    public void setIDGrupa(int IDGrupa) {
        this.IDGrupa = IDGrupa;
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



    @Override
    public int compareTo(Grupa t) {
           if(this.getIDGrupa()>t.getIDGrupa()){
        return 1;
        }else{
        return -1;
        }
    }

    public void izbaciAdmina(Admin admin) {
           Admin adminZaIzabaciti = null;
        for(Admin a:  getAdminiGrupe()){
            if (a.getIDAdmin()== admin.getIDAdmin()) {
                adminZaIzabaciti=a;
            }
        }
        if(adminZaIzabaciti!=null){
            getIspitiGrupe().remove(adminZaIzabaciti);
        }
    }

    public boolean provjeriDaLiJePovezanKorisnik(Korisnik korisnik) {
                for (Korisnik k : getClanoviGrupe()) {
            if (k.getIDKorisnik() == korisnik.getIDKorisnik()) {
                return true;
            }
        }
        return false;
    }

    public void izbaciKorisnika(Korisnik korisnik) {
               Korisnik korisnikZaIzabaciti = null;
        for(Korisnik k:  getClanoviGrupe()){
            if (k.getIDKorisnik()== korisnik.getIDKorisnik()) {
                korisnikZaIzabaciti=k;
            }
        }
        if(korisnikZaIzabaciti!=null){
            getClanoviGrupe().remove(korisnikZaIzabaciti);
        }
    }

    /**
     * @return the clanoviGrupe
     */
    public List<Korisnik> getClanoviGrupe() {
        return clanoviGrupe;
    }

    /**
     * @param clanoviGrupe the clanoviGrupe to set
     */
    public void setClanoviGrupe(List<Korisnik> clanoviGrupe) {
        this.clanoviGrupe = clanoviGrupe;
    }

    /**
     * @return the adminiGrupe
     */
    public List<Admin> getAdminiGrupe() {
        return adminiGrupe;
    }

    /**
     * @param adminiGrupe the adminiGrupe to set
     */
    public void setAdminiGrupe(List<Admin> adminiGrupe) {
        this.adminiGrupe = adminiGrupe;
    }

    /**
     * @return the ispitiGrupe
     */
    public List<Ispit> getIspitiGrupe() {
        return ispitiGrupe;
    }

    /**
     * @param ispitiGrupe the ispitiGrupe to set
     */
    public void setIspitiGrupe(List<Ispit> ispitiGrupe) {
        this.ispitiGrupe = ispitiGrupe;
    }
}