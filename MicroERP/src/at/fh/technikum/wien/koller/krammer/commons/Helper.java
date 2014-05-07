package at.fh.technikum.wien.koller.krammer.commons;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Helper {
	public static String excutePost(String targetURL, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;

		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/xml");

			connection.setRequestProperty("Content-Length",
					"" + String.valueOf(urlParameters.length()));

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setConnectTimeout(20000);

			// Send request
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			writer.write("\n" + urlParameters);
			writer.flush();
			writer.close();
			connection.getOutputStream().close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	 public static final boolean isNullOrEmpty(String str) {
	       return str == null || str.isEmpty();
	  }
}
