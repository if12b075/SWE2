package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IRechnungDao;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;

public class TestRechnungByIdOutOfDatabase {

	@Test
	public void testGetRechnungByIdRechnungsnummer() {
		Rechnung r = new Rechnung();
		r.setRechnungsnummer(1001);
	
		IRechnungDao rd = DaoFactory.createRechnungDao();
		
		assertEquals("Rechnung: RG-Nummer aus DB prüfen", r.getRechnungsnummer(), rd.getRechnungById(1).getRechnungsnummer());
	}
	
	@Test
	public void testGetRechnungByIdKommentar() {
		Rechnung r = new Rechnung();
		r.setKommentar("Testrechnung");
	
		IRechnungDao rd = DaoFactory.createRechnungDao();
		
		assertEquals("Rechnung: Kommentar aus DB prüfen", r.getKommentar(), rd.getRechnungById(1).getKommentar());
	}
	
	@Test
	public void testGetRechnungByIdNachricht() {
		Rechnung r = new Rechnung();
		r.setNachricht("foo foo foo");
	
		IRechnungDao rd = DaoFactory.createRechnungDao();
		
		assertEquals("Rechnung: Nachricht aus DB prüfen", r.getNachricht(), rd.getRechnungById(1).getNachricht());
	}
	
}
