package com.nbateampicker.di

import android.app.Application
import com.nbateampicker.VhealthApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        MainActivityModule::class,
        AppAssistedInjectModule::class]
)
interface AppComponent {

    fun inject(vhealthApp: VhealthApp)

//    fun factory(): AppWorkerFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
