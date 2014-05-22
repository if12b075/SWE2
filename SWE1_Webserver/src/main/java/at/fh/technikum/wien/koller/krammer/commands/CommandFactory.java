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
			break;
		/*case commons.CommandRequestTitel.GET_FILTER_RECHNUNG:
			c = new GetRechnungFilterCommand();
			break;*/
		case commons.CommandRequestTitel.GET_PERSON_BY_ID:
			c = new GetPersonByIdCommand();
			break;
		case commons.CommandRequestTitel.GET_FIRMA_BY_ID:
			c = new GetFirmaByIdCommand();
			break;
		case commons.CommandRequestTitel.GET_RECHNUNG_BY_ID:
			c = new GetRechnungByIdCommand();
			break;
		case commons.CommandRequestTitel.GET_KONTAKT_BY_ID:
			c = new GetKontaktByIdCommand();
			break;
		case commons.CommandRequestTitel.UPDATE_PERSON:
			c = new UpdatePersonCommand();
			break;
		case commons.CommandRequestTitel.UPDATE_FIRMA:
			c = new UpdateFirmaCommand();
			break;
		case commons.CommandRequestTitel.UPDATE_RECHNUNG:
			c = new UpdateRechnungCommand();
			break;
		case commons.CommandRequestTitel.UPDATE_RECHNUNGSZEILE:
			c = new UpdateRechnungszeileCommand();
			break;
		case commons.CommandRequestTitel.CREATE_PERSON:
			c = new CreatePersonCommand();
			break;
		case commons.CommandRequestTitel.CREATE_FIRMA:
			c = new CreateFirmaCommand();
			break;
		case commons.CommandRequestTitel.CREATE_RECHNUNG:
			c = new CreateRechnungCommand();
			break;
		case commons.CommandRequestTitel.CREATE_RECHNUNGSZEILE:
			c = new CreateRechnungszeileCommand();
			break;
		case commons.CommandRequestTitel.DELETE_KONTAKT:
			c = new DeleteKontaktCommand();
			break;
		case commons.CommandRequestTitel.DELETE_ADRESSE:
			c = new DeleteAdresseCommand();
			break;
		case commons.CommandRequestTitel.DELETE_RECHNUNG:
			c = new DeleteRechnungCommand();
			break;
		case commons.CommandRequestTitel.DELETE_RECHNUNGSZEILE:
			c = new DeleteRechnungszeileCommand();
			break;
		default:
			break;
		}
		
		return c;	
	}
}
