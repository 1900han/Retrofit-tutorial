package com.megamind.retrofit.tutorials.example.generic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenericType {

    public static void main(String[] args) throws Exception {
        Method testMethod = GenericType.class.getDeclaredMethod("test", Map.class);
        Type type = testMethod.getGenericReturnType();
        recursivelyCheckType(type);

        System.out.println("---------------------");

        for (Type genericParameterType : testMethod.getGenericParameterTypes()) {
            recursivelyCheckType(genericParameterType);
        }

        System.out.println("---------------------");

        Field signatureField = Method.class.getDeclaredField("signature");
        signatureField.setAccessible(true);
        System.out.println(signatureField.get(testMethod));

        System.out.println("---------------------");

        Type superType = SubType.class.getGenericSuperclass();
        recursivelyCheckType(superType);
    }

    public static Map<List<String>, Set<Map<Number, String>>> test(Map<String, Set<Map<Number, String>>> map) {
        return null;
    }

    static class SuperType<T>{

    }

    static class SubType extends SuperType<String>{

    }

    public static void recursivelyCheckType(Type type) {
        if (type instanceof ParameterizedType) {
            System.out.println("ParameterizedType: " + type);
            for (Type actualTypeArgument : ((ParameterizedType) type).getActualTypeArguments()) {
                recursivelyCheckType(actualTypeArgument);
            }
        } else {
            System.out.println("Type: " + type);
        }
    }
}
