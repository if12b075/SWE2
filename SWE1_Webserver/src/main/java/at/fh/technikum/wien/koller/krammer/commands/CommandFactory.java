package at.fh.technikum.wien.koller.krammer.commands;

import at.technikum.wien.winterhalderkreuzriegler.swe1.common.domain.interfaces.Uri;

public class CommandFactory {
	public static ICommand createCommand(Uri uri) {
		String choice = uri.getPath().substring(7, uri.getPath().length()-1);
		ICommand c = null;
		
		switch(choice) {
		case commons.CommandRequestTitel.GET_ALLE_KONTAKTE:
				c = new GetAlleKontakteCommand();
			break;
		case commons.CommandRequestTitel.GET_ALLE_RECHNUNGEN:
			c = new GetAlleRechnungenCommand();
			break;
		case commons.CommandRequestTitel.GET_FILTER_KONTAKTE:
			c = new GetKontaktFilterCommand();
			default:
				break;
		case commons.CommandRequestTitel.GET_PERSON_BY_ID:
			c = new GetPersonByIdCommand();
			break;
		case commons.CommandRequestTitel.GET_FIRMA_BY_ID:
			c = new GetFirmaByIdCommand();
			break;
		case commons.CommandRequestTitel.GET_RECHNUNG_BY_ID:
			c = new GetRechnungByIdCommand();
			break;
		case commons.CommandRequestTitel.UPDATE_PERSON:
			c = new UpdatePersonCommand();
			break;
		case commons.CommandRequestTitel.UPDATE_RECHNUNG:
			c = new UpdateRechnungCommand();
			break;
		}
		
		return c;	
	}
}
