package nl.hu.sd.tennis.domain.exception;

public class PlayerNotFoundException extends Exception{
    public PlayerNotFoundException() {
        super("Player with given id has not been found.");
    }
}
