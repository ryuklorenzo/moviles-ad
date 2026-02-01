import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    kotlin("plugin.serialization") version "2.1.0" // o tu versión de Kotlin
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm()
    
    js {
        browser()
        binaries.executable()
    }
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            //ktor
            implementation(libs.ktor.client.okhttp)
            //implementation(libs.ktor.client.android)

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            //corrutinas.
            // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
            //navegacion
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.1")

            //fecha y hora
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")

            //iconos
            implementation(compose.materialIconsExtended)
            //injección de dependencias
            implementation("io.insert-koin:koin-compose:4.1.1")
            implementation("io.insert-koin:koin-compose-viewmodel:4.1.1")
            implementation("io.insert-koin:koin-compose-viewmodel-navigation:4.1.1")
            //carga de imagenes
            // https://mvnrepository.com/artifact/io.coil-kt.coil3/coil
            implementation("io.coil-kt.coil3:coil:3.3.0")
            implementation("io.coil-kt.coil3:coil-compose:3.3.0")
            implementation("io.coil-kt.coil3:coil-network-ktor3:3.3.0")





            //ktor para servicios
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.content.negotiation)
            implementation("io.ktor:ktor-server-cors:3.3.0")
            implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.0")


            //tamanyo pantalla
            implementation("org.jetbrains.compose.material3.adaptive:adaptive:1.1.2")
            //json
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
            //diseños canonicos
            implementation("org.jetbrains.compose.material3.adaptive:adaptive:1.2.0")
            implementation("org.jetbrains.compose.material3.adaptive:adaptive-layout:1.2.0")
            implementation("org.jetbrains.compose.material3.adaptive:adaptive-navigation:1.2.0")
            //componentes experimentales
            // https://mvnrepository.com/artifact/androidx.compose.material3/material3
            //implementation("androidx.compose.material3:material3:1.5.0-alpha10")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jsMain.dependencies {
            //implementation(libs.ktor.client.js)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)

            implementation(libs.ktor.client.okhttp)
        }
    }
}

android {
    namespace = "ies.sequeros.dam.pmdp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "ies.sequeros.dam.pmdp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "ies.sequeros.dam.pmdp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ies.sequeros.dam.pmdp"
            packageVersion = "1.0.0"
        }
    }
}
