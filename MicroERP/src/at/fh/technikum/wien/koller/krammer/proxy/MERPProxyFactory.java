package at.fh.technikum.wien.koller.krammer.proxy;

import java.util.List;

import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;
import at.fh.technikum.wien.koller.krammer.proxy.mock.GetAlleKontakteMock;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetAlleKontakteRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetAlleRechnungenRequest;
import at.fh.technikum.wien.koller.krammer.proxy.request.GetKontaktFilterRequest;

public class MERPProxyFactory {
	private static final boolean MOCK_ALLE_KONTAKTE = false;
	private static final boolean MOCK_ALLE_RECHNUNGEN = false;
	private static final boolean MOCK_KONTAKTFILTER = false;

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
}
