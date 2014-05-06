package at.fh.technikum.wien.koller.krammer.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.fh.technikum.wien.koller.krammer.dao.IAdresseDao;
import at.fh.technikum.wien.koller.krammer.dao.IRechnungDao;
import at.fh.technikum.wien.koller.krammer.dao.IRechnungszeileDao;
import at.fh.technikum.wien.koller.krammer.database.DatabaseConnection;
import at.fh.technikum.wien.koller.krammer.merp.constants.MicroERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Adresse;
import at.fh.technikum.wien.koller.krammer.models.AdresseEnums;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;
import at.fh.technikum.wien.koller.krammer.models.Rechnungszeile;

public class AdresseImplDao implements IAdresseDao {
	private static Connection c = DatabaseConnection.getConnection(MicroERPConstants.DB_CON);

	@Override
	public void create(Adresse a) {
		// TODO Auto-generated method stub
		
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
				updatePersonAdresseStatement.setString(2, a.getAdrrow2());
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
				updatePersonRGAdresseStatement.setString(2, a.getAdrrow2());
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
				updatePersonLFAdresseStatement.setString(2, a.getAdrrow2());
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
		// TODO Auto-generated method stub
		
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
				
				addressList.add(ad);
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
				
				addressList.add(ad);
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
				
				addressList.add(ad);
			}
			
			selectPersonLFAdresseStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return addressList;
	}

	
}
