package finki.ukim.mk.biblioteka.repository;

import finki.ukim.mk.biblioteka.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
