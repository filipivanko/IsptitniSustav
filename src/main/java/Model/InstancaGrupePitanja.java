package Model;

import DAO.Repozitorij;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class InstancaGrupePitanja implements Serializable, Comparable<InstancaGrupePitanja> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int IDInstancaGrupePitanja;
    private int grupaPitanjaID;
    private String naziv;

    @ManyToOne
    private InstancaIspita instancaIspita;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "instancaGrupePitanja")
    @Fetch(FetchMode.SELECT)
    private List<InstancaPitanja> instancePitanja = new ArrayList<InstancaPitanja>();

    public InstancaGrupePitanja() {
    }

    InstancaGrupePitanja(GrupaPitanja grupaPitanja, InstancaIspita ispit, Repozitorij repo) {
        naziv = grupaPitanja.getNazivGrupe();
        IDInstancaGrupePitanja = grupaPitanja.getIDGrupaPitanja();
        instancaIspita = ispit;
        repo.spremiRucnoBezOtvaranjaIZatvaranja(this);
        napuniInstancePitanja(grupaPitanja, repo);
        repo.promjeniRucnoBezOtvaranjaIZatvaranja(this);
    }

    private void napuniInstancePitanja(GrupaPitanja grupaPitanja, Repozitorij repo) {

        List<Pitanje> svaPitanjaUGrupi = new ArrayList(grupaPitanja.getPitanjaUGrupi());
        Random rand = new Random();
        if (grupaPitanja.getBrojPitanja() < svaPitanjaUGrupi.size()) {
            for (int i = 0; i < grupaPitanja.getBrojPitanja(); i++) {
                Pitanje p = svaPitanjaUGrupi.get(rand.nextInt(svaPitanjaUGrupi.size()));
                svaPitanjaUGrupi.remove(p);

                InstancaPitanja ip = new InstancaPitanja(p, this, repo);
                repo.spremiRucnoBezOtvaranjaIZatvaranja(ip);
                getInstancePitanja().add(ip);
            }

        } else {

            for (Pitanje p : grupaPitanja.getPitanjaUGrupi()) {

                InstancaPitanja ip = new InstancaPitanja(p, this, repo);
                repo.spremiRucnoBezOtvaranjaIZatvaranja(ip);
                getInstancePitanja().add(ip);
            }
        }

    }

    /**
     * @return the IDInstancaGrupePitanja
     */
    public int getIDInstancaGrupePitanja() {
        return IDInstancaGrupePitanja;
    }

    /**
     * @param IDInstancaGrupePitanja the IDInstancaGrupePitanja to set
     */
    public void setIDInstancaGrupePitanja(int IDInstancaGrupePitanja) {
        this.IDInstancaGrupePitanja = IDInstancaGrupePitanja;
    }

    /**
     * @return the grupaPitanjaID
     */
    public int getGrupaPitanjaID() {
        return grupaPitanjaID;
    }

    /**
     * @param grupaPitanjaID the grupaPitanjaID to set
     */
    public void setGrupaPitanjaID(int grupaPitanjaID) {
        this.grupaPitanjaID = grupaPitanjaID;
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
     * @return the instancaIspita
     */
    public InstancaIspita getInstancaIspita() {
        return instancaIspita;
    }

    /**
     * @param instancaIspita the instancaIspita to set
     */
    public void setInstancaIspita(InstancaIspita instancaIspita) {
        this.instancaIspita = instancaIspita;
    }

    @Override
    public int compareTo(InstancaGrupePitanja t) {
        if (this.getIDInstancaGrupePitanja() > t.getIDInstancaGrupePitanja()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * @return the instancePitanja
     */
    public List<InstancaPitanja> getInstancePitanja() {
        return instancePitanja;
    }

    /**
     * @param instancePitanja the instancePitanja to set
     */
    public void setInstancePitanja(List<InstancaPitanja> instancePitanja) {
        this.instancePitanja = instancePitanja;
    }

}
