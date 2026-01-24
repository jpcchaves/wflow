package br.com.jpcchaves.wflow.model.exception;

import br.com.jpcchaves.wflow.model.FlowContext;
import lombok.Getter;

@Getter
public class FlowExecutionException extends RuntimeException {
    private final FlowContext<?> context;

    public FlowExecutionException(String message, Throwable cause, FlowContext<?> context) {
        super(message, cause);
        this.context = context;
    }
}
