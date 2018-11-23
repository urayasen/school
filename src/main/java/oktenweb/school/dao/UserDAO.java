package oktenweb.school.dao;

import com.sun.org.apache.xpath.internal.operations.String;
import oktenweb.school.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query("select c from User c where c.id = :xxx")
    User byId(@Param("xxx") Integer id);

}



