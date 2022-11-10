package pl.sdacademy.java.hibernate.workshop10;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.hibernate.tests.SakilaDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop10Test {

    @BeforeEach
    void setUp() throws Exception {
        SakilaDbHelper.INSTANCE.importDatabase();
    }

    @AfterEach
    void tearDown() {
        SakilaDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldCreateCountry() {
        //WHEN
        final var country = Workshop10.createCountry(SakilaDbHelper.INSTANCE.getProperties(), "Atlantis");

        //THEN
        assertThat(country.getName()).isEqualTo("Atlantis");
        assertThat(country.getCountryId()).isEqualTo(110);
    }
}
