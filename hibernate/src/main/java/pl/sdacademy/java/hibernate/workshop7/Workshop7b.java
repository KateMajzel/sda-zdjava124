package pl.sdacademy.java.hibernate.workshop7;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import pl.sdacademy.java.hibernate.common.sakila.Actor;
import pl.sdacademy.java.hibernate.common.sakila.Film;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/*
    Zmodyfikuj funkcję i zapytanie JPQL z poprzedniego zadania tak, by wyszukiwać aktorów według opisów filmów w których grali.

    Posortuj wyniki według: Nazwiska, Imienia, Tytułu filmu

    Zwróć uwagę na to, że kolekcja filmów może nie być kompletna.

    🤔 * Spróbuj samodzielnie: załaduj wszystkie filmy znalezionych aktorów
 */
public class Workshop7b {
    public static void main(String[] args) {
        System.out.println("Podaj fragment opisu:");
        final String descriptionPart = new Scanner(System.in).nextLine();

        final List<Actor> actors = findActorsByFilmDescription(ApplicationPropertiesProvider.getSakilaProperties(), descriptionPart);
        final StringBuilder sb = new StringBuilder();
        for (Actor actor : actors) {
            sb.append(actor.getFirstName()).append(" ").append(actor.getLastName()).append("\n");
            for (Film film : actor.getFilms()) {
                sb.append("\t").append(film.getTitle()).append(" (").append(film.getDescription()).append(")").append("\n");
            }
        }

        System.out.println(sb);
    }

    public static List<Actor> findActorsByFilmDescription(Properties properties, String descriptionPart) {
        throw new UnsupportedOperationException("TODO");
    }
}
