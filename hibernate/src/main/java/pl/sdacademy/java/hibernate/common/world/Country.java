package pl.sdacademy.java.hibernate.common.world;

public class Country {

    private String code;

    private String name;

    private String continent;

    private int population;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", population=" + population +
                '}';
    }
}
