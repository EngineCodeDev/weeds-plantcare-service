plugins {
	java
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	id("maven-publish")
}

group = "dev.enginecode.weeds"
version = "0.0.1-SNAPSHOT"

val dbRegion = project.findProperty("db.dbRegion.active") ?: ""
val dbData = mutableMapOf(
		"dbUrl" to (project.findProperty("db.$dbRegion.dbUrl") ?: "jdbc:postgresql://localhost:5432/weeds_db"),
		"dbUser" to (project.findProperty("db.$dbRegion.dbUser") ?: "weeds_user"),
		"dbPass" to (project.findProperty("db.$dbRegion.dbPass") ?: "weeds_pass123"),
		"dbSchema" to (project.findProperty("db.$dbRegion.dbSchema") ?: "public"),
		"dbAdmUser" to (project.findProperty("db.$dbRegion.dbAdmUser") ?: "weeds_admin"),
		"dbAdmPass" to (project.findProperty("db.$dbRegion.dbAdmPass") ?: "weeds_pass123")
)

dbData.keys.forEach{
	extra[it] = dbData[it]
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenLocal()
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.postgresql:postgresql:42.6.0")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
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
	}
}