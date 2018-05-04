/*
    InvalidLineSegmentException.java

    Exception thrown if a SLineSegment is degenerate (i.e. the end points are the same)
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
