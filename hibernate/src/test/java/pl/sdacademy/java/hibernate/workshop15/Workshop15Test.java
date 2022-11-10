package pl.sdacademy.java.hibernate.workshop15;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.hibernate.tests.SakilaDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop15Test {
    @BeforeEach
    void setUp() throws Exception {
        SakilaDbHelper.INSTANCE.importDatabase();
    }

    @AfterEach
    void tearDown() {
        SakilaDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldCreateCityAndCountry() {
        //WHEN
        final var city = Workshop15.createCity(SakilaDbHelper.INSTANCE.getProperties(), "Narnia", "Ankh-Morpork");

        //THEN
        assertThat(city.getName()).isEqualTo("Ankh-Morpork");
        assertThat(city.getCityId()).isEqualTo(601);
        assertThat(city.getCountry().getName()).isEqualTo("Narnia");
        assertThat(city.getCountry().getCountryId()).isEqualTo(110);
    }
}
