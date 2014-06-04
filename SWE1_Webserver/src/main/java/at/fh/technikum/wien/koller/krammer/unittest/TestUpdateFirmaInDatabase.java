package at.fh.technikum.wien.koller.krammer.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fh.technikum.wien.koller.krammer.dao.DaoFactory;
import at.fh.technikum.wien.koller.krammer.dao.IFirmaDao;
import at.fh.technikum.wien.koller.krammer.models.Firma;

public class TestUpdateFirmaInDatabase {

	@Test
	public void testUpdateFirmaName() {
		IFirmaDao fd = DaoFactory.createFirmaDao();
		Firma fOriginal = fd.getFirmaById(1);
		Firma fUpdate = fd.getFirmaById(1);
		
		fUpdate.setName("Test");
		// update testen
		fd.update(fUpdate);
		
		assertEquals("Firma: Name ge�ndert in DB", fUpdate.getName(), fd.getFirmaById(1).getName());
		
		// Daten auf Originalzustand zur�cksetzen
		fd.update(fOriginal);
		
		assertEquals("Firma: Name zur�ckge�ndert in DB", fOriginal.getName(), fd.getFirmaById(1).getName());
	}

	@Test
	public void testUpdateFirmaUid() {
		IFirmaDao fd = DaoFactory.createFirmaDao();
		Firma fOriginal = fd.getFirmaById(1);
		Firma fUpdate = fd.getFirmaById(1);
		
		fUpdate.setUid("Test");
		// update testen
		fd.update(fUpdate);
		
		assertEquals("Firma: UID ge�ndert in DB", fUpdate.getUid(), fd.getFirmaById(1).getUid());
		
		// Daten auf Originalzustand zur�cksetzen
		fd.update(fOriginal);
		
		assertEquals("Firma: UID zur�ckge�ndert in DB", fOriginal.getUid(), fd.getFirmaById(1).getUid());
	}

}
