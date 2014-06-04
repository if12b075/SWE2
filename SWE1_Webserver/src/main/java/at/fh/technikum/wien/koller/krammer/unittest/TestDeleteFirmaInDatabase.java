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

public class TestDeleteFirmaInDatabase {

	@Test
	public void testDeleteFirma() {
		Firma f = new Firma();
		
		f.setName("Delete_Name");
		f.setUid("Delete_UID");
		
		f.setWohnadresse(new Adresse(AdresseEnums.WOHNADRESSE,"Delete_Firmenweg 1", "Hauptgebäude", 1230, "Wien"));
		
		// Firma anlegen
		IFirmaDao fd = DaoFactory.createFirmaDao();
		fd.create(f);
	
		IKontaktDao kd = DaoFactory.createKontaktDao();
		List<Kontakt> kontaktList = new ArrayList<Kontakt>();
		kontaktList = kd.getFilterKontakte(new KontaktFilter("Delete_Name", true));
		
		long id = kontaktList.get(0).getId();
		
		// Firma vorhanden?
		assertEquals("Firma wurden in DB angelegt", f.getUid(), fd.getFirmaById(id).getUid());
		
		// Methode delete() testen
		kd.delete(id);
		kontaktList = kd.getFilterKontakte(new KontaktFilter("Delete_Name", false));
		
		// Person gelöscht?
		assertEquals("Firma wurde gelöscht - keine Daten aus DB zurückerhalten", 0, kontaktList.size());
	}
	
}