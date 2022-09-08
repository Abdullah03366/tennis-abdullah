package nl.hu.sd.tennis.application;

import nl.hu.sd.tennis.data.SetRepository;
import nl.hu.sd.tennis.domain.Player;
import nl.hu.sd.tennis.domain.Set;
import nl.hu.sd.tennis.domain.exception.PlayerNotFoundException;
import nl.hu.sd.tennis.domain.exception.SetAlreadyEndedException;
import nl.hu.sd.tennis.domain.exception.SetNotFoundException;
import nl.hu.sd.tennis.presentation.dto.SetDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SetService {
    private final SetRepository setRepository;
    private final PlayerService playerService;

    public SetService(SetRepository setRepository, PlayerService playerService) {
        this.setRepository = setRepository;
        this.playerService = playerService;
    }

    public Set newSet(SetDTO setDTO) throws PlayerNotFoundException {
        Player player1 = playerService.findPlayerById(setDTO.player1Id);
        Player player2 = playerService.findPlayerById(setDTO.player2Id);

        Set set = new Set(player1, player2);
        return this.setRepository.save(set);
    }

    public Set addPointToPlayer(long setId, long playerId) throws SetNotFoundException, PlayerNotFoundException, SetAlreadyEndedException {
        Player player = playerService.findPlayerById(playerId);
        Set set = setRepository.findBySetId(setId).orElseThrow(SetNotFoundException::new);
        set.addPoint(player);
        return this.setRepository.save(set);
    }

    public List<Set> findAllSets() {
        return setRepository.findAll();
    }

}
