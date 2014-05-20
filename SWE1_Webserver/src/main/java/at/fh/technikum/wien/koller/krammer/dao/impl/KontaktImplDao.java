package at.fh.technikum.wien.koller.krammer.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IAdresseDao;
import at.fh.technikum.wien.koller.krammer.dao.IFirmaDao;
import at.fh.technikum.wien.koller.krammer.dao.IKontaktDao;
import at.fh.technikum.wien.koller.krammer.dao.IPersonDao;
import at.fh.technikum.wien.koller.krammer.database.DatabaseConnection;
import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.merp.constants.MicroERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Adresse;
import at.fh.technikum.wien.koller.krammer.models.Firma;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.models.Person;

public class KontaktImplDao implements IKontaktDao {
	private static Connection c = DatabaseConnection.getConnection(MicroERPConstants.DB_CON);
	
	@Override
	public long create() {
		String createKontakt = "INSERT INTO TB_KONTAKT (ID_KONTAKT, GELOESCHT) "
				+ "VALUES (seq_kontakt.NEXTVAL, ?)";
		
		try {
			PreparedStatement createKontaktStatement = c.prepareStatement(createKontakt);
			
			createKontaktStatement.setInt(1, 0);
			
			createKontaktStatement.executeUpdate();
			createKontaktStatement.close();
			
			// Kontakt ID ermitteln
			Statement getKontaktIdStatement = c.createStatement();
			ResultSet rsGetKontaktId = getKontaktIdStatement.executeQuery("SELECT seq_kontakt.CURRVAL FROM TB_KONTAKT");
			rsGetKontaktId.next();
			
			long kontaktId = rsGetKontaktId.getLong(1);
			getKontaktIdStatement.close();
			
			return kontaktId;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	@Override
	public void update(long kontaktId, long wAdresse, long rAdresse, long lAdresse) {
		
		String updateKontakt = "UPDATE TB_KONTAKT SET ADRESSE = ?, "
				+ "RG_ADRESSE = ?, LF_ADRESSE = ? WHERE ID_KONTAKT = ?";
		
		try {
			PreparedStatement updateKontaktStatement = c.prepareStatement(updateKontakt);
			
			if(wAdresse != 0) {
				updateKontaktStatement.setLong(1, wAdresse);
			} else {
				updateKontaktStatement.setString(1, null);
			}
			
			if(rAdresse != 0) {
				updateKontaktStatement.setLong(2, rAdresse);
			} else {
				updateKontaktStatement.setString(2, null);
			}
			
			if(lAdresse != 0) {
				updateKontaktStatement.setLong(3, lAdresse);
			} else {
				updateKontaktStatement.setString(3, null);
			}
			
			updateKontaktStatement.setLong(4, kontaktId);
			
			updateKontaktStatement.executeUpdate();
			updateKontaktStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Kontakt> getAlleKontakte() {
		List<Kontakt> kontaktListe = new ArrayList<Kontakt>();
		
		String selectPerson = "SELECT * FROM TB_PERSON";
		String selectFirma = "SELECT * FROM TB_FIRMA";
		
			try {
				PreparedStatement selectPersonStatement = c.prepareStatement(selectPerson);
				PreparedStatement selectFirmaStatement = c.prepareStatement(selectFirma);
				
				ResultSet rs = selectPersonStatement.executeQuery();
				
				while(rs.next()) {
					Person p = new Person();
					p.setId(rs.getLong("ID_PERSON"));
					p.setTitel(rs.getString("TITEL"));
					p.setVorname(rs.getString("VORNAME"));
					p.setNachname(rs.getString("NACHNAME"));
					p.setSuffix(rs.getString("SUFFIX"));
					p.setGeburtstag(rs.getDate("GEB_DATUM"));
					p.setFirmaid(rs.getLong("TB_FIRMA_ID"));
					p.setGeloescht(rs.getInt("GELOESCHT"));
					
					if(p.getGeloescht() == 0) {
						kontaktListe.add(p);
					}
				}
				
				selectPersonStatement.close();
				
				rs = selectFirmaStatement.executeQuery();
				
				while(rs.next()) {
					Firma f = new Firma();
					f.setId(rs.getLong("ID_FIRMA"));
					f.setName(rs.getString("NAME"));
					f.setUid(rs.getString("UID_NR"));
					f.setGeloescht(rs.getInt("GELOESCHT"));
					
					if(f.getGeloescht() == 0) {
						kontaktListe.add(f);
					}
				}
				
				selectFirmaStatement.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return kontaktListe;
	}


	@Override
	public List<Kontakt> getFilterKontakte(KontaktFilter kf) {
		try {
			List<Kontakt> kontaktListe = new ArrayList<Kontakt>();
			
			if(kf.getIsFirma()!=null) {
				if(kf.getIsFirma()) {
					String select = "SELECT * FROM TB_FIRMA WHERE NAME LIKE ?";
					
					PreparedStatement selectStatement = c.prepareStatement(select);
					selectStatement.setString(1, "%" + kf.getName() + "%");
					
					ResultSet rs = selectStatement.executeQuery();
					
					while(rs.next()) {
						Firma f = new Firma();
						f.setId(rs.getLong("ID_FIRMA"));
						f.setName(rs.getString("NAME"));
						f.setUid(rs.getString("UID_NR"));
						f.setGeloescht(rs.getInt("GELOESCHT"));
						
						if(f.getGeloescht() == 0) {
							kontaktListe.add(f);
						}
					}
					
					selectStatement.close();
				} else {
					String select = "SELECT * FROM TB_PERSON WHERE (VORNAME LIKE ?) OR (NACHNAME LIKE ?)";
					
					PreparedStatement selectStatement = c.prepareStatement(select);
					selectStatement.setString(1, "%" + kf.getName() + "%");
					selectStatement.setString(2, "%" + kf.getName() + "%");
					
					ResultSet rs = selectStatement.executeQuery();
					
					while(rs.next()) {
						Person p = new Person();
						p.setId(rs.getLong("ID_PERSON"));
						p.setTitel(rs.getString("TITEL"));
						p.setVorname(rs.getString("VORNAME"));
						p.setNachname(rs.getString("NACHNAME"));
						p.setSuffix(rs.getString("SUFFIX"));
						p.setGeburtstag(rs.getDate("GEB_DATUM"));
						p.setFirmaid(rs.getLong("TB_FIRMA_ID"));
						p.setGeloescht(rs.getInt("GELOESCHT"));
						
						if(p.getGeloescht() == 0) {
							kontaktListe.add(p);
						}
						
					}
					
					selectStatement.close();
				}
					
			} else {
				String selectPerson = "SELECT * FROM TB_PERSON WHERE (VORNAME LIKE ?) OR (NACHNAME LIKE ?)";
				String selectFirma = "SELECT * FROM TB_FIRMA WHERE NAME LIKE ?";
				
				PreparedStatement selectPersonStatement = c.prepareStatement(selectPerson);
				PreparedStatement selectFirmaStatement = c.prepareStatement(selectFirma);
				
				selectFirmaStatement.setString(1, "%" + kf.getName() + "%");
				selectPersonStatement.setString(1, "%" + kf.getName() + "%");
				selectPersonStatement.setString(2, "%" + kf.getName() + "%");
				
				ResultSet rs = selectPersonStatement.executeQuery();
				
				while(rs.next()) {
					Person p = new Person();
					p.setId(rs.getLong("ID_PERSON"));
					p.setTitel(rs.getString("TITEL"));
					p.setVorname(rs.getString("VORNAME"));
					p.setNachname(rs.getString("NACHNAME"));
					p.setSuffix(rs.getString("SUFFIX"));
					p.setGeburtstag(rs.getDate("GEB_DATUM"));
					p.setFirmaid(rs.getLong("TB_FIRMA_ID"));
					p.setGeloescht(rs.getInt("GELOESCHT"));
					
					if(p.getGeloescht() == 0) {
						kontaktListe.add(p);
					}
				}
				
				selectPersonStatement.close();
				
				rs = selectFirmaStatement.executeQuery();
				
				while(rs.next()) {
					Firma f = new Firma();
					f.setId(rs.getLong("ID_FIRMA"));
					f.setName(rs.getString("NAME"));
					f.setUid(rs.getString("UID_NR"));
					f.setGeloescht(rs.getInt("GELOESCHT"));
					
					if(f.getGeloescht() == 0) {
						kontaktListe.add(f);
					}					
				}
				
				selectFirmaStatement.close();

			}
			
			
			return kontaktListe;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Kontakt getKontaktById(long id) {
		Kontakt k = null;
		
		switch(getKontaktType(id)) {
		case FIRMA:
			IFirmaDao fd = DaoFactory.createFirmaDao();
			k = fd.getFirmaById(id);
			break;
		case PERSON:
			IPersonDao pd = DaoFactory.createPersonDao();
			k = pd.getPersonById(id);
			break;
		}
		
		return k;
	}

	@Override
	public void delete(long id) {
		String deleteKontakt = "UPDATE TB_KONTAKT SET GELOESCHT = ? WHERE ID_KONTAKT = ?";
		
		try {	
			// Firma/Person löschen
			switch(getKontaktType(id)) {
			case FIRMA: 
				IFirmaDao fd = DaoFactory.createFirmaDao();
				fd.delete(id);
				break;
			case PERSON:
				IPersonDao pd = DaoFactory.createPersonDao();
				pd.delete(id);
				break;
			}
			
			// Adressen löschen
			IAdresseDao ad = DaoFactory.createAdresseDao();
			List<Adresse> adList = ad.getAlleAdressenVonKontakt(id);
			
			for(int i = 0; i < adList.size(); i++) {
				ad.delete(adList.get(i).getId());
			}

			// Kontakt löschen
			PreparedStatement deleteKontaktStatement = c.prepareStatement(deleteKontakt);
			
			deleteKontaktStatement.setLong(1, 1);
			deleteKontaktStatement.setLong(2, id);
			
			deleteKontaktStatement.executeUpdate();
			deleteKontaktStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public type getKontaktType(long id) {
		type kontaktType = null;
		
		String selectPerson = "SELECT ID_PERSON FROM TB_PERSON WHERE ID_PERSON = ?";
		String selectFirma = "SELECT ID_FIRMA FROM TB_FIRMA WHERE ID_FIRMA = ?";
		
		try {
			// Kontakt = Person
			PreparedStatement selectPersonStatement = c.prepareStatement(selectPerson);
			selectPersonStatement.setLong(1, id);
			
			ResultSet rs = selectPersonStatement.executeQuery();
			
			if(rs.next()) {				
				kontaktType = type.PERSON;
			}
			
			selectPersonStatement.close();
			
			// Kontakt = Firma
			PreparedStatement selectFirmaStatement = c.prepareStatement(selectFirma);
			selectFirmaStatement.setLong(1, id);
			
			rs = selectFirmaStatement.executeQuery();
			
			if(rs.next()) {				
				kontaktType = type.FIRMA;
			}
			
			selectFirmaStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return kontaktType;
	}

}
