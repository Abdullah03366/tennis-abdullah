package nl.hu.sd.tennis.domain;

import nl.hu.sd.tennis.domain.exception.SetAlreadyEndedException;
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

    private int scorePlayer1;
    private int scorePlayer2;

    public Set() {}

    public Set(Player player1, Player player2) {
        this.games = new ArrayList<>();
        this.status = GameStatus.PLAYING;

        this.scorePlayer1 = 0;
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

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

    public boolean addGame(Game game) {
        return this.games.add(game);
    }

    public GameStatus addPoint(Player player) throws SetAlreadyEndedException {
        GameStatus gameStatus = deterMineResultOfSet(player);
        if (gameStatus.equals(GameStatus.PLAYER1_WON_GAME)) {
            this.scorePlayer1++;
            Game mostRecentGame = this.games.get(games.size() - 1);
            this.games.add(new Game(mostRecentGame.getPlayer1(), mostRecentGame.getPlayer2()));
        }
        if (gameStatus.equals(GameStatus.PLAYER2_WON_GAME)) {
            this.scorePlayer2++;
            Game mostRecentGame = this.games.get(games.size() - 1);
            this.games.add(new Game(mostRecentGame.getPlayer1(), mostRecentGame.getPlayer2()));
        }
        return this.status;
    }

    public GameStatus deterMineResultOfSet(Player player) throws SetAlreadyEndedException {
        if (this.status == GameStatus.PLAYER1_WON_SET || this.status == GameStatus.PLAYER2_WON_SET) {
            throw new SetAlreadyEndedException();
        }
        Game mostRecentGame = this.games.get(games.size() - 1);
        GameStatus gameStatus = mostRecentGame.calculateScoreOfGame(player);

        if (this.scorePlayer1 >= 6 && this.scorePlayer2 <= (this.scorePlayer1 - 2)) {
            return this.status = GameStatus.PLAYER1_WON_SET;
        }
        if (this.scorePlayer2 >= 6 && this.scorePlayer1 <= (this.scorePlayer2 - 2)) {
            return this.status = GameStatus.PLAYER2_WON_SET;
        }
        return gameStatus;
    }
}
