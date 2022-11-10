package pl.sdacademy.java.hibernate.workshop16;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.hibernate.tests.SakilaDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop16Test {
    @BeforeEach
    void setUp() throws Exception {
        SakilaDbHelper.INSTANCE.importDatabase();
    }

    @AfterEach
    void tearDown() {
        SakilaDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldRenameExistingCountry() {
        //WHEN
        final var affectedRows = Workshop16.renameCountry(SakilaDbHelper.INSTANCE.getProperties(), 20, "Narnia");

        //THEN
        assertThat(affectedRows).isEqualTo(1);

        final var newCanadaName = SakilaDbHelper.INSTANCE
            .query("SELECT country FROM country WHERE country.country_id = 20;", String.class)
            .orElseThrow();

        assertThat(newCanadaName).isEqualTo("Narnia");
    }

    @Test
    void shouldNotRenameNonExistingCountry() {
        //WHEN
        final var affectedRows = Workshop16.renameCountry(SakilaDbHelper.INSTANCE.getProperties(), 999, "Narnia");

        //THEN
        assertThat(affectedRows).isEqualTo(0);
    }
}
