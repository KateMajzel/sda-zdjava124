# Warsztat 6b – adresy i personel
* Załaduj bazę [Sakila](https://dev.mysql.com/doc/sakila/en/sakila-structure.html) i skonfiguruj jednostkę utrwalania
* Zamodeluj następujące klasy encyjne:
    * `Country`: `countryId`, `name`, `cities` (`List<City>`)
    * `City`: `cityId`, `name`, `country` (`Country`), `addresses` (`List<Address>`)
    * `Address`: `addressId`, `address`, `city` (`City`)
    * `Staff`: `staffId`, `firstName`, `lastName`, `address` (`Address`)
* Potrzebne adnotacje: `@Column`, `@Entity`, `@Id`, `@JoinColumn`, `@ManyToOne`, `@OneToMany`, `@Table`
* Wesprzyj się [dokumentacją](https://javaee.github.io/javaee-spec/javadocs/javax/persistence/OneToMany.html)
