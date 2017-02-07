package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class GrupaPitanja implements Serializable,Comparable<GrupaPitanja>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int IDGrupaPitanja;
    private String nazivGrupe;
    private int brojPitanja;
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "grupePitanjaKojaSadrzePitanje")
    @Fetch (FetchMode.SELECT)
    private List<Pitanje> pitanjaUGrupi = new ArrayList<Pitanje>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch (FetchMode.SELECT)
    private List<Ispit> ispitiKojiSadrzeGrupuPitanja = new ArrayList<Ispit>();

    
    @ManyToOne
    private Kompanija kompanija;
    
    public GrupaPitanja() {
    }

    public GrupaPitanja(String nazivGrupe ,int brojPitanja, Kompanija kompanija) {
        this.nazivGrupe = nazivGrupe;
        this.kompanija = kompanija;
        this.brojPitanja = brojPitanja;
    }


    public void ukloniIspitPoIDu(int idIspit) {
        Ispit ispitZaUkloniti = null;
        for(Ispit i:getIspitiKojiSadrzeGrupuPitanja()){
        if(i.getIDIspit()==idIspit){
        ispitZaUkloniti= i;
        }
        }
        if(ispitZaUkloniti!=null){
            getIspitiKojiSadrzeGrupuPitanja().remove(ispitZaUkloniti);
        }
    }

    /**
     * @return the IDGrupaPitanja
     */
    public int getIDGrupaPitanja() {
        return IDGrupaPitanja;
    }

    /**
     * @param IDGrupaPitanja the IDGrupaPitanja to set
     */
    public void setIDGrupaPitanja(int IDGrupaPitanja) {
        this.IDGrupaPitanja = IDGrupaPitanja;
    }

    /**
     * @return the nazivGrupe
     */
    public String getNazivGrupe() {
        return nazivGrupe;
    }

    /**
     * @param nazivGrupe the nazivGrupe to set
     */
    public void setNazivGrupe(String nazivGrupe) {
        this.nazivGrupe = nazivGrupe;
    }

    /**
     * @return the pitanjaUGrupi
     */


    @Override
    public int compareTo(GrupaPitanja t) {
          if(this.getIDGrupaPitanja()>t.getIDGrupaPitanja()){
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

    public boolean provjeriDaLiJePovezanIspit(Ispit ispit) {
          for (Ispit is : getIspitiKojiSadrzeGrupuPitanja()) {
            if (is.getIDIspit() == ispit.getIDIspit()) {
                return true;
            }
        }
        return false;
    }

    public void izbaciIspit(Ispit ispit) {
        
               Ispit ispitZaIzabaciti = null;
        for(Ispit i:getIspitiKojiSadrzeGrupuPitanja()){
            if (i.getIDIspit()== ispit.getIDIspit()) {
               ispitZaIzabaciti=i;
            }
        }
        if(ispitZaIzabaciti!=null){
            getIspitiKojiSadrzeGrupuPitanja().remove(ispitZaIzabaciti);
        }
       
    }

    public boolean provjeriDaLiJePovezaoPitanje(Pitanje pitanje) {
           for (Pitanje p : getPitanjaUGrupi()) {
            if (p.getIDPitanje() == pitanje.getIDPitanje()) {
                return true;
            }
        }
        return false;
    }

    public void izbaciPitanje(Pitanje pitanje) {
        Pitanje pitanjeZaIzabaciti = null;
        for(Pitanje p:getPitanjaUGrupi()){
            if (p.getIDPitanje()== pitanje.getIDPitanje()) {
               pitanjeZaIzabaciti=p;
            }
        }
        if(pitanjeZaIzabaciti!=null){
            getPitanjaUGrupi().remove(pitanjeZaIzabaciti);
        }
    }

    /**
     * @return the pitanjaUGrupi
     */
    public List<Pitanje> getPitanjaUGrupi() {
        return pitanjaUGrupi;
    }

    /**
     * @param pitanjaUGrupi the pitanjaUGrupi to set
     */
    public void setPitanjaUGrupi(List<Pitanje> pitanjaUGrupi) {
        this.pitanjaUGrupi = pitanjaUGrupi;
    }

    /**
     * @return the ispitiKojiSadrzeGrupuPitanja
     */
    public List<Ispit> getIspitiKojiSadrzeGrupuPitanja() {
        return ispitiKojiSadrzeGrupuPitanja;
    }

    /**
     * @param ispitiKojiSadrzeGrupuPitanja the ispitiKojiSadrzeGrupuPitanja to set
     */
    public void setIspitiKojiSadrzeGrupuPitanja(List<Ispit> ispitiKojiSadrzeGrupuPitanja) {
        this.ispitiKojiSadrzeGrupuPitanja = ispitiKojiSadrzeGrupuPitanja;
    }

    /**
     * @return the brojPitanja
     */
    public int getBrojPitanja() {
        return brojPitanja;
    }

    /**
     * @param brojPitanja the brojPitanja to set
     */
    public void setBrojPitanja(int brojPitanja) {
        this.brojPitanja = brojPitanja;
    }

}
