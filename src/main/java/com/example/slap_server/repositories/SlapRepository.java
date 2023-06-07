package com.example.slap_server.repositories;

import com.example.slap_server.models.Slap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlapRepository extends JpaRepository <Slap, Long> {
}
