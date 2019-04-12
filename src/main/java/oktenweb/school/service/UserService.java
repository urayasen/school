package oktenweb.school.service;


import oktenweb.school.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(User user);

    User byId(Integer id);

    User findAll();
    User findByUsername(String username);
}
