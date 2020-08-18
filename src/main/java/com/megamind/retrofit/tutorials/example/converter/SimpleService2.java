/*
 * Copyright (C) 2012 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.megamind.retrofit.tutorials.example.converter;

import com.megamind.retrofit.tutorials.GitHub;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public final class SimpleService2 {
    public static final String API_URL = "https://api.github.com";

    public static void main(String... args) throws IOException {
        // Create a very simple REST adapter which points the GitHub API.
        OkHttpClient.Builder okhttpClient = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor(System.out::println).setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL).client(okhttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(DateConverter.DateConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        GitHub2 github = retrofit.create(GitHub2.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<GitHub2.Contributor>> call = github.contributors("square", "retrofit", new Date());

        // Fetch and print a list of the contributors to the library.
        List<GitHub2.Contributor> contributors = call.execute().body();
        for (GitHub2.Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
    }
}
