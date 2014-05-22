package at.fh.technikum.wien.koller.krammer.proxy.request;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import at.fh.technikum.wien.koller.krammer.commons.Helper;
import at.fh.technikum.wien.koller.krammer.commons.MERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import commons.Globals;

public class GetKontaktByIdRequest {
private static final String REQUEST_STRING = MERPConstants.HTTP_PLUGIN_URL + commons.CommandRequestTitel.GET_KONTAKT_BY_ID + "/";
	
	public static Kontakt getKontaktById(Kontakt k) {
		try {
			
			ByteArrayOutputStream temp = new ByteArrayOutputStream();
			String charset = "UTF-8";
			
			final XMLEncoder encoder = new XMLEncoder(temp);
			encoder.writeObject(k);
			encoder.close();
			
			String erg = Helper.excutePost(REQUEST_STRING, temp.toString(charset));

			InputStream is = new DataInputStream(new ByteArrayInputStream(
					erg.getBytes(Globals.CHARSET)));

			XMLDecoder decoder = new XMLDecoder(is);
			Kontakt k2 = (Kontakt) decoder.readObject();
			decoder.close();
			
			temp.close();
			
			return k2;
			
		} catch (IOException e) {
			System.out.println("Encoding failed!");
			return null;
		}
	}
}
