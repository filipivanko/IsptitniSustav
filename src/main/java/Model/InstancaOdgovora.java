package Model;

import DAO.Repozitorij;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class InstancaOdgovora implements Serializable, Comparable<InstancaOdgovora> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int IDInstancaOdgovora;
    private String tekst;
    private String adresaSlike;
    private String tocan;
    private boolean tocnoOdgovoren;
    @ManyToOne
    private InstancaPitanja instancaPitanja;

    public InstancaOdgovora() {
    }

    InstancaOdgovora(Odgovor o, InstancaPitanja instancaPitanja, Repozitorij repo) {
        tekst = o.getTekstOdgovora();
        adresaSlike = o.getAdresaSlikeOdovora();
        tocan = o.getTocanNetocanOdgovor();
        this.instancaPitanja = instancaPitanja;

        if (tocan.equals("tocan")) {
            tocnoOdgovoren = false;
        } else {
            tocnoOdgovoren = true;
        }
    }

    /**
     * @return the IDInstancaOdgovora
     */
    public int getIDInstancaOdgovora() {
        return IDInstancaOdgovora;
    }

    /**
     * @param IDInstancaOdgovora the IDInstancaOdgovora to set
     */
    public void setIDInstancaOdgovora(int IDInstancaOdgovora) {
        this.IDInstancaOdgovora = IDInstancaOdgovora;
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
     * @return the tocan
     */
    public String getTocan() {
        return tocan;
    }

    /**
     * @param tocan the tocan to set
     */
    public void setTocan(String tocan) {
        this.tocan = tocan;
    }

    /**
     * @return the instancaPitanja
     */
    public InstancaPitanja getInstancaPitanja() {
        return instancaPitanja;
    }

    /**
     * @param instancaPitanja the instancaPitanja to set
     */
    public void setInstancaPitanja(InstancaPitanja instancaPitanja) {
        this.instancaPitanja = instancaPitanja;
    }

    @Override
    public int compareTo(InstancaOdgovora t) {

        if (this.getIDInstancaOdgovora() > t.getIDInstancaOdgovora()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * @return the tocnoOdgovoren
     */
    public boolean isTocnoOdgovoren() {
        return tocnoOdgovoren;
    }

    /**
     * @param tocnoOdgovoren the tocnoOdgovoren to set
     */
    public void setTocnoOdgovoren(boolean tocnoOdgovoren) {
        this.tocnoOdgovoren = tocnoOdgovoren;
    }

}
