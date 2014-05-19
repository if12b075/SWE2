package at.fh.technikum.wien.koller.krammer.dao;

import java.util.List;

import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;

public interface IKontaktDao {
	public enum type { FIRMA, PERSON };
	public type getKontaktType(long id);
	
	public long create();
	public void update(long kontaktId, long wAdresse, long rAdresse, long lAdresse);
	public void delete(long id);
	
	public Kontakt getKontaktById(long id);
	public List<Kontakt> getAlleKontakte();
	public List<Kontakt> getFilterKontakte(KontaktFilter kf);
}
