package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IRechnungDao;
import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.filter.RechnungFilter;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;

public class TestRechnungFilterOutOfDatabase {

	@Test
	public void testRechnungFilterBetragVon() {
		IRechnungDao rd = DaoFactory.createRechnungDao();
		List<Rechnung> rechnungList = new ArrayList<Rechnung>();
		
		rechnungList = rd.getFilterRechnung(new RechnungFilter(null, null, 100, 0, null));
		
		assertEquals("Erwartetes Suchergebniss bei BetragVon 100: 3 RG",3,rechnungList.size());
	}
	
	@Test
	public void testRechnungFilterBetragBis() {
		IRechnungDao rd = DaoFactory.createRechnungDao();
		List<Rechnung> rechnungList = new ArrayList<Rechnung>();
		
		rechnungList = rd.getFilterRechnung(new RechnungFilter(null, null, 0, 100, null));
		
		assertEquals("Erwartetes Suchergebniss bei BetragBis 100: 2 RG",2,rechnungList.size());
	}
	
	@Test
	public void testRechnungFilterDatumVon() {
		IRechnungDao rd = DaoFactory.createRechnungDao();
		List<Rechnung> rechnungList = new ArrayList<Rechnung>();
		
		rechnungList = rd.getFilterRechnung(new RechnungFilter(new Date(), null, 0, 0, null));
		
		assertEquals("Erwartetes Suchergebniss bei DatumVon heute: 0 RG",0,rechnungList.size());
	}
	
	@Test
	public void testRechnungFilterDatumBis() {
		IRechnungDao rd = DaoFactory.createRechnungDao();
		List<Rechnung> rechnungList = new ArrayList<Rechnung>();
		
		rechnungList = rd.getFilterRechnung(new RechnungFilter(null, new Date(), 0, 0, null));
		
		assertEquals("Erwartetes Suchergebniss bei DatumBis heute: 5 RG",5,rechnungList.size());
	}
	
	@Test
	public void testRechnungFilterKontaktPerson() {
		IRechnungDao rd = DaoFactory.createRechnungDao();
		List<Rechnung> rechnungList = new ArrayList<Rechnung>();
		
		rechnungList = rd.getFilterRechnung(new RechnungFilter(null, null, 0, 0, new KontaktFilter("Susi", false)));
		
		assertEquals("Erwartetes Suchergebniss bei Kontakt-Person Susi: 1 RG",1,rechnungList.size());
	}
	
	@Test
	public void testRechnungFilterKontaktFirma() {
		IRechnungDao rd = DaoFactory.createRechnungDao();
		List<Rechnung> rechnungList = new ArrayList<Rechnung>();
		
		rechnungList = rd.getFilterRechnung(new RechnungFilter(null, null, 0, 0, new KontaktFilter("Firma", true)));
		
		assertEquals("Erwartetes Suchergebniss bei Kontakt-Firma Firma: 3 RG",3,rechnungList.size());
	}
}
