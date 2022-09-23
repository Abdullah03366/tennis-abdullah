package nl.hu.sd.tennis.domain.exception;

public class SetNotFoundException extends Exception{
    public SetNotFoundException() {
        super("Set with given id has not been found.");
    }
}
