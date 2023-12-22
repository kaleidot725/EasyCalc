pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "easycalc"

include(":app")
include(":core:ui")
include(":core:database")
include(":core:domain")
include(":core:repository")
include(":feature:category")
include(":feature:history")
include(":feature:home")
include(":feature:interrupt")
include(":feature:mylist")
include(":feature:progress")
