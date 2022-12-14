package nl.hu.sd.tennis.application;

import nl.hu.sd.tennis.data.PlayerRepository;
import nl.hu.sd.tennis.domain.Player;
import nl.hu.sd.tennis.domain.exception.PlayerNotFoundException;
import nl.hu.sd.tennis.presentation.dto.PlayerDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player newPlayer(PlayerDTO playerDTO) {
        Player player = new Player(playerDTO.name);
        return this.playerRepository.save(player);
    }

    public Player setPlayer(String playerName, long id) throws PlayerNotFoundException {
        Player player = this.playerRepository.findByPlayerId(id).orElseThrow(PlayerNotFoundException::new);
        player.setName(playerName);
        return player;
    }

    public boolean deletePlayer(long id) throws PlayerNotFoundException {
        Player player = this.playerRepository.findByPlayerId(id).orElseThrow(PlayerNotFoundException::new);
        this.playerRepository.delete(player);
        return true;
    }

    public Player findPlayerById(long id) throws PlayerNotFoundException {
        return this.playerRepository.findByPlayerId(id).orElseThrow(PlayerNotFoundException::new);
    }

    public List<Player> findAllPlayers() {
        return this.playerRepository.findAll();
    }
}
