package com.example.lbctest.application.injection

import com.example.lbctest.domain.repo.AlbumsRepository
import com.example.lbctest.data.AlbumsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun dataSource(impl: AlbumsRepositoryImpl): AlbumsRepository
}