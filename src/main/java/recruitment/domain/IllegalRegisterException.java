package recruitment.domain;

/**
 * Thrown whenever an attempt is made to perform a transaction that is
 * not allowed by the application's business rules.
 */
public class IllegalRegisterException extends Exception {

    /**
     * Creates new instance of with the specified message.
     *
     * @param msg A message explaining why the exception is thrown.
     */
    public IllegalRegisterException(String msg) {
        super(msg);
    }
}
