package pl.sdacademy.java.hibernate.common.sakila;

import java.util.List;

public class Actor {

    private Long actorId;

    private String firstName;

    private String lastName;

    private List<Film> films;

    public Long getActorId() {
        return actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
