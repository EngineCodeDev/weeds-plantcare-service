import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    id("java")
    id("org.liquibase.gradle") version "2.2.0"
    id("application")
}

group = "dev.enginecode.weeds"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    liquibaseRuntime("org.liquibase:liquibase-core:4.23.0")
    liquibaseRuntime("org.postgresql:postgresql:42.6.0")
    liquibaseRuntime("info.picocli:picocli:4.7.4")
}

val dbUrl = project.rootProject.extra["dbUrl"]
val dbSchema = project.rootProject.extra["dbSchema"]
val dbUser = project.rootProject.extra["dbUser"]
val dbAdmUser = project.rootProject.extra["dbAdmUser"]
val dbAdmPass = project.rootProject.extra["dbAdmPass"]


tasks.processResources {
    filesMatching("**/changes/*.sql") {
        filter<ReplaceTokens>("tokens" to mapOf("db_user" to dbUser))
    }
}

tasks.withType<org.liquibase.gradle.LiquibaseTask> {
    dependsOn(tasks.processResources)
}

liquibase {
    activities.register("defaultRunList") {
        this.arguments = mapOf(
                "classpath" to "${buildDir}/resources/main",
                "changelogFile" to "changelog.yml",
                "url" to dbUrl,
                "username" to dbAdmUser,
                "password" to dbAdmPass,
                "liquibaseSchemaName" to dbSchema,
                "defaultSchemaName" to dbSchema,
                "databaseChangelogTableName" to "ps_change_log",
                "databaseChangelogLockTableName" to "ps_change_lock"
        )
    }
    runList = "defaultRunList"
}