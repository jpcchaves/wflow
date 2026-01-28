package br.com.jpcchaves.wflow.examples.banktransfer.validator.validator.rules;

import br.com.jpcchaves.wflow.examples.banktransfer.dto.BankTransferDTO;
import br.com.jpcchaves.wflow.examples.banktransfer.validator.validator.IValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Order(3)
@Component
public class SenderEnoughBalance implements IValidator<BankTransferDTO> {

    @Override
    public void validate(BankTransferDTO data) {
        log.info("Validating if sender has enough balance");
        var senderBalance = findBalance(data.getAmount());

        if (senderBalance.compareTo(data.getAmount()) < 0) {
            log.error("Sender has not enough balance");
            throw new RuntimeException("Sender has not enough balance");
        }

        log.info("Sender has enough balance");
    }

    private BigDecimal findBalance(BigDecimal balance) {
        return balance;
    }
}
