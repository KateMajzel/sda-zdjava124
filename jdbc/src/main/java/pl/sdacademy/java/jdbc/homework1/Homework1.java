package pl.sdacademy.java.jdbc.homework1;

import pl.sdacademy.java.jdbc.model.Actor;
import pl.sdacademy.java.jdbc.utils.ApplicationPropertiesProvider;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Homework1 {

    public static void main(String[] args) {
        final String jdbcUrl = ApplicationPropertiesProvider.getApplicationProperties().getProperty("jdbc.url");

        System.out.println("Podaj frazę wyszukiwania:");
        final var query = new Scanner(System.in).nextLine();

        final var actorStrings = getActors(jdbcUrl, query).stream()
                .map(Actor::toString)
                .collect(Collectors.toList());

        System.out.printf("Aktorzy pasujący do frazy '%s':\n\n%s", query, String.join("\n", actorStrings));
    }

    public static List<Actor> getActors(String jdbcUrl, String query) {
        throw new UnsupportedOperationException("TODO");
    }
}
