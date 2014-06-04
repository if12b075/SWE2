package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IRechnungDao;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;

public class TestUpdateRechnungInDatabase {

	@Test
	public void testUpdateRechnungKommentar() {
		IRechnungDao rd = DaoFactory.createRechnungDao();
		Rechnung rOriginal = rd.getRechnungById(1);
		Rechnung rUpdate = rd.getRechnungById(1);
		
		rUpdate.setKommentar("Test Update");
		// update testen
		rd.update(rUpdate);
		
		assertEquals("Rechnung: Kommentar geändert in DB", rUpdate.getKommentar(), rd.getRechnungById(1).getKommentar());
		
		// Daten auf Originalzustand zurücksetzen
		rd.update(rOriginal);
		
		assertEquals("Rechnung: Kommentar zurückgeändert in DB", rOriginal.getKommentar(), rd.getRechnungById(1).getKommentar());

	}
	
	@Test
	public void testUpdateRechnungNachricht() {
		IRechnungDao rd = DaoFactory.createRechnungDao();
		Rechnung rOriginal = rd.getRechnungById(1);
		Rechnung rUpdate = rd.getRechnungById(1);
		
		rUpdate.setNachricht("Test Update");
		// update testen
		rd.update(rUpdate);
		
		assertEquals("Rechnung: Nachricht geändert in DB", rUpdate.getNachricht(), rd.getRechnungById(1).getNachricht());
		
		// Daten auf Originalzustand zurücksetzen
		rd.update(rOriginal);
		
		assertEquals("Rechnung: Nachricht zurückgeändert in DB", rOriginal.getNachricht(), rd.getRechnungById(1).getNachricht());

	}
	
}
