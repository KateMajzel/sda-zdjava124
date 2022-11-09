package pl.sdacademy.java.hibernate.common.sakila;

import jakarta.persistence.*;

@Entity
public class Staff {
    @Id
    @Column(name = "staff_id")
    private Long staffId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "address_id")
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
