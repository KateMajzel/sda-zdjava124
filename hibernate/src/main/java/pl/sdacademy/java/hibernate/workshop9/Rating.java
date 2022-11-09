package pl.sdacademy.java.hibernate.workshop9;

public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17"),
    ;

    private final String code;

    Rating(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
