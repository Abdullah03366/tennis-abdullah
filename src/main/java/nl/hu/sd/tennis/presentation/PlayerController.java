package nl.hu.sd.tennis.presentation;

import nl.hu.sd.tennis.application.PlayerService;
import nl.hu.sd.tennis.domain.Player;
import nl.hu.sd.tennis.domain.exception.PlayerNotFoundException;
import nl.hu.sd.tennis.presentation.dto.PlayerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tennis")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers(){
        return playerService.findAllPlayers();
    }

    @PostMapping("/players/new")
    public Player newPlayer(PlayerDTO playerDTO) {
        return playerService.newPlayer(playerDTO);
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(PlayerDTO playerDTO, @PathVariable long playerId) {
        try {
            return playerService.setPlayer(playerDTO, playerId);
        } catch (PlayerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/players/{id}")
    public boolean deletePlayer(@PathVariable long playerId) {
        try {
            return playerService.deletePlayer(playerId);
        } catch (PlayerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
