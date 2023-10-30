import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import main.java.Deps
import main.java.Package
import main.java.Versions

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("kotlinx-serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("com.codingfeline.buildkonfig")
}

android {
    compileSdk = Versions.androidCompileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.androidMinSdk
        targetSdk = Versions.androidTargetSdk
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

version = "1.0"
kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        //iosSimulatorArm64() sure all ios dependencies support this target
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    cocoapods {

        framework {
            summary = "Some description for the Shared Module"
            homepage = "http://www.vass.es"
            ios.deploymentTarget = "14.0"
            podfile = project.file("../iosApp/Podfile")
            baseName = "shared"
        }

        //xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        //xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
    }

    sourceSets {
        val commonMain by getting {
            dependencies {

                implementation(Deps.AndroidX.corutines)

                // SqlDelight
                implementation(Deps.SqlDelight.core)

                //koin
                implementation(Deps.Koin.core)

                // KTor
                with(Deps.KTor) {
                    implementation(core)
                    implementation(log)
                    implementation(json)
                    implementation(serialization)
                    implementation(serializationKotlinx)
                    implementation(serializationCore)
                    implementation(negotiation)
                }

                // Log
                implementation(kotlin("stdlib-common"))
                implementation(Deps.Logs.kermit)

            }
        }
        val commonTest by getting {
            dependencies {
                //implementation(Deps.Koin.test)
                implementation(Deps.AndroidX.coroutinesTest)
                //implementation(Deps.Test.mockk)

                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                // SqlDelight
                implementation(Deps.SqlDelight.android)

                // KTor
                implementation(Deps.KTor.android)

            }
        }

        //getByName("androidInstrumentedTest") { tambien funciona
        //SE DEBE RENOMBRAR DE androidTest a androidInstrumentedTest ("poc-kmm tomas/shared/src/androidInstrumentedTest")
        named("androidInstrumentedTest") {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(Deps.Test.junit)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            //iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                // SqlDelight
                implementation(Deps.SqlDelight.ios)

                // KTor
                implementation(Deps.KTor.ios)

            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            //iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

buildkonfig {
    packageName = Package.buildkonfig

    defaultConfigs {
        buildConfigField(STRING, "platform", "platform")
    }

    defaultConfigs("debug") {
        buildConfigField(STRING, "apiUrl", "https://api.breakingbadquotes.xyz/v1/")
    }
    defaultConfigs("release") {
        buildConfigField(STRING, "apiUrl", "https://api.breakingbadquotes.xyz/v1/")
    }

    targetConfigs("debug") {
        // this name should be the same as target names you specified
        create("android") {
            buildConfigField(STRING, "platform", "DebugAndroid")
        }

        create("ios") {
            buildConfigField(STRING, "platform", "DebugIos")
        }
    }

    targetConfigs("release") {
        // this name should be the same as target names you specified
        create("android") {
            buildConfigField(STRING, "platform", "ReleaseAndroid")
        }

        create("ios") {
            buildConfigField(STRING, "platform", "ReleaseIos")
        }
    }

}

sqldelight {
    database("KMMDatabase") {
        packageName = Package.db
    }

    linkSqlite = false
}