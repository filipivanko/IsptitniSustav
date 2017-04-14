package DAO;

import Model.Admin;
import Model.Grupa;
import Model.GrupaPitanja;
import Model.InstancaIspita;
import Model.InstancaOdgovora;
import Model.InstancaPitanja;
import Model.Ispit;
import Model.Kompanija;
import Model.Korisnik;
import Model.Odgovor;
import Model.Pitanje;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateRepozitorij implements Repozitorij {

    SessionFactory sessionFactory;
    Session hibernateSession;

    public Session getHibernateSession() {
		return hibernateSession;
	}

	public void setHibernateSession(Session hibernateSession) {
		this.hibernateSession = hibernateSession;
	}

	public void otvoriSession() {

        Configuration configuration = new Configuration().configure();

        sessionFactory = HibernateSessionFactorySingleton.getInstance();
        hibernateSession = sessionFactory.openSession();
        hibernateSession.beginTransaction();
    }

    public void zatvoriSession() {
        hibernateSession.getTransaction().commit();
        hibernateSession.flush();
        hibernateSession.close();
    }

    public <T> void spremiRucnoBezOtvaranjaIZatvaranja(T objektZaSpremiti) {
        hibernateSession.save(objektZaSpremiti);
    }

    public <T> void promjeniRucnoBezOtvaranjaIZatvaranja(T objektZaSpremiti) {
        hibernateSession.update(objektZaSpremiti);
    }

    public boolean postojiKorisnik(String korisnickoIme) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from Korisnik as K where K.korisnickoIme = '" + korisnickoIme + "'");
        List<Korisnik> rezultat = query.list();
        zatvoriSession();
        if (rezultat.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean postojiAdmin(String korisnickoIme) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from Admin as K where K.korisnickoIme = '" + korisnickoIme + "'");
        List<Admin> rezultat = query.list();
        zatvoriSession();
        if (rezultat.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public List<Korisnik> dohvatiSveKorisnike() {
        Query query;

        otvoriSession();

        List<Korisnik> sviKorisnici = new ArrayList<Korisnik>();
        query = hibernateSession.createQuery("from Korisnik");
        sviKorisnici = (List<Korisnik>) query.list();

        zatvoriSession();
        return sviKorisnici;
    }

    public Admin dohvatiAdminaPoKorisnickomImenu(String korisnickoIme) {

        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from Admin as K where K.korisnickoIme = '" + korisnickoIme + "'");

        Admin postojeciAdmin;
        if (query.list().size() > 0) {
            postojeciAdmin = (Admin) query.list().get(0);
        } else {
            postojeciAdmin = null;
        }
        zatvoriSession();
        return postojeciAdmin;

    }

    public <T> void spremi(T objektZaSpremiti) {
        otvoriSession();
        hibernateSession.save(objektZaSpremiti);
        zatvoriSession();
    }

    public List<Kompanija> dohvatiSveKompanije() {
        Query query;

        otvoriSession();

        List<Kompanija> sveKompanije = new ArrayList<Kompanija>();
        query = hibernateSession.createQuery("from Kompanija");
        sveKompanije = (List<Kompanija>) query.list();

        zatvoriSession();
        return sveKompanije;
    }

    public Kompanija dohvatiKompanijuPoIDu(int IDodabranaKompanija) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from Kompanija as K where K.IDKompanija = '" + IDodabranaKompanija + "'");
        Kompanija odabranaKompanija;
        if (query.list().size() > 0) {
            odabranaKompanija = (Kompanija) query.list().get(0);
        } else {

            odabranaKompanija = null;
        }
        zatvoriSession();
        return odabranaKompanija;
    }

    public Admin dohvatiAdminaPoIDu(int IDodabraniAdmin) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from Admin as K where K.IDAdmin = '" + IDodabraniAdmin + "'");
        Admin odabraniAdmin;
        if (query.list().size() > 0) {
            odabraniAdmin = (Admin) query.list().get(0);
        } else {
            odabraniAdmin = null;
        }

        zatvoriSession();

        return odabraniAdmin;
    }

    public void obrisiKompaniju(Kompanija kompanija) {

        otvoriSession();
        obrisiSvaPitanjaUKompaniji(kompanija);
        obrisiSveGrupePitanjaUKompaniji(kompanija);
        obrisiSveIspiteUKompaniji(kompanija);
        obrisiSveGrupeUKompaniji(kompanija);
        obrisiSveAdmineUKompaniji(kompanija);
        obrisiSveKorisnikeUKompaniji(kompanija);
        hibernateSession.delete(kompanija);

        zatvoriSession();
    }

    private void obrisiSvaPitanjaUKompaniji(Kompanija kompanija) {
        ArrayList<Pitanje> pitanjaUKompanij = new ArrayList<Pitanje>(kompanija.getPitanjaUKompaniji());
        for (Pitanje p : pitanjaUKompanij) {
            for (Odgovor o : p.getOdgovori()) {
                hibernateSession.delete(o);
            }
            hibernateSession.delete(p);
        }
    }

    private void obrisiSveGrupePitanjaUKompaniji(Kompanija kompanija) {
        ArrayList<GrupaPitanja> GrupePitanjaUKompanij = new ArrayList<GrupaPitanja>(kompanija.getGrupePitanjaKompanije());
        for (GrupaPitanja g : GrupePitanjaUKompanij) {
            ArrayList<Ispit> ispitiSaGrupomPitanja = new ArrayList<Ispit>(g.getIspitiKojiSadrzeGrupuPitanja());
            for (Ispit i : ispitiSaGrupomPitanja) {
                i.izbaciGrupuPitanja(g);
            }
            hibernateSession.delete(g);
        }
    }

    private void obrisiSveGrupeUKompaniji(Kompanija kompanija) {
        ArrayList<Grupa> grupeUkompaniji = new ArrayList<Grupa>(kompanija.getGrupeUKompaniji());
        for (Grupa g : grupeUkompaniji) {
            ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>(g.getClanoviGrupe());
            for (Korisnik k : korisnici) {
                g.izbaciKorisnika(k);

            }
            ArrayList<Admin> adminiGrupe = new ArrayList<Admin>(g.getAdminiGrupe());
            for (Admin a : adminiGrupe) {
                g.izbaciAdmina(a);

            }
            ArrayList<Ispit> ispitiGrupe = new ArrayList<Ispit>(g.getIspitiGrupe());
            for (Ispit i : ispitiGrupe) {
                g.izbaciIspit(i);

            }
            hibernateSession.delete(g);
        }
    }

    private void obrisiSveIspiteUKompaniji(Kompanija kompanija) {

        ArrayList<Ispit> ispitiUKompaniji = new ArrayList<Ispit>(kompanija.getIspitiKompanije());
        for (Ispit i : ispitiUKompaniji) {
            ArrayList<Grupa> grupeKojeKoristeIspit = new ArrayList<Grupa>(i.getGrupeKojeKoristeIspit());
            for (Grupa g : grupeKojeKoristeIspit) {
                g.izbaciIspit(i);
            }
            ArrayList<GrupaPitanja> grupePitanjaUIspitu = new ArrayList<GrupaPitanja>(i.getGrupePitanjaUIspitu());
            for (GrupaPitanja gp : grupePitanjaUIspitu) {
                gp.izbaciIspit(i);
            }
            hibernateSession.delete(i);
        }
    }

    private void obrisiSveAdmineUKompaniji(Kompanija kompanija) {
        ArrayList<Admin> adminiKompanije = new ArrayList<Admin>(kompanija.getAdminiKompanije());
        for (Admin a : adminiKompanije) {
            ArrayList<Grupa> GrupeKojePripadajuAdminu = new ArrayList(a.getGrupeKojePripadajuAdminu());
            for (Grupa g : GrupeKojePripadajuAdminu) {
                g.izbaciAdmina(a);
            }
            hibernateSession.delete(a);
        }
    }

    private void obrisiSveKorisnikeUKompaniji(Kompanija kompanija) {
        ArrayList<Korisnik> korisniciKompanije = new ArrayList<Korisnik>(kompanija.getKorisniciKompanije());
        for (Korisnik k : korisniciKompanije) {
            ArrayList<Grupa> grupeKojimaPripadaKorisnik = new ArrayList(k.getGrupeKojimaKorisnikPripada());
            for (Grupa g : grupeKojimaPripadaKorisnik) {
                g.izbaciKorisnika(k);
            }
            hibernateSession.delete(k);
        }
    }

    public <T> void obrisi(T odabraniObjekt) {

        otvoriSession();

        hibernateSession.delete(odabraniObjekt);

        zatvoriSession();
    }

    public Grupa dohvatiGrupuPoIDu(int idGrupa) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from Grupa as K where K.IDGrupa = '" + idGrupa + "'");
        Grupa odabranaGrupa;
        if (query.list().size() > 0) {
            odabranaGrupa = (Grupa) query.list().get(0);
        } else {
            odabranaGrupa = null;

        }
        zatvoriSession();
        return odabranaGrupa;
    }

    public void obrisiGrupu(Grupa grupa) {
        otvoriSession();
        for (Korisnik k : grupa.getClanoviGrupe()) {
            k.ukloniGrupuPoIDu(grupa.getIDGrupa());
            promjeniRucnoBezOtvaranjaIZatvaranja(k);
        }
        for (Admin a : grupa.getAdminiGrupe()) {
            a.ukloniGrupuPoIDu(grupa.getIDGrupa());
            promjeniRucnoBezOtvaranjaIZatvaranja(a);
        }
        for (Ispit i : grupa.getIspitiGrupe()) {
            i.ukloniGrupuPoIDu(grupa.getIDGrupa());
            promjeniRucnoBezOtvaranjaIZatvaranja(i);
        }

        hibernateSession.delete(grupa);
        zatvoriSession();
    }

    public Ispit dohvatiIspitPoIDu(int IDOdabraniIspit) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from Ispit as K where K.IDIspit = '" + IDOdabraniIspit + "'");
        Ispit odabraniIspit;
        if (query.list().size() > 0) {

            odabraniIspit = (Ispit) query.list().get(0);
        } else {
            odabraniIspit = null;
        }
        zatvoriSession();
        return odabraniIspit;
    }

    public void obrisiIspit(Ispit ispit) {
        otvoriSession();
        for (GrupaPitanja g : ispit.getGrupePitanjaUIspitu()) {
            g.ukloniIspitPoIDu(ispit.getIDIspit());
            promjeniRucnoBezOtvaranjaIZatvaranja(g);
        }
        hibernateSession.delete(ispit);
        zatvoriSession();
    }

    public Pitanje dohvatiPitanjePoIDu(int IDodabranoPitanje) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from Pitanje as K where K.IDPitanje = '" + IDodabranoPitanje + "'");

        Pitanje odabranoPitanje;
        if (query.list().size() > 0) {
            odabranoPitanje = (Pitanje) query.list().get(0);
        } else {
            odabranoPitanje = null;
        }
        zatvoriSession();
        return odabranoPitanje;
    }

    public GrupaPitanja dohvatiGrupuPitanjaPoIDu(int idGrupaPitanja) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from GrupaPitanja as K where K.IDGrupaPitanja = '" + idGrupaPitanja + "'");
        GrupaPitanja odabranaGrupaPitanja;
        if (query.list().size() > 0) {
            odabranaGrupaPitanja = (GrupaPitanja) query.list().get(0);
        } else {
            odabranaGrupaPitanja = null;
        }
        zatvoriSession();
        return odabranaGrupaPitanja;
    }

    public Odgovor dohvatiOdgovorPoIDu(int idOdgovor) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from Odgovor as K where K.IDOdgovor = '" + idOdgovor + "'");
        Odgovor odabraniOdgovor;
        if (query.list().size() > 0) {
            odabraniOdgovor = (Odgovor) query.list().get(0);
        } else {
            odabraniOdgovor = null;
        }
        zatvoriSession();
        return odabraniOdgovor;
    }

    public void obrisiGrupuPitanja(GrupaPitanja grupaPitanja) {
        otvoriSession();
        for (Pitanje p : grupaPitanja.getPitanjaUGrupi()) {
            p.ukloniGrupuPitanja(grupaPitanja.getIDGrupaPitanja());
            promjeniRucnoBezOtvaranjaIZatvaranja(p);
        }

        for (Ispit i : grupaPitanja.getIspitiKojiSadrzeGrupuPitanja()) {
            i.ukloniGrupuPitanjaPoIDu(grupaPitanja.getIDGrupaPitanja());
            promjeniRucnoBezOtvaranjaIZatvaranja(i);
        }
        hibernateSession.delete(grupaPitanja);
        zatvoriSession();
    }

    public void obrisiOdgovor(Odgovor odgovor) {
        otvoriSession();
        odgovor.getPitanje().izbaciOdgovor(odgovor);
        hibernateSession.delete(odgovor);
        hibernateSession.update(odgovor.getPitanje());
        zatvoriSession();

    }

    public void obrisiPitanje(Pitanje pitanje) {
        otvoriSession();
        for (Odgovor o : pitanje.getOdgovori()) {
            hibernateSession.delete(o);
        }
        hibernateSession.delete(pitanje);
        zatvoriSession();
    }

    public Korisnik dohvatiKorisnikaPoIDu(int IDodabraniKorisnik) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from Korisnik as K where K.IDKorisnik = '" + IDodabraniKorisnik + "'");

        Korisnik odabraniKorisnik;
        if (query.list().size() > 0) {
            odabraniKorisnik = (Korisnik) query.list().get(0);
        } else {
            odabraniKorisnik = null;
        }
        zatvoriSession();
        return odabraniKorisnik;
    }

    public void obrisiKorisnika(Korisnik odabraniKorisnk) {
        otvoriSession();
        for (Grupa g : odabraniKorisnk.getGrupeKojimaKorisnikPripada()) {
            g.izbaciKorisnika(odabraniKorisnk);
        }
        hibernateSession.delete(odabraniKorisnk);
        zatvoriSession();
    }

    public Korisnik dohvatiKorisnikaPoKorisnickomImenu(String korisnickoIme) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from Korisnik as K where K.korisnickoIme = '" + korisnickoIme + "'");
        Korisnik postojeciKorisnik;
        if (query.list().size() > 0) {
            postojeciKorisnik = (Korisnik) query.list().get(0);
        } else {
            postojeciKorisnik = null;
        }

        zatvoriSession();
        return postojeciKorisnik;
    }

    public List<InstancaIspita> dohvatiKorisnikoveInstanceIspita(Korisnik korisnik) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from InstancaIspita as K where K.korisnikID = '" + korisnik.getIDKorisnik() + "'");
        List<InstancaIspita> instanceIspitaKorisnika = (List<InstancaIspita>) query.list();
        zatvoriSession();
        return instanceIspitaKorisnika;
    }

    public InstancaIspita dohvatiInstancuIspitaPoIDu(int idOdabraneInstanceIspita) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from InstancaIspita as K where K.IDInstancaIspita = '" + idOdabraneInstanceIspita + "'");
        InstancaIspita instancaIspita;
        if (query.list().size() > 0) {
            instancaIspita = (InstancaIspita) query.list().get(0);
        } else {
            instancaIspita = null;
        }
        zatvoriSession();
        return instancaIspita;
    }

    public InstancaPitanja dohvatiInstancuPitanjaPoIDu(int idOdabraneInstancePitanja) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from InstancaPitanja as K where K.IDInstancaPitanja = '" + idOdabraneInstancePitanja + "'");
        
        InstancaPitanja instancaPitanja;
        if(query.list().size()>0){
        instancaPitanja = (InstancaPitanja) query.list().get(0);
        }else{
        instancaPitanja = null;
        }
        zatvoriSession();
        return instancaPitanja;
    }

    public InstancaOdgovora dohvatiInstancuOdgovoraPoIDu(int idOdabraneInstanceOdgovora) {
        Query query;
        otvoriSession();
        query = hibernateSession.createQuery("from InstancaOdgovora as K where K.IDInstancaOdgovora = '" + idOdabraneInstanceOdgovora + "'");
        
        InstancaOdgovora instancaOdgovora ;
        if(query.list().size()>0){
        instancaOdgovora = (InstancaOdgovora) query.list().get(0);
        }else{
        instancaOdgovora = null;
        }
        zatvoriSession();
        return instancaOdgovora;
    }

}
