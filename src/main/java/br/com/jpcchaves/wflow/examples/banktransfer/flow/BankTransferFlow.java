package br.com.jpcchaves.wflow.examples.banktransfer.flow;

import br.com.jpcchaves.wflow.examples.banktransfer.dto.BankTransferDTO;
import br.com.jpcchaves.wflow.examples.banktransfer.validator.handler.ValidateTransferHandler;
import br.com.jpcchaves.wflow.factory.FlowFactory;
import br.com.jpcchaves.wflow.model.FlowResult;
import org.springframework.stereotype.Component;

@Component
public class BankTransferFlow {

    private final ValidateTransferHandler validateTransferHandler;

    public BankTransferFlow(ValidateTransferHandler validateTransferHandler) {
        this.validateTransferHandler = validateTransferHandler;
    }

    public FlowResult<BankTransferDTO> executeFlow(BankTransferDTO input) {
        return FlowFactory.<BankTransferDTO>flow()
                .action("validate-transfer", validateTransferHandler::execute)
                .build()
                .execute(input);
    }
}
