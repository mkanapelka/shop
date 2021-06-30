package com.max.shop.aspect.handler;

import com.max.shop.aspect.StatisticsType;

public interface Handler {

    Object writeStatistics(Object target);

    boolean supports(StatisticsType type);
}
