package ru.mail.senokosov.artem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mail.senokosov.artem.repository.entity.PlayerType;

public interface PlayerTypeRepository extends JpaRepository<PlayerType, Long> {

    PlayerType findByName(String name);
}