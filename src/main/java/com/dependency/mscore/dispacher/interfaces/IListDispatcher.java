package com.dependency.mscore.dispacher.interfaces;

import java.util.List;

public interface IListDispatcher extends IResultDispatcher {
    <T, R> void registerListResultHandler(Class<T> paramClass, IHandlerMethod<T, List<R>> handler, Object config);
}
