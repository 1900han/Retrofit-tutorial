package com.megamind.retrofit.tutorials.example.baseurl;

import okhttp3.HttpUrl;

import java.lang.reflect.Field;

public class HttpUrlHelper {
    private static final Field hostField;

    static {
        Field field = null;
        try {
            field = HttpUrl.class.getDeclaredField("host");
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        hostField = field;
    }

    private final HttpUrl httpUrl;

    public HttpUrlHelper(HttpUrl httpUrl) {
        this.httpUrl = httpUrl;
    }

    public HttpUrl getHttpUrl() {
        return httpUrl;
    }

    public void setHost(String host) {
        try {
            hostField.set(httpUrl,host);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
