package pl.sdacademy.java.hibernate.common.world;

public class City {

    private Integer id;

    private String name;

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
