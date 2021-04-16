package finki.ukim.mk.biblioteka.service;

import finki.ukim.mk.biblioteka.model.Author;
import finki.ukim.mk.biblioteka.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);

    List<Author> findAll();

    Optional<Author> save(String name, String surname, Long country);

    void deleteById(Long id);
}
