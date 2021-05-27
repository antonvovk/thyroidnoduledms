plugins {
  war
  id("com.github.node-gradle.node") version "3.1.0"
}

node {
  version.set("14.17.0")
  download.set(true)
}

tasks.register<com.github.gradle.node.yarn.task.YarnTask>("yarnInstall") {
  args.set(listOf("install"))
}

tasks.register<com.github.gradle.node.yarn.task.YarnTask>("buildProd") {
  dependsOn("yarnInstall")
  group = "production"
  args.set(listOf("run", "production"))
}

tasks.jar {
  enabled = true
  dependsOn("buildProd")
  from("${projectDir}/dist/ThyroidNoduleDMS")
  into("static")
}
