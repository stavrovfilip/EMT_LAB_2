package finki.ukim.mk.biblioteka.service.impl;

import finki.ukim.mk.biblioteka.model.Author;
import finki.ukim.mk.biblioteka.model.Book;
import finki.ukim.mk.biblioteka.model.Category;
import finki.ukim.mk.biblioteka.model.dto.BookDto;
import finki.ukim.mk.biblioteka.model.exceptions.AuthorNotFoundException;
import finki.ukim.mk.biblioteka.model.exceptions.BookNotFoundException;
import finki.ukim.mk.biblioteka.repository.AuthorRepository;
import finki.ukim.mk.biblioteka.repository.BookRepository;
import finki.ukim.mk.biblioteka.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Book> save(String name, Category category, Long author, Integer availableCopies) {
        Author author1 = this.authorRepository.findById(author).
                orElseThrow(() ->  new AuthorNotFoundException(author));

        this.bookRepository.deleteByName(name);
        Book book = new Book(name, category, author1, availableCopies);
        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        this.bookRepository.deleteByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Transactional
    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long author, Integer availableCopies) {

        Book book = this.bookRepository.findById(id).
                orElseThrow(() -> new BookNotFoundException(id));
        Author author1 = this.authorRepository.findById(author).
                orElseThrow(() ->  new AuthorNotFoundException(author));

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author1);
        book.setAvailableCopies(availableCopies);

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void markAsTaken(Long id) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        book.setAvailableCopies(book.getAvailableCopies()-1);
        this.bookRepository.save(book);

        if(book.getAvailableCopies()<=0){
            this.deleteById(id);
        }
    }
}
