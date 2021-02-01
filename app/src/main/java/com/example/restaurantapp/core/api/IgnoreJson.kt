package com.example.restaurantapp.core.api


/**
 * Аннотация для исключения из сериализации
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class IgnoreJson