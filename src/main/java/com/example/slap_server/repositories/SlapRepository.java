package com.example.slap_server.repositories;

import com.example.slap_server.models.Slap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SlapRepository extends JpaRepository <Slap, Long> {

    List<Slap> findSlapByUserId(Long user_id);

}
