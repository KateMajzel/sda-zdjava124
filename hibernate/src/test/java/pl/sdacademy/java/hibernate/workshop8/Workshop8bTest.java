package pl.sdacademy.java.hibernate.workshop8;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.hibernate.tests.SakilaDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop8bTest {
    @BeforeAll
    static void setUp() throws Exception {
        SakilaDbHelper.INSTANCE.importDatabase();
    }

    @AfterAll
    static void tearDown() {
        SakilaDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldReturnAllStaffDataInExpectedOrder() {
        //WHEN
        final var countries = Workshop8b.findAllCountries(SakilaDbHelper.INSTANCE.getProperties());

        //THEN
        assertThat(countries).hasSize(109);
        assertThat(countries.get(0).getName()).isEqualTo("Afghanistan");
        assertThat(countries.get(0).getCities().get(0).getName()).isEqualTo("Kabul");

        assertThat(countries.get(108).getName()).isEqualTo("Zambia");
        assertThat(countries.get(108).getCities().get(0).getName()).isEqualTo("Kitwe");
    }

}
