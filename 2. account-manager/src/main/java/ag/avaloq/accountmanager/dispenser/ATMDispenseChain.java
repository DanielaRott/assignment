package ag.avaloq.accountmanager.dispenser;

import ag.avaloq.accountmanager.dispenser.value.*;

public class ATMDispenseChain {
    public DispenseChain initDispenseChain;

    public ATMDispenseChain() {
        this.initDispenseChain = new SwissFrank1000Dispenser();
        DispenseChain dispenseChain200 = new SwissFrank200Dispenser();
        DispenseChain dispenseChain100 = new SwissFrank100Dispenser();
        DispenseChain dispenseChain50 = new SwissFrank50Dispenser();
        DispenseChain dispenseChain20 = new SwissFrank20Dispenser();
        DispenseChain dispenseChain10 = new SwissFrank10Dispenser();

        initDispenseChain.setNextChain(dispenseChain200);
        dispenseChain200.setNextChain(dispenseChain100);
        dispenseChain100.setNextChain(dispenseChain50);
        dispenseChain50.setNextChain(dispenseChain20);
        dispenseChain20.setNextChain(dispenseChain10);
    }
}
