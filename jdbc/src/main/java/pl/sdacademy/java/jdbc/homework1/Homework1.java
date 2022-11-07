package pl.sdacademy.java.jdbc.homework1;

import pl.sdacademy.java.jdbc.model.Actor;
import pl.sdacademy.java.jdbc.utils.ApplicationPropertiesProvider;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Homework1 {

    public static void main(String[] args) {
        final String jdbcUrl = ApplicationPropertiesProvider.getApplicationProperties().getProperty("jdbc.url");

        System.out.println("Podaj frazę wyszukiwania:");
        final var query = new Scanner(System.in).nextLine();

        final var actorStrings = getActors(jdbcUrl, query)
                .stream()
                .map(Actor::toString)
                .collect(Collectors.toList());

        System.out.printf("Aktorzy pasujący do frazy '%s':\n\n%s", query, String.join("\n", actorStrings));
    }


    public static List<Actor> getActors(String jdbcUrl, String query) {

        if (query == null || query.isBlank() || query.length() < 3) {
            return Collections.emptyList();
        }

        final List<Actor> actors = new LinkedList<>();
        try (final Connection connection = DriverManager.getConnection(jdbcUrl)) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT actor.first_name, actor.last_name FROM film_actor JOIN actor ON actor.actor_id = film_actor.actor_id JOIN film  ON film.film_id = film_actor.film_id WHERE upper(actor.first_name) LIKE upper(?) OR upper(actor.last_name) LIKE upper(?) OR upper(film.title) LIKE upper(?) ORDER BY actor.last_name, actor.first_name");
            preparedStatement.setString(1, "%" + query + "%");
            preparedStatement.setString(2, "%" + query + "%");
            preparedStatement.setString(3, "%" + query + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Actor actor = new Actor (resultSet.getString(1), resultSet.getString(2));
                actors.add(actor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actors;
    }
}

