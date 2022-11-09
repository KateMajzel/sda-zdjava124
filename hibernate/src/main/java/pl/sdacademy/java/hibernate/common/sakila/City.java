package pl.sdacademy.java.hibernate.common.sakila;

import java.util.List;

public class City {

    private Long cityId;

    private String name;

    private Country country;

    private List<Address> addresses;

    public Long getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}
