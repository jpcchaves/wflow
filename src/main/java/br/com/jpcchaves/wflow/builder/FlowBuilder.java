package br.com.jpcchaves.wflow.builder;

import br.com.jpcchaves.wflow.executor.FlowExecutor;
import br.com.jpcchaves.wflow.model.FlowContext;
import br.com.jpcchaves.wflow.model.FlowStep;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
public class FlowBuilder<T> {
    private List<FlowStep<T>> steps = new ArrayList<>();
    private Consumer<T> globalErrorHandler;
    private BiConsumer<FlowContext<T>, Exception> errorHandler;

    public FlowBuilder<T> action(String stepName, Function<T, T> action) {
        FlowStep<T> step = FlowStep.<T>builder()
                .stepName(stepName)
                .action(action)
                .build();

        steps.add(step);

        return this;
    }

    public FlowBuilder<T> rollback(Consumer<T> rollbackHandler) {
        FlowStep<T> lastStep = getLastStep();
        lastStep.setRollback(rollbackHandler);
        updateLastStepInList(lastStep);
        return this;
    }

    public FlowBuilder<T> condition(Predicate<T> condition) {
        FlowStep<T> lastStep = getLastStep();
        lastStep.setCondition(condition);
        updateLastStepInList(lastStep);
        return this;
    }

    public FlowBuilder<T> onException(BiConsumer<T, Exception> handler) {
        FlowStep<T> lastStep = getLastStep();
        lastStep.setOnException(handler);
        updateLastStepInList(lastStep);
        return this;
    }

    public FlowBuilder<T> globalErrorHandler(Consumer<T> handler) {
        this.globalErrorHandler = handler;
        return this;
    }

    public FlowExecutor<T> build() {
        return new FlowExecutor<>(steps, globalErrorHandler, errorHandler);
    }

    private FlowStep<T> getLastStep() {
        return steps.get(getLastStepIndex());
    }

    private void updateLastStepInList(FlowStep<T> step) {
        steps.set(getLastStepIndex(), step);
    }

    private int getLastStepIndex() {
        if (steps.isEmpty()) {
            throw new IllegalStateException("Steps is empty!");
        }

        return steps.size() - 1;
    }
}
