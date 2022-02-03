package uk.co.mettle.backendtest.dao;

import org.springframework.data.repository.CrudRepository;
import uk.co.mettle.backendtest.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
