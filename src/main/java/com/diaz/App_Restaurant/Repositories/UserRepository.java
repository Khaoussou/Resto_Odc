package com.diaz.App_Restaurant.Repositories;

import com.diaz.App_Restaurant.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

}
