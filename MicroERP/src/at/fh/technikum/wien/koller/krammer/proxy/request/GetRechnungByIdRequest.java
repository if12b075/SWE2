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
import at.fh.technikum.wien.koller.krammer.models.Rechnung;

import commons.Globals;

public class GetRechnungByIdRequest {
private static final String REQUEST_STRING = MERPConstants.HTTP_PLUGIN_URL + commons.CommandRequestTitel.GET_RECHNUNG_BY_ID + "/";
	
	public static Rechnung getRechnungById(long id) {
		try {
			
			ByteArrayOutputStream temp = new ByteArrayOutputStream();
			String charset = "UTF-8";
			Rechnung getById = new Rechnung();
			getById.setId(id);
			
			final XMLEncoder encoder = new XMLEncoder(temp);
			encoder.writeObject(getById);
			encoder.close();
			
			String erg = Helper.excutePost(REQUEST_STRING, temp.toString(charset));

			InputStream is = new DataInputStream(new ByteArrayInputStream(
					erg.getBytes(Globals.CHARSET)));

			XMLDecoder decoder = new XMLDecoder(is);
			Rechnung r = (Rechnung) decoder.readObject();
			decoder.close();
			
			temp.close();
			
			return r;
			
		} catch (IOException e) {
			System.out.println("Encoding failed!");
			return null;
		}
	}
}
