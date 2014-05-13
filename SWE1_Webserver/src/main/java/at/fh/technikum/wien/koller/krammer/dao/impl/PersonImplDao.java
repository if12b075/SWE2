package at.fh.technikum.wien.koller.krammer.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.fh.technikum.wien.koller.krammer.dao.IPersonDao;
import at.fh.technikum.wien.koller.krammer.database.DatabaseConnection;
import at.fh.technikum.wien.koller.krammer.merp.constants.MicroERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Adresse;
import at.fh.technikum.wien.koller.krammer.models.Person;

public class PersonImplDao implements IPersonDao {
	private static Connection c = DatabaseConnection.getConnection(MicroERPConstants.DB_CON);

	@Override
	public void create(Person p) {
		String createPerson = "INSERT INTO TB_PERSON (ID_PERSON, TB_FIRMA_ID, "
				+ "TITEL, VORNAME, NACHNAME, SUFFIX, GEB_DATUM) "
				+ "VALUES (seq_person.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement createPersonStatement = c.prepareStatement(createPerson);
			
			createPersonStatement.setLong(1, p.getFirmaid());
			createPersonStatement.setString(2, p.getTitel());
			createPersonStatement.setString(3, p.getVorname());
			createPersonStatement.setString(4, p.getNachname());
			createPersonStatement.setString(5, p.getSuffix());
			createPersonStatement.setDate(6, (Date) p.getGeburtstag());
			
			createPersonStatement.executeUpdate();
			createPersonStatement.close();
			
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
			updatePersonStatement.setDate(6, new Date( p.getGeburtstag().getTime()));
			updatePersonStatement.setLong(7, p.getId());
			
			updatePersonStatement.executeUpdate();
			updatePersonStatement.close();
			
			// Adressen
			AdresseImplDao aid = new AdresseImplDao();
			
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
			AdresseImplDao aid = new AdresseImplDao();
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
