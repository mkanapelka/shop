package com.max.shop.converter.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ArrayUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.util.Set;

@UtilityClass
public class ConverterScanUtil {

    public <T> Set<Class<? extends T>> findConverters(String packageToScan, Class<T> type) {
        Reflections reflections = new Reflections(packageToScan, new SubTypesScanner());
        return reflections.getSubTypesOf(type);
    }

    @SneakyThrows
    public <T> T getInstance(Class<T> clazz) {
        return clazz.newInstance();
    }

    @SneakyThrows
    public boolean hasConstructors(Class<?> clazz) {
        return ArrayUtils.isNotEmpty(clazz.getDeclaredConstructors());
    }
}
