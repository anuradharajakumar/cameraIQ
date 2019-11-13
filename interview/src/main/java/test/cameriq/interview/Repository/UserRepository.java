package test.cameriq.interview.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.cameriq.interview.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
