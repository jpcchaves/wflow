package br.com.jpcchaves.wflow.examples.banktransfer.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CustomerDTO {
    private String name;
    private BigDecimal balance;
}
