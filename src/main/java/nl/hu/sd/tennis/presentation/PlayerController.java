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
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public List<Player> getAllPlayers(){
        return playerService.findAllPlayers();
    }

    @PostMapping("/new")
    public Player newPlayer(@RequestBody PlayerDTO playerDTO) {
        return playerService.newPlayer(playerDTO);
    }

    @PutMapping("/{id}")
    public Player updatePlayer(@RequestBody PlayerDTO playerDTO, @PathVariable long id) {
        try {
            return playerService.setPlayer(playerDTO.name, id);
        } catch (PlayerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public boolean deletePlayer(@PathVariable long id) {
        try {
            return playerService.deletePlayer(id);
        } catch (PlayerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
