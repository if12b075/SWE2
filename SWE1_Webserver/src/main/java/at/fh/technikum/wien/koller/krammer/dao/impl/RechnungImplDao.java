package at.fh.technikum.wien.koller.krammer.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IRechnungDao;
import at.fh.technikum.wien.koller.krammer.dao.IRechnungszeileDao;
import at.fh.technikum.wien.koller.krammer.database.DatabaseConnection;
import at.fh.technikum.wien.koller.krammer.merp.constants.MicroERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;
import at.fh.technikum.wien.koller.krammer.models.Rechnungszeile;

public class RechnungImplDao implements IRechnungDao {
	private static Connection c = DatabaseConnection.getConnection(MicroERPConstants.DB_CON);
	
	@Override
	public void create(Rechnung r) {
		String createRechnung = "INSERT INTO TB_RECHNUNG (ID_RECHNUNG, TB_KONTAKT_ID, "
				+ "RG_NUMMER, DATUM, FAELLIGKEIT, BEZAHLT, KOMMENTAR, NACHRICHT, GELOESCHT) "
				+ "VALUES (seq_rechnung.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			// Rechnung erstellen
			PreparedStatement createRechnungStatement = c.prepareStatement(createRechnung);
					
			createRechnungStatement.setLong(1, r.getKontaktid());
			createRechnungStatement.setLong(2, r.getRechnungsnummer());
			createRechnungStatement.setDate(3, new Date (r.getDatum().getTime()));
			createRechnungStatement.setDate(4, new Date (r.getFaelligkeitsdatum().getTime()));
			if(r.getBezahltAm() != null) {
				createRechnungStatement.setDate(5, new Date (r.getBezahltAm().getTime()));
			} else {
				createRechnungStatement.setString(5, null);
			}
			if(r.getKommentar() != null) {
				createRechnungStatement.setString(6, r.getKommentar());
			} else {
				createRechnungStatement.setString(6, null);
			}
			if(r.getNachricht() != null) {
				createRechnungStatement.setString(7, r.getNachricht());
			} else {
				createRechnungStatement.setString(7, null);
			}
			createRechnungStatement.setInt(8, 0);
			
			createRechnungStatement.executeUpdate();
			createRechnungStatement.close();
			
			/*
			// Rechnung ID ermitteln
			Statement getRechnungIdStatement = c.createStatement();
			ResultSet rsGetRechnungId = getRechnungIdStatement.executeQuery("SELECT seq_rechnung.CURRVAL FROM TB_RECHNUNG");
			rsGetRechnungId.next();
			
			long rechnungId = rsGetRechnungId.getLong(1);
			getRechnungIdStatement.close();
			
			// Rechnungszeilen erstellen
			IRechnungszeileDao rid = DaoFactory.createRechnungszeileDao();
			
			if(r.getRechnungszeilen() != null) {
				for(int i = 0; i < r.getRechnungszeilen().size(); i++) {
					r.getRechnungszeilen().get(i).setRechnungid(rechnungId);
					rid.create(r.getRechnungszeilen().get(i));
				}
			}
			*/
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
			if(r.getKommentar() != null) {
				updateRechnungStatement.setString(2, r.getKommentar());
			} else {
				updateRechnungStatement.setString(2, null);
			}
			if(r.getBezahltAm() != null) {
				updateRechnungStatement.setDate(3, new Date (r.getBezahltAm().getTime()));
			} else {
				updateRechnungStatement.setString(3, null);
			}
			updateRechnungStatement.setDate(4, new Date (r.getFaelligkeitsdatum().getTime()));
			if(r.getNachricht() != null) {
				updateRechnungStatement.setString(5, r.getNachricht());
			} else {
				updateRechnungStatement.setString(5, null);
			}
			updateRechnungStatement.setLong(6, r.getId());	
			
			updateRechnungStatement.executeUpdate();
			updateRechnungStatement.close();
			
			// Rechnungszeilen
			IRechnungszeileDao rzd = DaoFactory.createRechnungszeileDao();
			
			for(int i = 0; i < r.getRechnungszeilen().size(); i++) {
				rzd.update(r.getRechnungszeilen().get(i));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void delete(long id) {
		
		String deleteRechnung = "UPDATE TB_RECHNUNG SET GELOESCHT = ? WHERE ID_RECHNUNG = ?";
		
		try {
			PreparedStatement deleteRechnungStatement = c.prepareStatement(deleteRechnung);
			
			deleteRechnungStatement.setLong(1, 1);
			deleteRechnungStatement.setLong(2, id);
			
			deleteRechnungStatement.executeUpdate();
			deleteRechnungStatement.close();
			
			// Rechnungszeilen von Rechnung löschen
			IRechnungszeileDao rzd = DaoFactory.createRechnungszeileDao();
			ArrayList<Rechnungszeile> rzList = rzd.getAlleRechnungszeilenZuRechnung(id);
			
			for(int i = 0; i < rzList.size(); i++) {
				rzd.delete(rzList.get(i).getId());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
					rg.setGeloescht(rs.getInt("GELOESCHT"));
					
					if(rg.getGeloescht() == 0) {
						rechnungsListe.add(rg);	
					}
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
				rg.setGeloescht(rs.getInt("GELOESCHT"));
			}
			
			selectRechnungByIdStatement.close();
			
			// Rechnungszeilen
			IRechnungszeileDao rid = DaoFactory.createRechnungszeileDao();
			ArrayList<Rechnungszeile> rgRowList = new ArrayList<Rechnungszeile>();
			
			rgRowList = rid.getAlleRechnungszeilenZuRechnung(id); 
			rg.setRechnungszeilen(rgRowList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rg;
	}

}
