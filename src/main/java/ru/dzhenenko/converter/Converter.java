package ru.dzhenenko.converter;

public interface Converter<S, T> {
    T convert(S source);
}

