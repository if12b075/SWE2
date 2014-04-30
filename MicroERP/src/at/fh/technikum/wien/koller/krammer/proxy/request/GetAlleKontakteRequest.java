package at.fh.technikum.wien.koller.krammer.proxy.request;

import java.beans.XMLDecoder;
import java.net.URL;
import java.util.List;

import at.fh.technikum.wien.koller.krammer.commons.MERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;

public class GetAlleKontakteRequest {
	private final String REQUEST_STRING = MERPConstants.HTTP_PLUGIN_URL + commons.CommandRequestTitel.GET_ALLE_KONTAKTE +"/";
	
	public List<Kontakt> getResponse() {
		try {
			URL requestURL = new URL(REQUEST_STRING);
			
			XMLDecoder decoder = new XMLDecoder(requestURL.openStream());
			@SuppressWarnings("unchecked")
			List<Kontakt> kontaktliste = (List<Kontakt>) decoder.readObject();
			decoder.close();
			
			return kontaktliste;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		
		
	}
}
