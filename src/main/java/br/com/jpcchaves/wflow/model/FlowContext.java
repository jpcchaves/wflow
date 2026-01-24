package br.com.jpcchaves.wflow.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class FlowContext<T> {
    private T data;
    private List<FlowStep<T>> executedSteps;
    private Map<String, Object> metadata;
    private boolean hasError;
    private Exception lastException;

    public static <T> FlowContext<T> of(T input) {
        return FlowContext.<T>builder()
                .data(input)
                .executedSteps(new ArrayList<>())
                .metadata(new HashMap<>())
                .hasError(false)
                .lastException(null)
                .build();
    }
}
