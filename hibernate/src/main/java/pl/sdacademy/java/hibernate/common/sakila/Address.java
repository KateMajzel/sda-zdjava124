package pl.sdacademy.java.hibernate.common.sakila;

public class Address {

    private Long addressId;

    private String address;

    private City city;

    public Long getAddressId() {
        return addressId;
    }

    public String getAddress() {
        return address;
    }

    public City getCity() {
        return city;
    }
}
