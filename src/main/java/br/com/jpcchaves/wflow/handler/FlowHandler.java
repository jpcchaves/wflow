package br.com.jpcchaves.wflow.handler;

public interface FlowHandler<T> {

    T execute(T input);

    default void rollback(T data) {

    }

    default boolean shouldExecute(T data) {
        return true;
    }

    default void handleException(T data, Exception ex) {

    }
}
