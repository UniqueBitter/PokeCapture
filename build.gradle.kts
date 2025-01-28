import io.izzel.taboolib.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    java
    id("io.izzel.taboolib") version "2.0.22"
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
}

taboolib {
    env {
        install(Basic)
        install(Bukkit)
        install(BukkitHook)
        install(BukkitUI)
        install(BukkitUtil)
        install(BukkitNMS)
        install(BukkitNMSUtil)
        install(BukkitNMSItemTag)
        install(BukkitNMSDataSerializer)
        install(BukkitNMSEntityAI)
        install(MinecraftEffect)
        install(MinecraftChat)
        install(CommandHelper)
        install(Metrics)
        install(Database)
        install(DatabasePlayer)
    }
    description {
        name = "PokeCapture"
        contributors {
            name("Unique_Bitter")
        }
    }
    version { taboolib = "6.2.1-df22fb1" }
}

repositories {
    mavenCentral()
    maven("https://mvn.lumine.io/repository/maven-public/")
    maven {
        isAllowInsecureProtocol = true
        url = uri("http://server.pokemtd.top:31647/snapshots")
    }
}
dependencies {
    compileOnly("me.forge.forgedev:ForgeDev:1.16.5")
    compileOnly("ink.ptms.core:v12004:12004:mapped")
    compileOnly("ink.ptms.core:v12004:12004:universal")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
