package commons;

public class CommandRequestTitel {
	public static final String GET_ALLE_KONTAKTE = "GetAlleKontakte";
	public static final String GET_ALLE_RECHNUNGEN = "GetAlleRechnungen";
	public static final String GET_FILTER_KONTAKTE = "GetKontaktFilter";
	public static final String GET_FILTER_RECHNUNG = "GetRechnungFilter"; // Jasmin: Implementierung fehlt noch
	
	public static final String GET_PERSON_BY_ID = "GetPersonById";
	public static final String GET_FIRMA_BY_ID = "GetFirmaById";
	public static final String GET_RECHNUNG_BY_ID = "GetRechnungById";
	public static final String GET_KONTAKT_BY_ID = "GetKontaktById";
	
	public static final String UPDATE_RECHNUNG = "UpdateRechnung";
	public static final String UPDATE_PERSON = "UpdatePerson";
	public static final String UPDATE_FIRMA = "UpdateFirma";
	public static final String UPDATE_RECHNUNGSZEILE = "UpdateRechnungszeile";
	
	public static final String CREATE_PERSON = "CreatePerson";
	public static final String CREATE_FIRMA = "CreateFirma";
	public static final String CREATE_RECHNUNG = "CreateRechnung";
	public static final String CREATE_RECHNUNGSZEILE = "CreateRechnungszeile";
	
	public static final String DELETE_KONTAKT = "DeleteKontakt";
	public static final String DELETE_ADRESSE = "DeleteAdresse";
	public static final String DELETE_RECHNUNG = "DeleteRechnung";
	public static final String DELETE_RECHNUNGSZEILE = "DeleteRechnungszeile";

}
