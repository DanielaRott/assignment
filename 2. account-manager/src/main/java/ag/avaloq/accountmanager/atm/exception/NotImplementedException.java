package ag.avaloq.accountmanager.atm.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotImplementedException extends Exception {
    public NotImplementedException(String message) {
        super(message);
    }
}
