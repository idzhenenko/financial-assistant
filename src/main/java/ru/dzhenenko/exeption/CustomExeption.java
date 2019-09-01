package ru.dzhenenko.exeption;

public class CustomExeption extends RuntimeException {
    public CustomExeption(Throwable cause) {
        super(cause);
    }

    public CustomExeption(String message) {
        super(message);
    }
}

