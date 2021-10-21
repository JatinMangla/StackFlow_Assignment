package com.example.assignment_try;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public static final String BASE_URL = "https://api.stackexchange.com";
    private static Retrofit retrofit = null;
    private static RestClient  clientobject;


   /* public static Retrofit getClient() {
        if (retrofit == null) {
            /*OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request originalRequest = chain.request();

                    Request request = originalRequest.newBuilder()
                            .header("User-Agent", "Your-App-Name")
                            .header("Accept", "application/vnd.yourapi.v1.full+json")
                            .method(originalRequest.method(), originalRequest.body())
                            .build();

                    return chain.proceed(request);
                }
            });
            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                   // .client(client)
                    .build();
        }
        return retrofit;
    }*/

    RestClient()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                // .client(client)
                .build();
    }

    public static synchronized RestClient getInstance()
    {
        if (clientobject == null)
        {
            clientobject= new RestClient();

        }return clientobject;
    }

    QuestionAPIService getInfo()
    {
        return  retrofit.create(QuestionAPIService.class);
    }
}
