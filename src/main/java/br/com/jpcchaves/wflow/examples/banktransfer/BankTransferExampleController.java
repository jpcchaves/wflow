package br.com.jpcchaves.wflow.examples.banktransfer;

import br.com.jpcchaves.wflow.examples.banktransfer.dto.BankTransferDTO;
import br.com.jpcchaves.wflow.examples.banktransfer.service.BankTransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/examples/bank-transfer")
public class BankTransferExampleController {

    private final BankTransferService service;

    @PostMapping
    public ResponseEntity<?> doTransfer(@RequestBody BankTransferDTO input) {
        log.info("Receiving bank transfer request: {}", input);
        var response = service.doTrasnfer(input);
        log.info("Bank transfer response: {}", response);
        return ResponseEntity.ok(response);
    }
}

