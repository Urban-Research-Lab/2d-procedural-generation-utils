plugins {
    kotlin("jvm") version "1.5.31"
    `maven-publish`
}

group = "ru.itmo.idu"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
    maven(url = "https://repo.osgeo.org/repository/release/")
    maven(url = "https://nexus.geomatys.com/repository/geotoolkit/")
    maven {
        url = uri("https://maven.pkg.github.com/Urban-Research-Lab/jts-geometry-utils")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

configurations.all {
    exclude(group = "javax.media", module = "jai_core")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("org.locationtech.jts:jts-core:1.18.2")
    implementation("org.locationtech.jts.io:jts-io-common:1.18.2")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    implementation("ru.itmo.idu:jts-geometry-utils:3.0.0")

    implementation("org.geotools:gt-main:25.1")
    implementation("org.geotools:gt-epsg-hsql:25.1")
    implementation("org.geotools:gt-opengis:25.1")
    implementation("org.geotools:gt-geojson:25.1")

    implementation("org.jgrapht:jgrapht-core:1.5.1")
    implementation("com.google.guava:guava:31.1-jre")


}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Urban-Research-Lab/2d-procedural-generation-utils")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
