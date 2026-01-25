package br.com.jpcchaves.wflow.factory;

import br.com.jpcchaves.wflow.builder.FlowBuilder;

public class FlowFactory {

    private FlowFactory() {
    }

    public static <T> FlowBuilder<T> flow() {
        return new FlowBuilder<>();
    }
}
