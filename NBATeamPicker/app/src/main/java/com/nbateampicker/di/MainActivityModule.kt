package com.nbateampicker.di

import com.nbateampicker.ui.onboarding.OnboardingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeOnboardingActivity(): OnboardingActivity
}
