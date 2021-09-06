package ru.job4j.weather_forecast.tools;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static final String PATH = "https://api.openweathermap.org/data/2.5/";
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static final OkHttpClient.Builder client = new OkHttpClient.Builder()
            .addInterceptor(interceptor);

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
