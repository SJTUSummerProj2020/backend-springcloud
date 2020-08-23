package se128.jupiter.ssoserver.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import se128.jupiter.ssoserver.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query(value = "from UserEntity where username = :username and password = :password")
    UserEntity getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
