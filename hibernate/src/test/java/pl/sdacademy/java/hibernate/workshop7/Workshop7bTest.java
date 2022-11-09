package pl.sdacademy.java.hibernate.workshop7;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.hibernate.common.sakila.Film;
import pl.sdacademy.java.hibernate.tests.SakilaDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop7bTest {
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
        final var actors = Workshop7b.findActorsByFilmDescription(
            SakilaDbHelper.INSTANCE.getProperties(),
            "thrilling documentary"
        );

        //THEN
        assertThat(actors).isNotNull();
        assertThat(actors)
            .extracting(a -> a.getFirstName() + " " + a.getLastName())
            .containsExactly(
                "HARRISON BALE",
                "VIVIEN BASINGER",
                "HENRY BERRY",
                "MICHAEL BOLGER",
                "JOHNNY CAGE",
                "ED CHASE",
                "RIP CRAWFORD",
                "SIDNEY CROWE",
                "JUDY DEAN",
                "LUCILLE DEE",
                "JODIE DEGENERES",
                "ADAM GRANT",
                "GEOFFREY HESTON",
                "WOODY HOFFMAN",
                "WHOOPI HURT",
                "MARY KEITEL",
                "FAY KILMER",
                "JOHNNY LOLLOBRIGIDA",
                "GRETA MALDEN",
                "GENE MCKELLEN",
                "CATE MCQUEEN",
                "TOM MIRANDA",
                "JIM MOSTEL",
                "JAYNE NOLTE",
                "ELLEN PRESLEY",
                "JAYNE SILVERSTONE",
                "WALTER TORN",
                "DARYL WAHLBERG",
                "SEAN WILLIAMS",
                "BEN WILLIS",
                "RIP WINSLET"
            );

        assertThat(actors.get(27).getFilms()) //DARYL WAHLBERG
            .extracting(Film::getTitle)
            .containsExactlyInAnyOrder(
                "BALLROOM MOCKINGBIRD", "FORREST SONS", "SMOOCHY CONTROL"
            );
    }
}
