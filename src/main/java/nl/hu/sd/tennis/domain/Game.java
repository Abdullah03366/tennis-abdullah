package nl.hu.sd.tennis.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long gameId;

    @ElementCollection(fetch = FetchType.LAZY)
    private Map<Player, ArrayList<Point>> pointsOfPlayers;

    public Game() {}

    public Game(Player player1, Player player2) {
        this.pointsOfPlayers = new HashMap<>();
        ArrayList<Point> pointsofPlayer1 = new ArrayList<>();
        pointsofPlayer1.add(new Point());
        ArrayList<Point> pointsofPlayer2 = new ArrayList<>();
        pointsofPlayer2.add(new Point());
        this.pointsOfPlayers.put(player1, pointsofPlayer1);
        this.pointsOfPlayers.put(player2, pointsofPlayer2);
    }

    public boolean addPointToPlayer(Player player) {
        if (this.pointsOfPlayers.containsKey(player)) {
            return this.pointsOfPlayers.get(player).get(-1).increment();
        }
        return false;
    }


}
