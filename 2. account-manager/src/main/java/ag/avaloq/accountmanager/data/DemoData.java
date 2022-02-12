package ag.avaloq.accountmanager.data;

import ag.avaloq.accountmanager.account.model.Account;
import ag.avaloq.accountmanager.account.repository.IAccountRepository;
import ag.avaloq.accountmanager.atm.model.Atm;
import ag.avaloq.accountmanager.atm.model.Banknote;
import ag.avaloq.accountmanager.atm.model.Bill;
import ag.avaloq.accountmanager.atm.repository.IAtmRepository;
import ag.avaloq.accountmanager.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

@Component
public class DemoData implements ApplicationRunner {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IAtmRepository atmRepository;

    @Override
    public void run(ApplicationArguments args) {
        createAtm();

        createAccount();
    }

    private void createAccount() {
        var user = User.builder()
                .age(20)
                .firstName("John")
                .lastName("Doe")
                .build();

        var account = Account.builder()
                .iban("CH9300762011623852957")
                .balance(BigDecimal.valueOf(10000))
                .currency(Currency.getInstance("CHF"))
                .user(user)
                .creationDate(LocalDateTime.now())
                .build();

        accountRepository.save(account);
    }

    private void createAtm() {
        Set<Bill> bills = new HashSet<>();
        bills.add(Bill.builder().banknote(Banknote.ONE_THOUSAND).amount(BigDecimal.valueOf(50)).build());
        bills.add(Bill.builder().banknote(Banknote.ONE_HUNDRED).amount(BigDecimal.valueOf(200)).build());
        bills.add(Bill.builder().banknote(Banknote.FIFTY).amount(BigDecimal.valueOf(50)).build());
        bills.add(Bill.builder().banknote(Banknote.TWENTY).amount(BigDecimal.valueOf(100)).build());
        bills.add(Bill.builder().banknote(Banknote.TEN).amount(BigDecimal.valueOf(2550)).build());

        var atm = Atm.builder()
                .balance(BigDecimal.valueOf(100000))
                .currency(Currency.getInstance("CHF"))
                .location("Paradeplatz Zurich")
                .bills(bills)
                .build();
        atmRepository.save(atm);
    }
}
