package br.com.jpcchaves.wflow.examples.banktransfer.validator.validator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransferValidatorChain<T> {
    private final List<IValidator<T>> validatorList;

    public TransferValidatorChain(List<IValidator<T>> validatorList) {
        this.validatorList = validatorList;
    }

    public void execute(T input) {
        validatorList.forEach(v -> v.validate(input));
    }
}
