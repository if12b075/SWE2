package at.fh.technikum.wien.koller.krammer.proxy.request;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import commons.CommandRequestTitel;
import commons.Globals;
import at.fh.technikum.wien.koller.krammer.commons.Helper;
import at.fh.technikum.wien.koller.krammer.commons.MERPConstants;
import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;

public class GetKontaktFilterRequest {

	@SuppressWarnings("unchecked")
	public List<Kontakt> getResponse(KontaktFilter kf) {
		try {
			
			ByteArrayOutputStream temp = new ByteArrayOutputStream();
			String charset = "UTF-8";
			String url = MERPConstants.HTTP_PLUGIN_URL
					+ CommandRequestTitel.GET_FILTER_KONTAKTE + "/";
			
			final XMLEncoder encoder = new XMLEncoder(temp);
			encoder.writeObject(kf);
			encoder.close();
			
			String erg = Helper.excutePost(url, temp.toString(charset));

			InputStream is = new DataInputStream(new ByteArrayInputStream(
					erg.getBytes(Globals.CHARSET)));

			XMLDecoder decoder = new XMLDecoder(is);
			List<Kontakt> kontaktliste = (List<Kontakt>) decoder.readObject();
			decoder.close();
			
			temp.close();
			
			return kontaktliste;
			
		} catch (IOException e) {
			System.out.println("Encoding failed!");
			return null;
		}
	}

}
