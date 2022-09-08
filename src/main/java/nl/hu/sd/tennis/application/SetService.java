package nl.hu.sd.tennis.application;

import nl.hu.sd.tennis.data.SetRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class SetService {
    private final SetRepository setRepository;

    public SetService(SetRepository setRepository) {
        this.setRepository = setRepository;
    }


}
