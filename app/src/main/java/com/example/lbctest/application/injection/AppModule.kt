package com.example.lbctest.application.injection

import android.content.Context
import androidx.room.Room
import com.example.lbctest.application.AppConstants.DATABASE_NAME
import com.example.lbctest.application.AppConstants.LBC_URL
import com.example.lbctest.data.local.AppDatabase
import com.example.lbctest.data.remote.WebService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideSongsDao(db: AppDatabase) = db.cocktailDao()

    @Singleton
    @Provides
    fun provideRetrofitInstance() = Retrofit.Builder()
        .baseUrl(LBC_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun provideWebService(retrofit:Retrofit) = retrofit.create(WebService::class.java)
}