package io.github.cell303.rdndc.util;

public class RedundancyException extends RuntimeException {
    public RedundancyException() {
        super();
    }

    public RedundancyException(String message) {
        super(message);
    }

    public RedundancyException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedundancyException(Throwable cause) {
        super(cause);
    }
}
