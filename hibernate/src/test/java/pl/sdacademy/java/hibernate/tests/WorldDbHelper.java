package pl.sdacademy.java.hibernate.tests;

public class WorldDbHelper extends AbstractTestDbHelper {
    private static final String H2_DB_NAME="world-hibernate-test";
    private static final String H2_DB_FILE="/world-h2.sql";
    public static final WorldDbHelper INSTANCE = new WorldDbHelper();

    private WorldDbHelper() {
        super(H2_DB_NAME, H2_DB_FILE);
    }
}
