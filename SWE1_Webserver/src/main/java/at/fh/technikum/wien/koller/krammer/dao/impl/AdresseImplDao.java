package at.fh.technikum.wien.koller.krammer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import at.fh.technikum.wien.koller.krammer.dao.IAdresseDao;
import at.fh.technikum.wien.koller.krammer.database.DatabaseConnection;
import at.fh.technikum.wien.koller.krammer.merp.constants.MicroERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Adresse;
import at.fh.technikum.wien.koller.krammer.models.AdresseEnums;

public class AdresseImplDao implements IAdresseDao {
	private static Connection c = DatabaseConnection.getConnection(MicroERPConstants.DB_CON);

	@Override
	public long create(Adresse a) {
		String createAdresse = "INSERT INTO TB_ADRESSE (ID_ADRESSE, ADRESSE_Z1, "
				+ "ADRESSE_Z2, PLZ, ORT, GELOESCHT) VALUES (seq_adresse.NEXTVAL, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement createAdresseStatement = c.prepareStatement(createAdresse);
			
			createAdresseStatement.setString(1, a.getAdrrow1());
			if(a.getAdrrow2() != null) {
				createAdresseStatement.setString(2, a.getAdrrow2());
			} else {
				createAdresseStatement.setString(2, null);
			}
			createAdresseStatement.setInt(3, a.getPlz());
			createAdresseStatement.setString(4, a.getOrt());
			createAdresseStatement.setInt(5, 0);
			
			createAdresseStatement.executeUpdate();
			createAdresseStatement.close();
			
			// Adresse ID ermitteln
			Statement getAdresseIdStatement = c.createStatement();
			ResultSet rsGetAdresseIdStatement = getAdresseIdStatement.executeQuery("SELECT seq_adresse.CURRVAL FROM TB_ADRESSE");
			rsGetAdresseIdStatement.next();
			
			long adresseId = rsGetAdresseIdStatement.getLong(1);
			getAdresseIdStatement.close();
			
			return adresseId; 
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void update(Adresse a) {
		
		switch(a.getAdressart()) {
		
		case WOHNADRESSE:
			String updatePersonAdresse = "UPDATE TB_ADRESSE SET ADRESSE_Z1 = ?, ADRESSE_Z2 = ?, "
					+ "PLZ = ?, ORT = ? WHERE ID_ADRESSE = ?";
			
			try {
				PreparedStatement updatePersonAdresseStatement = c.prepareStatement(updatePersonAdresse);
				
				updatePersonAdresseStatement.setString(1, a.getAdrrow1());
				if(a.getAdrrow2() != null) {
					updatePersonAdresseStatement.setString(2, a.getAdrrow2());
				} else {
					updatePersonAdresseStatement.setString(2, null);
				}
				updatePersonAdresseStatement.setInt(3, a.getPlz());
				updatePersonAdresseStatement.setString(4, a.getOrt());
				updatePersonAdresseStatement.setLong(5, a.getId());
				
				updatePersonAdresseStatement.executeUpdate();
				updatePersonAdresseStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case RECHNUNGSADRESSE:
			String updatePersonRGAdresse = "UPDATE TB_ADRESSE SET ADRESSE_Z1 = ?, ADRESSE_Z2 = ?, "
					+ "PLZ = ?, ORT = ? WHERE ID_ADRESSE = ?";
			
			try {
				PreparedStatement updatePersonRGAdresseStatement = c.prepareStatement(updatePersonRGAdresse);
				
				updatePersonRGAdresseStatement.setString(1, a.getAdrrow1());
				if(a.getAdrrow2() != null) {
					updatePersonRGAdresseStatement.setString(2, a.getAdrrow2());
				} else {
					updatePersonRGAdresseStatement.setString(2, null);
				}
				updatePersonRGAdresseStatement.setInt(3, a.getPlz());
				updatePersonRGAdresseStatement.setString(4, a.getOrt());
				updatePersonRGAdresseStatement.setLong(5, a.getId());
				
				updatePersonRGAdresseStatement.executeUpdate();
				updatePersonRGAdresseStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case LIEFERADRESSE:
			String updatePersonLFAdresse = "UPDATE TB_ADRESSE SET ADRESSE_Z1 = ?, ADRESSE_Z2 = ?, "
					+ "PLZ = ?, ORT = ? WHERE ID_ADRESSE = ?";
			
			try {
				PreparedStatement updatePersonLFAdresseStatement = c.prepareStatement(updatePersonLFAdresse);
				
				updatePersonLFAdresseStatement.setString(1, a.getAdrrow1());
				if(a.getAdrrow2() != null) {
					updatePersonLFAdresseStatement.setString(2, a.getAdrrow2());
				} else {
					updatePersonLFAdresseStatement.setString(2, null);
				}
				updatePersonLFAdresseStatement.setInt(3, a.getPlz());
				updatePersonLFAdresseStatement.setString(4, a.getOrt());
				updatePersonLFAdresseStatement.setLong(5, a.getId());
				
				updatePersonLFAdresseStatement.executeUpdate();
				updatePersonLFAdresseStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		
	}

	@Override
	public void delete(long id) {
		String deleteAdresse = "UPDATE TB_ADRESSE SET GELOESCHT = ? WHERE ID_ADRESSE = ?";
		
		try {
			PreparedStatement deleteAdresseStatement = c.prepareStatement(deleteAdresse);
			
			deleteAdresseStatement.setLong(1, 1);
			deleteAdresseStatement.setLong(2, id);
			
			deleteAdresseStatement.executeUpdate();
			deleteAdresseStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Adresse> getAlleAdressenVonKontakt(long kontaktid) {
		List<Adresse> addressList = new ArrayList<Adresse>();
		
		String selectPersonAdresse = "SELECT * FROM TB_ADRESSE INNER JOIN TB_KONTAKT "
				+ "ON TB_ADRESSE.ID_ADRESSE = TB_KONTAKT.ADRESSE WHERE TB_KONTAKT.ID_KONTAKT = ?";
		String selectPersonRGAdresse = "SELECT * FROM TB_ADRESSE INNER JOIN TB_KONTAKT "
				+ "ON TB_ADRESSE.ID_ADRESSE = TB_KONTAKT.RG_ADRESSE WHERE TB_KONTAKT.ID_KONTAKT = ?";
		String selectPersonLFAdresse = "SELECT * FROM TB_ADRESSE INNER JOIN TB_KONTAKT "
				+ "ON TB_ADRESSE.ID_ADRESSE = TB_KONTAKT.LF_ADRESSE WHERE TB_KONTAKT.ID_KONTAKT = ?";
		
		try {
			// Wohnadresse
			PreparedStatement selectPersonAdresseStatement = c.prepareStatement(selectPersonAdresse);
			selectPersonAdresseStatement.setLong(1, kontaktid);
			
			ResultSet rs = selectPersonAdresseStatement.executeQuery();
			
			if(rs.next()) {
				Adresse ad = new Adresse();
				ad.setAdressart(AdresseEnums.WOHNADRESSE);
				ad.setId(rs.getLong("ID_ADRESSE"));
				ad.setAdrrow1(rs.getString("ADRESSE_Z1"));
				ad.setAdrrow2(rs.getString("ADRESSE_Z2"));
				ad.setPlz(rs.getInt("PLZ"));
				ad.setOrt(rs.getString("ORT"));
				ad.setGeloescht(rs.getInt("GELOESCHT"));
				
				if(ad.getGeloescht() == 0) {
					addressList.add(ad);
				}
			}
			
			selectPersonAdresseStatement.close();
			
			// Rechnungsadresse
			PreparedStatement selectPersonRGAdresseStatement = c.prepareStatement(selectPersonRGAdresse);
			selectPersonRGAdresseStatement.setLong(1, kontaktid);
			
			rs = selectPersonRGAdresseStatement.executeQuery();
			
			if(rs.next()) {
				Adresse ad = new Adresse();
				ad.setAdressart(AdresseEnums.RECHNUNGSADRESSE);
				ad.setId(rs.getLong("ID_ADRESSE"));
				ad.setAdrrow1(rs.getString("ADRESSE_Z1"));
				ad.setAdrrow2(rs.getString("ADRESSE_Z2"));
				ad.setPlz(rs.getInt("PLZ"));
				ad.setOrt(rs.getString("ORT"));
				ad.setGeloescht(rs.getInt("GELOESCHT"));
				
				if(ad.getGeloescht() == 0) {
					addressList.add(ad);
				}
			}
			
			selectPersonRGAdresseStatement.close();
			
			// Lieferadresse
			PreparedStatement selectPersonLFAdresseStatement = c.prepareStatement(selectPersonLFAdresse);
			selectPersonLFAdresseStatement.setLong(1, kontaktid);
			
			rs = selectPersonLFAdresseStatement.executeQuery();
			
			if(rs.next()) {
				Adresse ad = new Adresse();
				ad.setAdressart(AdresseEnums.LIEFERADRESSE);
				ad.setId(rs.getLong("ID_ADRESSE"));
				ad.setAdrrow1(rs.getString("ADRESSE_Z1"));
				ad.setAdrrow2(rs.getString("ADRESSE_Z2"));
				ad.setPlz(rs.getInt("PLZ"));
				ad.setOrt(rs.getString("ORT"));
				ad.setGeloescht(rs.getInt("GELOESCHT"));
				
				if(ad.getGeloescht() == 0) {
					addressList.add(ad);
				}
			}
			
			selectPersonLFAdresseStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return addressList;
	}

	
}
