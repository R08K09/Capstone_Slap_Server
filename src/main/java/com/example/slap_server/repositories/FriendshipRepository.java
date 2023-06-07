package com.example.slap_server.repositories;

import com.example.slap_server.models.Friendship;
import com.example.slap_server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    // gets 2 user and returns true if that friendship record exists
    boolean existsByFirstUserAndSecondUser(User first, User second);

    // will return a list of friendships which has the user as the first user in table
    List<Friendship> findByFirstUser(User user);

    // will return a list of friendships which has the user as the second user in table
    List<Friendship> findBySecondUser(User user);

}
