package finki.ukim.mk.biblioteka.model.dto;

import finki.ukim.mk.biblioteka.model.Author;
import finki.ukim.mk.biblioteka.model.Category;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class BookDto {

    private String name;

    private Category category;

    private Long author;

    private Integer availableCopies;

    public BookDto(){}

    public BookDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
