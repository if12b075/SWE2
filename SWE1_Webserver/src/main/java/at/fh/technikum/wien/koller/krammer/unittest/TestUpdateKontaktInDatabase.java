package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IKontaktDao;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;

public class TestUpdateKontaktInDatabase {

	@Test
	public void testUpdateKontaktAlleAdressen() {
		IKontaktDao kd = DaoFactory.createKontaktDao();
		Kontakt kOriginal = kd.getKontaktById(6);
		
		kd.update(6, 1, 2, 3);
	
		assertEquals("Kontakt: Wohnadresse in DB ge�ndert", 1, kd.getKontaktById(6).getWohnadresse().getId());
		assertEquals("Kontakt: Rechnungsadresse in DB ge�ndert", 2, kd.getKontaktById(6).getRechnungsadresse().getId());
		assertEquals("Kontakt: Lieferadresse in DB ge�ndert", 3, kd.getKontaktById(6).getLieferadresse().getId());
		
		// Daten auf Originalzustand zur�cksetzen
		kd.update(6, kOriginal.getWohnadresse().getId(), kOriginal.getRechnungsadresse().getId(), kOriginal.getLieferadresse().getId());
		
		assertEquals("Kontakt: Wohnadresse in DB zur�ckge�ndert", kOriginal.getWohnadresse().getId(), kd.getKontaktById(6).getWohnadresse().getId());
		assertEquals("Kontakt: Rechnungsadresse in DB zur�ckge�ndert", kOriginal.getRechnungsadresse().getId(), kd.getKontaktById(6).getRechnungsadresse().getId());
		assertEquals("Kontakt: Lieferadresse in DB zur�ckge�ndert", kOriginal.getLieferadresse().getId(), kd.getKontaktById(6).getLieferadresse().getId());
		
	}
	
	@Test
	public void testUpdateKontaktAufEineAdressen() {
		IKontaktDao kd = DaoFactory.createKontaktDao();
		Kontakt kOriginal = kd.getKontaktById(1);
		
		kd.update(1, 0, 0, 1);
		System.out.println(kd.getKontaktById(1).getWohnadresse());
	
		assertEquals("Kontakt: Wohnadresse in DB gel�scht", null, kd.getKontaktById(1).getWohnadresse());
		assertEquals("Kontakt: Rechnungsadresse in DB gel�scht", null, kd.getKontaktById(1).getRechnungsadresse());
		assertEquals("Kontakt: Lieferadresse in DB ge�ndert", 1, kd.getKontaktById(1).getLieferadresse().getId());
		
		// Daten auf Originalzustand zur�cksetzen
		kd.update(1, kOriginal.getWohnadresse().getId(), kOriginal.getRechnungsadresse().getId(), kOriginal.getLieferadresse().getId());
		
		assertEquals("Kontakt: Wohnadresse in DB zur�ckge�ndert", kOriginal.getWohnadresse().getId(), kd.getKontaktById(1).getWohnadresse().getId());
		assertEquals("Kontakt: Rechnungsadresse in DB zur�ckge�ndert", kOriginal.getRechnungsadresse().getId(), kd.getKontaktById(1).getRechnungsadresse().getId());
		assertEquals("Kontakt: Lieferadresse in DB zur�ckge�ndert", kOriginal.getLieferadresse().getId(), kd.getKontaktById(1).getLieferadresse().getId());
		
	}

}
