package nl.hu.sd.tennis.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "games")
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long gameId;

    @Enumerated
    private GameStatus status;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Player player1;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Point> pointsOfPlayer1;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Player player2;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Point> pointsOfPlayer2;

    public Game() {}

    public Game(Player player1, Player player2) {
        this.status = GameStatus.PLAYING;

        this.player1 = player1;
        pointsOfPlayer1 = new ArrayList<>();
        pointsOfPlayer1.add(new Point());

        this.player2 = player2;
        pointsOfPlayer2 = new ArrayList<>();
        pointsOfPlayer2.add(new Point());
    }

    private void addPointToPlayer(Player player) {
        if (player == this.player1) {
            this.pointsOfPlayer1.get(pointsOfPlayer1.size()-1).increment();
            return;
        }
        this.pointsOfPlayer2.get(pointsOfPlayer2.size()-1).increment();
    }

    public long getGameId() {
        return gameId;
    }

    public Point getLastPointOfPlayer1() {
        return this.pointsOfPlayer1.get(pointsOfPlayer1.size() - 1);
    }

    public Point getLastPointOfPlayer2() {
        return this.pointsOfPlayer2.get(pointsOfPlayer2.size() - 1);
    }

    public GameStatus calculateScoreOfGame(Player player) {
        addPointToPlayer(player);

        int player1Point = getLastPointOfPlayer1().getScore();
        int player2Point = getLastPointOfPlayer2().getScore();
        int diffPlayerScores = player1Point - player2Point;

        if (player1Point == 3 && player2Point == 3 ) {
            getLastPointOfPlayer1().setCall("deuce");
            getLastPointOfPlayer2().setCall("deuce");
            return this.status = GameStatus.DEUCE;
        }
        if (this.status == GameStatus.DEUCE && diffPlayerScores != 0) {
            if (diffPlayerScores == 1) {
                getLastPointOfPlayer1().setCall("advantage");
            } else {
                getLastPointOfPlayer2().setCall("advantage");
            }
            return this.status = GameStatus.ADVANTAGE;
        }
        if ((player1Point > 3 && player2Point <= 2) || (this.status == GameStatus.ADVANTAGE && diffPlayerScores == 2)) {
            return this.status = GameStatus.PLAYER1_WON_GAME;
        }
        if ((player2Point > 3 && player1Point <= 2) || (this.status == GameStatus.ADVANTAGE && diffPlayerScores == -2)) {
            return this.status = GameStatus.PLAYER2_WON_GAME;
        }
        return null;
    }

    public GameStatus getStatus() {
        return status;
    }
}
