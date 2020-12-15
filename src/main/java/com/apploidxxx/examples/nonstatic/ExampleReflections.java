package com.apploidxxx.examples.nonstatic;

import com.apploidxxx.examples.Cat;
import com.apploidxxx.examples.SuperCat;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.Set;

/**
 * Find annotation with Reflections lib
 *
 * @author Arthur Kupriyanov on 27.11.2020
 */
public class ExampleReflections {
    private static final Home HOME = new Home();

    public static void main(String[] args) {

        Reflections reflections = new Reflections("com.apploidxxx.examples");

        Set<Class<?>> superCats = reflections.getTypesAnnotatedWith(SuperCat.class);

        for (Class<?> clazz : superCats) {
            toCat(clazz).ifPresent(Cat::meow);
        }
    }


    private static Optional<Cat> toCat(Class<?> clazz) {
        try {
            return Optional.of((Cat) clazz.getDeclaredConstructor(Home.class).newInstance(HOME));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // IN THIS EXAMPLE you will catch here NoSuchMethodException from StaticHome inner static classes
            // because they don't have constructor with home class
            return Optional.empty();
        }
    }
}