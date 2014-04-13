import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.imie.jdbc.tp7.Personne;
import fr.imie.jdbc.tp7.DAO.Persist;
import fr.imie.jdbc.tp7.Exception.PersistanceException;
import fr.imie.jdbc.tp7.InterfaceDAO.IPersist;


public class TestUnitaire {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws PersistanceException 
	 */
	public static void main(String[] args) throws ParseException, PersistanceException {
	
		Personne personne = new Personne();
		personne.setPrenom("pretest1");
		personne.setNom("test1");
		Date dateNaiss = new SimpleDateFormat().parse("05/04/1989".toString());
		personne.setDateNaissance(dateNaiss);
		IPersist jdbc = new Persist();
		jdbc.createPerson(personne);

	}

}
