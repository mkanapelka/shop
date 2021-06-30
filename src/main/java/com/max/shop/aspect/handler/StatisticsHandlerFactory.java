package com.max.shop.aspect.handler;

import com.max.shop.aspect.StatisticsType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticsHandlerFactory {

    //        private final List<Handler> handlers;
    private Map<StatisticsType, Handler> map = new HashMap<>();

    @PostConstruct
    public void setUpHandlersMap(List<Handler> handlers) {

        Arrays.stream(StatisticsType.values())
            .forEach(type ->
                handlers.stream()
                    .filter(handler -> handler.supports(type))
                    .findFirst()
                    .ifPresent(handler -> map.put(type, handler))
            );
    }

    public Handler getHandler(StatisticsType type) {
        return this.map.get(type);
    }

}
