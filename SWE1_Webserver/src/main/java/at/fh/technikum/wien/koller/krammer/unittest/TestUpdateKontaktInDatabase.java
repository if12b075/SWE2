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
	
		assertEquals("Kontakt: Wohnadresse in DB geändert", 1, kd.getKontaktById(6).getWohnadresse().getId());
		assertEquals("Kontakt: Rechnungsadresse in DB geändert", 2, kd.getKontaktById(6).getRechnungsadresse().getId());
		assertEquals("Kontakt: Lieferadresse in DB geändert", 3, kd.getKontaktById(6).getLieferadresse().getId());
		
		// Daten auf Originalzustand zurücksetzen
		kd.update(6, kOriginal.getWohnadresse().getId(), kOriginal.getRechnungsadresse().getId(), kOriginal.getLieferadresse().getId());
		
		assertEquals("Kontakt: Wohnadresse in DB zurückgeändert", kOriginal.getWohnadresse().getId(), kd.getKontaktById(6).getWohnadresse().getId());
		assertEquals("Kontakt: Rechnungsadresse in DB zurückgeändert", kOriginal.getRechnungsadresse().getId(), kd.getKontaktById(6).getRechnungsadresse().getId());
		assertEquals("Kontakt: Lieferadresse in DB zurückgeändert", kOriginal.getLieferadresse().getId(), kd.getKontaktById(6).getLieferadresse().getId());
		
	}
	
	@Test
	public void testUpdateKontaktAufEineAdressen() {
		IKontaktDao kd = DaoFactory.createKontaktDao();
		Kontakt kOriginal = kd.getKontaktById(1);
		
		kd.update(1, 0, 0, 1);
		System.out.println(kd.getKontaktById(1).getWohnadresse());
	
		assertEquals("Kontakt: Wohnadresse in DB gelöscht", null, kd.getKontaktById(1).getWohnadresse());
		assertEquals("Kontakt: Rechnungsadresse in DB gelöscht", null, kd.getKontaktById(1).getRechnungsadresse());
		assertEquals("Kontakt: Lieferadresse in DB geändert", 1, kd.getKontaktById(1).getLieferadresse().getId());
		
		// Daten auf Originalzustand zurücksetzen
		kd.update(1, kOriginal.getWohnadresse().getId(), kOriginal.getRechnungsadresse().getId(), kOriginal.getLieferadresse().getId());
		
		assertEquals("Kontakt: Wohnadresse in DB zurückgeändert", kOriginal.getWohnadresse().getId(), kd.getKontaktById(1).getWohnadresse().getId());
		assertEquals("Kontakt: Rechnungsadresse in DB zurückgeändert", kOriginal.getRechnungsadresse().getId(), kd.getKontaktById(1).getRechnungsadresse().getId());
		assertEquals("Kontakt: Lieferadresse in DB zurückgeändert", kOriginal.getLieferadresse().getId(), kd.getKontaktById(1).getLieferadresse().getId());
		
	}

}
