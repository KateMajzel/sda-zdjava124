package pl.sdacademy.java.hibernate.common.sakila;

public class Language {

    private Long languageId;

    private String name;


    public Long getLanguageId() {
        return languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
