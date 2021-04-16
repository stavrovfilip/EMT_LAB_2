package finki.ukim.mk.biblioteka.repository;

import finki.ukim.mk.biblioteka.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    void deleteByName(String name);
}
