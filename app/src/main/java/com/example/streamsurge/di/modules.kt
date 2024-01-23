package com.example.streamsurge.di


import androidx.room.Room
import com.example.streamsurge.data.db.Database
import com.example.streamsurge.network.ApiProvider
import com.example.streamsurge.network.PreferenceManager
import com.example.streamsurge.repository.StreamRepository
import com.example.streamsurge.ui.dashboard.DashboardViewModel
import com.example.streamsurge.ui.detail.TvSeriesDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object AppModule {
    fun appModules() = viewModelModules + repoModules + commonModules

    private val commonModules = module {
        single {
            Room.databaseBuilder(
                androidContext(),
                Database::class.java,
                "stream-surge-db"
            ).fallbackToDestructiveMigration().build()
        }
        single { get<Database>().localDao() }
        /*single { get<Database>().tvDetailDao() }*/
        single { ApiProvider.client }
        single { PreferenceManager(androidContext()) }

    }

    private val repoModules = module {
        single { StreamRepository(get(), get(), get()) }
    }

    private val viewModelModules = module {
        viewModel { DashboardViewModel(get()) }
        viewModel { TvSeriesDetailViewModel(get()) }
    }

}