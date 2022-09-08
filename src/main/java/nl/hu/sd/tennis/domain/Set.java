package nl.hu.sd.tennis.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Set {
    private GameStatus status;
    private ArrayList<Game> games;
    private Player player1;
    private int scorePlayer1;
    private Player player2;
    private int scorePlayer2;


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
