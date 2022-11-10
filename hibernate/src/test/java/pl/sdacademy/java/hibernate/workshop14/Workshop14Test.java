package pl.sdacademy.java.hibernate.workshop14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.hibernate.tests.SakilaDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop14Test {
    @BeforeEach
    void setUp() throws Exception {
        SakilaDbHelper.INSTANCE.importDatabase();
    }

    @AfterEach
    void tearDown() {
        SakilaDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldCreateCity() {
        //WHEN
        final var city = Workshop14.createCity(SakilaDbHelper.INSTANCE.getProperties(), "Ankh-Morpork", 20);

        //THEN
        assertThat(city.getName()).isEqualTo("Ankh-Morpork");
        assertThat(city.getCityId()).isEqualTo(601);

        final var countryId = SakilaDbHelper.INSTANCE
            .query("SELECT country_id FROM city WHERE city.city_id = 601;", Integer.class)
            .orElseThrow();

        assertThat(countryId).isEqualTo(20);
    }
}
