package br.com.jpcchaves.wflow.model;

import br.com.jpcchaves.wflow.model.exception.FlowExecutionException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlowResult<T> {
    private boolean success;
    private T data;
    private FlowContext<T> context;
    private FlowExecutionException exception;

    public static <T> FlowResult<T> success(T data, FlowContext<T> context) {
        return FlowResult.<T>builder()
                .data(data)
                .success(true)
                .context(context)
                .exception(null)
                .build();
    }

    public static <T> FlowResult<T> failure(T data, FlowContext<T> context, FlowExecutionException ex) {
        return FlowResult.<T>builder()
                .data(data)
                .success(false)
                .context(context)
                .exception(ex)
                .build();
    }
}
