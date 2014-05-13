package at.fh.technikum.wien.koller.krammer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.fh.technikum.wien.koller.krammer.dao.IFirmaDao;
import at.fh.technikum.wien.koller.krammer.database.DatabaseConnection;
import at.fh.technikum.wien.koller.krammer.merp.constants.MicroERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Adresse;
import at.fh.technikum.wien.koller.krammer.models.Firma;

public class FirmaImplDao implements IFirmaDao {
	private static Connection c = DatabaseConnection.getConnection(MicroERPConstants.DB_CON);

	@Override
	public void create(Firma f) {
		String createFirma = "INSERT INTO TB_FIRMA (ID_FIRMA, NAME, UID_NR) VALUES (seq_firma.NEXTVAL, ?, ?)";
		
		try {
			PreparedStatement createFirmaStatement = c.prepareStatement(createFirma);
			
			createFirmaStatement.setString(1, f.getName());
			createFirmaStatement.setString(2, f.getUid());
			
			createFirmaStatement.executeUpdate();
			createFirmaStatement.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Firma f) {
		String udpateFirma = "UPDATE TB_FIRMA SET NAME = ?, UID_NR = ? WHERE ID_FIRMA = ?";
		
		try {
			// Firmen Daten
			PreparedStatement updateFirmaStatement = c.prepareStatement(udpateFirma);
			
			updateFirmaStatement.setString(1, f.getName());
			updateFirmaStatement.setString(2, f.getUid());
			updateFirmaStatement.setLong(3, f.getId());
			
			updateFirmaStatement.executeUpdate();
			updateFirmaStatement.close();
			
			// Adressen
			AdresseImplDao aid = new AdresseImplDao();
			
			aid.update(f.getWohnadresse());
			aid.update(f.getRechnungsadresse());
			aid.update(f.getLieferadresse());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Firma getFirmaById(long id) {
		Firma firma = new Firma();
		
		String selectFirmaById = "SELECT * FROM TB_FIRMA WHERE ID_FIRMA = ?";
		
		try {
			// Firmen Daten
			PreparedStatement selectFirmaByIdStatement = c.prepareStatement(selectFirmaById);
			selectFirmaByIdStatement.setLong(1, id);
			
			ResultSet rs = selectFirmaByIdStatement.executeQuery();
			
			if(rs.next()) {	
				firma.setId(rs.getLong("ID_FIRMA"));
				firma.setName(rs.getString("NAME"));
				firma.setUid(rs.getString("UID_NR"));
			}
			
			selectFirmaByIdStatement.close();
			
			// Adressen
			AdresseImplDao aid = new AdresseImplDao();
			List<Adresse> addressList = new ArrayList<Adresse>();
			
			addressList = aid.getAlleAdressenVonKontakt(id);
			for(int i = 0; i < addressList.size(); i++) {
				switch(addressList.get(i).getAdressart()) {
				case WOHNADRESSE:
					firma.setWohnadresse(addressList.get(i));
					break;
				case RECHNUNGSADRESSE:
					firma.setRechnungsadresse(addressList.get(i));
					break;
				case LIEFERADRESSE:
					firma.setLieferadresse(addressList.get(i));
					break;				
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return firma;
	}

	
}
