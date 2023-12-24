import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serizalization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.about.libraries)
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "com.mikepenz.aboutlibraries.plugin")

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.1")
        detektPlugins("io.nlopez.compose.rules:detekt:0.2.1")
    }

    detekt {
        toolVersion = "1.23.1"
        config = files("${rootProject.projectDir}/config/detekt/detekt.yml")
        buildUponDefaultConfig = true
        basePath = projectDir.toString()
        ignoreFailures = true
        parallel = true
        autoCorrect = true
    }
}

val reportMerge by tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
    output.set(rootProject.layout.buildDirectory.file("reports/detekt/detekt.xml"))
}

subprojects {
    detekt {
        reports.xml.required.set(true)
    }

    plugins.withType<io.gitlab.arturbosch.detekt.DetektPlugin> {
        tasks.withType<Detekt> detekt@{
            finalizedBy(reportMerge)
            reportMerge.configure {
                input.from(this@detekt.xmlReportFile)
            }
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            if (project.findProperty("composeCompilerReports") == "true") {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.absolutePath}/compose_compiler"
                )
            }
            if (project.findProperty("composeCompilerMetrics") == "true") {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.buildDir.absolutePath}/compose_compiler"
                )
            }
        }
    }
}
