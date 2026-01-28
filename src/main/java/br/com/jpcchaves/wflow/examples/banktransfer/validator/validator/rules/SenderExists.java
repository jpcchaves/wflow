package br.com.jpcchaves.wflow.examples.banktransfer.validator.validator.rules;

import br.com.jpcchaves.wflow.examples.banktransfer.dto.BankTransferDTO;
import br.com.jpcchaves.wflow.examples.banktransfer.dto.CustomerDTO;
import br.com.jpcchaves.wflow.examples.banktransfer.validator.validator.IValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Order(1)
@Component
public class SenderExists implements IValidator<BankTransferDTO> {

    @Override
    public void validate(BankTransferDTO data) {
        log.info("Validating sender exists");

        var customer = findCustomer();

        if (customer == null) {
            log.error("Error validating sender exists: {}", data);
            throw new RuntimeException("Sender does not exist");
        }

        log.info("Sender exists");
    }

    private CustomerDTO findCustomer() {
        return CustomerDTO.builder()
                .balance(new BigDecimal("100"))
                .name("John Doe")
                .build();
    }
}
