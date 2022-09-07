package nl.hu.sd.tennis.domain;

import java.util.ArrayList;

public class Player {
    private String name;
    private String age;
    private ArrayList<Game> games;


    public Player(String name, String age) {
        this.name = name;
        this.age = age;
        this.games = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }
}
