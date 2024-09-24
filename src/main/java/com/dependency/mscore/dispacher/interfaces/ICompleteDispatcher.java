package com.dependency.mscore.dispacher.interfaces;

public interface ICompleteDispatcher extends IListDispatcher{

    <T, R> void registerPageResultHandler(Class<T> paramClass, IHandlerMethod<T, R> handler, Object config);
}
