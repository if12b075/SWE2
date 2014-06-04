package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IPersonDao;

public class TestPersonByIdAdresseOutOfDatabase {

	@Test
	public void testGetPersonByIdAdresse() {	
		IPersonDao pd = DaoFactory.createPersonDao();
		
		assertEquals("Person: Adresse (ID) aus DB prüfen", 
				3, pd.getPersonById(6).getWohnadresse().getId());
		assertEquals("Person: Adresse (1. Zeile) aus DB prüfen", 
				"Unter der Kirche 27", pd.getPersonById(6).getWohnadresse().getAdrrow1());
		assertEquals("Person: Adresse (2. Zeile) aus DB prüfen", 
				"Postfach 100", pd.getPersonById(6).getWohnadresse().getAdrrow2());
		assertEquals("Person: Adresse (PLZ) aus DB prüfen", 
				1110, pd.getPersonById(6).getWohnadresse().getPlz());
		assertEquals("Person: Adresse (Ort) aus DB prüfen", 
				"Wien", pd.getPersonById(6).getWohnadresse().getOrt());
	}		
	
	@Test
	public void testGetPersonByIdRGAdresse() {	
		IPersonDao pd = DaoFactory.createPersonDao();
		
		assertEquals("Person: RG-Adresse (ID) aus DB prüfen", 
				1, pd.getPersonById(6).getRechnungsadresse().getId());
		assertEquals("Person: RG-Adresse (1. Zeile) aus DB prüfen", 
				"Poststraße 1", pd.getPersonById(6).getRechnungsadresse().getAdrrow1());
		assertEquals("Person: RG-Adresse (2. Zeile) aus DB prüfen", 
				"Zentrales Gebäude", pd.getPersonById(6).getRechnungsadresse().getAdrrow2());
		assertEquals("Person: RG-Adresse (PLZ) aus DB prüfen", 
				1230, pd.getPersonById(6).getRechnungsadresse().getPlz());
		assertEquals("Person: RG-Adresse (Ort) aus DB prüfen", 
				"Wien", pd.getPersonById(6).getRechnungsadresse().getOrt());
	}
	
	@Test
	public void testGetPersonByIdLFAdresse() {	
		IPersonDao pd = DaoFactory.createPersonDao();
		
		assertEquals("Person: LF-Adresse (ID) aus DB prüfen", 
				2, pd.getPersonById(6).getLieferadresse().getId());
		assertEquals("Person: LF-Adresse (1. Zeile) aus DB prüfen", 
				"Mustergasse 3", pd.getPersonById(6).getLieferadresse().getAdrrow1());
		assertEquals("Person: LF-Adresse (2. Zeile) aus DB prüfen", 
				"zH Max Muster", pd.getPersonById(6).getLieferadresse().getAdrrow2());
		assertEquals("Person: LF-Adresse (PLZ) aus DB prüfen", 
				5020, pd.getPersonById(6).getLieferadresse().getPlz());
		assertEquals("Person: LF-Adresse (Ort) aus DB prüfen", 
				"Graz", pd.getPersonById(6).getLieferadresse().getOrt());
	}	

}
