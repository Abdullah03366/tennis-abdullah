package nl.hu.sd.tennis.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long playerId;
    private String name;

    public Player() {}

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
