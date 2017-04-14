package Aspekti;

import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.Repozitorij;
import Model.Admin;
import Model.Korisnik;
import Model.Kompanija;

public aspect ProvjeraTransakcije {

	pointcut ProvjeriDaLiJeRepoUspioVoid(Repozitorij repo):
		 this(repo) && call(void *.Repozitorij.*(..));

	pointcut ProvjeriDaLiJeRepoUspioSaReturnom(Repozitorij repo):
		 this(repo) && call(* *.Repozitorij.*(..))&&  !call(void *.Repozitorij.*(..));

	pointcut LogirajBrisanje(Repozitorij repo, Object objektKojiseBrise):
		 this(repo) && args(objektKojiseBrise) && call(void *.Repozitorij.obrisi*(..));

	void around(Repozitorij repo):ProvjeriDaLiJeRepoUspioVoid(repo){

		try {
			proceed(repo);
		} catch (Exception ex) {

			Logger.getLogger(repo.getClass().getName()).log(Level.SEVERE, null, ex);
		}
	}

	Object around(Repozitorij repo):ProvjeriDaLiJeRepoUspioSaReturnom(repo){

		Object VracenaVrijednost = null;
		try {
			VracenaVrijednost = proceed(repo);

		} catch (Exception ex) {

			Logger.getLogger(repo.getClass().getName()).log(Level.SEVERE, null, ex);
		}
		return VracenaVrijednost;
	}

	after(Repozitorij repo, Object objektKojiseBrise):LogirajBrisanje(repo, objektKojiseBrise){

		Logger.getLogger(repo.getClass().getName()).log(Level.SEVERE, null,
				"obrisan objekt klase " + objektKojiseBrise.getClass().toString());
		
		if (objektKojiseBrise.getClass().toString() == "class Model.Korisnik") {
			Logger.getLogger(repo.getClass().getName()).log(Level.SEVERE, null,
					"Obrisan je Korisnik || KorisnickoIme :" + ((Korisnik) objektKojiseBrise).getKorisnickoIme()+"|| Ime:"+  ((Korisnik) objektKojiseBrise).getIme() +
					"|| Prezime:" + ((Korisnik) objektKojiseBrise).getPrezime() +
					"|| Id Korisnika:"+ ((Korisnik) objektKojiseBrise).getIDKorisnik());

		}
		if (objektKojiseBrise.getClass().toString() == "class Model.Admin") {
			Logger.getLogger(repo.getClass().getName()).log(Level.SEVERE, null,
					"Obrisan je Admin || Koisnicko ime || " + ((Admin) objektKojiseBrise).getKorisnickoIme()
					+ "|| Kompanija:" + ((Admin) objektKojiseBrise).getKompanija() 
					+ "|| Razina Ovlasti:" + ((Admin) objektKojiseBrise).getRazinaovlasti() 
					+ "|| Id Admina:"+ ((Admin) objektKojiseBrise).getIDAdmin());

		}
		if (objektKojiseBrise.getClass().toString() == "class Model.Kompanija") {
			Logger.getLogger(repo.getClass().getName()).log(Level.SEVERE, null,
					"Obrisana je Kompanija || Naziv || " + ((Kompanija) objektKojiseBrise).getNaziv() 
					+ "|| Sjedište:" + ((Kompanija) objektKojiseBrise).getSjediste() 
					+ "|| Id Kompanije:"+ ((Kompanija) objektKojiseBrise).getIDKompanija());

		}
	}

}
