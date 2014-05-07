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
import at.fh.technikum.wien.koller.krammer.models.Firma;
import commons.Globals;

public class GetFirmaByIdRequest {
private static final String REQUEST_STRING = MERPConstants.HTTP_PLUGIN_URL + commons.CommandRequestTitel.GET_FIRMA_BY_ID + "/";
	
	public static Firma getFirmaById(long id) {
		try {
			
			ByteArrayOutputStream temp = new ByteArrayOutputStream();
			String charset = "UTF-8";
			Firma getById = new Firma();
			getById.setId(id);
			
			final XMLEncoder encoder = new XMLEncoder(temp);
			encoder.writeObject(getById);
			encoder.close();
			
			String erg = Helper.excutePost(REQUEST_STRING, temp.toString(charset));

			InputStream is = new DataInputStream(new ByteArrayInputStream(
					erg.getBytes(Globals.CHARSET)));

			XMLDecoder decoder = new XMLDecoder(is);
			Firma f = (Firma) decoder.readObject();
			decoder.close();
			
			temp.close();
			
			return f;
			
		} catch (IOException e) {
			System.out.println("Encoding failed!");
			return null;
		}
	}
}
