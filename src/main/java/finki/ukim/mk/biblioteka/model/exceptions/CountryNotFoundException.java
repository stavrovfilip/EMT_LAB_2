package finki.ukim.mk.biblioteka.model.exceptions;

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(Long id) {
        super(String.format("Country with id %d was not found", id));
    }
}
