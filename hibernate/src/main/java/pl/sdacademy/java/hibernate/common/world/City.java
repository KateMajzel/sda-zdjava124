package pl.sdacademy.java.hibernate.common.world;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class City {

    @Id
    private Integer id;

    private String name;

    @JoinColumn(name = "CountryCode")
    @ManyToOne
    private Country country;

    private String district;

    private int population;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }
}
