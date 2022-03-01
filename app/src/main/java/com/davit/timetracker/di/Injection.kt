package com.davit.timetracker.di

import android.content.Context
import androidx.room.Room
import com.davit.timetracker.data.repository.RepositoryImpl
import com.davit.timetracker.data.storage.TaskDB
import com.davit.timetracker.data.storage.TaskDao
import com.davit.timetracker.domain.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Injection {

    @Provides
    @Singleton
    fun provideDao(taskDB: TaskDB): TaskDao = taskDB.TaskDao()

    @Provides
    @Singleton
    fun provideTaskDB(@ApplicationContext appContext: Context): TaskDB {
        return Room.databaseBuilder(
            appContext,
            TaskDB::class.java,
            "task_db"
        ).build()
    }

    @Provides
    fun provideRepo(taskDB: TaskDB): Repository {
        return RepositoryImpl(taskDB)
    }

}