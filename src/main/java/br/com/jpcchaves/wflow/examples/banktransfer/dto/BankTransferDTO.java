package br.com.jpcchaves.wflow.examples.banktransfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankTransferDTO {

    private UUID transactionId;
    private String receiverId;
    private String senderId;
    private BigDecimal amount;
}
