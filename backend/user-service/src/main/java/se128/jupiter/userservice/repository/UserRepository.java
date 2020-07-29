package se128.jupiter.userservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se128.jupiter.userservice.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "from User where username = :username and password = :password")
    User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User getUserByUserId(Integer userId);

    User getUserByUsername(String username);
}
