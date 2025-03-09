package com.nastirlex.bank.app.hilt

import com.nastirlex.data.net.core.CoreDataSourceImpl
import com.nastirlex.domain.core.CoreDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {
    @Binds
    fun bindCoreDataSource(coreDataSourceImpl: CoreDataSourceImpl): CoreDataSource
}