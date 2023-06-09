package com.example.slap_server.repositories;

import com.example.slap_server.models.Slap;
import com.example.slap_server.models.SlapDTO;
import com.example.slap_server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SlapRepository extends JpaRepository <Slap, Long> {

//    List<SlapDTO> findSlapByUserId(Long user_id);
    List<Slap> findByUser(User user);
}
