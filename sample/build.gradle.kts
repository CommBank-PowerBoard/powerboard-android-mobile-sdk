import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.ProductFlavor
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp.devtools)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.cba.sample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.cba.sample"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    signingConfigs {
        create("debugtest") {
            storeFile = file(System.getProperty("user.home") + "/.android/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }
    buildTypes {
        getByName("debug") {
            isDefault = true
            isDebuggable = true
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debugtest")
        }
    }
    flavorDimensions += "environment"
    productFlavors {
        create("staging") {
            isDefault = true
            dimension = "environment"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            configureFlavorBuildConfig(
                "com.paydock.core.domain.model.Environment.STAGING",
                true,
                "staging"
            )
        }
        create("preprod") {
            dimension = "environment"
            applicationIdSuffix = ".preprod"
            versionNameSuffix = "-preprod"
            configureFlavorBuildConfig(
                "com.paydock.core.domain.model.Environment.PRE_PRODUCTION",
                true,
                "preprod"
            )
        }
        create("prod") {
            dimension = "environment"
            configureFlavorBuildConfig(
                "com.paydock.core.domain.model.Environment.PRODUCTION",
                false,
                "prod",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        // https://developer.android.com/jetpack/androidx/releases/compose-compiler
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtension.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

enum class BuildVariable(private val baseEnvName: String) {
    // Shared Variables
    MERCHANT_IDENTIFIER("MERCHANT_IDENTIFIER"),
    // Environment Specific Variables
    WIDGET_ACCESS_TOKEN("WIDGET_ACCESS_TOKEN"),
    API_ACCESS_TOKEN("API_ACCESS_TOKEN"),
    GATEWAY_ID_MPGS("GATEWAY_ID_MPGS"),
    GATEWAY_ID_PAY_PAL("GATEWAY_ID_PAY_PAL"),
    GATEWAY_ID_AFTER_PAY("GATEWAY_ID_AFTER_PAY"),
    GATEWAY_ID_CLICK_TO_PAY("GATEWAY_ID_CLICK_TO_PAY"),
    GATEWAY_ID_GOOGLE_PAY("GATEWAY_ID_GOOGLE_PAY");

    fun getEnvName(flavor: String): String {
        return when {
            this == MERCHANT_IDENTIFIER -> this.baseEnvName
            flavor.isEmpty() -> this.baseEnvName
            else -> "${this.baseEnvName}_${flavor.uppercase()}"
        }
    }
}

fun ApplicationProductFlavor.configureFlavorBuildConfig(
    sdkEnvironment: String,
    enableTestMode: Boolean,
    flavor: String
): ApplicationProductFlavor {
    return this.apply {
        addBuildConfigField(
            "com.paydock.core.domain.model.Environment",
            "SDK_ENVIRONMENT",
            sdkEnvironment
        )
        addBuildConfigField("Boolean", "ENABLE_TEST_MODE", enableTestMode.toString())
        BuildVariable.values().forEach { variable ->
            addBuildConfigField("String", variable.name, readBuildVariable(variable, flavor))
        }
    }
}

fun readBuildVariable(variable: BuildVariable, flavor: String): String {
    val envValue = System.getenv(variable.getEnvName(flavor))
    val configValue: String = if (envValue != null) {
        // CI/CD environment - Remove excessive double quotes if present
        sanitizeEnvValue("\"${System.getenv(envValue)}\"")
    } else {
        // Config properties fallback
        val props = getLocalConfigProps(flavor)
        props.getProperty(variable.name) ?: "\"\""
    }
    return configValue
}

fun sanitizeEnvValue(value: String): String {
    return if (value.startsWith("\"") && value.endsWith("\"")) {
        value.trim('"') // Remove surrounding double quotes
    } else {
        value
    }
}

fun getLocalConfigProps(flavor: String): Properties {
    val props = Properties()
    val propsFile = file("src/$flavor/config.properties")
    if (propsFile.exists()) {
        try {
            FileInputStream(propsFile).use {
                props.load(it)
            }
        } catch (e: Exception) {
            println("Error loading properties from $path: ${e.message}")
        }
    } else {
        println("Properties file not found: $path")
    }
    return props
}

fun ProductFlavor.addBuildConfigField(type: String, name: String, value: String) =
    buildConfigField(type, name, value)

dependencies {
    // Modules
    implementation(project(":mobile-sdk"))
    // Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.ktx)
    implementation(platform(libs.kotlin.bom))
    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.extended)
    // To allow builds to build for release
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.ui.tooling)
    // Hilt
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.android.compiler)
    // Retrofit
    implementation(libs.bundles.retrofit)
    // Test Libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}