package at.fh.technikum.wien.koller.krammer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IAdresseDao;
import at.fh.technikum.wien.koller.krammer.dao.IFirmaDao;
import at.fh.technikum.wien.koller.krammer.dao.IKontaktDao;
import at.fh.technikum.wien.koller.krammer.database.DatabaseConnection;
import at.fh.technikum.wien.koller.krammer.merp.constants.MicroERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Adresse;
import at.fh.technikum.wien.koller.krammer.models.Firma;

public class FirmaImplDao implements IFirmaDao {
	private static Connection c = DatabaseConnection.getConnection(MicroERPConstants.DB_CON);

	@Override
	public void create(Firma f) {
		long wohnadresseId = 0;
		long rechnungsadresseId = 0;
		long lieferadresseId = 0;
		
		String createFirma = "INSERT INTO TB_FIRMA (ID_FIRMA, NAME, UID_NR) VALUES (?, ?, ?)";
		
		try {
			// Kontakt anlegen
			IKontaktDao ikd = DaoFactory.createKontaktDao();
			f.setId(ikd.create());
			
			// Firma anlegen
			PreparedStatement createFirmaStatement = c.prepareStatement(createFirma);
			
			createFirmaStatement.setLong(1, f.getId());
			createFirmaStatement.setString(2, f.getName());
			createFirmaStatement.setString(3, f.getUid());
			
			createFirmaStatement.executeUpdate();
			createFirmaStatement.close();
			
			// Adressen anlegen
			IAdresseDao aid = DaoFactory.createAdresseDao();
			
			if(f.getWohnadresse() != null) {
				wohnadresseId = aid.create(f.getWohnadresse());
			}
			
			if(f.getRechnungsadresse() != null) {
				rechnungsadresseId = aid.create(f.getRechnungsadresse());
			}
			
			if(f.getLieferadresse() != null) {
				lieferadresseId = aid.create(f.getLieferadresse());
			}
			
			// Kontakt mit Adressen updaten
			ikd.update(f.getId(), wohnadresseId, rechnungsadresseId, lieferadresseId);
			
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
