package nl.hu.sd.tennis.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long pointId;

    public int score;
    public String call;

    public Point() {
        this.score = 0;
        this.call = "love";
    }

    public int getScore() {
        return score;
    }

    public String getCall() {
        return call;
    }

    public void scoreCall() {
        switch (score) {
            case 1 -> call = "15";
            case 2 -> call = "30";
            case 3 -> call = "40";
        }
    }

    public boolean increment() {
        this.score++;
        scoreCall();
        return true;
    }
}
