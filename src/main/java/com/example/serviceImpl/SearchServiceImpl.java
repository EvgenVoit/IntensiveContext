package com.example.serviceImpl;

import com.example.annotation.IntensiveComponent;
import com.example.service.SearchService;
import org.reflections.Reflections;


import java.util.Set;

/**
 * Реализация поиска классов, аннотированных @IntensiveComponent.
 */
public class SearchServiceImpl implements SearchService {
    @Override
    public Set<Class<?>> findComponents(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        return reflections.getTypesAnnotatedWith(IntensiveComponent.class);
    }
}
