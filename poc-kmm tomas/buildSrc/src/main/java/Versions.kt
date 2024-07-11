package main.java

object Versions {
    const val kotlin = "1.8.10"
    const val gradle = "7.4.0"

    const val androidMinSdk = 22
    const val androidCompileSdk = 34
    const val androidTargetSdk = androidCompileSdk

    const val corutinesVersion = "1.6.0-native-mt"
    const val koin = "3.3.2"

    const val ktor = "2.3.5"

    const val kotlinxSerialization = "1.3.2"
    const val sqlDelight = "1.5.5"

    const val material = "1.5.0"
    const val activityCompose = "1.8.0"
    const val compose = "1.4.2"
    const val compose_runtime = "1.5.4"
    const val koin_compose = "3.4.1"
    const val lifecycleExtension = "2.2.0-beta01"

    const val kermit = "1.0.0"

    const val junit = "4.13.2"
    const val mockk = "1.12.2"
}

object Deps {
    object Gradle {
        const val kotlinGradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val toolsGradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
        const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        const val buildKonfig = "com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.11.0"
    }

    object Android {
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object AndroidX {
        const val lifecycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleExtension}"
        const val lifecycleViewmodelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleExtension}"
        const val corutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.corutinesVersion}"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.corutinesVersion}"
    }

    object Compose {
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val compiler = "androidx.compose.compiler:compiler:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
        const val foundationLayout =
            "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"

        const val runtime = "androidx.compose.runtime:runtime-livedata:${Versions.compose_runtime}"
        const val koin = "io.insert-koin:koin-androidx-compose:${Versions.koin_compose}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
    }

    object SqlDelight {
        const val core = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        const val ios = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        const val android = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    }

    object KTor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val log = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val json = "io.ktor:ktor-client-json:${Versions.ktor}"
        const val serialization = "io.ktor:ktor-serialization:${Versions.ktor}"
        const val serializationKotlinx = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        const val serializationCore =
            "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"

        const val negotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"

        const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val okHttp = "io.ktor:ktor-client-okhttp-jvm:${Versions.ktor}"
    }

    object Logs {
        const val kermit = "co.touchlab:kermit:${Versions.kermit}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
    }
}

object Package {
    const val appAndroid = "com.vass.example.kmmapp.android"
    const val db = "sqldelight.vass.db"
    const val buildkonfig = "com.vass.example.kmmappp"
}