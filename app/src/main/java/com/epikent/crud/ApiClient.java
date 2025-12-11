package com.epikent.crud;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    private static final String BASE_URL = "https://reqres.in";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {

            // Define your API Key here (replace with your actual key if needed)
            final String API_KEY_VALUE = "reqres_42cbf27d33ff498aafeabfbdafb80ba0";
            final String HEADER_NAME = "X-API-Key";

            // 1. Create the Interceptor
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();

                    // 2. Add the desired header to the request builder
                    Request newRequest = originalRequest.newBuilder()
                            .header(HEADER_NAME, API_KEY_VALUE)
                            .build();

                    return chain.proceed(newRequest);
                }
            };

            // Optional: Add logging for debugging network traffic
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // 3. Build the OkHttpClient with the interceptors
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(loggingInterceptor) // Keep this for debugging
                    .build();

            // 4. Build Retrofit using the configured client
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
