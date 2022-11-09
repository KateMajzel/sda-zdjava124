package pl.sdacademy.java.hibernate.tests;

public class SakilaDbHelper extends AbstractTestDbHelper {

    private static final String H2_DB_NAME="sakila-hibernate-test";
    private static final String H2_DB_FILE="/sakila-h2.sql";
    public static final SakilaDbHelper INSTANCE = new SakilaDbHelper();

    private SakilaDbHelper() {
        super(H2_DB_NAME, H2_DB_FILE);
    }
}
