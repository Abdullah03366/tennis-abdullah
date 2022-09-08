package nl.hu.sd.tennis.presentation;

import nl.hu.sd.tennis.application.SetService;
import nl.hu.sd.tennis.domain.Set;
import nl.hu.sd.tennis.domain.exception.PlayerNotFoundException;
import nl.hu.sd.tennis.domain.exception.SetAlreadyEndedException;
import nl.hu.sd.tennis.domain.exception.SetNotFoundException;
import nl.hu.sd.tennis.presentation.dto.PlayerIdDTO;
import nl.hu.sd.tennis.presentation.dto.SetDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sets")
public class SetController {
    private final SetService setService;

    public SetController(SetService setService) {
        this.setService = setService;
    }

    @PostMapping("/new")
    public Set newSet(@RequestBody SetDTO setDTO) {
        try {
            return setService.newSet(setDTO.player1Id);
        } catch (PlayerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Set addPointToPlayer(@RequestBody PlayerIdDTO playerIdDTO, @PathVariable long id) {
        try {
            return setService.addPointToPlayer(id, playerIdDTO.playerId);
        } catch (SetNotFoundException | PlayerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (SetAlreadyEndedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @GetMapping()
    public List<Set> getAllSets() {
        return setService.findAllSets();
    }
}
