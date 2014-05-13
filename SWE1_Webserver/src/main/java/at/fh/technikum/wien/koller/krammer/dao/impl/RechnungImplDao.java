package at.fh.technikum.wien.koller.krammer.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.fh.technikum.wien.koller.krammer.dao.IRechnungDao;
import at.fh.technikum.wien.koller.krammer.database.DatabaseConnection;
import at.fh.technikum.wien.koller.krammer.merp.constants.MicroERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;
import at.fh.technikum.wien.koller.krammer.models.Rechnungszeile;

public class RechnungImplDao implements IRechnungDao {
	private static Connection c = DatabaseConnection.getConnection(MicroERPConstants.DB_CON);
	
	@Override
	public void create(Rechnung r) {
		String createRechnung = "INSERT INTO TB_RECHNUNG (ID_RECHNUNG, TB_KONTAKT_ID, "
				+ "RG_NUMMER, DATUM, FAELLIGKEIT, BEZAHLT, KOMMENTAR, NACHRICHT) "
				+ "VALUES (seq_rechnung.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement createRechnungStatement = c.prepareStatement(createRechnung);
			
			createRechnungStatement.setLong(1, r.getKontaktid());
			createRechnungStatement.setLong(2, r.getRechnungsnummer());
			createRechnungStatement.setDate(3, (Date) r.getDatum());
			createRechnungStatement.setDate(4, (Date) r.getFaelligkeitsdatum());
			createRechnungStatement.setDate(5, (Date) r.getBezahltAm());
			createRechnungStatement.setString(6, r.getKommentar());
			createRechnungStatement.setString(7, r.getNachricht());
			
			createRechnungStatement.executeUpdate();
			createRechnungStatement.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Rechnung r) {
		String updateRechnung = "UPDATE TB_RECHNUNG SET TB_KONTAKT_ID = ?, KOMMENTAR = ?, "
				+ "BEZAHLT = ?, FAELLIGKEIT = ?, NACHRICHT = ? WHERE ID_RECHNUNG = ?";
		
		try {
			// Rechnungsdaten
			PreparedStatement updateRechnungStatement = c.prepareStatement(updateRechnung);
			
			updateRechnungStatement.setLong(1, r.getKontaktid());
			updateRechnungStatement.setString(2, r.getKommentar());
			updateRechnungStatement.setDate(3, (Date) r.getBezahltAm());
			updateRechnungStatement.setDate(4, (Date) r.getFaelligkeitsdatum());
			updateRechnungStatement.setString(5, r.getNachricht());
			updateRechnungStatement.setLong(6, r.getId());
			
			updateRechnungStatement.executeUpdate();
			updateRechnungStatement.close();
			
			// Rechnungszeilen
			RechnungszeileImplDao rid = new RechnungszeileImplDao();
			
			for(int i = 0; i < r.getRechnungszeilen().size(); i++) {
				rid.update(r.getRechnungszeilen().get(i));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Rechnung> getAlleRechnungen() {
		List<Rechnung> rechnungsListe = new ArrayList<Rechnung>();
		
		String selectRechnung = "SELECT * FROM TB_RECHNUNG";
		
			try {
				PreparedStatement selectRechnungStatement = c.prepareStatement(selectRechnung);

				ResultSet rs = selectRechnungStatement.executeQuery();
				
				while(rs.next()) {
					Rechnung rg = new Rechnung();
					rg.setId(rs.getLong("ID_RECHNUNG"));
					rg.setKontaktid(rs.getLong("TB_KONTAKT_ID"));
					rg.setKommentar(rs.getString("KOMMENTAR"));
					rg.setBezahltAm(rs.getDate("BEZAHLT"));
					rg.setDatum(rs.getDate("DATUM"));
					rg.setFaelligkeitsdatum(rs.getDate("FAELLIGKEIT"));
					rg.setNachricht(rs.getString("NACHRICHT"));
					rg.setRechnungsnummer(rs.getLong("RG_NUMMER"));
					
					rechnungsListe.add(rg);	
				}
				
				selectRechnungStatement.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return rechnungsListe;
	}

	@Override
	public Rechnung getRechnungById(long id) {
		Rechnung rg = new Rechnung();
				
		String selectRechnungById = "SELECT * FROM TB_RECHNUNG WHERE ID_RECHNUNG = ?";
		
		try {
			// Rechnungsdaten
			PreparedStatement selectRechnungByIdStatement = c.prepareStatement(selectRechnungById);
			selectRechnungByIdStatement.setLong(1, id);
			
			ResultSet rs = selectRechnungByIdStatement.executeQuery();
			
			if(rs.next()) {				
				rg.setId(rs.getLong("ID_RECHNUNG"));
				rg.setKontaktid(rs.getLong("TB_KONTAKT_ID"));
				rg.setKommentar(rs.getString("KOMMENTAR"));
				rg.setBezahltAm(rs.getDate("BEZAHLT"));
				rg.setDatum(rs.getDate("DATUM"));
				rg.setFaelligkeitsdatum(rs.getDate("FAELLIGKEIT"));
				rg.setNachricht(rs.getString("NACHRICHT"));
				rg.setRechnungsnummer(rs.getLong("RG_NUMMER"));
			}
			
			selectRechnungByIdStatement.close();
			
			// Rechnungszeilen
			RechnungszeileImplDao rid = new RechnungszeileImplDao();
			ArrayList<Rechnungszeile> rgRowList = new ArrayList<Rechnungszeile>();
			
			rgRowList = rid.getAlleRechnungszeilenZuRechnung(id); 
			rg.setRechnungszeilen(rgRowList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rg;
	}

}
