# Warsztat 6a – filmy i aktorzy
* Załaduj bazę [Sakila](https://dev.mysql.com/doc/sakila/en/sakila-structure.html) i skonfiguruj jednostkę utrwalania
* Zamodeluj następujące klasy encyjne:
  * `Language`: `languageId`, `name`
  * `Film`: `filmId`, `title`, `description`, `language` (`Language`), `originalLanguage` (`Language`), `actors` (`List<Actor>`)
  * `Actor`: `actorId`, `firstName`, `lastName`, `films` (`List<Film>`)
* Potrzebne adnotacje: `@Column`, `@Entity`, `@Id`, `@JoinColumn`, `@JoinTable`, `@ManyToMany`, `@Table`
* Wesprzyj się [dokumentacją](https://javaee.github.io/javaee-spec/javadocs/javax/persistence/ManyToMany.html)
