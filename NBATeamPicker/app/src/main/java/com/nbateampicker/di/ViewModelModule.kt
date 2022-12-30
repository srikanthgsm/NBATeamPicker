package com.nbateampicker.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nbateampicker.factory.AppViewModelFactory
import com.nbateampicker.viewmodel.TeamDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TeamDetailsViewModel::class)
    abstract fun bindMyDetailsViewModel(medicalViewModel: TeamDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}
