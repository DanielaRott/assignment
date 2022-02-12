package ag.avaloq.accountmanager.atm.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AmountNotAvailableException extends Exception {
    public AmountNotAvailableException(String message) {
        super(message);
    }
}
