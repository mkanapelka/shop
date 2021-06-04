package com.max.shop.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapperService {

    private final ConversionService conversionService;

    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        return conversionService.canConvert(sourceType, targetType);
    }

    public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return conversionService.canConvert(sourceType, targetType);
    }

    public <T> T convert(Object source, Class<T> targetType) {
        return conversionService.convert(source, targetType);
    }

    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return conversionService.convert(source, sourceType, targetType);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> convertList(Object source, Class<T> targetType) {
        TypeDescriptor sourceDescriptor = TypeDescriptor.forObject(source);
        TypeDescriptor targetDescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(targetType));
        return (List<T>) conversionService.convert(source, sourceDescriptor, targetDescriptor);
    }
}
