package ag.avaloq.accountmanager.dispenser.value;

import ag.avaloq.accountmanager.dispenser.DispenseChain;
import ag.avaloq.accountmanager.atm.model.Atm;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class SwissFrank20Dispenser implements DispenseChain {

    private DispenseChain chain;
    private static final int dispenseValue = 20;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain=nextChain;
    }

    @Override
    public void dispense(Atm atm, BigDecimal amount) {
        log.info("Called SwissFrank{}Dispenser::dispense with amount: {}", dispenseValue, amount);

        if(amount.compareTo(BigDecimal.valueOf(dispenseValue)) < 0) {
            this.chain.dispense(atm, amount);
            return;
        }

        BigDecimal remainingAmount = dispenseBills(atm, amount, dispenseValue);

        if(remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.chain.dispense(atm, remainingAmount);
        }
    }
}
