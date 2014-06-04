package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IRechnungDao;
import at.fh.technikum.wien.koller.krammer.filter.RechnungFilter;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;
import at.fh.technikum.wien.koller.krammer.models.Rechnungszeile;

public class TestCreateRechnungInDatabase {

	@Test
	public void testInsertRechnungWithOneRechnungszeile() {
		Rechnung r = new Rechnung();
		
		r.setKontaktid(1);
		r.setRechnungsnummer(123);
		r.setDatum(new Date());
		r.setFaelligkeitsdatum(new Date());
		r.setBezahltAm(new Date());
		r.setKommentar("Test vom Server");
		r.setNachricht("bar bar bar");
		
		Rechnungszeile rz = new Rechnungszeile();
		
		rz.setBezeichnung("Testartikel");
		rz.setStueckpreis(10.0f);
		rz.setUst(20);
		rz.setMenge(10);
		
		ArrayList<Rechnungszeile> al = new ArrayList<Rechnungszeile>();
		al.add(rz);
		
		r.setRechnungszeilen(al);
		
		// Methode create() testen
		IRechnungDao ird = DaoFactory.createRechnungDao();
		ird.create(r);
		
		// Überprüfen, ob Daten in DB vorhanden sind
		IRechnungDao rd = DaoFactory.createRechnungDao();
		List<Rechnung> rechnungList = new ArrayList<Rechnung>();
		rechnungList = rd.getFilterRechnung(new RechnungFilter(null, null, 100, 100, null));
		
		long id = rechnungList.get(0).getId();
		
		assertEquals("Rechnung wurden in DB angelegt", 
				r.getRechnungsnummer(), 
				rd.getRechnungById(id).getRechnungsnummer());
		assertEquals("Rechnungszeile wurde in DB angelegt", 
				r.getRechnungszeilen().get(0).getBezeichnung(), 
				rd.getRechnungById(id).getRechnungszeilen().get(0).getBezeichnung());
		
		// Daten wieder löschen
		rd.delete(id);
	}
	
	@Test
	public void testInsertRechnungWithTwoRechnungszeilen() {
		Rechnung r = new Rechnung();
		
		r.setKontaktid(1);
		r.setRechnungsnummer(123);
		r.setDatum(new Date());
		r.setFaelligkeitsdatum(new Date());
		r.setBezahltAm(new Date());
		r.setKommentar("Test vom Server 2");
		r.setNachricht("bar bar bar");
		
		Rechnungszeile rz1 = new Rechnungszeile();
		rz1.setBezeichnung("Testartikel-1");
		rz1.setStueckpreis(10.0f);
		rz1.setUst(20);
		rz1.setMenge(10);
		
		Rechnungszeile rz2 = new Rechnungszeile();
		rz2.setBezeichnung("Testartikel-2");
		rz2.setStueckpreis(10.0f);
		rz2.setUst(20);
		rz2.setMenge(10);
		
		ArrayList<Rechnungszeile> al = new ArrayList<Rechnungszeile>();
		al.add(rz1);
		al.add(rz2);
		
		r.setRechnungszeilen(al);
		
		// Methode create() testen
		IRechnungDao ird = DaoFactory.createRechnungDao();
		ird.create(r);
		
		// Überprüfen, ob Daten in DB vorhanden sind
		IRechnungDao rd = DaoFactory.createRechnungDao();
		List<Rechnung> rechnungList = new ArrayList<Rechnung>();
		rechnungList = rd.getFilterRechnung(new RechnungFilter(null, null, 200, 200, null));
		
		long id = rechnungList.get(0).getId();
		
		assertEquals("Rechnung wurden in DB angelegt", 
				r.getRechnungsnummer(), 
				rd.getRechnungById(id).getRechnungsnummer());
		assertEquals("Rechnungszeile 1 wurde in DB angelegt", 
				r.getRechnungszeilen().get(0).getBezeichnung(), 
				rd.getRechnungById(id).getRechnungszeilen().get(0).getBezeichnung());
		assertEquals("Rechnungszeile 2 wurde in DB angelegt", 
				r.getRechnungszeilen().get(1).getBezeichnung(), 
				rd.getRechnungById(id).getRechnungszeilen().get(1).getBezeichnung());
		
		// Daten wieder löschen
		rd.delete(id);
	}

}
