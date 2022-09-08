package nl.hu.sd.tennis.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity(name = "sets")
public class Set {
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
}
