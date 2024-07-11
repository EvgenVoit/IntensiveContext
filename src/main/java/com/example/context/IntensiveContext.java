package com.example.context;

import com.example.service.InjectionService;
import com.example.service.SearchService;
import com.example.serviceImpl.InjectionServiceImpl;
import com.example.serviceImpl.SearchServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Класс контекста для управления жизненным циклом компонентов.
 */
public class IntensiveContext {
    private final String basePackage;
    private final SearchService searchService;
    private final InjectionService injectionService;
    private final Map<Class<?>, Object> singletonCache = new HashMap<>();

    public IntensiveContext(String basePackage) {
        this.basePackage = basePackage;
        this.searchService = new SearchServiceImpl();
        this.injectionService = new InjectionServiceImpl();
    }

    public <T> T getObject(Class<T> type) {
        if (singletonCache.containsKey(type)) {
            return type.cast(singletonCache.get(type));
        }

        Set<Class<?>> components = searchService.findComponents(basePackage);
        Class<?> implementationClass = findImplementation(type, components);
        try {
            T instance = type.cast(implementationClass.getDeclaredConstructor().newInstance());
            injectionService.injectDependencies(instance, this);
            singletonCache.put(type, instance);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + type, e);
        }
    }

    private Class<?> findImplementation(Class<?> type, Set<Class<?>> components) {
        for (Class<?> component : components) {
            if (type.isAssignableFrom(component)) {
                return component;
            }
        }
        throw new RuntimeException("No implementation found for " + type);
    }
}
