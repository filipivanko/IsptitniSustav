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
public class Pitanje implements Serializable, Comparable<Pitanje> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int IDPitanje;
    private String tekstPitanja;
    private String adresaSlikePitanja;
    private String vrstaOdgovora;
    private boolean aktivnoPitanje;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pitanje")
    @Fetch (FetchMode.SELECT)
    private List<Odgovor> odgovori = new ArrayList<Odgovor>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch (FetchMode.SELECT)
    private List<GrupaPitanja> grupePitanjaKojaSadrzePitanje = new ArrayList<GrupaPitanja>();
    
    @ManyToOne
    private Kompanija kompanija;

    public Pitanje() {
    }

    public Pitanje(String tekstPitanja, String adresaSlikePitanja, String vrstaOdgovora, Kompanija kompanija) {

        this.tekstPitanja = tekstPitanja;
        this.adresaSlikePitanja = adresaSlikePitanja;
        this.vrstaOdgovora = vrstaOdgovora;
        this.kompanija = kompanija;
    }

    /**
     * @return the IDPitanje
     */
    public int getIDPitanje() {
        return IDPitanje;
    }

    /**
     * @param IDPitanje the IDPitanje to set
     */
    public void setIDPitanje(int IDPitanje) {
        this.IDPitanje = IDPitanje;
    }

    /**
     * @return the tekstPitanja
     */
    public String getTekstPitanja() {
        return tekstPitanja;
    }

    /**
     * @param tekstPitanja the tekstPitanja to set
     */
    public void setTekstPitanja(String tekstPitanja) {
        this.tekstPitanja = tekstPitanja;
    }

    /**
     * @return the adresaSlikePitanja
     */
    public String getAdresaSlikePitanja() {
        return adresaSlikePitanja;
    }

    /**
     * @param adresaSlikePitanja the adresaSlikePitanja to set
     */
    public void setAdresaSlikePitanja(String adresaSlikePitanja) {
        this.adresaSlikePitanja = adresaSlikePitanja;
    }

    /**
     * @return the vrstaOdgovora
     */
    public String getVrstaOdgovora() {
        return vrstaOdgovora;
    }

    /**
     * @param vrstaOdgovora the vrstaOdgovora to set
     */
    public void setVrstaOdgovora(String vrstaOdgovora) {
        this.vrstaOdgovora = vrstaOdgovora;
    }

    /**
     * @return the aktivnoPitanje
     */
    public boolean isAktivnoPitanje() {
        return aktivnoPitanje;
    }

    /**
     * @param aktivnoPitanje the aktivnoPitanje to set
     */
    public void setAktivnoPitanje(boolean aktivnoPitanje) {
        this.aktivnoPitanje = aktivnoPitanje;
    }


    /**
     * @return the grupaPitanjaKojaSadrziPitanje
     */
    @Override
    public int compareTo(Pitanje t) {
        if (this.getIDPitanje() > t.getIDPitanje()) {
            return 1;
        } else {
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


    public void ukloniGrupuPitanja(int idGrupaPitanja) {
        GrupaPitanja grupaPitanjaZaUkloniti = null;
        for (GrupaPitanja g : getGrupePitanjaKojaSadrzePitanje()) {
            if (g.getIDGrupaPitanja() == idGrupaPitanja) {
                grupaPitanjaZaUkloniti = g;

            }
        }
        if (grupaPitanjaZaUkloniti != null) {
            getGrupePitanjaKojaSadrzePitanje().remove(grupaPitanjaZaUkloniti);
        }
    }

    public boolean provjeriDaLiJePovezanaGrupaPitanja(GrupaPitanja grupaPitanja) {
        for (GrupaPitanja p : getGrupePitanjaKojaSadrzePitanje()) {
            if (p.getIDGrupaPitanja() == grupaPitanja.getIDGrupaPitanja()) {
                return true;
            }
        }
        return false;
    }

    public void izbaciGrupuPitanja(GrupaPitanja grupaPitanja) {

        GrupaPitanja grupaPitanjaZaIzbaciti = null;
        for (GrupaPitanja g : getGrupePitanjaKojaSadrzePitanje()) {
            if (g.getIDGrupaPitanja() == grupaPitanja.getIDGrupaPitanja()) {
                grupaPitanjaZaIzbaciti = g;
            }
        }
        if (grupaPitanjaZaIzbaciti != null) {
            getGrupePitanjaKojaSadrzePitanje().remove(grupaPitanjaZaIzbaciti);
        }

    }

    /**
     * @return the odgovori
     */
    public List<Odgovor> getOdgovori() {
        return odgovori;
    }

    /**
     * @param odgovori the odgovori to set
     */
    public void setOdgovori(List<Odgovor> odgovori) {
        this.odgovori = odgovori;
    }

    /**
     * @return the grupePitanjaKojaSadrzePitanje
     */
    public List<GrupaPitanja> getGrupePitanjaKojaSadrzePitanje() {
        return grupePitanjaKojaSadrzePitanje;
    }

    /**
     * @param grupePitanjaKojaSadrzePitanje the grupePitanjaKojaSadrzePitanje to set
     */
    public void setGrupePitanjaKojaSadrzePitanje(List<GrupaPitanja> grupePitanjaKojaSadrzePitanje) {
        this.grupePitanjaKojaSadrzePitanje = grupePitanjaKojaSadrzePitanje;
    }

    public void izbaciOdgovor(Odgovor odgovor) {
       Odgovor odgovorZaIzbaciti =null;
       for(Odgovor o: odgovori){
           if (o.getIDOdgovor()==odgovor.getIDOdgovor()) {
               odgovorZaIzbaciti=o;
           }
       }
       if(odgovorZaIzbaciti!=null){
       odgovori.remove(odgovorZaIzbaciti);
       }
    }
}
