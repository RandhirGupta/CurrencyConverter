android {

    defaultConfig {
        applicationId = "com.cyborg.currencyconverter"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }

    val isRunningOnTravis = System.getenv("CI") == "true"

    if (isRunningOnTravis) {
        // configuring keystore
        signingConfigs.getByName("release").storeFile = file("../currency_converter.jks")
        signingConfigs.getByName("release").storePassword = System.getenv("keystore_password")
        signingConfigs.getByName("release").keyAlias = System.getenv("keystore_alias")
        signingConfigs.getByName("release").keyPassword = System.getenv("keystore_alias_password")
    }

    dataBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
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
    implementation(CurrencyConverterConfig.Libs.Arch.lifeCycleReactiveStream)
    implementation(CurrencyConverterConfig.Libs.Arch.room)
    implementation(CurrencyConverterConfig.Libs.Arch.roomRxJava)

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
    implementation(CurrencyConverterConfig.Libs.Misc.glide)

    kapt(CurrencyConverterConfig.Libs.Dagger.daggerCompiler)
    kapt(CurrencyConverterConfig.Libs.Dagger.daggerAndroidCompiler)
    kapt(CurrencyConverterConfig.Libs.Arch.roomCompiler)

    testImplementation(CurrencyConverterConfig.Libs.Test.junit)
    testImplementation(CurrencyConverterConfig.Libs.Arch.roomTestHelper)
    testImplementation(CurrencyConverterConfig.Libs.Test.Mockito.mocito)
    testImplementation(CurrencyConverterConfig.Libs.Test.Mockito.nhaarmanMock)
    testImplementation(CurrencyConverterConfig.Libs.Misc.retrofitMock)
    testImplementation(CurrencyConverterConfig.Libs.AndroidTest.archCoreTest)
    testImplementation(CurrencyConverterConfig.Libs.Test.Mockito.mocitoInline)

    androidTestImplementation(CurrencyConverterConfig.Libs.AndroidTest.testRunner)
    androidTestImplementation(CurrencyConverterConfig.Libs.AndroidTest.espressoCore)
    androidTestImplementation(CurrencyConverterConfig.Libs.AndroidTest.testRules)
}