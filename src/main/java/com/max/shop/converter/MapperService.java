package com.max.shop.converter;

import com.max.shop.converter.update.UpdateConverter;
import com.max.shop.converter.util.ConverterScanUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapperService {

    private final ConversionService conversionService;

    private Map<Pair<String, String>, UpdateConverter<?, ?>> updateConverters;

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void setUpUpdateConverters() {
        Map<Pair<String, String>, UpdateConverter<?, ?>> converters =
            ConverterScanUtil.findConverters("com.max.shop.converter.update", UpdateConverter.class).stream()
                .filter(ConverterScanUtil::hasConstructors)
                .collect(Collectors.toMap(
                    converterClass -> resolveGenericTypes(converterClass, UpdateConverter.class),
                    ConverterScanUtil::getInstance));

        this.updateConverters = Collections.unmodifiableMap(converters);
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

    //TODO test it
    @SuppressWarnings({"unchecked", "ConstantConditions"})
    public <S, T> void update(S source, T target) {
        val key = Pair.of(source.getClass().getName(), target.getClass().getName());
        val converter = updateConverters.get(key);
        if (converter == null) {
            throw new ConverterNotFoundException(TypeDescriptor.forObject(source), TypeDescriptor.forObject(target));
        }
        ((UpdateConverter<S, T>) converter).update(source, target);
    }

    @SuppressWarnings({"ConstantConditions"})
    private static Pair<String, String> resolveGenericTypes(Class<?> converter, Class<?> superClass) {
        String[] typeNames = Arrays
            .stream(GenericTypeResolver.resolveTypeArguments(converter, superClass))
            .map(Class::getName)
            .toArray(String[]::new);
        return Pair.of(typeNames[0], typeNames[1]);
    }
}
