pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")

            credentials{
                username = "mapbox"
                // Use the secret token you stored in gradle.properties as the password
                password = "@string/mapbox_access_token" ?: ""
            }
        }
    }
}

rootProject.name = "Travel Map"
include(":app")
