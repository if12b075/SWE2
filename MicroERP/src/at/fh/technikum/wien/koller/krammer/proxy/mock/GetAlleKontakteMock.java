package at.fh.technikum.wien.koller.krammer.proxy.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fh.technikum.wien.koller.krammer.models.Adresse;
import at.fh.technikum.wien.koller.krammer.models.AdresseEnums;
import at.fh.technikum.wien.koller.krammer.models.Firma;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.models.Person;

public class GetAlleKontakteMock {
	private static ArrayList<Kontakt> al = new ArrayList<Kontakt>();
	
	public GetAlleKontakteMock() {
		al.add(new Person("klaus","kleber",new Date(),1,new Adresse(AdresseEnums.WOHNADRESSE,"tri tra weg 1", "stock 5 top 7",1010,"Wien",1),1));
		al.add(new Firma("kaspale gmbh","AT02342345",new Adresse(AdresseEnums.WOHNADRESSE,"tri tra weg 1", "stock 5 top 7",1010,"Wien",1),2));
	}
	
	public static List<Kontakt> getAlleKontaktMock(){
		return al;
	}
}
