package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IRechnungDao;
import at.fh.technikum.wien.koller.krammer.dao.IRechnungszeileDao;
import at.fh.technikum.wien.koller.krammer.models.Rechnungszeile;

public class TestUpdateRechnungszeileInDatabase {

	@Test
	public void testUpdateRechnungszeileBezeichnung() {		
		IRechnungDao rd = DaoFactory.createRechnungDao();
		IRechnungszeileDao rzd = DaoFactory.createRechnungszeileDao();
		
		for(int i = 0; i < rd.getRechnungById(1).getRechnungszeilen().size(); i++) {
			
			Rechnungszeile rzOriginal = rd.getRechnungById(1).getRechnungszeilen().get(i);
			Rechnungszeile rzUpdate = rd.getRechnungById(1).getRechnungszeilen().get(i);
			
			rzUpdate.setBezeichnung("Test");
			rzd.update(rzUpdate);
			
			assertEquals(i + ". Rechnungszeile (Bezeichnung) in DB ge�ndert", 
						rzUpdate.getBezeichnung(), 
						rd.getRechnungById(1).getRechnungszeilen().get(i).getBezeichnung());
			
			// Daten auf Originalzustand zur�cksetzen
			rzd.update(rzOriginal);
			
			assertEquals(i + ". Rechnungszeile (Bezeichnung) in DB zur�ckge�ndert", 
					rzOriginal.getBezeichnung(), 
					rd.getRechnungById(1).getRechnungszeilen().get(i).getBezeichnung());
		}
		
	}
	
	@Test
	public void testUpdateRechnungszeileStueckpreis() {		
		IRechnungDao rd = DaoFactory.createRechnungDao();
		IRechnungszeileDao rzd = DaoFactory.createRechnungszeileDao();
		
		for(int i = 0; i < rd.getRechnungById(1).getRechnungszeilen().size(); i++) {
			
			Rechnungszeile rzOriginal = rd.getRechnungById(1).getRechnungszeilen().get(i);
			Rechnungszeile rzUpdate = rd.getRechnungById(1).getRechnungszeilen().get(i);
			
			rzUpdate.setStueckpreis(1f);
			rzd.update(rzUpdate);
			
			assertEquals(i + ". Rechnungszeile (St�ckpreis) in DB ge�ndert", 
						1,00 , 
						rd.getRechnungById(1).getRechnungszeilen().get(i).getStueckpreis());
			
			// Daten auf Originalzustand zur�cksetzen
			rzd.update(rzOriginal);
		}
		
	}
	
	@Test
	public void testUpdateRechnungszeileMenge() {		
		IRechnungDao rd = DaoFactory.createRechnungDao();
		IRechnungszeileDao rzd = DaoFactory.createRechnungszeileDao();
		
		for(int i = 0; i < rd.getRechnungById(1).getRechnungszeilen().size(); i++) {
			
			Rechnungszeile rzOriginal = rd.getRechnungById(1).getRechnungszeilen().get(i);
			Rechnungszeile rzUpdate = rd.getRechnungById(1).getRechnungszeilen().get(i);
			
			rzUpdate.setMenge(100);
			rzd.update(rzUpdate);
			
			assertEquals(i + ". Rechnungszeile (Menge) in DB ge�ndert", 
						rzUpdate.getMenge(), 
						rd.getRechnungById(1).getRechnungszeilen().get(i).getMenge());
			
			// Daten auf Originalzustand zur�cksetzen
			rzd.update(rzOriginal);
			
			assertEquals(i + ". Rechnungszeile (Menge) in DB zur�ckge�ndert", 
					rzOriginal.getMenge(), 
					rd.getRechnungById(1).getRechnungszeilen().get(i).getMenge());
		}
		
	}
	
	@Test
	public void testUpdateRechnungszeileUst() {		
		IRechnungDao rd = DaoFactory.createRechnungDao();
		IRechnungszeileDao rzd = DaoFactory.createRechnungszeileDao();
		
		for(int i = 0; i < rd.getRechnungById(1).getRechnungszeilen().size(); i++) {
			
			Rechnungszeile rzOriginal = rd.getRechnungById(1).getRechnungszeilen().get(i);
			Rechnungszeile rzUpdate = rd.getRechnungById(1).getRechnungszeilen().get(i);
			
			rzUpdate.setUst(1);
			rzd.update(rzUpdate);
			
			assertEquals(i + ". Rechnungszeile (UST) in DB ge�ndert", 
						rzUpdate.getUst(), 
						rd.getRechnungById(1).getRechnungszeilen().get(i).getUst());
			
			// Daten auf Originalzustand zur�cksetzen
			rzd.update(rzOriginal);
			
			assertEquals(i + ". Rechnungszeile (UST) in DB zur�ckge�ndert", 
					rzOriginal.getUst(), 
					rd.getRechnungById(1).getRechnungszeilen().get(i).getUst());
		}
		
	}
}
