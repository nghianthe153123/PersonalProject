package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.name = :name AND u.idProvider = :idProvider AND u.loginSource = 'facebook'")
    Optional<User> findByNameAndIdProviderAndLoginSource(@Param("name") String name, @Param("idProvider") String idProvider);


}
