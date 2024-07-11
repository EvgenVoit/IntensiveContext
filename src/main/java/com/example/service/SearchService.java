package com.example.service;

import java.util.Set;

/**
 * Интерфейс для поиска классов, аннотированных @IntensiveComponent.
 */
public interface SearchService {
    Set<Class<?>> findComponents(String basePackage);
}
