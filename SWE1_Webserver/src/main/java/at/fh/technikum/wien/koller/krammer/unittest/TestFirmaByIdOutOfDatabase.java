package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IFirmaDao;
import at.fh.technikum.wien.koller.krammer.models.Firma;

public class TestFirmaByIdOutOfDatabase {

	@Test
	public void testGetFirmaByIdName() {
		Firma f = new Firma();
		f.setName("Firma 1 GmbH");
	
		IFirmaDao fd = DaoFactory.createFirmaDao();
		
		assertEquals("Firma: Name aus DB prüfen", f.getName(), fd.getFirmaById(1).getName());
	}
	
	@Test
	public void testGetFirmaByIdUid() {
		Firma f = new Firma();
		f.setUid("ATU123456");
	
		IFirmaDao fd = DaoFactory.createFirmaDao();
		
		assertEquals("Firma: UID aus DB prüfen", f.getUid(), fd.getFirmaById(1).getUid());
	}

}
