package pl.sdacademy.java.hibernate.common.sakila;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Language {

    @Id
    @Column(name = "language_id")
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
