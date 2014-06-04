package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IAdresseDao;
import at.fh.technikum.wien.koller.krammer.dao.IPersonDao;
import at.fh.technikum.wien.koller.krammer.models.Adresse;

public class TestUpdateAdresseInDatabase {

	@Test
	public void testUpdateAdresseErsteZeile() {	
		IPersonDao pd = DaoFactory.createPersonDao();
		IAdresseDao ad = DaoFactory.createAdresseDao();
		
		Adresse aOriginal = pd.getPersonById(6).getWohnadresse();
		Adresse aUpdate = pd.getPersonById(6).getWohnadresse();	
		
		aUpdate.setAdrrow1("Test");
		ad.update(aUpdate);
		
		assertEquals("Adresse (1. Zeile) in DB geändert", 
				aUpdate.getAdrrow1(), pd.getPersonById(6).getWohnadresse().getAdrrow1());
		
		// Daten auf Originalzustand zurücksetzen
		ad.update(aOriginal);
		
		assertEquals("Adresse (1. Zeile) in DB zurückgeändert", 
				aOriginal.getAdrrow1(), pd.getPersonById(6).getWohnadresse().getAdrrow1());
		
	}
	
	@Test
	public void testUpdateAdresseZweiteZeile() {	
		IPersonDao pd = DaoFactory.createPersonDao();
		IAdresseDao ad = DaoFactory.createAdresseDao();
		
		Adresse aOriginal = pd.getPersonById(6).getWohnadresse();
		Adresse aUpdate = pd.getPersonById(6).getWohnadresse();	
		
		aUpdate.setAdrrow2("Test");
		ad.update(aUpdate);
		
		assertEquals("Adresse (2. Zeile) in DB geändert", 
				aUpdate.getAdrrow2(), pd.getPersonById(6).getWohnadresse().getAdrrow2());
		
		// Daten auf Originalzustand zurücksetzen
		ad.update(aOriginal);
		
		assertEquals("Adresse (2. Zeile) in DB zurückgeändert", 
				aOriginal.getAdrrow2(), pd.getPersonById(6).getWohnadresse().getAdrrow2());
		
	}	
	
	@Test
	public void testUpdateAdressePLZ() {	
		IPersonDao pd = DaoFactory.createPersonDao();
		IAdresseDao ad = DaoFactory.createAdresseDao();
		
		Adresse aOriginal = pd.getPersonById(6).getWohnadresse();
		Adresse aUpdate = pd.getPersonById(6).getWohnadresse();	
		
		aUpdate.setPlz(9999);
		ad.update(aUpdate);
		
		assertEquals("Adresse (PLZ) in DB geändert", 
				aUpdate.getPlz(), pd.getPersonById(6).getWohnadresse().getPlz());
		
		// Daten auf Originalzustand zurücksetzen
		ad.update(aOriginal);
		
		assertEquals("Adresse (PLZ) in DB zurückgeändert", 
				aOriginal.getPlz(), pd.getPersonById(6).getWohnadresse().getPlz());
		
	}	
	
	@Test
	public void testUpdateAdresseOrt() {	
		IPersonDao pd = DaoFactory.createPersonDao();
		IAdresseDao ad = DaoFactory.createAdresseDao();
		
		Adresse aOriginal = pd.getPersonById(6).getWohnadresse();
		Adresse aUpdate = pd.getPersonById(6).getWohnadresse();	
		
		aUpdate.setOrt("Test");
		ad.update(aUpdate);
		
		assertEquals("Adresse (Ort) in DB geändert", 
				aUpdate.getOrt(), pd.getPersonById(6).getWohnadresse().getOrt());
		
		// Daten auf Originalzustand zurücksetzen
		ad.update(aOriginal);
		
		assertEquals("Adresse (Ort) in DB zurückgeändert", 
				aOriginal.getOrt(), pd.getPersonById(6).getWohnadresse().getOrt());
		
	}	

}
