package com.max.shop.converter.update;

public interface UpdateConverter<SOURCE, TARGET> {

    void update(SOURCE source, TARGET target);
}
