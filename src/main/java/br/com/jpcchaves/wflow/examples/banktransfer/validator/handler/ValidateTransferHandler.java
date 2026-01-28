package br.com.jpcchaves.wflow.examples.banktransfer.validator.handler;

import br.com.jpcchaves.wflow.examples.banktransfer.dto.BankTransferDTO;
import br.com.jpcchaves.wflow.examples.banktransfer.validator.validator.TransferValidatorChain;
import br.com.jpcchaves.wflow.handler.FlowHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidateTransferHandler implements FlowHandler<BankTransferDTO> {

    private final TransferValidatorChain<BankTransferDTO> transferValidatorChain;

    public ValidateTransferHandler(TransferValidatorChain<BankTransferDTO> transferValidatorChain) {
        this.transferValidatorChain = transferValidatorChain;
    }

    @Override
    public BankTransferDTO execute(BankTransferDTO input) {
        log.info("Starting Executing ValidateTransferHandler. Proceeding to validators with data: {}", input);
        transferValidatorChain.execute(input);
        log.info("Finished Executing ValidateTransferHandler. Proceeding to validators with data: {}", input);
        return input;
    }
}
