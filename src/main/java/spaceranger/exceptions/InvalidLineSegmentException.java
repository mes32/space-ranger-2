/*
    InvalidLineSegmentException.java

    Exception thrown if a SLineSegment is not perpendicular to the GameBoard
 */

package spaceranger.exceptions;

public class InvalidLineSegmentException extends Exception {

    public InvalidLineSegmentException() {
        super();
    }

    public InvalidLineSegmentException(String message) {
        super(message);
    }

    public InvalidLineSegmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLineSegmentException(Throwable cause) {
        super(cause);
    }
}
