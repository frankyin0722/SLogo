package alerts;

/**
 * @author Shicheng Rao
 */

/**
 * Class to be able to throw exceptions based on the errors in executing commands.
 */

public class CommandException extends RuntimeException {

    /**
     * set the ID to the default value
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create an exception based on a problem in the code
     */

    public CommandException(String message, Object ... values) {
        super(String.format(message, values));
    }

    /**
     * Create an exception based on a caught exception
     */

    public CommandException(Throwable problem) {
        super(problem);
    }
}
