plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serizalization)
}

android {
    namespace = "jp.kaleidot725.easycalc"
    compileSdk = 34
    defaultConfig {
        applicationId = "jp.albites.easycalc"
        minSdk = 24
        targetSdk = 34
        versionCode = 17
        versionName = "1.9.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    buildTypes {
        debug {
            buildConfigField("Boolean", "DEBUG_MODE", "true")
        }
        release {
            buildConfigField("Boolean", "DEBUG_MODE", "false")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    flavorDimensions("version")
    productFlavors {
        create("free") {
            buildConfigField("Boolean", "ENABLE_AD", "true")
            dimension = "version"
        }
    }
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:repository"))
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
    implementation(project(":feature:category"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.aboutlibraries.core)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.orbit.viewmodel)
    implementation(libs.orbit.compose)
    implementation(libs.review.ktx)
    implementation(libs.timber)
    implementation(libs.accompanist.systemuicontroller)
    implementation(project(":feature:hisotry"))

    debugImplementation(libs.flipper)
    debugImplementation(libs.soloader)
    releaseImplementation(libs.flipper.noop)

    implementation(platform("androidx.compose:compose-bom:2023.09.02"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
