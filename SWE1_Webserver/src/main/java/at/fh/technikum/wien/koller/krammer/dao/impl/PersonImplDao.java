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
				+ "TITEL, VORNAME, NACHNAME, SUFFIX, GEB_DATUM, GELOESCHT) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			// Kontakt anlegen
			IKontaktDao ikd = DaoFactory.createKontaktDao();
			p.setId(ikd.create());
		
			// Person anlegen
			PreparedStatement createPersonStatement = c.prepareStatement(createPerson);
			
			createPersonStatement.setLong(1, p.getId());
			if(p.getFirmaid() != 0) {
				createPersonStatement.setLong(2, p.getFirmaid());
			} else {
				createPersonStatement.setString(2, null);
			}
			if(p.getTitel() != null) {
				createPersonStatement.setString(3, p.getTitel());
			} else {
				createPersonStatement.setString(3, null);
			}
			createPersonStatement.setString(4, p.getVorname());
			createPersonStatement.setString(5, p.getNachname());
			createPersonStatement.setString(6, p.getSuffix());
			createPersonStatement.setDate(7, new Date(p.getGeburtstag().getTime()));
			createPersonStatement.setInt(8, 0);
			
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
		long wohnadresseId = 0;
		long rechnungsadresseId = 0;
		long lieferadresseId = 0;
		
		String udpatePerson = "UPDATE TB_PERSON SET TB_FIRMA_ID = ?, TITEL = ?, VORNAME = ?, "
				+ "NACHNAME = ?, SUFFIX = ?, GEB_DATUM = ? WHERE ID_PERSON = ?";
		
		try {
			// Persönliche Daten
			PreparedStatement updatePersonStatement = c.prepareStatement(udpatePerson);
			
			if(p.getFirmaid() != 0) {
				updatePersonStatement.setLong(1, p.getFirmaid());
			} else {
				updatePersonStatement.setString(1, null);
			}
			if(p.getTitel() != null) {
				updatePersonStatement.setString(2, p.getTitel());
			} else {
				updatePersonStatement.setString(2, null);
			}
			updatePersonStatement.setString(3, p.getVorname());
			updatePersonStatement.setString(4, p.getNachname());
			updatePersonStatement.setString(5, p.getSuffix());
			updatePersonStatement.setDate(6, new Date(p.getGeburtstag().getTime()));
			updatePersonStatement.setLong(7, p.getId());
			
			updatePersonStatement.executeUpdate();
			updatePersonStatement.close();
			
			// Adressen updaten/anlegen
			IAdresseDao aid = DaoFactory.createAdresseDao();
			
			if(p.getWohnadresse() != null) {
				if(p.getWohnadresse().getId() != 0) {
					aid.update(p.getWohnadresse());
					wohnadresseId = p.getWohnadresse().getId();
				} else {
					wohnadresseId = aid.create(p.getWohnadresse());
				}
			}
			
			if(p.getRechnungsadresse() != null) {
				if(p.getRechnungsadresse().getId() != 0) {
					aid.update(p.getRechnungsadresse());
					rechnungsadresseId = p.getRechnungsadresse().getId();
				} else {
					rechnungsadresseId = aid.create(p.getRechnungsadresse());
				}
			}
			
			if(p.getLieferadresse() != null) {
				if(p.getLieferadresse().getId() != 0) {
					aid.update(p.getLieferadresse());
					lieferadresseId = p.getLieferadresse().getId();
				} else {
					lieferadresseId = aid.create(p.getLieferadresse());
				}
			}
			
			// Kontakt mit Adressen updaten
			IKontaktDao ikd = DaoFactory.createKontaktDao();
			ikd.update(p.getId(), wohnadresseId, rechnungsadresseId, lieferadresseId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void delete(long id) {
		String deletePerson = "UPDATE TB_PERSON SET GELOESCHT = ? WHERE ID_PERSON = ?";
		
		try {
			PreparedStatement deletePersonStatement = c.prepareStatement(deletePerson);
			
			deletePersonStatement.setLong(1, 1);
			deletePersonStatement.setLong(2, id);
			
			deletePersonStatement.executeUpdate();
			deletePersonStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				person.setGeloescht(rs.getInt("GELOESCHT"));
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
