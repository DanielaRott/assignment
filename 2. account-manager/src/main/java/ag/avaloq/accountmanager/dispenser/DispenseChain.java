package ag.avaloq.accountmanager.dispenser;

import ag.avaloq.accountmanager.atm.model.Atm;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public interface DispenseChain {

    void setNextChain(DispenseChain nextChain);

    void dispense(Atm atm, BigDecimal amount);

    default BigDecimal dispenseBills(Atm atm, BigDecimal amount, int dispenseValue) {
        int numberOfBills = amount.divide(BigDecimal.valueOf(dispenseValue)).intValue();

        atm.getBills().stream()
                .filter(bill -> bill.getBanknote().value == dispenseValue)
                .map(bill -> {
                    bill.setAmount(bill.getAmount().subtract(BigDecimal.valueOf(numberOfBills)));
                    return bill;
                })
                .collect(Collectors.toSet());

        return amount.remainder(BigDecimal.valueOf(dispenseValue));
    }
}
