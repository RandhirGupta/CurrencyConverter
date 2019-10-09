package com.cyborg.currencyconverter.di.component

import android.app.Application
import com.cyborg.currencyconverter.CurrencyConverterApplication
import com.cyborg.currencyconverter.di.ApplicationModule
import com.cyborg.currencyconverter.di.NetworkModule
import com.cyborg.currencyconverter.di.RepositoryModule
import com.cyborg.currencyconverter.di.UseCaseModule
import com.cyborg.currencyconverter.di.builder.ActivityBuilder
import com.cyborg.currencyconverter.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityBuilder::class]
)
interface ApplicationComponent : AndroidInjector<CurrencyConverterApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun useCaseModule(useCaseModule: UseCaseModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder
        fun build(): ApplicationComponent
    }

    override fun inject(instance: CurrencyConverterApplication)
}