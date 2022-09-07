package nl.hu.sd.tennis.domain;

import java.util.ArrayList;

public class Round {
    private ArrayList<Point> pointsOfPlayer1;
    private ArrayList<Point> pointsOfPlayer2;


    public Round() {
        this.pointsOfPlayer1 = new ArrayList<>();
        this.pointsOfPlayer2 = new ArrayList<>();
    }

    public boolean addPointToPlayer1(Point point) {
        return pointsOfPlayer1.add(point);
    }

    public boolean addPointToPlayer2(Point point) {
        return pointsOfPlayer2.add(point);
    }
}
