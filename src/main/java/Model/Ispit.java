
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Ispit implements Serializable,Comparable<Ispit> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int IDIspit;
    private String naziv;
    private double pragZaProlaz;
    private int brojDanaIspitJeAktivan;
   @ManyToOne
   private Kompanija kompanijaKojojIspitPripada;
    
  @ManyToMany(fetch = FetchType.EAGER,mappedBy = "ispitiKojiSadrzeGrupuPitanja")
  @Fetch (FetchMode.SELECT)
  private List<GrupaPitanja> grupePitanjaUIspitu = new ArrayList<GrupaPitanja>();
    
   @ManyToMany(fetch = FetchType.EAGER)
   private List<Grupa> grupeKojeKoristeIspit = new ArrayList<Grupa>() ;

    public Ispit() {
    }

    public Ispit( String naziv, double pragZaProlaz,Kompanija kompanja, int brojDana) {
        this.naziv = naziv;
        this.pragZaProlaz = pragZaProlaz;
        this.kompanijaKojojIspitPripada  = kompanja;
        this.brojDanaIspitJeAktivan = brojDana;
    }


    public void ukloniGrupuPoIDu(int idGrupa) {
             Grupa grupaZaUkloniti = null;
        for (Grupa g : getGrupeKojeKoristeIspit()) {
            if (g.getIDGrupa() == idGrupa) {
                grupaZaUkloniti = g;
            }
        }
        if (grupaZaUkloniti != null) {
            getGrupeKojeKoristeIspit().remove(grupaZaUkloniti);
        }
    }

    /**
     * @return the IDIspit
     */
    public int getIDIspit() {
        return IDIspit;
    }

    /**
     * @param IDIspit the IDIspit to set
     */
    public void setIDIspit(int IDIspit) {
        this.IDIspit = IDIspit;
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
     * @return the pragZaProlaz
     */
    public double getPragZaProlaz() {
        return pragZaProlaz;
    }

    /**
     * @param pragZaProlaz the pragZaProlaz to set
     */
    public void setPragZaProlaz(double pragZaProlaz) {
        this.pragZaProlaz = pragZaProlaz;
    }

    /**
     * @return the kompanijaKojojIspitPripada
     */
    public Kompanija getKompanijaKojojIspitPripada() {
        return kompanijaKojojIspitPripada;
    }

    /**
     * @param kompanijaKojojIspitPripada the kompanijaKojojIspitPripada to set
     */
    public void setKompanijaKojojIspitPripada(Kompanija kompanijaKojojIspitPripada) {
        this.kompanijaKojojIspitPripada = kompanijaKojojIspitPripada;
    }



    @Override
    public int compareTo(Ispit t) {
           if(this.getIDIspit()>t.getIDIspit()){
        return 1;
        }else{
        return -1;
        }
    }

    public boolean provjeriDaLiJePovezanaGrupa(Grupa grupa) {
           for (Grupa gr : getGrupeKojeKoristeIspit()) {
            if (gr.getIDGrupa() == grupa.getIDGrupa()) {
                return true;
            }
        }
        return false;
    }

    public void izbaciGrupu(Grupa grupa) {
           Grupa grupaZaIzabaciti = null;
        for(Grupa g: getGrupeKojeKoristeIspit()){
            if (g.getIDGrupa()== grupa.getIDGrupa()) {
               grupaZaIzabaciti=g;
            }
        }
        if(grupaZaIzabaciti!=null){
        getGrupeKojeKoristeIspit().remove(grupaZaIzabaciti);
        }
    }

    public boolean provjeriDaLiJePovezanaGrupaPitanja(GrupaPitanja grupaPitanja) {
              for (GrupaPitanja gr : getGrupePitanjaUIspitu()) {
            if (gr.getIDGrupaPitanja()== grupaPitanja.getIDGrupaPitanja()) {
                return true;
            }
        }
        return false;
    }

    public void ukloniGrupuPitanjaPoIDu(int idGrupaPitanja) {
        GrupaPitanja grupaPitanjaZaUkloniti = null;
        for (GrupaPitanja g : getGrupePitanjaUIspitu()) {
            if (g.getIDGrupaPitanja()== idGrupaPitanja) {
                grupaPitanjaZaUkloniti = g;
            }
        }
        if (grupaPitanjaZaUkloniti != null) {
            getGrupePitanjaUIspitu().remove(grupaPitanjaZaUkloniti);
        }
    }

    public void izbaciGrupuPitanja(GrupaPitanja grupaPitanja) {
            GrupaPitanja grupaPitanjaZaIzabaciti = null;
        for(GrupaPitanja g:getGrupePitanjaUIspitu()){
            if (g.getIDGrupaPitanja()== grupaPitanja.getIDGrupaPitanja()) {
               grupaPitanjaZaIzabaciti=g;
            }
        }
        if(grupaPitanjaZaIzabaciti!=null){
            getGrupePitanjaUIspitu().remove(grupaPitanjaZaIzabaciti);
        }
    
    }

    /**
     * @return the grupePitanjaUIspitu
     */
    public List<GrupaPitanja> getGrupePitanjaUIspitu() {
        return grupePitanjaUIspitu;
    }

    /**
     * @param grupePitanjaUIspitu the grupePitanjaUIspitu to set
     */
    public void setGrupePitanjaUIspitu(List<GrupaPitanja> grupePitanjaUIspitu) {
        this.grupePitanjaUIspitu = grupePitanjaUIspitu;
    }

    /**
     * @return the grupeKojeKoristeIspit
     */
    public List<Grupa> getGrupeKojeKoristeIspit() {
        return grupeKojeKoristeIspit;
    }

    /**
     * @param grupeKojeKoristeIspit the grupeKojeKoristeIspit to set
     */
    public void setGrupeKojeKoristeIspit(List<Grupa> grupeKojeKoristeIspit) {
        this.grupeKojeKoristeIspit = grupeKojeKoristeIspit;
    }

    /**
     * @return the brojDanaIspitJeAktivan
     */
    public int getBrojDanaIspitJeAktivan() {
        return brojDanaIspitJeAktivan;
    }

    /**
     * @param brojDanaIspitJeAktivan the brojDanaIspitJeAktivan to set
     */
    public void setBrojDanaIspitJeAktivan(int brojDanaIspitJeAktivan) {
        this.brojDanaIspitJeAktivan = brojDanaIspitJeAktivan;
    }
}
