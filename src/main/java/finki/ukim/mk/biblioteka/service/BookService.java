package finki.ukim.mk.biblioteka.service;

import finki.ukim.mk.biblioteka.model.Book;
import finki.ukim.mk.biblioteka.model.Category;
import finki.ukim.mk.biblioteka.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    List<Book> findAll();

    Optional<Book> save(String name, Category category, Long author, Integer availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, Category category, Long author, Integer availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    void markAsTaken(Long id);


}
