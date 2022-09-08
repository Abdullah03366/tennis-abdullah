package nl.hu.sd.tennis.data;

import nl.hu.sd.tennis.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByPlayerId(long id);
}
