package com.nbateampicker.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@Module(includes = [AssistedInject_AppAssistedInjectModule::class])
@AssistedModule
interface AppAssistedInjectModule