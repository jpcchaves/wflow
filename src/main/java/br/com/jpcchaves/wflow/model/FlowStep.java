package br.com.jpcchaves.wflow.model;

import lombok.Builder;
import lombok.Data;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Data
@Builder
public class FlowStep<T> {
    private String stepName;
    private Function<T, T> action;
    private Consumer<T> rollback;
    private Predicate<T> condition;
    private BiConsumer<T, Exception> onException;
}


