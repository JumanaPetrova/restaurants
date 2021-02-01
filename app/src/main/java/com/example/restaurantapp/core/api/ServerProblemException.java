package com.example.restaurantapp.core.api;

/**
 * Проблема на стороне сервера
 *
 */
@SuppressWarnings("WeakerAccess")
public class ServerProblemException extends RuntimeException {
    public ServerProblemException(String detailMessage) {
        super(detailMessage);
    }
}
