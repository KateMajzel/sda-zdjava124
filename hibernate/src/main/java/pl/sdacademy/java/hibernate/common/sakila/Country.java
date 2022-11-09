package pl.sdacademy.java.hibernate.common.sakila;

import java.util.List;

public class Country {

    private Long countryId;

    private String name;

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
