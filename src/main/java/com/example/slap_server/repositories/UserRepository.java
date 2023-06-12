package com.example.slap_server.repositories;

import com.example.slap_server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User findByName(String username);

    User findByEmailAndPassword(String email, String password);
}
