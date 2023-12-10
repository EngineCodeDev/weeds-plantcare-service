import org.apache.commons.lang3.RandomStringUtils
import org.apache.tools.ant.Project
import org.apache.tools.ant.taskdefs.SQLExec
import java.util.*

plugins {
	java
	id("idea")
	id("groovy")
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.3"
	id("maven-publish")
}

group = "dev.enginecode.weeds"
version = "0.0.1-SNAPSHOT"

val properties = Properties()
file("gradle-local.properties").takeIf { it.exists() }?.inputStream()?.use { properties.load(it) }

val dbRegion = project.findProperty("db.dbRegion.active") ?: ""
val dbData = mutableMapOf(
		"dbUrl" to (project.findProperty("db.$dbRegion.dbUrl") ?: "jdbc:postgresql://localhost:5432/weeds_db"),
		"dbSchema" to (project.findProperty("db.$dbRegion.dbSchema") ?: "app_test"),
		"dbUser" to (project.findProperty("db.$dbRegion.dbUser") ?: "weeds_user"),
		"dbPass" to (project.findProperty("db.$dbRegion.dbPass") ?: "weeds_pass123"),
		"dbAdmUser" to (project.findProperty("db.$dbRegion.dbAdmUser") ?: "weeds_admin"),
		"dbAdmPass" to (project.findProperty("db.$dbRegion.dbAdmPass") ?: "weeds_pass123"),
		"dbGenRandomSchemaSuffix" to (project.findProperty("db.$dbRegion.dbGenRandomSchemaSuffix")?.toString()?.toBoolean() ?: false)
)
if (dbData["dbGenRandomSchemaSuffix"] as Boolean) {
	dbData["dbSchema"] = "${dbData["dbSchema"]}${generateRandomSuffix()}"
}
dbData.keys.forEach{
	extra[it] = dbData[it]
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

buildscript {
	repositories {
		mavenLocal()
		mavenCentral()
	}
	dependencies {
		classpath("org.apache.commons:commons-lang3:3.13.0")
	}
}

configurations {
	create("intestImplementation") {
		extendsFrom(configurations["testImplementation"])
		isCanBeResolved = true
	}
}

repositories {
	mavenLocal()
	mavenCentral()
	maven {
		name = "EngineCodeGitHubPackages"
		url = uri("https://maven.pkg.github.com/EngineCodeDev/packages")
		credentials {
			username = project.findProperty("gpr.user")?.toString() ?: properties.getProperty("gpr.user") ?: System.getenv("GITHUB_ACTOR")
			password = project.findProperty("gpr.key")?.toString() ?: properties.getProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")
		}
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web:3.2.0")
	implementation("org.springframework.boot:spring-boot-starter-jdbc:3.2.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.0")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.2.0")
	implementation ("org.springframework.boot:spring-boot-starter-cache:3.2.0")
	implementation("org.postgresql:postgresql:42.6.0")
	implementation("dev.enginecode:ec-commons:0.0.3-SNAPSHOT")
	implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")

	developmentOnly("org.springframework.boot:spring-boot-devtools:3.1.5")

	testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.5")
	testImplementation("org.spockframework:spock-core:2.4-M1-groovy-4.0")
	testImplementation("org.spockframework:spock-spring:2.4-M1-groovy-4.0")
}


sourceSets {
	create("intest") {
		groovy {
			setSrcDirs(listOf("$projectDir/src/intest/groovy"))
		}
		resources {
			setSrcDirs(listOf("$projectDir/src/intest/resources"))
		}
		compileClasspath += sourceSets["main"].output + sourceSets["test"].output + configurations["intestImplementation"]
		runtimeClasspath += sourceSets["main"].output + sourceSets["test"].output + configurations["intestImplementation"]
	}
}


val intest by tasks.creating(Test::class) {
	group = "verification"
	description = "Runs integration tests."

	testClassesDirs = sourceSets["intest"].output.classesDirs
	classpath = sourceSets["intest"].runtimeClasspath

	dependsOn("initDB")
	finalizedBy("dropDB")

	jvmArgs = listOf(
			"-Dspring.datasource.url=${dbData["dbUrl"]}",
			"-Dspring.datasource.username=${dbData["dbUser"]}",
			"-Dspring.datasource.password=${dbData["dbPass"]}",
			"-Dspring.datasource.hikari.schema=${dbData["dbSchema"]}"
	)
}

tasks.register("initDB") {
	description = "Initialize database structures used in Integration Tests"

	val liquibaseClean = project(":database").tasks.getByName("clean")
	dependsOn(liquibaseClean)
	doFirst {
		logger.lifecycle("Initializing database structures")
		runScript(
				"${project.projectDir}/src/intest/resources/db/setup-sql/init-db.sql",
				"@db_schema@" to (dbData["dbSchema"] as String),
				"@db_user@" to (dbData["dbUser"] as String)
		)
	}

	val liquibaseUpdate = project(":database").tasks.getByName("update")
	finalizedBy(liquibaseUpdate)
}

tasks.register("dropDB") {
	description = "Drop all database structures used in Integration Tests"

	doFirst {
		logger.lifecycle("Dropping database structures")
		runScript(
				"${project.projectDir}/src/intest/resources/db/setup-sql/drop-db.sql",
				"@db_schema@" to (dbData["dbSchema"] as String),
				"@db_user@" to (dbData["dbUser"] as String)
		)
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["java"])

			versionMapping {
				usage("java-api") {
					fromResolutionOf("runtimeClasspath")
				}
				usage("java-runtime") {
					fromResolutionResult()
				}
			}

			groupId = project.group.toString()
			artifactId = project.name
			version = project.version.toString()
		}
	}

	repositories {
		mavenLocal()
		maven {
			name = "EngineCodeGitHubPackages"
			url = uri("https://maven.pkg.github.com/EngineCodeDev/packages")
			credentials {
				username = project.findProperty("gpr.user")?.toString() ?: System.getenv("GITHUB_ACTOR")
				password = project.findProperty("gpr.key")?.toString() ?: System.getenv("GITHUB_TOKEN")
			}
		}
	}
}


fun runScript(scriptPath: String, vararg replacementPairs: Pair<String, String>) {
	val sqlExec = SQLExec()
	sqlExec.project = Project()
	sqlExec.project.init()

	sqlExec.setDriver("org.postgresql.Driver")
	sqlExec.createClasspath().setPath(project.sourceSets["main"].runtimeClasspath.asPath)
	sqlExec.url = dbData["dbUrl"] as String
	sqlExec.setUserid(dbData["dbAdmUser"] as String)
	sqlExec.password = dbData["dbAdmPass"] as String
	sqlExec.setPrint(true)

	val scriptFile = file(scriptPath)
	var script = scriptFile.readText()
	replacementPairs.forEach {
		script = script.replace(it.first, it.second)
	}
	sqlExec.addText(script)
	sqlExec.execute()
}

fun generateRandomSuffix(): String {
	val allowedChars = "0123456789abcdefghijklmnopqrstuvwxyz"
	val suffixLength = 5
	return RandomStringUtils.random(suffixLength, allowedChars)
}
