package com.cyborg.currencyconverter.di.component

import android.app.Application
import com.cyborg.currencyconverter.CurrencyConverterApplication
import com.cyborg.currencyconverter.di.*
import com.cyborg.currencyconverter.di.builder.ActivityBuilder
import com.cyborg.currencyconverter.di.builder.FragmentBuilder
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
        DatabaseModule::class,
        NetworkModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class]
)
interface ApplicationComponent : AndroidInjector<CurrencyConverterApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun useCaseModule(useCaseModule: UseCaseModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder
        fun build(): ApplicationComponent
    }

    override fun inject(instance: CurrencyConverterApplication)
}