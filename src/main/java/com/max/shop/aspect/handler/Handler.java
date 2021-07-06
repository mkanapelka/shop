package com.max.shop.aspect.handler;

import com.max.shop.aspect.StatisticsType;

public interface Handler {

    void writeStatistics(Object target) throws Throwable;

    boolean supports(StatisticsType type);

    Object getResultByArg(Object object);
}
