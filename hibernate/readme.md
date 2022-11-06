# Projekt ćwiczeniowy dla bloku Hibernate

## Warsztaty 1-5 (baza danych World)

Aby skonfigurować połączenie z bazą danych, utwórz plik `src/main/resources/world.properties` o treści:

```properties
jakarta.persistence.jdbc.url=jdbc:mysql://root@localhost:6306/world
jakarta.persistence.jdbc.username=root
jakarta.persistence.jdbc.password=example
```

Pamiętaj o ustawieniu poprawnej nazwy użytkownika (jeśli jest inny niż `root`), portu (jeśli jest inny niż `6306`;
domyślny dla MySQL port to `3306`) oraz hasła.
