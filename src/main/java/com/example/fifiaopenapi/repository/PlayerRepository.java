package com.example.fifiaopenapi.repository;


import com.example.fifiaopenapi.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
