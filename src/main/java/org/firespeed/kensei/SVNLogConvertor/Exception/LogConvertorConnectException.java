package org.firespeed.kensei.SVNLogConvertor.Exception;

/**
 * 
 * LogConvertorConnectException
 * 
 * @author BCC
 *
 */
public class LogConvertorConnectException extends Exception {

    private static final long serialVersionUID = -7285416582066606529L;

    /**
     * 
     */
    public LogConvertorConnectException() {
            super();
    }

    /**
     * 
     * @param message
     * @param cause
     */
    public LogConvertorConnectException(String message, Throwable cause) {
            super(message, cause);
    }

    /**
     * 
     * @param message
     */
    public LogConvertorConnectException(String message) {
            super(message);
    }

    /**
     * 
     * @param cause
     */
    public LogConvertorConnectException(Throwable cause) {
            super(cause);
    }
}
