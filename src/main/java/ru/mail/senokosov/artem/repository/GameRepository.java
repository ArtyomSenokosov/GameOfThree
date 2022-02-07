package ru.mail.senokosov.artem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mail.senokosov.artem.repository.entity.Game;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByUserNameAndWinnerIsNull(String userName);

    List<Game> findAllByWinnerIsNotNull();
}