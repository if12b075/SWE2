package at.fh.technikum.wien.koller.krammer.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IAdresseDao;
import at.fh.technikum.wien.koller.krammer.dao.IKontaktDao;
import at.fh.technikum.wien.koller.krammer.dao.IPersonDao;
import at.fh.technikum.wien.koller.krammer.database.DatabaseConnection;
import at.fh.technikum.wien.koller.krammer.merp.constants.MicroERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Adresse;
import at.fh.technikum.wien.koller.krammer.models.Person;

public class PersonImplDao implements IPersonDao {
	private static Connection c = DatabaseConnection.getConnection(MicroERPConstants.DB_CON);

	@Override
	public void create(Person p) {
		long wohnadresseId = 0;
		long rechnungsadresseId = 0;
		long lieferadresseId = 0;
		
		String createPerson = "INSERT INTO TB_PERSON (ID_PERSON, TB_FIRMA_ID, "
				+ "TITEL, VORNAME, NACHNAME, SUFFIX, GEB_DATUM) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			// Kontakt anlegen
			IKontaktDao ikd = DaoFactory.createKontaktDao();
			p.setId(ikd.create());
		
			// Person anlegen
			PreparedStatement createPersonStatement = c.prepareStatement(createPerson);
			
			createPersonStatement.setLong(1, p.getId());
			createPersonStatement.setLong(2, p.getFirmaid());
			createPersonStatement.setString(3, p.getTitel());
			createPersonStatement.setString(4, p.getVorname());
			createPersonStatement.setString(5, p.getNachname());
			createPersonStatement.setString(6, p.getSuffix());
			createPersonStatement.setDate(7, new Date(p.getGeburtstag().getTime()));  
			
			createPersonStatement.executeUpdate();
			createPersonStatement.close();
			
			// Adressen anlegen
			IAdresseDao aid = DaoFactory.createAdresseDao();
			
			if(p.getWohnadresse() != null) {
				wohnadresseId = aid.create(p.getWohnadresse());
			}
			
			if(p.getRechnungsadresse() != null) {
				rechnungsadresseId = aid.create(p.getRechnungsadresse());
			}
			
			if(p.getLieferadresse() != null) {
				lieferadresseId = aid.create(p.getLieferadresse());
			}
			
			// Kontakt mit Adressen updaten
			ikd.update(p.getId(), wohnadresseId, rechnungsadresseId, lieferadresseId);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void update(Person p) {		
		String udpatePerson = "UPDATE TB_PERSON SET TB_FIRMA_ID = ?, TITEL = ?, VORNAME = ?, "
				+ "NACHNAME = ?, SUFFIX = ?, GEB_DATUM = ? WHERE ID_PERSON = ?";
		
		try {
			// Persönliche Daten
			PreparedStatement updatePersonStatement = c.prepareStatement(udpatePerson);
			
			updatePersonStatement.setLong(1, p.getFirmaid());
			updatePersonStatement.setString(2, p.getTitel());
			updatePersonStatement.setString(3, p.getVorname());
			updatePersonStatement.setString(4, p.getNachname());
			updatePersonStatement.setString(5, p.getSuffix());
			updatePersonStatement.setDate(6, new Date(p.getGeburtstag().getTime()));
			updatePersonStatement.setLong(7, p.getId());
			
			updatePersonStatement.executeUpdate();
			updatePersonStatement.close();
			
			// Adressen
			IAdresseDao aid = DaoFactory.createAdresseDao();
			
			aid.update(p.getWohnadresse());
			aid.update(p.getRechnungsadresse());
			aid.update(p.getLieferadresse());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person getPersonById(long id) {
		Person person = new Person();
				
		String selectPersonById = "SELECT * FROM TB_PERSON WHERE ID_PERSON = ?";
		
		try {
			// persönliche Daten
			PreparedStatement selectPersonByIdStatement = c.prepareStatement(selectPersonById);
			selectPersonByIdStatement.setLong(1, id);
			
			ResultSet rs = selectPersonByIdStatement.executeQuery();
			
			if(rs.next()) {				
				person.setId(rs.getLong("ID_PERSON"));
				person.setTitel(rs.getString("TITEL"));
				person.setVorname(rs.getString("VORNAME"));
				person.setNachname(rs.getString("NACHNAME"));
				person.setSuffix(rs.getString("SUFFIX"));
				person.setGeburtstag(rs.getDate("GEB_DATUM"));
				person.setFirmaid(rs.getLong("TB_FIRMA_ID"));
			}
			
			selectPersonByIdStatement.close();
			
			// Adressen
			IAdresseDao aid = DaoFactory.createAdresseDao();
			List<Adresse> addressList = new ArrayList<Adresse>();
			
			addressList = aid.getAlleAdressenVonKontakt(id);
			for(int i = 0; i < addressList.size(); i++) {
				switch(addressList.get(i).getAdressart()) {
				case WOHNADRESSE:
					person.setWohnadresse(addressList.get(i));
					break;
				case RECHNUNGSADRESSE:
					person.setRechnungsadresse(addressList.get(i));
					break;
				case LIEFERADRESSE:
					person.setLieferadresse(addressList.get(i));
					break;				
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return person;
	}

}
