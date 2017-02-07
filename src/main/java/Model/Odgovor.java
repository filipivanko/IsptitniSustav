
package Model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Odgovor implements Serializable,Comparable<Odgovor>{
    @Id
    @GeneratedValue
    private int IDOdgovor;
    private String vrstaOdgovora;
    private String tekstOdgovora;
    private String adresaSlikeOdovora;
    
    @ManyToOne
    private Pitanje pitanje;
    private String tocanNetocanOdgovor;
    private boolean tocnoIliNetocnoOdgovoren;
    
    public Odgovor() {
    }

    public Odgovor(String vrstaOdgovora, String tekstOdgovora, String adresaSlikeOdovora, Pitanje pitanje, String tocanNetocanOdgovor) {
        this.vrstaOdgovora = vrstaOdgovora;
        this.tekstOdgovora = tekstOdgovora;
        this.adresaSlikeOdovora = adresaSlikeOdovora;
        this.pitanje = pitanje;
        this.tocanNetocanOdgovor = tocanNetocanOdgovor;
    }

    /**
     * @return the IDOdgovor
     */
    public int getIDOdgovor() {
        return IDOdgovor;
    }

    /**
     * @param IDOdgovor the IDOdgovor to set
     */
    public void setIDOdgovor(int IDOdgovor) {
        this.IDOdgovor = IDOdgovor;
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
     * @return the tekstOdgovora
     */
    public String getTekstOdgovora() {
        return tekstOdgovora;
    }

    /**
     * @param tekstOdgovora the tekstOdgovora to set
     */
    public void setTekstOdgovora(String tekstOdgovora) {
        this.tekstOdgovora = tekstOdgovora;
    }

    /**
     * @return the adresaSlikeOdovora
     */
    public String getAdresaSlikeOdovora() {
        return adresaSlikeOdovora;
    }

    /**
     * @param adresaSlikeOdovora the adresaSlikeOdovora to set
     */
    public void setAdresaSlikeOdovora(String adresaSlikeOdovora) {
        this.adresaSlikeOdovora = adresaSlikeOdovora;
    }

    /**
     * @return the pitanje
     */
    public Pitanje getPitanje() {
        return pitanje;
    }

    /**
     * @param pitanje the pitanje to set
     */
    public void setPitanje(Pitanje pitanje) {
        this.pitanje = pitanje;
    }

    /**
     * @return the tocanNetocanOdgovor
     */
    public String isTocanNetocanOdgovor() {
        return getTocanNetocanOdgovor();
    }

    /**
     * @param tocanNetocanOdgovor the tocanNetocanOdgovor to set
     */
    public void setTocanNetocanOdgovor(String tocanNetocanOdgovor) {
        this.tocanNetocanOdgovor = tocanNetocanOdgovor;
    }

    /**
     * @return the tocnoIliNetocnoOdgovoren
     */
    public boolean isTocnoIliNetocnoOdgovoren() {
        return tocnoIliNetocnoOdgovoren;
    }

    /**
     * @param tocnoIliNetocnoOdgovoren the tocnoIliNetocnoOdgovoren to set
     */
    public void setTocnoIliNetocnoOdgovoren(boolean tocnoIliNetocnoOdgovoren) {
        this.tocnoIliNetocnoOdgovoren = tocnoIliNetocnoOdgovoren;
    }

    @Override
    public int compareTo(Odgovor t) {
           if(this.getIDOdgovor()>t.getIDOdgovor()){
        return 1;
        }else{
        return -1;
        }
    }

    /**
     * @return the tocanNetocanOdgovor
     */
    public String getTocanNetocanOdgovor() {
        return tocanNetocanOdgovor;
    }


    
    
}
