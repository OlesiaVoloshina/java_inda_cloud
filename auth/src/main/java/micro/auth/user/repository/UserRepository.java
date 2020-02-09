package micro.auth.user.repository;

import micro.auth.user.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByLogin(String login);
}
