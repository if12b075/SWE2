package at.fh.technikum.wien.koller.krammer.models;

public abstract class AbstractDatabaseObject {

	private long id;
	private int geloescht; // 0 = aktiv, 1 = gelöscht

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGeloescht() {
		return geloescht;
	}

	public void setGeloescht(int geloescht) {
		this.geloescht = geloescht;
	}
	
	

}