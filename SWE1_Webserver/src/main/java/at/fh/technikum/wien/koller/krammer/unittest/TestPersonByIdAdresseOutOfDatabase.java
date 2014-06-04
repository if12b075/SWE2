package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IPersonDao;

public class TestPersonByIdAdresseOutOfDatabase {

	@Test
	public void testGetPersonByIdAdresse() {	
		IPersonDao pd = DaoFactory.createPersonDao();
		
		assertEquals("Person: Adresse (ID) aus DB pr�fen", 
				3, pd.getPersonById(6).getWohnadresse().getId());
		assertEquals("Person: Adresse (1. Zeile) aus DB pr�fen", 
				"Unter der Kirche 27", pd.getPersonById(6).getWohnadresse().getAdrrow1());
		assertEquals("Person: Adresse (2. Zeile) aus DB pr�fen", 
				"Postfach 100", pd.getPersonById(6).getWohnadresse().getAdrrow2());
		assertEquals("Person: Adresse (PLZ) aus DB pr�fen", 
				1110, pd.getPersonById(6).getWohnadresse().getPlz());
		assertEquals("Person: Adresse (Ort) aus DB pr�fen", 
				"Wien", pd.getPersonById(6).getWohnadresse().getOrt());
	}		
	
	@Test
	public void testGetPersonByIdRGAdresse() {	
		IPersonDao pd = DaoFactory.createPersonDao();
		
		assertEquals("Person: RG-Adresse (ID) aus DB pr�fen", 
				1, pd.getPersonById(6).getRechnungsadresse().getId());
		assertEquals("Person: RG-Adresse (1. Zeile) aus DB pr�fen", 
				"Poststra�e 1", pd.getPersonById(6).getRechnungsadresse().getAdrrow1());
		assertEquals("Person: RG-Adresse (2. Zeile) aus DB pr�fen", 
				"Zentrales Geb�ude", pd.getPersonById(6).getRechnungsadresse().getAdrrow2());
		assertEquals("Person: RG-Adresse (PLZ) aus DB pr�fen", 
				1230, pd.getPersonById(6).getRechnungsadresse().getPlz());
		assertEquals("Person: RG-Adresse (Ort) aus DB pr�fen", 
				"Wien", pd.getPersonById(6).getRechnungsadresse().getOrt());
	}
	
	@Test
	public void testGetPersonByIdLFAdresse() {	
		IPersonDao pd = DaoFactory.createPersonDao();
		
		assertEquals("Person: LF-Adresse (ID) aus DB pr�fen", 
				2, pd.getPersonById(6).getLieferadresse().getId());
		assertEquals("Person: LF-Adresse (1. Zeile) aus DB pr�fen", 
				"Mustergasse 3", pd.getPersonById(6).getLieferadresse().getAdrrow1());
		assertEquals("Person: LF-Adresse (2. Zeile) aus DB pr�fen", 
				"zH Max Muster", pd.getPersonById(6).getLieferadresse().getAdrrow2());
		assertEquals("Person: LF-Adresse (PLZ) aus DB pr�fen", 
				5020, pd.getPersonById(6).getLieferadresse().getPlz());
		assertEquals("Person: LF-Adresse (Ort) aus DB pr�fen", 
				"Graz", pd.getPersonById(6).getLieferadresse().getOrt());
	}	

}
