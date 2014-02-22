package models.connection;


public enum ConnectionState {

	UNINIALIZED, CREATED, VERIFIED ;

    public String getName() {
        return name();
    }
}

