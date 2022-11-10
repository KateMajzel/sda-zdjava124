package pl.sdacademy.java.hibernate.common.sakila;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Country {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;

    @Column(name = "country")
    private String name;

    @OneToMany(mappedBy = "country")
    private List<City> cities;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }
}
