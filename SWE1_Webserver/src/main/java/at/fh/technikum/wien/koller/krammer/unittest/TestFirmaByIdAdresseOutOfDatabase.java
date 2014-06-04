package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IFirmaDao;

public class TestFirmaByIdAdresseOutOfDatabase {

	@Test
	public void testGetFirmaByIdAdresse() {	
		IFirmaDao fd = DaoFactory.createFirmaDao();
		
		assertEquals("Firma: Adresse (ID) aus DB pr�fen", 
				1, fd.getFirmaById(1).getWohnadresse().getId());
		assertEquals("Firma: Adresse (1. Zeile) aus DB pr�fen", 
				"Poststra�e 1", fd.getFirmaById(1).getWohnadresse().getAdrrow1());
		assertEquals("Firma: Adresse (2. Zeile) aus DB pr�fen", 
				"Zentrales Geb�ude", fd.getFirmaById(1).getWohnadresse().getAdrrow2());
		assertEquals("Firma: Adresse (PLZ) aus DB pr�fen", 
				1230, fd.getFirmaById(1).getWohnadresse().getPlz());
		assertEquals("Firma: Adresse (Ort) aus DB pr�fen", 
				"Wien", fd.getFirmaById(1).getWohnadresse().getOrt());
	}
	
	@Test
	public void testGetFirmaByIdRGAdresse() {	
		IFirmaDao fd = DaoFactory.createFirmaDao();
		
		assertEquals("Firma: RG-Adresse (ID) aus DB pr�fen", 
				2, fd.getFirmaById(1).getRechnungsadresse().getId());
		assertEquals("Firma: RG-Adresse (1. Zeile) aus DB pr�fen", 
				"Mustergasse 3", fd.getFirmaById(1).getRechnungsadresse().getAdrrow1());
		assertEquals("Firma: RG-Adresse (2. Zeile) aus DB pr�fen", 
				"zH Max Muster", fd.getFirmaById(1).getRechnungsadresse().getAdrrow2());
		assertEquals("Firma: RG-Adresse (PLZ) aus DB pr�fen", 
				5020, fd.getFirmaById(1).getRechnungsadresse().getPlz());
		assertEquals("Firma: RG-Adresse (Ort) aus DB pr�fen", 
				"Graz", fd.getFirmaById(1).getRechnungsadresse().getOrt());
	}	
	
	@Test
	public void testGetFirmaByIdLFAdresse() {	
		IFirmaDao fd = DaoFactory.createFirmaDao();
		
		assertEquals("Firma: LF-Adresse (ID) aus DB pr�fen", 
				3,fd.getFirmaById(1).getLieferadresse().getId());
		assertEquals("Firma: LF-Adresse (1. Zeile) aus DB pr�fen", 
				"Unter der Kirche 27", fd.getFirmaById(1).getLieferadresse().getAdrrow1());
		assertEquals("Firma: LF-Adresse (2. Zeile) aus DB pr�fen", 
				"Postfach 100", fd.getFirmaById(1).getLieferadresse().getAdrrow2());
		assertEquals("Firma: LF-Adresse (PLZ) aus DB pr�fen", 
				1110, fd.getFirmaById(1).getLieferadresse().getPlz());
		assertEquals("Firma: LF-Adresse (Ort) aus DB pr�fen", 
				"Wien", fd.getFirmaById(1).getLieferadresse().getOrt());
	}		

}
