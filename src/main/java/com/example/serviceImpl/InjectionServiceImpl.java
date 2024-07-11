package com.example.serviceImpl;

import com.example.annotation.IntensiveComponent;
import com.example.context.IntensiveContext;
import com.example.service.InjectionService;

import java.lang.reflect.Field;

/**
 * Реализация инъекции зависимостей в объект.
 */
public class InjectionServiceImpl implements InjectionService {
    @Override
    public void injectDependencies(Object object, IntensiveContext context) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(IntensiveComponent.class)) {
                field.setAccessible(true);
                try {
                    Object dependency = context.getObject(field.getType());
                    field.set(object, dependency);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to inject dependency", e);
                }
            }
        }
    }
}
