package jslozano.challenge8.repositories;

import jslozano.challenge8.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
