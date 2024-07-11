package com.example.classes;

import com.example.annotation.IntensiveComponent;
import com.example.context.IntensiveContext;

public class Main {
    @IntensiveComponent
    public static class SomeClass {
        public void run() {
            System.out.println("SomeClass is running!");
        }
    }

    public static void main(String[] args) {
        IntensiveContext context = new IntensiveContext("com.example.classes");
        SomeClass someClass = context.getObject(SomeClass.class);
        someClass.run();

    }
}
