package ir.mehdisekoba.banimode.utils.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.mehdisekoba.banimode.BuildConfig
import ir.mehdisekoba.banimode.data.network.ApiServices
import ir.mehdisekoba.banimode.utils.BASE_URL
import ir.mehdisekoba.banimode.utils.CONNECTION_TIME
import ir.mehdisekoba.banimode.utils.NAMED_PING
import ir.mehdisekoba.banimode.utils.PING_INTERVAL
import jakarta.inject.Named
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideClient(
        timeOut: Long,
        @Named(NAMED_PING) ping: Long,
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .pingInterval(ping, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideInterceptor() = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @Singleton
    fun provideTimeout() = CONNECTION_TIME

    @Provides
    @Singleton
    @Named(NAMED_PING)
    fun providePingInterval() = PING_INTERVAL

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, moshi: Moshi, client: OkHttpClient): ApiServices =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiServices::class.java)
}


