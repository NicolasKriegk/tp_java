import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.imie.jdbc.DAO.PersonDAO;
import fr.imie.jdbc.DTO.PersonDTO;
import fr.imie.jdbc.Exception.PersistanceException;
import fr.imie.jdbc.InterfaceDAO.IPersonDAO;


public class TestUnitaire {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws PersistanceException 
	 */
	public static void main(String[] args) throws ParseException, PersistanceException {
	
		PersonDTO personne = new PersonDTO();
		personne.setPrenom("pretest1");
		personne.setNom("test1");
		Date dateNaiss = new SimpleDateFormat().parse("05/04/1989".toString());
		personne.setDateNaissance(dateNaiss);
		IPersonDAO jdbc = new PersonDAO();
		jdbc.createPerson(personne);

	}

}
