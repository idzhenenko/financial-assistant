package ru.dzhenenko.controller;

public interface SecureController<REQ, RES> {
    RES handle(REQ request, Long userId) throws Exception;

    Class<REQ> getRequestClass();
}
