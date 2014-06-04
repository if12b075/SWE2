package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IRechnungDao;

public class TestRechnungByIdRechnungszeileOutOfDatabase {

	@Test
	public void testGetRechnungByIdRechnungszeileBezeichnung() {	
		IRechnungDao rd = DaoFactory.createRechnungDao();
		
		for(int i = 0; i < rd.getRechnungById(1).getRechnungszeilen().size(); i++) {
			if(i == 0) {
				assertEquals("Rechnung: 1. Rechnungszeile (Bezeichnung) aus DB pr�fen", 
						"Computer", rd.getRechnungById(1).getRechnungszeilen().get(i).getBezeichnung());
			}
			if(i == 1) {
				assertEquals("Rechnung: 2. Rechnungszeile (Bezeichnung) aus DB pr�fen", 
						"Maus", rd.getRechnungById(1).getRechnungszeilen().get(i).getBezeichnung());
			}
		}
	}
	
	@Test
	public void testGetRechnungByIdRechnungszeileStueckpreis() {	
		IRechnungDao rd = DaoFactory.createRechnungDao();
		
		for(int i = 0; i < rd.getRechnungById(1).getRechnungszeilen().size(); i++) {
			if(i == 0) {
				assertEquals("Rechnung: 1. Rechnungszeile (St�ckpreis) aus DB pr�fen", 
						100,00 , rd.getRechnungById(1).getRechnungszeilen().get(i).getStueckpreis());
			}
			if(i == 1) {
				assertEquals("Rechnung: 2. Rechnungszeile (St�ckpreis) aus DB pr�fen", 
						10,00, rd.getRechnungById(1).getRechnungszeilen().get(i).getStueckpreis());
			}
		}
	}
	
	@Test
	public void testGetRechnungByIdRechnungszeileMenge() {	
		IRechnungDao rd = DaoFactory.createRechnungDao();
		
		for(int i = 0; i < rd.getRechnungById(1).getRechnungszeilen().size(); i++) {
			if(i == 0) {
				assertEquals("Rechnung: 1. Rechnungszeile (Menge) aus DB pr�fen", 
						2, rd.getRechnungById(1).getRechnungszeilen().get(i).getMenge());
			}
			if(i == 1) {
				assertEquals("Rechnung: 2. Rechnungszeile (Menge) aus DB pr�fen", 
						2, rd.getRechnungById(1).getRechnungszeilen().get(i).getMenge());
			}
		}
	}
	
	@Test
	public void testGetRechnungByIdRechnungszeileUst() {	
		IRechnungDao rd = DaoFactory.createRechnungDao();
		
		for(int i = 0; i < rd.getRechnungById(1).getRechnungszeilen().size(); i++) {
			if(i == 0) {
				assertEquals("Rechnung: 1. Rechnungszeile (UST) aus DB pr�fen", 
						20, rd.getRechnungById(1).getRechnungszeilen().get(i).getUst());
			}
			if(i == 1) {
				assertEquals("Rechnung: 2. Rechnungszeile (UST) aus DB pr�fen", 
						20, rd.getRechnungById(1).getRechnungszeilen().get(i).getUst());
			}
		}
	}
}
