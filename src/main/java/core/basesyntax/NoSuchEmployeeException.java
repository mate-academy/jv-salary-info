package core.basesyntax;

public class NoSuchEmployeeException extends Exception {

    public NoSuchEmployeeException(String message) {
        super(message);
        System.err.println("There is no such employee " + message + " in the database");
    }

}
