package ru.dzhenenko.api.converter;

public interface Converter<S, T> {
    T convert(S source);
}