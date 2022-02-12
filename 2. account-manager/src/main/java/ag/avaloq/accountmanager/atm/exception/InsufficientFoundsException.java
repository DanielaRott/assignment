package ag.avaloq.accountmanager.atm.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InsufficientFoundsException extends Exception {
    public InsufficientFoundsException(String message) {
        super(message);
    }
}
