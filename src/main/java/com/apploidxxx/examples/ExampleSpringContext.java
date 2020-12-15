package com.apploidxxx.examples;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * @author Arthur Kupriyanov on 28.11.2020
 */
public class ExampleSpringContext {
    private static final Home HOME = new Home();
    public static void main(String[] args) throws Exception {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter( SuperCat.class));
        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents("com.apploidxxx.examples");
        System.out.println(candidateComponents.size());
        for (BeanDefinition bd : candidateComponents) {
            Object obj = Class.forName(bd.getBeanClassName()).getDeclaredConstructor(Home.class)
                    .newInstance(HOME);

            Cat cat = (Cat) obj;
            cat.meow();
        }

    }
}
