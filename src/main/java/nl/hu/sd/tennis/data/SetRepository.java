package nl.hu.sd.tennis.data;

import nl.hu.sd.tennis.domain.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SetRepository extends JpaRepository<Set, Long> {
    Optional<Set> findBySetId(long id);
}
