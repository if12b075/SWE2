package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IPersonDao;
import at.fh.technikum.wien.koller.krammer.models.Person;

public class TestUpdatePersonInDatabase {

	@Test
	public void testUpdatePersonTitel() {
		IPersonDao pd = DaoFactory.createPersonDao();
		Person pOriginal = pd.getPersonById(6);
		Person pUpdate = pd.getPersonById(6);
		
		pUpdate.setTitel("Test");
		// update testen
		pd.update(pUpdate);
		
		assertEquals("Person: Titel ge�ndert in DB", pUpdate.getTitel(), pd.getPersonById(6).getTitel());
		
		// Daten auf Originalzustand zur�cksetzen
		pd.update(pOriginal);
		
		assertEquals("Person: Titel zur�ckge�ndert in DB", pOriginal.getTitel(), pd.getPersonById(6).getTitel());
		
	}

	@Test
	public void testUpdatePersonVorname() {
		IPersonDao pd = DaoFactory.createPersonDao();
		Person pOriginal = pd.getPersonById(6);
		Person pUpdate = pd.getPersonById(6);
		
		pUpdate.setVorname("Test");
		// update testen
		pd.update(pUpdate);
		
		assertEquals("Person: Vorname ge�ndert in DB", pUpdate.getVorname(), pd.getPersonById(6).getVorname());
		
		// Daten auf Originalzustand zur�cksetzen
		pd.update(pOriginal);
		
		assertEquals("Person: Vorname zur�ckge�ndert in DB", pOriginal.getVorname(), pd.getPersonById(6).getVorname());
		
	}
	
	@Test
	public void testUpdatePersonNachname() {
		IPersonDao pd = DaoFactory.createPersonDao();
		Person pOriginal = pd.getPersonById(6);
		Person pUpdate = pd.getPersonById(6);
		
		pUpdate.setNachname("Test");
		// update testen
		pd.update(pUpdate);
		
		assertEquals("Person: Nachname ge�ndert in DB", pUpdate.getNachname(), pd.getPersonById(6).getNachname());
		
		// Daten auf Originalzustand zur�cksetzen
		pd.update(pOriginal);
		
		assertEquals("Person: Nachname zur�ckge�ndert in DB", pOriginal.getNachname(), pd.getPersonById(6).getNachname());
		
	}
	
	@Test
	public void testUpdatePersonSuffix() {
		IPersonDao pd = DaoFactory.createPersonDao();
		Person pOriginal = pd.getPersonById(6);
		Person pUpdate = pd.getPersonById(6);
		
		pUpdate.setSuffix("Test");
		// update testen
		pd.update(pUpdate);
		
		assertEquals("Person: Suffix ge�ndert in DB", pUpdate.getSuffix(), pd.getPersonById(6).getSuffix());
		
		// Daten auf Originalzustand zur�cksetzen
		pd.update(pOriginal);
		
		assertEquals("Person: Suffix zur�ckge�ndert in DB", pOriginal.getSuffix(), pd.getPersonById(6).getSuffix());
		
	}

}
