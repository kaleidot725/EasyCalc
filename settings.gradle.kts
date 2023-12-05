pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "EasyCalc"

include(":app")
include(":ui")
include(":domain")
include(":repository")
include(":db")
include(":api")
