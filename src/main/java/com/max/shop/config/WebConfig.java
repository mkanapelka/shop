package com.max.shop.config;

import com.max.shop.converter.util.ConverterScanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
        ConverterScanUtil.findConverters("com.max.shop.converter", Converter.class).stream()
            .filter(ConverterScanUtil::hasConstructors)
            .forEach(converterClass ->
                registry.addConverter(ConverterScanUtil.getInstance(converterClass)));
    }
}
