package org.firespeed.kensei.SVNLogConvertor.Exception;

/**
 * 
 * LogConvertorException
 * 
 * @author kensei
 *
 */
public class LogConvertorException extends Exception {

    private static final long serialVersionUID = -2620133815379831784L;

    /**
     * 
     */
    public LogConvertorException() {
            super();
    }

    /**
     * 
     * @param message
     * @param cause
     */
    public LogConvertorException(String message, Throwable cause) {
            super(message, cause);
    }

    /**
     * 
     * @param message
     */
    public LogConvertorException(String message) {
            super(message);
    }

    /**
     * 
     * @param cause
     */
    public LogConvertorException(Throwable cause) {
            super(cause);
    }
}