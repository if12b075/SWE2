package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IKontaktDao;
import at.fh.technikum.wien.koller.krammer.dao.IPersonDao;
import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.models.Adresse;
import at.fh.technikum.wien.koller.krammer.models.AdresseEnums;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.models.Person;

public class TestDeletePersonInDatabase {

	@Test
	public void testDeletePerson() {
		Person p = new Person();
		p.setTitel("Delete_Titel");
		p.setVorname("Delete_Vorname");
		p.setNachname("Delete_Nachname");
		p.setSuffix("Delete_Suffix");
		p.setFirmaid(1);
		p.setGeburtstag(new Date());
		p.setWohnadresse(new Adresse(AdresseEnums.WOHNADRESSE,"Delete_Testweg 1", "Stock 1/2/3", 1230, "Wien"));
		
		// Person anlegen
		IPersonDao pd = DaoFactory.createPersonDao();
		pd.create(p);
		
		IKontaktDao kd = DaoFactory.createKontaktDao();
		List<Kontakt> kontaktList = new ArrayList<Kontakt>();
		kontaktList = kd.getFilterKontakte(new KontaktFilter("Delete_Nachname", false));
		
		long id = kontaktList.get(0).getId();
		
		// Person vorhanden?
		assertEquals("Person wurden in DB angelegt", p.getVorname(), pd.getPersonById(id).getVorname());
		
		// Methode delete() testen
		kd.delete(id);
		kontaktList = kd.getFilterKontakte(new KontaktFilter("Delete_Nachname", false));
		
		// Person gelöscht?
		assertEquals("Person wurde gelöscht - keine Daten aus DB zurückerhalten", 0, kontaktList.size());
		
	}

}
