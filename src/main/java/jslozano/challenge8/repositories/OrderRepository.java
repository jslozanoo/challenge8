package jslozano.challenge8.repositories;

import jslozano.challenge8.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
