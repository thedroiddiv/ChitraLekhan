import org.gradle.api.publish.maven.MavenPublication

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.serialization)
    id("maven-publish")
    id("signing")
}

android {
    namespace = "com.daiatech.chitralekhan"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions.jvmTarget = "17"
    buildFeatures.compose = true

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.kotlinx.serialization.json)
}

// Publishing tasks
val publishGroupId = "io.github.karya-inc"
val publishArtifactVersion = "0.0.3"
val publishArtifactId = "chitralekhan"

group = publishGroupId
version = version

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = publishGroupId
            artifactId = publishArtifactId
            version = publishArtifactVersion

            afterEvaluate { from(components["release"]) }

            pom {
                name.set(publishArtifactId)
                description.set("An Android library for image annotationo")
                url.set("https://github.com/karya-inc/ChitraLekhan.git")

                licenses {
                    license {
                        name.set("GNU license")
                        url.set("https://opensource.org/license/gpl-3-0")
                    }
                }

                developers {
                    developer {
                        id.set("divyansh@karya.in")
                        name.set("Divyansh Kushwaha")
                        email.set("divyansh@karya.in")
                    }
                }

                scm {
                    connection.set("scm:git:ssh://git@github.com/karya-inc/ChitraLekhan.git")
                    developerConnection.set("scm:git:ssh://git@github.com/karya-inc/ChitraLekhan.git")
                    url.set("https://github.com/karya-inc/ChitraLekhan.git")
                }
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        rootProject.ext["signing.keyId"] as String,
        rootProject.ext["signing.key"] as String,
        rootProject.ext["signing.password"] as String
    )
    sign(publishing.publications)
}
