
package Model;

import DAO.Repozitorij;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class InstancaPitanja implements  Serializable, Comparable<InstancaPitanja> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private int IDInstancaPitanja;
   private int pitanjeID;
   private String vrstaOdgovora;
   private String tekst;
   private String adresaSlike;
   private boolean tocnoOdgovoreno;
   private String korisnikovOdgovor;
   private String tocanOdgovor;
   @ManyToOne
   private InstancaGrupePitanja instancaGrupePitanja;
   
   @OneToMany(fetch = FetchType.EAGER,mappedBy = "instancaPitanja")
   @Fetch (FetchMode.SELECT)
   private List<InstancaOdgovora> instanceOdgovora =new ArrayList<InstancaOdgovora>();

    public InstancaPitanja() {
    }
   

    InstancaPitanja(Pitanje p , InstancaGrupePitanja ig,Repozitorij repo) {
        pitanjeID = p.getIDPitanje();
        vrstaOdgovora = p.getVrstaOdgovora();
        tekst = p.getTekstPitanja();
        adresaSlike = p.getAdresaSlikePitanja();
        instancaGrupePitanja = ig;
        repo.spremiRucnoBezOtvaranjaIZatvaranja(this);
        napuniInstanceOdgovora(p,repo);
        repo.promjeniRucnoBezOtvaranjaIZatvaranja(this);
        tocnoOdgovoreno = false;
    }

    private void napuniInstanceOdgovora(Pitanje p,Repozitorij repo) {
        for(Odgovor o:p.getOdgovori()){
            InstancaOdgovora io=new InstancaOdgovora(o,this,repo);
            repo.spremiRucnoBezOtvaranjaIZatvaranja(io);
            getInstanceOdgovora().add(io);
        }
    }

    /**
     * @return the IDInstancaPitanja
     */
    public int getIDInstancaPitanja() {
        return IDInstancaPitanja;
    }

    /**
     * @param IDInstancaPitanja the IDInstancaPitanja to set
     */
    public void setIDInstancaPitanja(int IDInstancaPitanja) {
        this.IDInstancaPitanja = IDInstancaPitanja;
    }

    /**
     * @return the pitanjeID
     */
    public int getPitanjeID() {
        return pitanjeID;
    }

    /**
     * @param pitanjeID the pitanjeID to set
     */
    public void setPitanjeID(int pitanjeID) {
        this.pitanjeID = pitanjeID;
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
     * @return the tekst
     */
    public String getTekst() {
        return tekst;
    }

    /**
     * @param tekst the tekst to set
     */
    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    /**
     * @return the adresaSlike
     */
    public String getAdresaSlike() {
        return adresaSlike;
    }

    /**
     * @param adresaSlike the adresaSlike to set
     */
    public void setAdresaSlike(String adresaSlike) {
        this.adresaSlike = adresaSlike;
    }

    /**
     * @return the instancaGrupePitanja
     */
    public InstancaGrupePitanja getInstancaGrupePitanja() {
        return instancaGrupePitanja;
    }

    /**
     * @param instancaGrupePitanja the instancaGrupePitanja to set
     */
    public void setInstancaGrupePitanja(InstancaGrupePitanja instancaGrupePitanja) {
        this.instancaGrupePitanja = instancaGrupePitanja;
    }



    @Override
    public int compareTo(InstancaPitanja t) {
        if (this.getIDInstancaPitanja() > t.getIDInstancaPitanja()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * @return the instanceOdgovora
     */
    public List<InstancaOdgovora> getInstanceOdgovora() {
        return instanceOdgovora;
    }

    /**
     * @param instanceOdgovora the instanceOdgovora to set
     */
    public void setInstanceOdgovora(List<InstancaOdgovora> instanceOdgovora) {
        this.instanceOdgovora = instanceOdgovora;
    }

    /**
     * @return the tocnoOdgovoreno
     */
    public boolean isTocnoOdgovoreno() {
        return tocnoOdgovoreno;
    }

    /**
     * @param tocnoOdgovoreno the tocnoOdgovoreno to set
     */
    public void setTocnoOdgovoreno(boolean tocnoOdgovoreno) {
        this.tocnoOdgovoreno = tocnoOdgovoreno;
    }

    /**
     * @return the korisnikovOdgovor
     */
    public String getKorisnikovOdgovor() {
        return korisnikovOdgovor;
    }

    /**
     * @param korisnikovOdgovor the korisnikovOdgovor to set
     */
    public void setKorisnikovOdgovor(String korisnikovOdgovor) {
        this.korisnikovOdgovor = korisnikovOdgovor;
    }

    /**
     * @return the tocanOdgovor
     */
    public String getTocanOdgovor() {
        return tocanOdgovor;
    }

    /**
     * @param tocanOdgovor the tocanOdgovor to set
     */
    public void setTocanOdgovor(String tocanOdgovor) {
        this.tocanOdgovor = tocanOdgovor;
    }
}
