package br.com.jpcchaves.wflow.examples.banktransfer.validator.validator.rules;

import br.com.jpcchaves.wflow.examples.banktransfer.dto.BankTransferDTO;
import br.com.jpcchaves.wflow.examples.banktransfer.dto.CustomerDTO;
import br.com.jpcchaves.wflow.examples.banktransfer.validator.validator.IValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Order(2)
@Component
public class ReceiverExists implements IValidator<BankTransferDTO> {

    @Override
    public void validate(BankTransferDTO data) {
        log.info("Validating receiver exists");

        var customer = findCustomer();

        if (customer == null) {
            log.error("Error validating receiver exists: {}", data);
            throw new RuntimeException("receiver does not exist");
        }

        log.info("Receiver exists");
    }

    private CustomerDTO findCustomer() {
        return CustomerDTO.builder()
                .balance(new BigDecimal("100"))
                .name("Bill Gates")
                .build();
    }
}
