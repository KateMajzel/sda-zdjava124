package pl.sdacademy.java.hibernate.workshop1;

import org.junit.jupiter.api.*;
import pl.sdacademy.java.hibernate.common.world.Country;
import pl.sdacademy.java.hibernate.tests.WorldDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop1Test {

    @BeforeAll
    static void setUp() throws Exception {
        WorldDbHelper.INSTANCE.importDatabase();
    }

    @AfterAll
    static void tearDown() {
        WorldDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldReturnPoland() {
        //WHEN
        final Country country = Workshop1.loadCountryByCode(WorldDbHelper.INSTANCE.getProperties(), "POL");

        //THEN
        assertThat(country).isNotNull();
        assertThat(country.getCode()).isEqualTo("POL");
        assertThat(country.getName()).isEqualTo("Poland");
        assertThat(country.getContinent()).isEqualTo("Europe");
    }
}
