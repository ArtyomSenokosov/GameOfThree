package ru.mail.senokosov.artem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mail.senokosov.artem.repository.entity.GameStatus;

public interface GameStatusRepository extends JpaRepository<GameStatus, Long> {

    GameStatus findByName(String name);
}