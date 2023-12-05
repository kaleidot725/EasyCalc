pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "easycalc"

include(":app")
include(":ui")
include(":domain")
include(":repository")
include(":db")
include(":api")
