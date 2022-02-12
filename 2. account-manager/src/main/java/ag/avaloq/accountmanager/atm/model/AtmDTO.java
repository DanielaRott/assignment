package ag.avaloq.accountmanager.atm.model;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AtmDTO {

    @NonNull
    private UUID atmId;

    @NonNull
    private UUID accountId;

    @NonNull
    @DecimalMin("0")
    @Pattern(regexp = "[0-9]*0")
    private BigDecimal amount;
}
