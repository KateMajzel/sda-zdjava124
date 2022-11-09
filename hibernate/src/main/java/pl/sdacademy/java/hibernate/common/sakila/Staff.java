package pl.sdacademy.java.hibernate.common.sakila;

public class Staff {

    private Long staffId;

    private String firstName;

    private String lastName;

    private Address address;

    public Long getStaffId() {
        return staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }
}
