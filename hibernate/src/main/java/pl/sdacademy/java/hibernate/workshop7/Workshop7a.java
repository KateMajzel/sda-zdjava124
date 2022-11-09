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
    Wiedząc, że w JPQL dostępne są:
        * Instrukcja LIKE
        * Funkcja upper(...) zmieniająca wielkość znaków na wielkie litery
        * Funkcja concat(…) łącząca teksty (np. concat('%', :param, '%'))

    Zaimplementuj wyszukiwanie filmów według fragmentu opisu.

    Co się stanie w momencie pobrania informacji o aktorach?

    Czy @ManyToMany(fetch=FetchType.EAGER) poprawi sytuację?

    Czy JOIN FETCH poprawi sytuację?

    Jakie inne rozwiązania są możliwe?
 */
public class Workshop7a {
    public static void main(String[] args) {
        System.out.println("Podaj fragment opisu:");
        final String descriptionPart = new Scanner(System.in).nextLine();

        final List<Film> films = findFilmsByDescription(ApplicationPropertiesProvider.getSakilaProperties(), descriptionPart);
        final StringBuilder sb = new StringBuilder();
        for (Film film : films) {
            sb.append(film.getTitle()).append(" (").append(film.getDescription()).append(")").append("\n");
            for (Actor actor : film.getActors()) {
                sb.append("\t").append(actor.getFirstName()).append(" ").append(actor.getLastName()).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static List<Film> findFilmsByDescription(Properties properties, String descriptionPart) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SakilaPU", properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Film> query = entityManager.createQuery("""
                    SELECT film FROM Film film
                    JOIN FETCH film.actors
                    JOIN FETCH film.language
                    LEFT JOIN FETCH film.originalLanguage
                    WHERE UPPER(film.description) LIKE concat('%',UPPER(:description),'%') ORDER BY film.title
                     """, Film.class);
            query.setParameter("description", descriptionPart);

            List<Film> films = query.getResultList();
            return films;

        } finally {
            entityManagerFactory.close();
        }
    }
}
