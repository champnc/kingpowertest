package com.champnc.kingpowertest.di

import com.champnc.kingpowertest.BuildConfig
import com.champnc.kingpowertest.data.repository.AlbumRepository
import com.champnc.kingpowertest.data.repository.AlbumRepositoryImpl
import com.champnc.kingpowertest.data.service.AlbumService
import com.champnc.kingpowertest.domain.usecase.GetAlbumUseCase
import com.champnc.kingpowertest.domain.usecase.GetAlbumUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val bodyLogging = HttpLoggingInterceptor()
        bodyLogging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            client.addNetworkInterceptor(bodyLogging)
        }
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): AlbumService {
        return retrofit.create(AlbumService::class.java)
    }

    @Provides
    @Singleton
    fun provideAlbumRepository(albumService: AlbumService): AlbumRepository {
        return AlbumRepositoryImpl(albumService)
    }

    @Provides
    @Singleton
    fun provideGetAlbumUsecase(albumRepository: AlbumRepository): GetAlbumUseCase {
        return GetAlbumUseCaseImpl(albumRepository)
    }
}