package pl.piotrschodzinski.homebudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrschodzinski.homebudget.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
