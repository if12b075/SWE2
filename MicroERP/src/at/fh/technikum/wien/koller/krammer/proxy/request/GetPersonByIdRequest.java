package at.fh.technikum.wien.koller.krammer.proxy.request;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import commons.Globals;

import at.fh.technikum.wien.koller.krammer.commons.Helper;
import at.fh.technikum.wien.koller.krammer.commons.MERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Person;

public class GetPersonByIdRequest {
	private static final String REQUEST_STRING = MERPConstants.HTTP_PLUGIN_URL + commons.CommandRequestTitel.GET_PERSON_BY_ID + "/";
	
	public static Person getPersonById(long id) {
		try {
			
			ByteArrayOutputStream temp = new ByteArrayOutputStream();
			String charset = "UTF-8";
			Person getById = new Person();
			getById.setId(id);
			
			final XMLEncoder encoder = new XMLEncoder(temp);
			encoder.writeObject(getById);
			encoder.close();
			
			String erg = Helper.excutePost(REQUEST_STRING, temp.toString(charset));

			InputStream is = new DataInputStream(new ByteArrayInputStream(
					erg.getBytes(Globals.CHARSET)));

			XMLDecoder decoder = new XMLDecoder(is);
			Person p = (Person) decoder.readObject();
			decoder.close();
			
			temp.close();
			
			return p;
			
		} catch (IOException e) {
			System.out.println("Encoding failed!");
			return null;
		}
	}
}
