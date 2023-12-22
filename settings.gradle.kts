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
include(":feature:quiz")
include(":feature:result")
include(":feature:setting")
include(":feature:start")
include(":feature:stats")
