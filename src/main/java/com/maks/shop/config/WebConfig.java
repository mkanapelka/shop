package com.maks.shop.config;

import lombok.SneakyThrows;
import org.apache.commons.lang3.ArrayUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        findConverters().stream()
            .filter(this::hasConstructors)
            .forEach(converterClass ->
                registry.addConverter(getInstance(converterClass)));
    }

    private Set<Class<? extends Converter>> findConverters() {
        Reflections reflections = new Reflections("com.example.shop.converter", new SubTypesScanner());
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
