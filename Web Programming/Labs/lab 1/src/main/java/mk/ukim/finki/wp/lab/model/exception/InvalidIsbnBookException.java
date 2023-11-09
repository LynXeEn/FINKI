package mk.ukim.finki.wp.lab.model.exception;

public class InvalidIsbnBookException extends RuntimeException{
    public InvalidIsbnBookException(){
        super("Invalid Isbn Book Exception");
    }
}
