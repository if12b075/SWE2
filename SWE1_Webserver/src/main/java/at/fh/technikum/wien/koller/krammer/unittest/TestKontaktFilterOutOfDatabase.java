package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IKontaktDao;
import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;

public class TestKontaktFilterOutOfDatabase {

	@Test
	public void testKontaktFilterNachname() {
		IKontaktDao kd = DaoFactory.createKontaktDao();
		List<Kontakt> kontaktList = new ArrayList<Kontakt>();
		
		kontaktList = kd.getFilterKontakte(new KontaktFilter("Muster", false));
		
		assertEquals("Erwartetes Suchergebniss bei Nachname Muster: 3 Personen",3,kontaktList.size());
	}
	
	@Test
	public void testKontaktFilterVorname() {
		IKontaktDao kd = DaoFactory.createKontaktDao();
		List<Kontakt> kontaktList = new ArrayList<Kontakt>();
		
		kontaktList = kd.getFilterKontakte(new KontaktFilter("Susi", false));
		
		assertEquals("Erwartetes Suchergebniss bei Vorname Susi: 2 Personen",2,kontaktList.size());
	}
	
	@Test
	public void testKontaktFilterFirmenName() {
		IKontaktDao kd = DaoFactory.createKontaktDao();
		List<Kontakt> kontaktList = new ArrayList<Kontakt>();
		
		kontaktList = kd.getFilterKontakte(new KontaktFilter("Firma", true));
		
		assertEquals("Erwartetes Suchergebniss bei Firmenname Firma: 5 Firmen",5,kontaktList.size());
	}
}
