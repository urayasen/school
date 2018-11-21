package oktenweb.school.dao;

import oktenweb.school.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("userRepository")
public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}



