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

public class TestDeleteRechnungInDatabase {

	@Test
	public void testDeleteRechnung() {
		Rechnung r = new Rechnung();
		
		r.setKontaktid(1);
		r.setRechnungsnummer(123);
		r.setDatum(new Date());
		r.setFaelligkeitsdatum(new Date());
		r.setBezahltAm(new Date());
		r.setKommentar("Delete Rechnung");
		r.setNachricht("bar bar bar");
		
		
		Rechnungszeile rz = new Rechnungszeile();
		
		rz.setBezeichnung("Testartikel");
		rz.setStueckpreis(100.0f);
		rz.setUst(20);
		rz.setMenge(10);
		
		ArrayList<Rechnungszeile> al = new ArrayList<Rechnungszeile>();
		al.add(rz);
		
		r.setRechnungszeilen(al);
		
		
		IRechnungDao ird = DaoFactory.createRechnungDao();
		ird.create(r);
		
		IRechnungDao rd = DaoFactory.createRechnungDao();
		List<Rechnung> rechnungList = new ArrayList<Rechnung>();
		rechnungList = rd.getFilterRechnung(new RechnungFilter(null, null, 1000, 1000, null));
		
		long id = rechnungList.get(0).getId();
		
		// Rechnung angelegt ?
		assertEquals("Rechnung wurden in DB angelegt", 
				r.getRechnungsnummer(), 
				rd.getRechnungById(id).getRechnungsnummer());
		
		// Daten wieder löschen
		rd.delete(id);
		rechnungList = rd.getFilterRechnung(new RechnungFilter(null, null, 1000, 1000, null));
		
		// Rechnung gelöscht?
		assertEquals("Rechnung wurde gelöscht - keine Daten aus DB zurückerhalten", 0, rechnungList.size());
	}
	
}