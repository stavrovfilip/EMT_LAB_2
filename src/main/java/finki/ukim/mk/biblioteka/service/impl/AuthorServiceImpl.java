package finki.ukim.mk.biblioteka.service.impl;

import finki.ukim.mk.biblioteka.model.Author;
import finki.ukim.mk.biblioteka.model.Country;
import finki.ukim.mk.biblioteka.model.exceptions.CountryNotFoundException;
import finki.ukim.mk.biblioteka.repository.AuthorRepository;
import finki.ukim.mk.biblioteka.repository.CountryRepository;
import finki.ukim.mk.biblioteka.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(String name, String surname, Long country) {
        Country country1 = this.countryRepository.findById(country)
                .orElseThrow(() -> new CountryNotFoundException(country));

        this.authorRepository.deleteByName(name);
        Author author = new Author(name, surname, country1);
        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
