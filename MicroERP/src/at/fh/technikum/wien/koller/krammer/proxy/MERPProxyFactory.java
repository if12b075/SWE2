package at.fh.technikum.wien.koller.krammer.proxy;

import java.util.List;

import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.models.Firma;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.models.Person;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;
import at.fh.technikum.wien.koller.krammer.models.Rechnungszeile;
import at.fh.technikum.wien.koller.krammer.proxy.mock.GetAlleKontakteMock;
import at.fh.technikum.wien.koller.krammer.proxy.mock.GetPersonByIdMock;
import at.fh.technikum.wien.koller.krammer.proxy.request.CreateFirmaRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.CreatePersonRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.CreateRechnungszeileRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.DeleteKontaktRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.DeleteRechnungszeileRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetAlleKontakteRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetAlleRechnungenRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetFirmaByIdRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetKontaktByIdRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetKontaktFilterRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetPersonByIdRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetRechnungByIdRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.UpdateFirmaRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.UpdatePersonRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.UpdateRechnungszeileRequest;

public class MERPProxyFactory {
	private static final boolean MOCK_ALLE_KONTAKTE = false;
	private static final boolean MOCK_ALLE_RECHNUNGEN = false;
	private static final boolean MOCK_KONTAKTFILTER = false;
	private static final boolean MOCK_GET_PERSON_BY_ID = false;
	private static final boolean MOCK_GET_KONTAKT_BY_ID = false;
	private static final boolean MOCK_GET_FIRMA_BY_ID = false;
	private static final boolean MOCK_UPDATE_PERSON = false;
	private static final boolean MOCK_UPDATE_FIRMA = false;
	private static final boolean MOCK_UPDATE_RECHNUNGSZEILE = false;
	private static final boolean MOCK_CREATE_FIRMA = false;
	private static final boolean MOCK_CREATE_PERSON = false;
	private static final boolean MOCK_CREATE_RECHNUNGSZEILE = false;
	private static final boolean MOCK_GET_RECHNUNG_BY_ID = false;
	private static final boolean MOCK_DELETE_KONTAKT = false;
	private static final boolean MOCK_DELETE_RECHNUNGSZEILE = false;

	@SuppressWarnings("static-access")
	public static List<Kontakt> getAlleKontakte() {
		if (MOCK_ALLE_KONTAKTE)
			return new GetAlleKontakteMock().getAlleKontaktMock();

		else
			return (new GetAlleKontakteRequest()).getResponse();
	}

	public static List<Rechnung> getAlleRechnungen() {
		if (MOCK_ALLE_RECHNUNGEN)
			return null;
		else
			return (new GetAlleRechnungenRequest()).getResponse();
	}

	public static List<Kontakt> getKontaktFilter(KontaktFilter kf) {
		if (MOCK_KONTAKTFILTER)
			return null;
		else
			return (new GetKontaktFilterRequest()).getResponse(kf);
	}
	
	@SuppressWarnings("static-access")
	public static Person getPersonById(long id) {
		if (MOCK_GET_PERSON_BY_ID)
			return GetPersonByIdMock.getPersonById(id);
		else
			return (new GetPersonByIdRequest().getPersonById(id));
	}
	
	@SuppressWarnings("static-access")
	public static Firma getFirmaById(long id) {
		if (MOCK_GET_FIRMA_BY_ID)
			return null;
		else
			return (new GetFirmaByIdRequest().getFirmaById(id));
	}
	
	@SuppressWarnings("static-access")
	public static Rechnung getRechnungById(long id) {
		if (MOCK_GET_RECHNUNG_BY_ID)
			return null;
		else
			return (new GetRechnungByIdRequest().getRechnungById(id));
	}
	
	@SuppressWarnings("static-access")
	public static boolean updatePerson(Person p) {
		if (MOCK_UPDATE_PERSON)
			return false;
		else
			return (new UpdatePersonRequest()).updatePerson(p);
	}
	
	@SuppressWarnings("static-access")
	public static boolean updateFirma(Firma f) {
		if (MOCK_UPDATE_FIRMA)
			return false;
		else
			return (new UpdateFirmaRequest()).updateFirma(f);
	}
	
	@SuppressWarnings("static-access")
	public static boolean updateRechnungszeile(Rechnungszeile r) {
		if (MOCK_UPDATE_RECHNUNGSZEILE)
			return false;
		else
			return (new UpdateRechnungszeileRequest().updateRechnungszeile(r));
	}
	
	@SuppressWarnings("static-access")
	public static boolean createFirma(Firma f) {
		if (MOCK_CREATE_FIRMA)
			return false;
		else
			return (new CreateFirmaRequest().createFirma(f));
	}
	
	@SuppressWarnings("static-access")
	public static boolean createPerson(Person p) {
		if (MOCK_CREATE_PERSON)
			return false;
		else
			return (new CreatePersonRequest().createPerson(p));
	}
	
	@SuppressWarnings("static-access")
	public static boolean createRechnungszeile(Rechnungszeile r) {
		if (MOCK_CREATE_RECHNUNGSZEILE)
			return false;
		else
			return (new CreateRechnungszeileRequest().createRechnungszeile(r));
	}
	
	@SuppressWarnings("static-access")
	public static boolean deleteKontakt(Kontakt k) {
		if (MOCK_DELETE_KONTAKT)
			return false;
		else
			return (new DeleteKontaktRequest().deleteKontakt(k));
	}
	
	@SuppressWarnings("static-access")
	public static boolean deleteRechnungszeile(Rechnungszeile r) {
		if (MOCK_DELETE_RECHNUNGSZEILE)
			return false;
		else
			return (new DeleteRechnungszeileRequest().deleteRechnungszeile(r));
	}
	
	@SuppressWarnings("static-access")
	public static Kontakt getKontaktById(Kontakt k) {
		if (MOCK_GET_KONTAKT_BY_ID)
			return null;
		else
			return (new GetKontaktByIdRequest().getKontaktById(k));
	}
}
