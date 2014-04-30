package at.fh.technikum.wien.koller.krammer.proxy.request;

import java.beans.XMLDecoder;
import java.net.URL;
import java.util.List;

import at.fh.technikum.wien.koller.krammer.commons.MERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;

public class GetAlleRechnungenRequest {
private final String REQUEST_STRING = MERPConstants.HTTP_PLUGIN_URL + commons.CommandRequestTitel.GET_ALLE_RECHNUNGEN + "/";
	
	public List<Rechnung> getResponse() {
		try {
			URL requestURL = new URL(REQUEST_STRING);
			XMLDecoder decoder = new XMLDecoder(requestURL.openStream());
			@SuppressWarnings("unchecked")
			List<Rechnung> rechnungsliste = (List<Rechnung>) decoder.readObject();
			decoder.close();
			
			return rechnungsliste;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		
		
	}
}
