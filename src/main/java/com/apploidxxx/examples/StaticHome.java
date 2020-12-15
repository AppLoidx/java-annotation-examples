package com.apploidxxx.examples;

import com.apploidxxx.examples.nonstatic.Home;


/**
 * Another version of {@link Home} with static inner classes
 *
 * @author Arthur Kupriyanov on 27.11.2020
 */
@SuperCat
public class StaticHome {

    public static class Tom extends Cat {
        @Override
        public void meow() {
            System.out.println("Tom-style meow!");
        }
    }

    @SuperCat
    public static class Alex extends Cat {
        @Override
        public void meow() {
            System.out.println("Alex-style meow!");
        }
    }
}