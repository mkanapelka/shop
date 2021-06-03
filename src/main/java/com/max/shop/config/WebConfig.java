package com.max.shop.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ArrayUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.file.async-timeout:5000}")//5 seconds
    private Long timeout;

    private final AsyncTaskExecutor mvcTaskExecutor;
    private final CallableProcessingInterceptor callableProcessingInterceptor;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer
            .setDefaultTimeout(timeout)
            .setTaskExecutor(mvcTaskExecutor)
            .registerCallableInterceptors(callableProcessingInterceptor);
        WebMvcConfigurer.super.configureAsyncSupport(configurer);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        findConverters().stream()
            .filter(this::hasConstructors)
            .forEach(converterClass ->
                registry.addConverter(getInstance(converterClass)));
    }

    private Set<Class<? extends Converter>> findConverters() {
        Reflections reflections = new Reflections("com.max.shop.converter", new SubTypesScanner());
        return reflections.getSubTypesOf(Converter.class);
    }

    @SneakyThrows
    private <T> T getInstance(Class<T> clazz) {
        return clazz.newInstance();
    }

    @SneakyThrows
    private boolean hasConstructors(Class<?> clazz) {
        return ArrayUtils.isNotEmpty(clazz.getDeclaredConstructors());
    }
}
