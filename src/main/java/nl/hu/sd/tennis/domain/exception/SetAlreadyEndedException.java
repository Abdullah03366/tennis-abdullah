package nl.hu.sd.tennis.domain.exception;

public class SetAlreadyEndedException extends Exception{
    public SetAlreadyEndedException() {
        super("Game with given id has already ended.");
    }
}
