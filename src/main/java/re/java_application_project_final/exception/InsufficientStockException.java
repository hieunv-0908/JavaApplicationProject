package re.java_application_project_final.exception;

public class InsufficientStockException
        extends RuntimeException {

    public InsufficientStockException(
            String message
    ) {
        super(message);
    }
}
