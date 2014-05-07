package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IPersonDao;
import at.fh.technikum.wien.koller.krammer.models.Person;

public class TestPersonOutOfDatabase {

	@Test
	public void testGetPersonByIdTitel() {
		Person p = new Person();
		p.setTitel("Frau");
	
		IPersonDao pd = DaoFactory.createPersonDao();
		
		assertEquals("Person: Titel aus DB prüfen", p.getTitel(), pd.getPersonById(6).getTitel());
	}
	
	@Test
	public void testGetPersonByIdVorname() {
		Person p = new Person();
		p.setVorname("Susi");
	
		IPersonDao pd = DaoFactory.createPersonDao();
		
		assertEquals("Person: Vorname aus DB prüfen", p.getVorname(), pd.getPersonById(6).getVorname());
	}
	
	@Test
	public void testGetPersonByIdNachname() {
		Person p = new Person();
		p.setNachname("Muster");
	
		IPersonDao pd = DaoFactory.createPersonDao();
		
		assertEquals("Person: Nachname aus DB prüfen", p.getNachname(), pd.getPersonById(6).getNachname());
	}

	@Test
	public void testGetPersonByIdFirmaid() {
		Person p = new Person();
		p.setFirmaid(1);
	
		IPersonDao pd = DaoFactory.createPersonDao();
		
		assertEquals("Person: FirmenID aus DB prüfen", p.getFirmaid(), pd.getPersonById(6).getFirmaid());
	}
	
	@Test
	public void testGetPersonByIdSuffix() {
		Person p = new Person();
		p.setSuffix("Dr.");
	
		IPersonDao pd = DaoFactory.createPersonDao();
		
		assertEquals("Person: Suffix aus DB prüfen", p.getSuffix(), pd.getPersonById(6).getSuffix());
	}

}
