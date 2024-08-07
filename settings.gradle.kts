rootProject.name = "CBA SampleApp"
includeBuild("convention-plugins")

// Only include sample app if not on JitPack
if (!System.getenv().containsKey("JITPACK")) {
    include(":sample")
}
include(":mobile-sdk")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Used for core submodules
        maven { setUrl("https://www.jitpack.io") }
    }
}