package br.com.jpcchaves.wflow.examples.banktransfer.service;

import br.com.jpcchaves.wflow.examples.banktransfer.dto.BankTransferDTO;
import br.com.jpcchaves.wflow.examples.banktransfer.flow.BankTransferFlow;
import org.springframework.stereotype.Service;

@Service
public class BankTransferService {

    private final BankTransferFlow flow;

    public BankTransferService(BankTransferFlow flow) {
        this.flow = flow;
    }

    public BankTransferDTO doTrasnfer(BankTransferDTO dto) {
        var result = flow.executeFlow(dto);
        return result.getData();
    }
}
