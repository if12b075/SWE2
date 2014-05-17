package at.fh.technikum.wien.koller.krammer.proxy;

import java.util.List;

import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.models.Firma;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.models.Person;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;
import at.fh.technikum.wien.koller.krammer.proxy.mock.GetAlleKontakteMock;
import at.fh.technikum.wien.koller.krammer.proxy.mock.GetPersonByIdMock;
import at.fh.technikum.wien.koller.krammer.proxy.request.CreateFirmaRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.CreatePersonRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetAlleKontakteRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetAlleRechnungenRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetFirmaByIdRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetKontaktFilterRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetPersonByIdRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.UpdateFirmaRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.UpdatePersonRequest;

public class MERPProxyFactory {
	private static final boolean MOCK_ALLE_KONTAKTE = false;
	private static final boolean MOCK_ALLE_RECHNUNGEN = false;
	private static final boolean MOCK_KONTAKTFILTER = false;
	private static final boolean MOCK_GET_PERSON_BY_ID = false;
	private static final boolean MOCK_GET_FIRMA_BY_ID = false;
	private static final boolean MOCK_UPDATE_PERSON = false;
	private static final boolean MOCK_UPDATE_FIRMA = false;
	private static final boolean MOCK_CREATE_FIRMA = false;
	private static final boolean MOCK_CREATE_PERSON = false;

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
}
