package pl.sdacademy.java.hibernate.common.sakila;

import jakarta.persistence.*;
import pl.sdacademy.java.hibernate.workshop9.Rating;
import pl.sdacademy.java.hibernate.workshop9.RatingConverter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Film {

    @Id
    @Column(name = "film_id")
    private Long filmId;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;


    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public Long getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(Language originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
