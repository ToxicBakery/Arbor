plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "com.ToxicBakery.logging"

dependencies {
    implementation(projects.arbor)
    implementation(libs.kotlin.stdlib)
}

application {
    mainClass = "com.ToxicBakery.logging.Main"
}
