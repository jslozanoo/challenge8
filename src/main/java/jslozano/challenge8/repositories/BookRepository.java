package jslozano.challenge8.repositories;

import jslozano.challenge8.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
