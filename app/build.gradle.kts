android {

    defaultConfig {
        applicationId = "com.cyborg.currencyconverter"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(CurrencyConverterConfig.Libs.Kotlin.jvm)

    implementation(CurrencyConverterConfig.Libs.Support.appCompat)
    implementation(CurrencyConverterConfig.Libs.Support.design)
    implementation(CurrencyConverterConfig.Libs.Support.constraintLayout)
    implementation(CurrencyConverterConfig.Libs.Support.multiDex)
    implementation(CurrencyConverterConfig.Libs.Support.annotations)
    implementation(CurrencyConverterConfig.Libs.Support.materialDesign)


    implementation(CurrencyConverterConfig.Libs.Arch.lifeCycle)
    implementation(CurrencyConverterConfig.Libs.Arch.room)

    implementation(CurrencyConverterConfig.Libs.Rx.rxJava)
    implementation(CurrencyConverterConfig.Libs.Rx.rxKotlin)
    implementation(CurrencyConverterConfig.Libs.Rx.rxAndroid)

    implementation(CurrencyConverterConfig.Libs.Dagger.daggerAndroid)
    implementation(CurrencyConverterConfig.Libs.Dagger.daggerAndroidSupport)

    implementation(CurrencyConverterConfig.Libs.Misc.retrofit)
    implementation(CurrencyConverterConfig.Libs.Misc.retrofitGSONConverter)
    implementation(CurrencyConverterConfig.Libs.Misc.rxRetrofitAdapter)
    implementation(CurrencyConverterConfig.Libs.Misc.okHttpInterceptor)
    implementation(CurrencyConverterConfig.Libs.Misc.retrofitAdapter)

    kapt(CurrencyConverterConfig.Libs.Dagger.daggerCompiler)
    kapt(CurrencyConverterConfig.Libs.Dagger.daggerAndroidCompiler)
    kapt(CurrencyConverterConfig.Libs.Arch.roomCompiler)

    testImplementation(CurrencyConverterConfig.Libs.Test.junit)
    testImplementation(CurrencyConverterConfig.Libs.Arch.roomTestHelper)
    testImplementation(CurrencyConverterConfig.Libs.Test.Mockito.nhaarmanMock)
    testImplementation(CurrencyConverterConfig.Libs.Misc.retrofitMock)

    androidTestImplementation(CurrencyConverterConfig.Libs.AndroidTest.testRunner)
    androidTestImplementation(CurrencyConverterConfig.Libs.AndroidTest.espressoCore)
}