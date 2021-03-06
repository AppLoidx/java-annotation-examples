package com.apploidxxx.examples.nonstatic;

import com.apploidxxx.examples.Cat;
import com.apploidxxx.examples.SuperCat;

import java.io.File;
import java.lang.annotation.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;





/**
 * @author Arthur Kupriyanov on 27.11.2020
 */
public class Home {

    public static void main(String[] args) throws Exception {

        String packageName = "com.apploidxxx.examples.nonstatic";
        ClassLoader classLoader = Home.class.getClassLoader();

        String packagePath = packageName.replace('.', '/');
        URL urls = classLoader.getResource(packagePath);


        File folder = new File(urls.getPath());
        File[] classes = folder.listFiles();

        List<Cat> superCats = new ArrayList<>();
        final Home home = new Home();

        for (File aClass : classes) {
            int index = aClass.getName().indexOf(".");
            String className = aClass.getName().substring(0, index);
            String classNamePath = packageName + "." + className;
            Class<?> repoClass = Class.forName(classNamePath);
            Annotation[] annotations = repoClass.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == SuperCat.class) {
                    Object obj = repoClass.getDeclaredConstructor(Home.class).newInstance(home);
                    superCats.add((Cat) obj);
                }
            }
        }

        superCats.forEach(Cat::meow);
    }

    public class Tom extends Cat {
        @Override
        public void meow() {
            System.out.println("Tom-style meow!");
        }
    }

    @SuperCat
    public class Alex extends Cat {
        @Override
        public void meow() {
            System.out.println("Alex-style meow!");
        }
    }
}