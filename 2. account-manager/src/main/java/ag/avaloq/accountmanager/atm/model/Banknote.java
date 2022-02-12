package ag.avaloq.accountmanager.atm.model;

import java.math.BigDecimal;

public enum Banknote {
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    ONE_HUNDRED(100),
    TWO_HUNDRED(200),
    ONE_THOUSAND(1000);

    public int value;

    Banknote(int value) {
        this.value = value;
    }
}