package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IFirmaDao;
import at.fh.technikum.wien.koller.krammer.dao.IKontaktDao;
import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.models.Adresse;
import at.fh.technikum.wien.koller.krammer.models.AdresseEnums;
import at.fh.technikum.wien.koller.krammer.models.Firma;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;

public class TestCreateFirmaInDatabase {

	@Test
	public void testInsertFirmaWithOneAdresse() {
		Firma f = new Firma();
		
		f.setName("Testfirma_Name");
		f.setUid("Testfirma_UID");
		
		f.setWohnadresse(new Adresse(AdresseEnums.WOHNADRESSE,"Firmenweg 1", "Hauptgebäude", 1230, "Wien"));
		
		// Methode create() testen
		IFirmaDao fd = DaoFactory.createFirmaDao();
		fd.create(f);
	
		// Überprüfen, ob Daten in DB vorhanden sind
		IKontaktDao kd = DaoFactory.createKontaktDao();
		List<Kontakt> kontaktList = new ArrayList<Kontakt>();
		kontaktList = kd.getFilterKontakte(new KontaktFilter("Testfirma_Name", true));
		
		long id = kontaktList.get(0).getId();
		
		assertEquals("Firma wurden in DB angelegt", f.getUid(), fd.getFirmaById(id).getUid());
		assertEquals("Adresse wurde in DB angelegt", f.getWohnadresse().getAdrrow1(), fd.getFirmaById(id).getWohnadresse().getAdrrow1());
		
		// Daten wieder löschen
		kd.delete(id);
	}
	
	@Test
	public void testInsertFirmaWithThreeAdresses() {
		Firma f = new Firma();
		
		f.setName("Testfirma_Name");
		f.setUid("Testfirma_UID");
		
		f.setWohnadresse(new Adresse(AdresseEnums.WOHNADRESSE,"Firmenweg 1", "Hauptgebäude", 1230, "Wien"));
		f.setRechnungsadresse(new Adresse(AdresseEnums.RECHNUNGSADRESSE,"Firmenweg 2", "Hauptgebäude", 1230, "Wien"));
		f.setLieferadresse(new Adresse(AdresseEnums.LIEFERADRESSE,"Firmenweg 3", "Hauptgebäude", 1230, "Wien"));
		
		// Methode create() testen
		IFirmaDao fd = DaoFactory.createFirmaDao();
		fd.create(f);
	
		// Überprüfen, ob Daten in DB vorhanden sind
		IKontaktDao kd = DaoFactory.createKontaktDao();
		List<Kontakt> kontaktList = new ArrayList<Kontakt>();
		kontaktList = kd.getFilterKontakte(new KontaktFilter("Testfirma_Name", true));
		
		long id = kontaktList.get(0).getId();
		
		assertEquals("Firma wurden in DB angelegt", f.getUid(), fd.getFirmaById(id).getUid());
		assertEquals("Adresse wurde in DB angelegt", f.getWohnadresse().getAdrrow1(), fd.getFirmaById(id).getWohnadresse().getAdrrow1());
		assertEquals("Rechnungsadresse wurde in DB angelegt", f.getRechnungsadresse().getAdrrow1(), fd.getFirmaById(id).getRechnungsadresse().getAdrrow1());
		assertEquals("Lieferadresse wurde in DB angelegt", f.getLieferadresse().getAdrrow1(), fd.getFirmaById(id).getLieferadresse().getAdrrow1());
		
		// Daten wieder löschen
		kd.delete(id);
	}
	


}
