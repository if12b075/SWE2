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

public class TestCreatePersonInDatabase {

	@Test
	public void testInsertPersonWithOneAdresse() {
		Person p = new Person();
		p.setTitel("Testperson_Titel");
		p.setVorname("Testperson_Vorname");
		p.setNachname("Testperson_Nachname");
		p.setSuffix("Testperson_Suffix");
		p.setFirmaid(1);
		p.setGeburtstag(new Date());
		p.setWohnadresse(new Adresse(AdresseEnums.WOHNADRESSE,"Testweg 1", "Stock 1/2/3", 1230, "Wien"));
		
		// Methode create() testen
		IPersonDao pd = DaoFactory.createPersonDao();
		pd.create(p);
		
		// Überprüfen, ob Daten in DB vorhanden sind
		IKontaktDao kd = DaoFactory.createKontaktDao();
		List<Kontakt> kontaktList = new ArrayList<Kontakt>();
		kontaktList = kd.getFilterKontakte(new KontaktFilter("Testperson_Nachname", false));
		
		long id = kontaktList.get(0).getId();
		
		assertEquals("Person wurden in DB angelegt", p.getVorname(), pd.getPersonById(id).getVorname());
		assertEquals("Adresse wurde in DB angelegt", p.getWohnadresse().getAdrrow1(), pd.getPersonById(id).getWohnadresse().getAdrrow1());
		
		// Daten wieder löschen
		kd.delete(id);
	}
	
	@Test
	public void testInsertPersonWithThreeAdresses() {
		Person p = new Person();
		p.setTitel("Testperson_Titel");
		p.setVorname("Testperson_Vorname");
		p.setNachname("Testperson_Nachname");
		p.setSuffix("Testperson_Suffix");
		p.setFirmaid(1);
		p.setGeburtstag(new Date());
		
		p.setWohnadresse(new Adresse(AdresseEnums.WOHNADRESSE,"Testweg 1", "Stock 1/2/3", 1230, "Wien"));
		p.setRechnungsadresse(new Adresse(AdresseEnums.RECHNUNGSADRESSE,"Testweg 2", "Stock 1/2/3", 1230, "Wien"));
		p.setLieferadresse(new Adresse(AdresseEnums.LIEFERADRESSE,"Testweg 3", "Stock 1/2/3", 1230, "Wien"));
		
		// Methode create() testen
		IPersonDao pd = DaoFactory.createPersonDao();
		pd.create(p);
		
		// Überprüfen, ob Daten in DB vorhanden sind
		IKontaktDao kd = DaoFactory.createKontaktDao();
		List<Kontakt> kontaktList = new ArrayList<Kontakt>();
		kontaktList = kd.getFilterKontakte(new KontaktFilter("Testperson_Nachname", false));
		
		long id = kontaktList.get(0).getId();
		
		assertEquals("Daten wurden in DB angelegt", p.getVorname(), pd.getPersonById(id).getVorname());
		assertEquals("Wohnadresse wurde in DB angelegt", p.getWohnadresse().getAdrrow1(), pd.getPersonById(id).getWohnadresse().getAdrrow1());
		assertEquals("Rechnungsadresse wurde in DB angelegt", p.getRechnungsadresse().getAdrrow1(), pd.getPersonById(id).getRechnungsadresse().getAdrrow1());
		assertEquals("Lieferadresse wurde in DB angelegt", p.getLieferadresse().getAdrrow1(), pd.getPersonById(id).getLieferadresse().getAdrrow1());
		
		// Daten wieder löschen
		kd.delete(id);
	}
	


}
