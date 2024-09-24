package com.dependency.mscore.dispacher.interfaces.impl;

import com.dependency.mscore.dispacher.interfaces.ICompleteDispatcher;
import com.dependency.mscore.dispacher.interfaces.IHandlerMethod;
import com.dependency.mscore.grpc.dto.request.GenericRequestDTO;
import com.dependency.mscore.grpc.dto.response.GenericResponseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("DispatcherComponent")
public class DispatcherComponent implements ICompleteDispatcher {

    private final Map<String, ConfigAnyOne> routes = new HashMap<>();

    @Override
    public <T, R> void registerPageResultHandler(Class<T> paramClass, IHandlerMethod<T, R> handler, Object config) {

    }

    @Override
    public <T, R> void registerListResultHandler(Class<T> paramClass, IHandlerMethod<T, List<R>> handler, Object key) {

        ConfigAnyOne configAnyOne = routes.computeIfAbsent((String) key, c -> new ConfigAnyOne<T>());
        configAnyOne.setRequestClass(paramClass);
        configAnyOne.getHandlers().add(handler);

    }

    @Override
    public <T, R> void registerSimpleResultHandler(Class<T> paramClass, IHandlerMethod<T, R> handler, Object key) {
        ConfigAnyOne configAnyOne = routes.computeIfAbsent((String) key, c -> new ConfigAnyOne<T>());
        configAnyOne.setRequestClass(paramClass);
        configAnyOne.getHandlers().add(handler);
    }

    @Override
    public <T, R> GenericResponseDTO<R> dispatch(GenericRequestDTO<T> request) {

        GenericRequestDTO<T> anyone = null;
        try {

            if (request == null) {
                throw new RuntimeException("Request is null");

            }
            String option = request.getOption();
            if (!routes.isEmpty()) {
                Set<Map.Entry<String, ConfigAnyOne>> entrySet = routes.entrySet();
                for (Map.Entry<String, ConfigAnyOne> entry : entrySet) {
                    if (entry.getKey().equalsIgnoreCase(option)) {
                        anyone = GenericRequestDTO.<T>builder()
                                .origin(request.getOrigin())
                                .transactionId(request.getTransactionId())
                                .option(option)
                                .ipRequest(request.getIpRequest())
                                .userRequest(request.getUserRequest())
                                .payload(request.getPayload())
                                .build();
                        break;
                    }

                }
            }
            if (anyone == null) {
                throw new RuntimeException("Option not found");

            }
            List<IHandlerMethod> handlers = routes.get(option).getHandlers();
            if (handlers.size() > 1) {
                throw new RuntimeException("Multiple handlers found for option: " + option + " - " + handlers.size() + " handlers found.");
            }
            return GenericResponseDTO.<R>builder()
                    .code(200)
                    .status("OK")
                    .message("Solicitud procesada correctamente")
                    .payload((R) handlers.get(0).handle(anyone))
                    .build();
        } catch (Exception e) {

            return GenericResponseDTO.<R>builder()
                    .code(500)
                    .status("ERROR")
                    .message(e.getMessage()).build();
        }
    }

    @Getter
    @Setter
    public class ConfigAnyOne<T> {
        private List<IHandlerMethod> handlers;

        private Class<T> requestClass;

        public ConfigAnyOne() {
            handlers = new ArrayList<>();
        }
    }
}
