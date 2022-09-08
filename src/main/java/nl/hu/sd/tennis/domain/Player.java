package nl.hu.sd.tennis.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "players")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long playerId;
    private String name;

    public Player() {}

    public Player(String name) {
        this.name = name;
    }

    public long getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
