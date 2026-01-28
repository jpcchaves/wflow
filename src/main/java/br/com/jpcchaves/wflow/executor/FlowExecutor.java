package br.com.jpcchaves.wflow.executor;

import br.com.jpcchaves.wflow.model.FlowContext;
import br.com.jpcchaves.wflow.model.FlowResult;
import br.com.jpcchaves.wflow.model.FlowStep;
import br.com.jpcchaves.wflow.model.exception.FlowExecutionException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Slf4j
public class FlowExecutor<T> {
    private final List<FlowStep<T>> steps;
    private final Consumer<T> globalErrorHandler;
    private final BiConsumer<FlowContext<T>, Exception> errorHandler;

    public FlowExecutor(
            List<FlowStep<T>> steps,
            Consumer<T> globalErrorHandler,
            BiConsumer<FlowContext<T>, Exception> errorHandler
    ) {
        this.steps = steps;
        this.globalErrorHandler = globalErrorHandler;
        this.errorHandler = errorHandler;
    }

    public FlowResult<T> execute(T input) {
        FlowContext<T> context = FlowContext.of(input);

        int currentStepIndex = 0;
        try {
            for (FlowStep<T> step : steps) {
                currentStepIndex = currentStepIndex++;

                // Verify if condition to execute step exists.
                // If exists, submit context data to test condition
                if (step.getCondition() != null && !step.getCondition().test(context.getData())) {
                    continue;
                }

                try {
                    // Executes action passing input
                    T result = step.getAction().apply(input);

                    // Updates context data with result
                    context.setData(result);

                    // Add executed step to executed steps list
                    context.getExecutedSteps().add(step);
                } catch (Exception e) {
                    log.error("Exception while executing step action. stepName={}", step.getStepName(), e);
                    // If any error in step, update context with error data
                    context.setHasError(true);
                    context.setLastException(e);

                    if (step.getOnException() != null) {
                        step.getOnException().accept(context.getData(), e);
                    }

                    executeRollback(context);

                    if (errorHandler != null) {
                        errorHandler.accept(context, e);
                    }

                    if (globalErrorHandler != null) {
                        globalErrorHandler.accept(context.getData());
                    }

                    throw new FlowExecutionException("Error in step: %s".formatted(step.getStepName()), e, context);
                }

            }

            return FlowResult.success(input, context);
        } catch (FlowExecutionException e) {
            log.error("Error executing in step={} stepName={}", currentStepIndex, steps.get(currentStepIndex).getStepName(), e);
            return FlowResult.failure(input, context, e);
        } catch (Exception e) {
            log.error("Unexpected error while executing step action", e);
            throw e;
        }
    }

    private void executeRollback(FlowContext<T> context) {
        List<FlowStep<T>> executedSteps = context.getExecutedSteps();

        IntStream.range(0, executedSteps.size() - 1)
                .forEach(i -> {
                    FlowStep<T> step = executedSteps.get(i);

                    if (step.getRollback() != null) {
                        try {
                            step.getRollback().accept(context.getData());
                        } catch (Exception e) {
                            log.error("Exception while executing step rollback. stepName={}", step.getStepName(), e);
                        }
                    }
                });
    }
}
