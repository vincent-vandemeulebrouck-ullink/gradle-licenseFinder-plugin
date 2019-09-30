# gradle-licenseFinder-plugin
plugin gradle to use pivotal/licenseFinder on your build process

# build status
[![Build Status](https://travis-ci.org/Itiviti/gradle-licenseFinder-plugin.svg?branch=master)](https://travis-ci.org/Itiviti/gradle-licenseFinder-plugin)

# Documentation

it is compatible with the new plugin mechanism and can be used with :

```
plugins {
  id 'com.ullink.gradle.licenseFinder' version: '0.1-SNAPSHOT'
}
```

or, when using gradler lower than 2.1 :

```
buildscript {
    repositories {
      mavenCentral()
    }

    dependencies {
        classpath "com.ullink.gradle:gradle-licenseFinder-plugin:0.1-SNAPSHOT"
    }
}
```

it creates a task 'licenseFinder' that may be configured as follows :

```
licenseFinder {
    dependencyConfiguration
    checkLicensesScriptArgs
    checkLicensesDescription
    checkLicensesScript
    decisions
    whiteList
}
```
