package br.com.jpcchaves.wflow.examples.banktransfer.validator.validator;

@FunctionalInterface
public interface IValidator<T> {
    void validate(T data);
}
