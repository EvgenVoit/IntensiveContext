package com.example.service;

import com.example.context.IntensiveContext;

/**
 * Интерфейс для инъекции зависимостей в объект.
 */
public interface InjectionService {
    void injectDependencies(Object object, IntensiveContext context);
}
