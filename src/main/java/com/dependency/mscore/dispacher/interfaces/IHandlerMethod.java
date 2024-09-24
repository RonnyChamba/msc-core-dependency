package com.dependency.mscore.dispacher.interfaces;

@FunctionalInterface
public interface IHandlerMethod<T, R> {
    R handle(T paramT) throws Exception;
}
