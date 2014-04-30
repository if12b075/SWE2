package at.fh.technikum.wien.koller.krammer.models;

public abstract class AbstractDatabaseObject {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}