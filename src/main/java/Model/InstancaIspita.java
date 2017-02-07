package Model;

import DAO.Repozitorij;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class InstancaIspita implements Serializable,Comparable<InstancaIspita> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int IDInstancaIspita;
    private String naziv;
    private int ispitID;
    private int korisnikID;
    private double pragZaProlaz;
    private double prosjekRijesenostiIspita;
    private boolean zavrsen;
    private boolean polozen;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumPocetkaIspita;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumPisanjaIspita;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date krajnjirokIspita;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "instancaIspita")
    @Fetch (FetchMode.SELECT)
    private List<InstancaGrupePitanja> instanceGrupaPitanja = new ArrayList<InstancaGrupePitanja>();


    public InstancaIspita() {
    }

    public InstancaIspita(Ispit ispit, Korisnik korisnik,Repozitorij repo) {
        repo.otvoriSession();
        this.naziv = ispit.getNaziv();
        this.ispitID = ispit.getIDIspit();
        this.korisnikID = korisnik.getIDKorisnik();
        this.zavrsen = false;
        this.polozen = false;
        this.pragZaProlaz = ispit.getPragZaProlaz();
        datumPocetkaIspita= new Date();
        krajnjirokIspita = new Date(getDatumPocetkaIspita().getTime() + ispit.getBrojDanaIspitJeAktivan()*(1000 * 60 * 60 * 24));
        
        
        repo.spremiRucnoBezOtvaranjaIZatvaranja(this);
        napuniInstanceGrupaPitanja(ispit,repo);
        repo.promjeniRucnoBezOtvaranjaIZatvaranja(this);

        repo.zatvoriSession();

    }

    private void napuniInstanceGrupaPitanja(Ispit ispit,Repozitorij repo) {
        for (GrupaPitanja g : ispit.getGrupePitanjaUIspitu()) {
            InstancaGrupePitanja igp = new InstancaGrupePitanja(g, this, repo);
            repo.spremiRucnoBezOtvaranjaIZatvaranja(igp);
            getInstanceGrupaPitanja().add(igp);
        }
    }

    /**
     * @return the IDInstancaIspita
     */
    public int getIDInstancaIspita() {
        return IDInstancaIspita;
    }

    /**
     * @param IDInstancaIspita the IDInstancaIspita to set
     */
    public void setIDInstancaIspita(int IDInstancaIspita) {
        this.IDInstancaIspita = IDInstancaIspita;
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
     * @return the ispitID
     */
    public int getIspitID() {
        return ispitID;
    }

    /**
     * @param ispitID the ispitID to set
     */
    public void setIspitID(int ispitID) {
        this.ispitID = ispitID;
    }

    /**
     * @return the korisnikID
     */
    public int getKorisnikID() {
        return korisnikID;
    }

    /**
     * @param korisnikID the korisnikID to set
     */
    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    /**
     * @return the zavrsen
     */
    public boolean isZavrsen() {
        return zavrsen;
    }

    /**
     * @param zavrsen the zavrsen to set
     */
    public void setZavrsen(boolean zavrsen) {
        this.zavrsen = zavrsen;
    }

    /**
     * @return the polozen
     */
    public boolean isPolozen() {
        return polozen;
    }

    /**
     * @param polozen the polozen to set
     */
    public void setPolozen(boolean polozen) {
        this.polozen = polozen;
    }

    @Override
    public int compareTo(InstancaIspita t) {
        if (this.getIDInstancaIspita() > t.getIDInstancaIspita()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * @return the instanceGrupaPitanja
     */
    public List<InstancaGrupePitanja> getInstanceGrupaPitanja() {
        return instanceGrupaPitanja;
    }

    /**
     * @param instanceGrupaPitanja the instanceGrupaPitanja to set
     */
    public void setInstanceGrupaPitanja(List<InstancaGrupePitanja> instanceGrupaPitanja) {
        this.instanceGrupaPitanja = instanceGrupaPitanja;
    }

    public void obradiRezultate() {
        setZavrsen(true);
        setPolozen(obradiGrupePitanja());
    }

    private boolean obradiGrupePitanja() {
        double ukupniBrojRaspolozivihBodovaNaIspitu =0;
        double brojOstvarenihBodovaNaIspitu=0;
        
        for(InstancaGrupePitanja igp: getInstanceGrupaPitanja()){
            double brojRaspolozivihbodovauGrupi =0;
            double brojPostignutihBodovaUgrupi =0;
            for(InstancaPitanja ip:igp.getInstancePitanja()){
                ukupniBrojRaspolozivihBodovaNaIspitu ++;
                brojRaspolozivihbodovauGrupi ++;
                boolean pitnjeTocnoOdgovoreno=true;
                for (InstancaOdgovora io :ip.getInstanceOdgovora()) {
                   if(!io.isTocnoOdgovoren()){
                   pitnjeTocnoOdgovoreno=false;
                   }
                }
                
                ip.setTocnoOdgovoreno(pitnjeTocnoOdgovoreno);
                if(pitnjeTocnoOdgovoreno){
                brojOstvarenihBodovaNaIspitu ++;
                brojPostignutihBodovaUgrupi ++;
                }
            } 
        }
        setProsjekRijesenostiIspita(brojOstvarenihBodovaNaIspitu/ukupniBrojRaspolozivihBodovaNaIspitu);
       
        if(getProsjekRijesenostiIspita()>=getPragZaProlaz()){
        return true;
        
        }
        else{
        return false;
        
        }
       
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
     * @return the prosjekRijesenostiIspita
     */
    public double getProsjekRijesenostiIspita() {
        return prosjekRijesenostiIspita;
    }

    /**
     * @param prosjekRijesenostiIspita the prosjekRijesenostiIspita to set
     */
    public void setProsjekRijesenostiIspita(double prosjekRijesenostiIspita) {
        this.prosjekRijesenostiIspita = prosjekRijesenostiIspita;
    }

    /**
     * @return the datumPocetkaIspita
     */
    public Date getDatumPocetkaIspita() {
        return datumPocetkaIspita;
    }

    /**
     * @param datumPocetkaIspita the datumPocetkaIspita to set
     */
    public void setDatumPocetkaIspita(Date datumPocetkaIspita) {
        this.datumPocetkaIspita = datumPocetkaIspita;
    }

    /**
     * @return the datumPisanjaIspita
     */
    public Date getDatumPisanjaIspita() {
        return datumPisanjaIspita;
    }

    /**
     * @param datumPisanjaIspita the datumPisanjaIspita to set
     */
    public void setDatumPisanjaIspita(Date datumPisanjaIspita) {
        this.datumPisanjaIspita = datumPisanjaIspita;
    }

    /**
     * @return the krajnjirokIspita
     */
    public Date getKrajnjirokIspita() {
        return krajnjirokIspita;
    }

    /**
     * @param krajnjirokIspita the krajnjirokIspita to set
     */
    public void setKrajnjirokIspita(Date krajnjirokIspita) {
        this.krajnjirokIspita = krajnjirokIspita;
    }

}
