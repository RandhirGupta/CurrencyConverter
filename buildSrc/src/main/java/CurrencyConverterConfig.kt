object CurrencyConverterConfig {

    private const val kotlinVersion = "1.3.50"
    const val version = "1.0"


    object SdkVersion {
        const val compileSdkVersion = 29
        const val targetSdkVersion = 29
        const val minSdkVersion = 22
    }

    object Plugins {
        const val androidPlugin = "com.android.tools.build:gradle:3.5.1"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object Libs {
        object Kotlin {
            const val jvm = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
        }

        object Support {
            private const val buildToolVersion = "29.0.3"

            const val appCompat = "androidx.appcompat:appcompat:1.0.0-alpha1"
            const val design = "com.android.support:design:$buildToolVersion"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
            const val multiDex = "com.android.support:multidex:1.0.3"
            const val annotations = "com.android.support:support-annotations:$buildToolVersion"
            const val materialDesign = "com.google.android.material:material:1.0.0-alpha1"
        }

        object Arch {
            private const val lifeCycleVersion = "2.0.0"
            private const val roomVersion = "2.2.0"

            const val lifeCycle = "androidx.lifecycle:lifecycle-extensions:$lifeCycleVersion"
            const val lifeCycleReactiveStream =
                "android.arch.lifecycle:reactivestreams:$lifeCycleVersion"

            const val room = "androidx.room:room-runtime:$roomVersion"
            const val roomCompiler =
                "androidx.room:room-compiler:$roomVersion"
            const val roomTestHelper = "androidx.room:room-testing:$roomVersion"
            const val roomRxJava = "androidx.room:room-rxjava2:$roomVersion"
        }

        object Misc {
            private const val retrofitVersion = "2.4.0"
            private const val okHttpVersion = "3.11.0"
            private const val glideVersion = "4.8.0"

            const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
            const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:$retrofitVersion"
            const val retrofitGSONConverter =
                "com.squareup.retrofit2:converter-gson:$retrofitVersion"
            const val rxRetrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
            const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
            const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava:2.1.0"
            const val glide = "com.github.bumptech.glide:glide:$glideVersion"
        }

        object Test {
            const val junit = "junit:junit:4.12"

            object Mockito {
                const val mocito = "org.mockito:mockito-core:2.19.0"
                const val nhaarmanMock = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0"
                const val mocitoInline = "org.mockito:mockito-inline:2.13.0"
            }
        }

        object AndroidTest {

            const val testRunner = "androidx.test:runner:1.1.0"
            const val espressoCore = "androidx.test.espresso:espresso-core:3.1.0"
            const val testRules = "androidx.test:rules:1.1.0"
            const val archCoreTest = "android.arch.core:core-testing:1.1.1"

        }

        object Dagger {
            private const val daggerVersion = "2.16"

            const val daggerAndroid = "com.google.dagger:dagger-android:$daggerVersion"
            const val daggerAndroidSupport =
                "com.google.dagger:dagger-android-support:$daggerVersion"

            const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
            const val daggerAndroidCompiler =
                "com.google.dagger:dagger-android-processor:$daggerVersion"
        }

        object Rx {
            private const val rxJavaVersion = "2.2.6"
            private const val rxKotlinVersion = "2.3.0"
            private const val rxAndroidVersion = "2.1.1"

            const val rxJava = "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
            const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:$rxKotlinVersion"
            const val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxAndroidVersion"
        }
    }
}