plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("api-plugin") {
            id = "api-plugin"
            implementationClass = "com.example.plugins.ApiPlugin"
        }
    }
}
