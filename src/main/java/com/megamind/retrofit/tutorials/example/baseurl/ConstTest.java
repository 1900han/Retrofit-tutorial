package com.megamind.retrofit.tutorials.example.baseurl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ConstTest {
    private final int a = 1;
    private int b = 1;

    private static final int c = 1;

    public static void main(String[] args) throws Exception {
//        access2FinalInt();
        access2StaticFinalInt();
    }

    private static void access2StaticFinalInt() throws NoSuchFieldException, IllegalAccessException {
        System.out.println(ConstTest.c);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);

        Field cField = ConstTest.class.getDeclaredField("c");
        modifiersField.set(cField, modifiersField.getInt(cField) ^ Modifier.FINAL);
        cField.setAccessible(true);
        cField.set(null, 2);
        System.out.println(ConstTest.c);
    }

    private static void access2FinalInt() throws NoSuchFieldException, IllegalAccessException {
        ConstTest constTest = new ConstTest();
        System.out.println(constTest.a);
        Field aField = ConstTest.class.getDeclaredField("a");
        aField.setAccessible(true);
        aField.set(constTest, 2);
        System.out.println(constTest.a);
        System.out.println(constTest.b);
    }
}
