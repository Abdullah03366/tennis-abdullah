package nl.hu.sd.tennis.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private Status status;
    private HashMap<ArrayList<Point>, ArrayList<Point>> rounds = new HashMap<>();
    private Player player1;
    private Player player2;


    public Game(Player player1, Player player2) {
        this.status = Status.PLAYING;
        this.player1 = player1;
        this.player2 = player2;
    }


}
