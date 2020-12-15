package com.apploidxxx.examples;

import com.apploidxxx.examples.Cat;
import com.apploidxxx.examples.SuperCat;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * @author Arthur Kupriyanov on 28.11.2020
 */
public class ExampleSpringContext {

    public static void main(String[] args) throws Exception {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter( SuperCat.class));
        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents("com.apploidxxx.examples");
        for (BeanDefinition bd : candidateComponents) {
            Class<?> clazz = Class.forName(bd.getBeanClassName());
            if (isCat(clazz)) {
                Cat cat = (Cat) clazz.getDeclaredConstructor().newInstance();
                cat.meow();
            }
        }

    }

    private static boolean isCat(Class<?> clazz) {
        return clazz.getSuperclass() == Cat.class;
    }
}
