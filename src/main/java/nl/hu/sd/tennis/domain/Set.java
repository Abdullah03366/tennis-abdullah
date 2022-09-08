package nl.hu.sd.tennis.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity(name = "sets")
public class Set implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long setId;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Game> games;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Player player1;
    private int scorePlayer1;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Player player2;
    private int scorePlayer2;

    public Set() {}

    public Set(Player player1, Player player2) {
        this.games = new ArrayList<>();
        this.status = GameStatus.PLAYING;

        this.player1 = player1;
        this.scorePlayer1 = 0;
        this.player2 = player2;
        this.scorePlayer2 = 0;

        Game game = new Game(player1, player2);
        this.games.add(game);
    }

    public long getSetId() {
        return setId;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Player getPlayer1() {
        return player1;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }
}
