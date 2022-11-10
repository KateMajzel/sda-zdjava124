package pl.sdacademy.java.hibernate.workshop9;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;

public class RatingConverter implements AttributeConverter<Rating,String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating.getCode();
    }

    @Override
    public Rating convertToEntityAttribute(String code) {
        return Arrays.stream(Rating.values())
            .filter(r -> r.getCode().equals(code))
            .findAny()
            .orElseThrow();
    }
}
