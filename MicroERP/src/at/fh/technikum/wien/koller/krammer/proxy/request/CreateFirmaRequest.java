package at.fh.technikum.wien.koller.krammer.proxy.request;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import at.fh.technikum.wien.koller.krammer.commons.Helper;
import at.fh.technikum.wien.koller.krammer.commons.MERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Firma;

public class CreateFirmaRequest {
	private static final String REQUEST_STRING = MERPConstants.HTTP_PLUGIN_URL
			+ commons.CommandRequestTitel.CREATE_FIRMA + "/";

	public static boolean createFirma(Firma f) {
		try {

			ByteArrayOutputStream temp = new ByteArrayOutputStream();
			String charset = "UTF-8";

			final XMLEncoder encoder = new XMLEncoder(temp);
			encoder.writeObject(f);
			encoder.close();

			String erg = Helper.excutePost(REQUEST_STRING,
					temp.toString(charset));

			if (erg.equals("failed"))
				return false;

			else
				return true;

		} catch (IOException e) {
			System.out.println("Encoding failed!");
			return false;
		}
	}
}
