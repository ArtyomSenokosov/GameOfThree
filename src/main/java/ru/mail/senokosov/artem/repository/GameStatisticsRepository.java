package ru.mail.senokosov.artem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mail.senokosov.artem.repository.entity.Game;
import ru.mail.senokosov.artem.repository.entity.GameStatistics;

import java.util.List;

@Repository
public interface GameStatisticsRepository extends JpaRepository<GameStatistics, Long> {

    List<GameStatistics> findByGame(Game game);
}