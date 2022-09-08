package nl.hu.sd.tennis.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "points")
public class Point implements Serializable {
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
