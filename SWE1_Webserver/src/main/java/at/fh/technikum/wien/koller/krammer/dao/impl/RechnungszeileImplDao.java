package at.fh.technikum.wien.koller.krammer.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.fh.technikum.wien.koller.krammer.dao.IRechnungDao;
import at.fh.technikum.wien.koller.krammer.dao.IRechnungszeileDao;
import at.fh.technikum.wien.koller.krammer.database.DatabaseConnection;
import at.fh.technikum.wien.koller.krammer.merp.constants.MicroERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;
import at.fh.technikum.wien.koller.krammer.models.Rechnungszeile;

public class RechnungszeileImplDao implements IRechnungszeileDao {
	private static Connection c = DatabaseConnection.getConnection(MicroERPConstants.DB_CON);

	@Override
	public void create(Rechnungszeile r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Rechnungszeile r) {
		String updateRechnungszeile = "UPDATE TB_RECHNUNGSZEILE SET BEZEICHNUNG = ?, STUECKPREIS = ?, "
				+ "UST = ?, MENGE = ? WHERE ID_RECHNUNGSZEILE = ?";
		
		try {
			PreparedStatement updateRechnungszeileStatement = c.prepareStatement(updateRechnungszeile);
			
			updateRechnungszeileStatement.setString(1, r.getBezeichnung());
			updateRechnungszeileStatement.setFloat(2, r.getStueckpreis());
			updateRechnungszeileStatement.setInt(3, r.getUst());
			updateRechnungszeileStatement.setInt(4, r.getMenge());
			updateRechnungszeileStatement.setLong(5, r.getId());
					
			updateRechnungszeileStatement.executeUpdate();
			updateRechnungszeileStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Rechnungszeile> getAlleRechnungszeilenZuRechnung(long rechnungsid) {
		ArrayList<Rechnungszeile> rgRowList = new ArrayList<Rechnungszeile>();
		
		String selectRechnungszeileById = "SELECT * FROM TB_RECHNUNGSZEILE WHERE TB_RECHNUNG_ID = ?";
		
		
		try {
			PreparedStatement selectRechnungszeilenByIdStatement;
			selectRechnungszeilenByIdStatement = c.prepareStatement(selectRechnungszeileById);
			
			selectRechnungszeilenByIdStatement.setLong(1, rechnungsid);
			
			ResultSet rs = selectRechnungszeilenByIdStatement.executeQuery();
			
			while(rs.next()) {
				Rechnungszeile rz = new Rechnungszeile();
				rz.setId(rs.getLong("ID_RECHNUNGSZEILE"));
				rz.setBezeichnung(rs.getString("BEZEICHNUNG"));
				rz.setMenge(rs.getInt("MENGE"));
				rz.setStueckpreis(rs.getFloat("STUECKPREIS"));
				rz.setUst(rs.getInt("UST"));
				
				rgRowList.add(rz);
			}
			
			selectRechnungszeilenByIdStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rgRowList;
	}
	
	

}
