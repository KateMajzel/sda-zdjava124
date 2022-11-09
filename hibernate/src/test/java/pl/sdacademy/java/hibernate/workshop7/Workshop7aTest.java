package pl.sdacademy.java.hibernate.workshop7;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.hibernate.common.sakila.Film;
import pl.sdacademy.java.hibernate.tests.SakilaDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop7aTest {

    @BeforeAll
    static void setUp() throws Exception {
        SakilaDbHelper.INSTANCE.importDatabase();
    }

    @AfterAll
    static void tearDown() {
        SakilaDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldReturnFilmsWithActorsInExpectedOrder() {
        //WHEN
        final var films = Workshop7a.findFilmsByDescription(
                SakilaDbHelper.INSTANCE.getProperties(),
                "thrilling documentary"
        );

        //THEN
        assertThat(films).isNotNull();
        assertThat(films)
            .extracting(Film::getTitle)
            .containsExactly(
                "BALLROOM MOCKINGBIRD",
                "BIRD INDEPENDENCE",
                "FORREST SONS",
                "PATTON INTERVIEW",
                "SIMON NORTH",
                "SMOOCHY CONTROL",
                "SPY MILE"
            );

        assertThat(films.get(0).getActors())
            .extracting(a -> a.getFirstName() + " " + a.getLastName())
            .containsExactlyInAnyOrder(
                "JUDY DEAN",
                "ADAM GRANT",
                "BEN WILLIS",
                "DARYL WAHLBERG",
                "HARRISON BALE",
                "LUCILLE DEE",
                "GENE MCKELLEN"
            );
    }
}
