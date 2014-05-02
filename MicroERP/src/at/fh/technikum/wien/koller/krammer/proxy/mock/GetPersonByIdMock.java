package at.fh.technikum.wien.koller.krammer.proxy.mock;

import java.util.Date;

import at.fh.technikum.wien.koller.krammer.models.Adresse;
import at.fh.technikum.wien.koller.krammer.models.AdresseEnums;
import at.fh.technikum.wien.koller.krammer.models.Person;

public class GetPersonByIdMock {
	private static Person p = new Person("klaus","kleber",new Date(),1,new Adresse(AdresseEnums.WOHNADRESSE,"tri tra weg 1", "stock 5 top 7",1010,"Wien",1),1);
	
	public static Person getPersonById(long id) {
		return p;
	}
}
